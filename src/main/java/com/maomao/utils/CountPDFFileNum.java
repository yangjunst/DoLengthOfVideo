package com.maomao.utils;

import java.io.File;
import java.io.FileFilter;

/**
 * Description:    统计文件夹下所有pdf电子书的数量
 * Author:         杨俊
 * CreateDate:     2020/3/24 12:01
 * Version:        1.0
 */
public class CountPDFFileNum {
    private static int sum;
    private static final String FILE_NAME = "";
    private static boolean has = false;

    public static void count(File file) {
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".pdf") || f.isDirectory();
            }
        });
        for (File f : files) {
            if (f.isDirectory()) {
                count(f);
            } else {
                sum++;
                System.out.println(f.getAbsolutePath());
            }

        }
    }

    /**
     * 判断当前文件夹下有无指定的pdf文件
     * @param file
     */
    public static void hasThePDFFile(File file) {
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".pdf") || f.isDirectory();
            }
        });
        for (File f : files) {
            if (f.isDirectory()) {
                hasThePDFFile(f);
            } else {
                if (f.getAbsolutePath().contains(FILE_NAME)) {
                    has = true;
                }
            }
        }
    }


    public static void main(String[] args) {
        File f = new File("F:\\Architect-Library");
        count(f);
        System.out.println("pdf-->" + sum);
    }


}
