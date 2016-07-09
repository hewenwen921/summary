package com.wen.common.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hcw on 16-3-17.
 */
public class ReflectBeanUtils {
    /**
     * 将Bean转换为Element的共用方法
     *
     * @param targetClazz 需要转换成的目标类型
     * @param list        要执行转换的List集合
     * @return 返回转换后的集合
     */
    public static synchronized List<?> convertBeanToElement(Class<?> targetClazz,
                                                            List<?> list) {
        int size = list.size();
        List<Object> objList = new ArrayList<Object>(size);
        Object object = null;
        Object[] objs = null;

        for (int i = 0; i < size; i++) {
            //这里要用反射将bean转换为element封装进response
            objs = new Object[1];
            objs[0] = list.get(i);
            object = ReflectBeanUtils.parse(targetClazz, objs);
            objList.add(object);
        }

        return objList;
    }

    /**
     * 转换bean
     *
     * @param targetClass 目标类
     * @param sourceObjs  原始对象
     * @return 目标对象
     */
    public static Object parse(Class<?> targetClass, Object[] sourceObjs) {
        /**
         * 返回的对象
         */
        Object returnObj = null;

        /**
         * 原始对象
         */
        Object sourceObject = null;
        try {
            /**
             * 获取返回对象实例
             */
            returnObj = targetClass.newInstance();
        } catch (Exception e) {
//            log.error("new instance new object error!");
        }

        /**
         * 原始对象class数组
         */
        Class<?>[] sourceClasss = new Class<?>[sourceObjs.length];

        /**
         * 获取原始对象class
         */
        for (int i = 0; i < sourceObjs.length; i++) {
            sourceClasss[i] = sourceObjs[i].getClass();
        }

        /**
         * 返回对象所有方法
         */
        Method[] targetMethods = targetClass.getMethods();

        /**
         * 所有原始对象方法
         */
        Method[] sourceMethods = null;

        /**
         * 原始对象方法
         */
        Method sourceMethod = null;

        /**
         * 原始对象方法名称
         */
        String sourceMethodName = "";
        /**
         * 方法
         */
        Method targetMethod = null;

        /**
         * 方法名字
         */
        String targetMethodName = "";

        /**
         * 原对象class
         */
        Class<?> sourceClass = null;

        /**
         * get开头的方法名
         */
        String getName = "";

        /**
         * get方法
         */
        Method getMethod = null;
        /**
         * 循环所有方法
         */
        for (int i = 0; i < targetMethods.length; i++) {
            targetMethod = targetMethods[i];

            targetMethodName = targetMethod.getName();
            /**
             * 如果方法名字是set开头的
             */
            if (targetMethodName.startsWith("set")) {
            }
            /**
             * 得到get方法名
             */
            getName = targetMethodName.replaceFirst("set", "get");

            /**
             * 循环传入的原始对象
             */
            for (int j = 0; j < sourceClasss.length; j++) {
                sourceClass = sourceClasss[j];
                sourceMethods = sourceClass.getMethods();

                /**
                 * 循环所有原始对象方法
                 */
                for (int k = 0; k < sourceMethods.length; k++) {
                    sourceMethod = sourceMethods[k];

                    /**
                     * 获取方法名称
                     */
                    sourceMethodName = sourceMethod.getName();

                    /**
                     * 如果方法名和get名相同,并退出循环
                     */
                    if (sourceMethodName.equals(getName)) {
                        getMethod = sourceMethod;
                        sourceObject = sourceObjs[j];
                        break;
                    }
                }
            }

            /**
             * 如果getMethod不为空
             */
            if (null != getMethod) {
                invoke(getMethod, targetMethod, sourceObject, returnObj);
            }
        }
//    }

        return returnObj;

    }

    private static void invoke(Method getMethod, Method targetMethod,
                               Object sourceObject, Object returnObject) {
        /**
         * set类型
         */
        Class<?> setType = null;

        /**
         * get类型
         */
        Class<?> getType = null;

        /**
         * 获取Set的值类型
         */
        setType = targetMethod.getParameterTypes()[0];

        /**
         * 获取get的返回值类型
         */
        getType = getMethod.getReturnType();

        /**
         * get获取的对象
         */
        Object getObject = null;

        /**
         * 空对象数组,用于传于get方法的invoke
         */
        Object[] getObjs = {};

        /**
         * 获取get所返回的对象
         */
        try {
            getObject = getMethod.invoke(sourceObject, getObjs);
        } catch (Exception e) {
//            log.error("invoke getMethod error!");
        }

        if (null != getObject) {
            try {
                /**
                 * 如果set,get相同类型直接invoke
                 */
                if (setType.equals(getType)) {
                    targetMethod.invoke(returnObject, getObject);
                } else {
                    if (setType.equals(Date.class)) {
                        Date returnData = (Date) getObject;
                        String newDate = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss").format(returnData);
                        targetMethod.invoke(returnObject, newDate);

                    }

                    if (getType.equals(Date.class)) {
                        String returnData = (String) getObject;
                        Date newDate = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss").parse(returnData);
                        targetMethod.invoke(returnObject, newDate);
                    }

                    parseNumberInvoke(setType,
                            getType,
                            targetMethod,
                            returnObject,
                            getObject);

                }
            } catch (Exception e) {
//                log.error("invoke error!");
            }
        }
        getMethod = null;

        sourceObject = null;


    }

    private static void parseNumberInvoke(Class<?> setType, Class<?> getType,
                                          Method targetMethod, Object returnObject, Object getObject) {
        try {
            if (int.class.equals(setType) || double.class.equals(setType)
                    || float.class.equals(setType)) {
                targetMethod.invoke(returnObject, getObject.toString());
            }

            if (int.class.equals(getType)) {
                String returnData = (String) getObject;
                int newInt = Integer.parseInt(returnData);
                targetMethod.invoke(returnObject, newInt);
            }
            if (double.class.equals(getType)) {
                String returnData = (String) getObject;
                double newDouble = Double.parseDouble(returnData);
                targetMethod.invoke(returnObject, newDouble);
            }
            if (Float.class.equals(getType)) {
                String returnData = (String) getObject;
                float newFloat = Float.parseFloat(returnData);
                targetMethod.invoke(returnObject, newFloat);
            }
        } catch (Exception e) {
//            log.error("invoke error!");
        }

    }
}