package com.maomao.utils;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 计算指定文件夹下的视频文件总时长
 */

public class GetLengthOfVideo
{
    private static Long ReadVideoTime(File source) {
        Encoder encoder = new Encoder();
        String length = "";
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration()/1000;
//            int hour = (int) (ls/3600);
//            int minute = (int) (ls%3600)/60;
//            int second = (int) (ls-hour*3600-minute*60);
//            length = hour+":"+minute+":"+second;]
            return ls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }
    public static List<File> getVideoFiles(File dir){
        List<File> list=new ArrayList<>();
        File[] fiels=dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
//                return file.isDirectory()||file.getName().endsWith(".pdf");
                return file.isDirectory()||file.getName().endsWith(".mp4")||file.getName().endsWith(".wmv")||file.getName()
                        .endsWith(".avi")||file.getName().endsWith(".flv")||file.getName().endsWith(".mov")||file.getName().endsWith(".mkv");
            }
        });
       for(File f:fiels){
           if(f.isDirectory()){
               list.addAll(getVideoFiles(f));
           }else{
               list.add(f);
           }
       }
        return list;
    }
    public static String countTotalLength(List<File> fs){
        String length = "";
        long ls=0;
        for(File f:fs){
            ls+=ReadVideoTime(f);
        }
        int hour = (int) (ls/3600);
        int minute = (int) (ls%3600)/60;
        int second = (int) (ls-hour*3600-minute*60);
        length = hour+":"+minute+":"+second;
        return length;
    }
    public static void main(String[] args) {
        Objects.isNull(null);
        File f=new File("H:\\SSM\\尚学堂\\04 SSM框架整合和核心要点复习\\视频");
        List<File> fs=getVideoFiles(f);
        for(File file:fs){
            System.out.println(file.getAbsoluteFile());
        }
//        System.out.println(fs.size());
        System.out.println(countTotalLength(fs));
    }

}
