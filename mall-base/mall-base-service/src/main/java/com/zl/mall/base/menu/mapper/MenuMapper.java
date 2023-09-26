package  com.zl.mall.base.menu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.menu.entity.MenuEntity;
/**
 * 
 * @author coolz
 *
 */
@Mapper
public interface MenuMapper{
	
	/**
	 * 查询菜单列表
	 * @param condition 查询条件
	 * @return 返回菜单列表
	 */
	public List<MenuEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增菜单
	 * @param menuEntity 菜单
	 * @return 返回新增记录数
	 */
	public int add(MenuEntity menuEntity);
	
	/**
	 * 修改菜单
	 * @param menuEntity 菜单
	 * @return 返回修改记录数
	 */
	public int update(MenuEntity menuEntity);
	
	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 * @return 返回删除记录数
 	 */
	public int delete(@Param("menuId") String menuId );
	
	/**
	 * 查询根菜单
	 * @return 返回跟菜单列表
	 */
	public List<Map<String, String>> selectRoot();
	
	/**
	 * 查询根菜单
	 * @return 返回根菜单列表
	 */
	public List<MenuEntity> queryRoot();
	
	/**
	 * 根据用户id 查询菜单
	 * @param map 用户id
	 * @return 返回菜单列表
	 */
	public List<MenuEntity> selectMenuByUserId(Map<String, String> map);
}