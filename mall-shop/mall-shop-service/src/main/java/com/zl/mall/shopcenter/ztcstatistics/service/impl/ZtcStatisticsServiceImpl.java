package com.zl.mall.shopcenter.ztcstatistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.utils.DateUtils;
import com.zl.mall.common.utils.HttpRequestUtil;
import com.zl.mall.shopcenter.productinfo.entity.ProductInfoEntity;
import com.zl.mall.shopcenter.productinfo.service.ProductInfoService;
import com.zl.mall.shopcenter.ztcstatistics.entity.ZtcStatisticsEntity;
import com.zl.mall.shopcenter.ztcstatistics.mapper.ZtcStatisticsMapper;
import com.zl.mall.shopcenter.ztcstatistics.service.ZtcStatisticsService;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author coolz
 *
*/
@Service
public class ZtcStatisticsServiceImpl implements ZtcStatisticsService {

	private static final Logger logger = LoggerFactory.getLogger(ZtcStatisticsServiceImpl.class);
	private final ZtcStatisticsMapper ztcStatisticsMapper;

	private final TemplateClient templateClient;

	private final ProductInfoService productInfoService;

	@Inject
	public ZtcStatisticsServiceImpl(ZtcStatisticsMapper ztcStatisticsMapper, TemplateClient templateClient, ProductInfoService productInfoService){
		this.ztcStatisticsMapper = ztcStatisticsMapper;
		this.templateClient = templateClient;
		this.productInfoService = productInfoService;
	}

