package com.tryMyAnnotation.bean;

import com.tryMyAnnotation.anno.Bean;
import com.tryMyAnnotation.anno.Di;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gim 2023/9/15 下午10:11
 */
public class ApplicationContextImpl implements ApplicationContext {
    private Map<Class, Object> beans = new HashMap<>();
    private static String rootPath;

    @Override
    public Object getBean(Class clazz) {
        return beans.get(clazz);
    }

    //设置扫描规则,这个目录中，哪个对象有我自定义的@Bean注解，我就需要处理
    public ApplicationContextImpl(String basePackage) {
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(basePackage);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String path = URLDecoder.decode(url.getFile(), "utf-8");
                rootPath = path.substring(0, path.length() - basePackage.length());
                loadBean(new File(path));

            }
            //这些bean已经全部都new好了，现在该set属性了
            loadDi();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void loadDi() throws IllegalAccessException {
        Set<Map.Entry<Class, Object>> entries = beans.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            Object value = entry.getValue();
            Class<?> aClass = value.getClass();
            //获取类中的属性
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Di annotation = field.getAnnotation(Di.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    field.set(value,beans.get(field.getType()));
                }
            }
        }
    }

    private  void loadBean(File file) throws Exception {
        //和之前学IO流的递归式copy文件夹内所有的内容一样，现在是递归式处理文件夹内所有的内容
        //如果这个类头上顶着@Bean注解，我就需要处理它，把它放入容器中
        if (file.isDirectory()) {//如果当前目录是文件夹
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                loadBean(f);
            }
        } else {
            //我想拿反射时用到的类的全名，这时候就需要处理这个字符串了
            if (file.getAbsolutePath().endsWith(".class")) {
                String pathWithClass = file.getAbsolutePath().substring(rootPath.length() - 1);
              String className=pathWithClass.replaceAll("\\\\",".").replace(".class","");
                Class<?> aClass = Class.forName(className);
                if (!aClass.isInterface()){
                    Bean bean = aClass.getAnnotation(Bean.class);
                    if (bean != null) {//如果你有我自定义的这个注解
                        Object o = aClass.getConstructor().newInstance();
                        if (aClass.getInterfaces().length>0){
                            beans.put(aClass.getInterfaces()[0],o);
                        }else {
                            beans.put(aClass,o);
                        }
                    }
                }
            }
        }
    }
}
