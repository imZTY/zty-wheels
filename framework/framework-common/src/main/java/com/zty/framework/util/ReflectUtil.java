package com.zty.framework.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tianyi
 * @date 2020-05-01 23:55
 */
public class ReflectUtil {

    /**
     * 将sourceObj的值复制给clazz2的新实例并返回新实例
     * @param sourceObj 值源对象
     * @param clazz1 sourceObj的类型
     * @param clazz2 返回实例所属类
     * @return 类为clazz2的新实例
     * @throws InstantiationException 实例化失败
     * @throws IllegalAccessException 访问非法
     */
    public static Object propertyMapper(Object sourceObj, Class clazz1, Class clazz2) throws InstantiationException, IllegalAccessException {
        if (sourceObj == null){
            return null;
        }
        if (sourceObj.getClass().equals(ArrayList.class)){
            return propertyMapperForList((ArrayList<Object>) sourceObj, clazz1, clazz2);
        }
        if (!sourceObj.getClass().equals(clazz1)){
            throw new ClassCastException("参数类型不匹配，sourceClass="+sourceObj.getClass());
        }
        Field[] clazz1Fields = clazz1.getDeclaredFields();
        Set<String> clazz2Fields = Arrays.stream(clazz2.getDeclaredFields()).map(field -> field.getName()).collect(Collectors.toSet());
        try {
            // 反射生成的对象
            Object rtObj = clazz2.newInstance();
            for (int i = 0; i < clazz1Fields.length; i++) {
                //设置对象的访问权限，保证对private的属性的访问
                Field declaredField = clazz1Fields[i];
                // 判断是否为同名字段
                if (clazz2Fields.contains(declaredField.getName())){
                    // 设置访问private的权限
                    declaredField.setAccessible(true);
                    // 判断是否为空
                    if (declaredField.get(sourceObj) != null){
                        // 非空则复制value
                        Field class2Field = clazz2.getDeclaredField(declaredField.getName());
                        class2Field.setAccessible(true);
                        class2Field.set(rtObj, declaredField.get(sourceObj));
                    }
                }
            }
            return rtObj;
        } catch (InstantiationException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } catch (NoSuchFieldException e) {
            // 不可能事件
            return null;
        }
    }

    /**
     * 将sourceObj的值复制给clazz2的新实例并返回新实例
     * @param sourceObjList 值源对象列表
     * @param clazz1 sourceObj的类型
     * @param clazz2 返回实例所属类
     * @return 类为clazz2的新实例列表
     * @throws InstantiationException 实例化失败
     * @throws IllegalAccessException 访问非法
     */
    public static List<Object> propertyMapperForList(List<Object> sourceObjList, Class clazz1, Class clazz2) throws InstantiationException, IllegalAccessException {
        if (sourceObjList.isEmpty()){
            throw new IndexOutOfBoundsException("sourceObjList不能为空");
        }
        if (!sourceObjList.get(0).getClass().equals(clazz1)){
            throw new ClassCastException("参数类型不匹配");
        }
        Field[] clazz1Fields = clazz1.getDeclaredFields();
        Set<String> clazz2Fields = Arrays.stream(clazz2.getDeclaredFields()).map(field -> field.getName()).collect(Collectors.toSet());
        try {
            // 反射生成的对象列表
            List<Object> rtObjList = new ArrayList<>();
            for (int j = 0; j < sourceObjList.size(); j++) {
                Object sourceObj = sourceObjList.get(j);
                Object rtObj = clazz2.newInstance();
                for (int i = 0; i < clazz1Fields.length; i++) {
                    //设置对象的访问权限，保证对private的属性的访问
                    Field declaredField = clazz1Fields[i];
                    // 判断是否为同名字段
                    if (clazz2Fields.contains(declaredField.getName())){
                        // 设置访问private的权限
                        declaredField.setAccessible(true);
                        // 判断是否为空
                        if (declaredField.get(sourceObj) != null){
                            // 非空则复制value
                            Field class2Field = clazz2.getDeclaredField(declaredField.getName());
                            class2Field.setAccessible(true);
                            class2Field.set(rtObj, declaredField.get(sourceObj));
                        }
                    }
                }
                rtObjList.add(rtObj);
            }
            return rtObjList;
        } catch (InstantiationException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } catch (NoSuchFieldException e) {
            // 不可能事件
            return null;
        }
    }

}