	public List<ZtcStatisticsEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		return ztcStatisticsMapper.queryList(queryCondition.getCondition());
	}
	public int add(ZtcStatisticsEntity ztcStatisticsEntity){
		TemplateDto templateDto = new TemplateDto();
		templateDto.setName(TempConstant.ZTC_TEMP);
		ResultDto<String> result = templateClient.getSeqno(templateDto);
		String id = result.getData();
		ztcStatisticsEntity.setId(id);
		return ztcStatisticsMapper.add(ztcStatisticsEntity);
	}
	public int update(ZtcStatisticsEntity ztcStatisticsEntity){
		return ztcStatisticsMapper.update(ztcStatisticsEntity);
	}
	public int delete(String id){
		return ztcStatisticsMapper.delete(id);
	}

	@Override
	public int fileUpload(MultipartFile file, String shopId) {
		long currented = System.currentTimeMillis();
		String path="F:/opt/upload/ztc" + currented + ".xlsx";
		File destination = new File(path);
		int num = 0;
		try {
			if (!destination.getParentFile().exists()) {
				FileUtils.forceMkdirParent(destination); //使用commons-io的工具类
			}
			file.transferTo(destination);
			InputStream in = Files.newInputStream(destination.toPath());
			XSSFWorkbook book = new XSSFWorkbook(in);
			XSSFSheet sheet = book.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			List<ZtcStatisticsEntity> list = new ArrayList<>();
			Map<String, String> map = new HashedMap<>(16);
			UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
			String userId = null;
			if (requestUser != null) {
				userId = requestUser.getUserId();
			}
			String currentDate = DateUtils.getCurrentDate();
			for(int i =1; i <= lastRowNum; i++){
				XSSFRow row = sheet.getRow(i);
				short firstCellNum = row.getFirstCellNum();
				short lastCellNum = row.getLastCellNum();
				ZtcStatisticsEntity entity = new ZtcStatisticsEntity();
				entity.setId(UUID.randomUUID().toString().replaceAll("-",""));
				XSSFCell keyCell = row.getCell(firstCellNum);
				if(keyCell != null){
					String prdName = keyCell.getStringCellValue();
					if(StringUtils.isNotEmpty(prdName)){
						String prdId = map.get(prdName);
						if(StringUtils.isEmpty(prdId) ){
							ProductInfoEntity productInfoEntity = productInfoService.queryProductInfoByName(prdName);
							if (productInfoEntity != null) {
								prdId=productInfoEntity.getPrdId();
								map.put(prdName,prdId);
							}
						}
						entity.setPrdId(prdId);
					}else {
						logger.error("第{}行数据没有产品名：", i);
						break;
					}
				}
				// 关键词
				XSSFCell keyWordCell = row.getCell(firstCellNum + 2);
				if (keyWordCell != null) {
					String keyWord = keyWordCell.getStringCellValue();
					entity.setKeyWord(keyWord);
				}
				// 访问日期
				XSSFCell statDateCell = row.getCell(firstCellNum + 1);
				if(statDateCell != null){
					Date dateCellValue = statDateCell.getDateCellValue();
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
					String format = sdf.format(dateCellValue);
					entity.setStatDate(format);
				}
				// 潜力指数
				XSSFCell potentialIdxCell = row.getCell(firstCellNum +3);
				if (potentialIdxCell != null) {
					double numericCellValue = potentialIdxCell.getNumericCellValue();
					entity.setPotentialIdx((int)numericCellValue);
				}
				// 展现数
				XSSFCell impressionsCell = row.getCell(firstCellNum +4);
				if (impressionsCell != null) {
					double value = impressionsCell.getNumericCellValue();
					entity.setImpressions((int) value);
				}

				// 点击数
				XSSFCell hitsCell = row.getCell(firstCellNum + 5);
				if (hitsCell != null) {
					double hits = hitsCell.getNumericCellValue();
					entity.setHits((int) hits);
				}

				// 总花费
				XSSFCell costCell = row.getCell(firstCellNum + 6);
				if (costCell != null) {
					double cost = costCell.getNumericCellValue();
					BigDecimal value = BigDecimal.valueOf(cost);
					entity.setCost(value.setScale(2, RoundingMode.HALF_EVEN));
				}

				// 收藏数
				XSSFCell collectionsCell = row.getCell(firstCellNum + 9);
				if (collectionsCell != null) {
					double collections = collectionsCell.getNumericCellValue();
					entity.setCollections((int)collections);
				}
				// 加购数
				XSSFCell addPurchasesCell = row.getCell(firstCellNum + 10);
				if (addPurchasesCell != null) {
					double addPurchases = addPurchasesCell.getNumericCellValue();
					entity.setAddPurchases((int)addPurchases);
				}
				
				// 店铺收藏数
				XSSFCell shopCollectionsCell = row.getCell(firstCellNum + 11);
				if (shopCollectionsCell != null) {
					double shopCollections = shopCollectionsCell.getNumericCellValue();
					entity.setShopCollections((int)shopCollections);
				}
				// 关键词状态
				XSSFCell keyStateCell = row.getCell(firstCellNum + 15);
				if (keyStateCell != null) {
					String keyStateValue = keyStateCell.getStringCellValue();
					if(StringUtils.isNotEmpty(keyStateValue) && "已屏蔽".equals(keyStateValue)){
						entity.setKeyState("0");
					}else{
						entity.setKeyState("1");
					}
				}else{
					entity.setKeyState("1");
				}
				entity.setShopId(shopId);
				entity.setCreatedUserId(userId);
				entity.setCreateDate(currentDate);
				list.add(entity);
//				String value = "";

				/*
				for(int j =firstCellNum; j <=lastCellNum-1; j ++){
					XSSFCell cell = row.getCell(j);

					if( cell != null) {
						CellType cellType = cell.getCellType();
						if(cellType.equals(CellType.FORMULA)){
							String rawValue = cell.getRawValue();
							value +=rawValue + "\t";
						}else if(cellType.equals(CellType.NUMERIC)){
							if(DateUtil.isCellDateFormatted(cell)){
								Date dateCellValue = cell.getDateCellValue();
								SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
								String format = sdf.format(dateCellValue);
								value += format + "\t";
							}else {
								double numericCellValue = cell.getNumericCellValue();
								value +=numericCellValue + "\t";
							}
						}else if(cellType.equals(CellType.STRING)){
							String stringCellValue = cell.getStringCellValue();
							value += stringCellValue + "\t";
						}else {
							value += cellType.toString();
						}
					}else{
						value += "null\t";
					}
				}
				 */
			}
			num = ztcStatisticsMapper.insertBatch(list);
			logger.info("生成的直通车数据:{}",num);
		}catch (IOException e){
			logger.error(Arrays.toString(e.getStackTrace()));
		}
		return num;
	}
}