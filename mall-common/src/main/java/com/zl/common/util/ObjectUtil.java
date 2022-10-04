package com.zl.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongliang
 */
public class ObjectUtil {

    public static Map<String, Object> objectToMap(Object obj){
        Map<String, Object> map = new HashMap<>(16);
        if(obj==null){
            return null;
        }
        //获取类的各个属性值
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            //获取类的属性名称
            String fieldName =  field.getName();
            //获取类的属性名称对应的值
            if(getValueByFieldName(fieldName,obj)!=null){
                map.put(fieldName,  getValueByFieldName(fieldName,obj));
            }
        }
        return map;
    }

    /**
     * 根据属性名获取该类此属性的值
     * @param fieldName 字段名
     * @param object 对象
     * @return 返回对象
     */
    private static Object getValueByFieldName(String fieldName, Object object){
        String firstLetter=fieldName.substring(0,1).toUpperCase();
        String getter = "get"+firstLetter+fieldName.substring(1);
        try {
            Method method = object.getClass().getMethod(getter);
            return method.invoke(object);
        } catch (Exception e) {
            return null;
        }
    }

}
