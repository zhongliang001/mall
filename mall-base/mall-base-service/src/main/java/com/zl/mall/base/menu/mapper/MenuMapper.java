package  com.zl.mall.base.menu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.menu.entity.MenuEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface MenuMapper{
	public List<MenuEntity> queryList(Map<String, Object> condtion);
	
	public int add(MenuEntity menuEntity);
	
	public int update(MenuEntity menuEntity);
	
	public int delete(@Param("menuId") String menuId );
	
	public List<Map<String, String>> selectRoot();
	
	public List<MenuEntity> queryRoot();
	
	public List<MenuEntity> selectMenuByUserId(Map<String, String> map);
}