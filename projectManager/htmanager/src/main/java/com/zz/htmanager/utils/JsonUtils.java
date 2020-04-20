package com.zz.htmanager.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonUtils {
    public static String beanToJson(Object object) {
        String json = "{";
        Field[] field = object.getClass().getDeclaredFields();
        for (Field f : field
        ) {
            Method method = null;
            try {
                method = object.getClass().getMethod("get" + do17(f.getName()), null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object getValue = null;
            try {
                getValue = method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
//            System.out.println(f.getName()+"-----"+getValue);
            json += "[" + f.getName() + ":" + getValue.toString() + "]";
        }
        return json + "}";
    }

    //让第一个字母大写
    public static String do17(String str) {
        if (str != null && str != "") {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

//    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        UserEntity userEntity = new UserEntity(1,"zhangsan","zengwujun");
//        System.out.println(beanToJson(userEntity));
//    }
}
