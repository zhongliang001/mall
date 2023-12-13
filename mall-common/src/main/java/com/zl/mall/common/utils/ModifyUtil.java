package com.zl.mall.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;
import com.zl.mall.common.annotation.Column;
import com.zl.mall.common.annotation.PrimaryKey;
import com.zl.mall.common.annotation.Record;
import com.zl.mall.common.annotation.Table;
import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * 生修改记录dto
 * 
 * @author coolz
 *
 */
public class ModifyUtil {

	public static HistoryInfoDto getModifyHis(Object newObject, Object oldObject) {

		Class<? extends Object> class1 = newObject.getClass();

		Table[] tables = class1.getAnnotationsByType(Table.class);
		String tableName = "";
		if (tables.length > 0) {
			tableName = tables[0].value();
		} else {
			return null;
		}
		UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
		String userId= null;
		if(requestUser != null) {
			userId = requestUser.getUserId();
		}
		HistoryInfoDto historyInfoDto = new HistoryInfoDto();
		List<HistoryInfoEntity> list = new ArrayList<>();
		historyInfoDto.setList(list);
		Field[] fields = class1.getDeclaredFields();
		for (int num = 0; num < fields.length; num++) {
			Field field = fields[num];

			Column[] columns = field.getAnnotationsByType(Column.class);
			if (columns.length > 0) {
				Column column = columns[0];
				String name = column.name();
				Record[] records = field.getAnnotationsByType(Record.class);
				if (records.length > 0) {
					Method method = getMethod(field, class1);
					try {
						if (method != null) {
							Object value1 = method.invoke(newObject);
							Object value2 = method.invoke(oldObject);
							// 对比获取修改的字段
							if ((value1 != null && !value1.equals(value2)) || (value1 == null && value1 != value2)) {
								HistoryInfoEntity historyInfoEntity = new HistoryInfoEntity();
								historyInfoEntity.setTableName(tableName);
								historyInfoEntity.setNewValue(value1 == null ? null : value1.toString());
								historyInfoEntity.setOldValue(value2 == null ? null : value2.toString());
								historyInfoEntity.setModKey(name);
								historyInfoEntity.setModName(column.comment());
								historyInfoEntity.setUpdateTime(DateUtils.getCurrentDate());
								historyInfoEntity.setUpdateUserId(userId);

								// 记录版本号
								Method getVersionMethod = null;
								Method setVersionMethod = null;
								try {
									getVersionMethod = class1.getMethod("getVersion");
									if (getVersionMethod != null) {
										Object versionObj = getVersionMethod.invoke(oldObject);
										if (versionObj != null) {
											Integer version = Integer.parseInt(versionObj.toString());
											Integer newVersion = version + 1;
											setVersionMethod = class1.getMethod("setVersion", Integer.class);
											setVersionMethod.invoke(newObject, newVersion);
											historyInfoEntity.setVersion(newVersion);
										}
									}
								} catch (NoSuchMethodException e) {
									e.printStackTrace();
								} catch (SecurityException e) {
									e.printStackTrace();
								}
								list.add(historyInfoEntity);

							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}

				PrimaryKey[] primaryKeys = field.getDeclaredAnnotationsByType(PrimaryKey.class);
				Method method = getMethod(field, class1);
				try {
					Object keyValue = method.invoke(oldObject);
					if (primaryKeys.length > 0) {
						historyInfoDto.setPriKey(name);
						if (keyValue != null)
							historyInfoDto.setPriValue(keyValue.toString());

					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

			}

		}
		return historyInfoDto;
	}

	private static Method getMethod(Field field, Class<? extends Object> clazz) {
		String fieldName = field.getName();
		String getMethod = "get"
				+ fieldName.replaceFirst(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
		Method method = null;
		try {
			method = clazz.getMethod(getMethod);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}
}
