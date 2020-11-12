package com.maomao.utils;

/**
 * Description:
 * Author:         杨俊
 * CreateDate:     2020/3/1 17:23
 * Version:        1.0
 */
import java.io.File;

public class ChangeVideoName {
    public static void change(File file){
        File[] files=file.listFiles();
        for(File f:files){
            if(f.isFile()) {
                String name=f.getAbsolutePath();
                Integer n=Integer.parseInt(name.substring(name.indexOf("第")+1,name.indexOf("节")));
                if(n<10){
                    name=name.substring(0,name.indexOf("第"))+"第00"+n+name.substring(name.indexOf("节"));
                }else if(n<100){
                    name=name.substring(0,name.indexOf("第"))+"第0"+n+name.substring(name.indexOf("节"));
                }
                f.renameTo(new File(name));
            }
        }
    }
    public static void main(String[] args) {
        File f=new File("C:\\Users\\yangjunst\\Desktop\\设计模式全解10-04-41\\next");
        change(f);

    }
}
