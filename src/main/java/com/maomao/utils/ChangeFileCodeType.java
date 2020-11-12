package com.maomao.utils;

/**
 * Description:    将选中文件以GBK批量改为UTF-8
 * Author:         杨俊
 * CreateDate:     2020/3/3 19:50
 * Version:        1.0
 */
import java.io.*;

public class ChangeFileCodeType {

    private static boolean GBKfileToUTF8(String filePath) {
        // 以GBK格式,读取文件
        try {
            try (FileInputStream fis = new FileInputStream(filePath);
                 InputStreamReader isr = new InputStreamReader(fis, "GBK");
                 BufferedReader br = new BufferedReader(isr)) {
                String str;

                // 创建StringBuffer字符串缓存区
                StringBuilder sb = new StringBuilder();

                // 通过readLine()方法遍历读取文件
                while ((str = br.readLine()) != null) {
                    // 使用readLine()方法无法进行换行,需要手动在原本输出的字符串后面加"\n"或"\r"
                    str += "\n";
                    sb.append(str);
                }
                String str2 = sb.toString();

                // 以UTF-8格式写入文件,file.getAbsolutePath()即该文件的绝对路径,false代表不追加直接覆盖,true代表追加文件
                File file = new File(filePath);
                try (FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(), false);
                     OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8")) {
                    osw.write(str2);
                    osw.flush();
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;

        } catch (UnsupportedEncodingException e) {
            return false;

        } catch (IOException e) {
            return false;

        }

    }
    private static void change(File f){
        if(f.isDirectory()){
            File[] files=f.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.getName().endsWith(".txt")||file.getName().endsWith(".java")||file.isDirectory();
                }
            });
            for(File file:files){
                if(file.isDirectory()){
                    change(file);
                }else{
                    GBKfileToUTF8(file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        File f=new File("F:\\A架构师之路电子书");
        change(f);
    }
}
