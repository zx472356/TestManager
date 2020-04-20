package com.zz.htmanager.utils;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class DataUtils {
    private static Properties Inpro;
    private static Properties Outpro;
    private static OutputStream outputStream;
    private static boolean inFlag = false;
    private static boolean outFlag = false;

    synchronized static private void storeProperties() {

        try {
            getPro();
            outputStream = new FileOutputStream("E:\\gitManager\\src\\main\\resources\\mydata.properties");
            outFlag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    synchronized static private void loadProperties() {
        try {
            getPro();
            InputStream resourceAsStream = new FileInputStream("E:\\gitManager\\src\\main\\resources\\mydata.properties");
            Inpro.load(resourceAsStream);
            inFlag = true;
            System.out.println("------文件加载------");
        } catch (IOException e) {
            System.out.println("找不到文件");
            e.printStackTrace();
        }
    }

    private static void getPro() {
        if (Inpro == null) {
            Inpro = new Properties();
        }
        if (Outpro == null) {
            Outpro = new Properties();
        }
        System.out.println("配置文件对象已创建");
    }

    private static void inCheck() {
        if (inFlag == false) {
            loadProperties();
        }
    }

    private static void outCheck() {
        if (outFlag == false) {
            storeProperties();
        }
    }

    public static boolean insert(Object object) {
        try {
            outCheck();
            Outpro.setProperty(object.getClass().getName(), JsonUtils.beanToJson(object));
            Outpro.store(outputStream, "xianzai shi:" + new Date().toString());
//            System.out.println("文件信息写入");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //    public static boolean delete(Object object){
//        try{
//            inCheck();
//            Inpro.remove(object.getClass().getName());
//            System.out.println("文件删除成功");
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//    public static boolean update(Object object){
//        try{
//            outCheck();
//            Outpro.replace(object.getClass().getName(),object.toString());
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
    public static String select(String s) {
        try {
            inCheck();
            return Inpro.getProperty(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

//    public static void main(String[] args) {
//
//        UserEntity userEntity = new UserEntity(1,"zengwujun","zengwujun");
//        System.out.println(DataUtils.insert(userEntity));
//        System.out.println(DataUtils.select(userEntity));
//    }
}