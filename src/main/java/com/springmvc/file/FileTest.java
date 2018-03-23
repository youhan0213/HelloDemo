package com.springmvc.file;

import java.io.File;

import com.springmvc.util.FileUtil;

public class FileTest {

    public static void main(String[] args)  
    {  
        String photoUrl = "http://cache.zeasn.tv/static/dev/apk/20180116065236527.apk";                                      
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));   
        //System.out.println("fileName---->"+fileName);  
        String filePath = "d:";    
        File file = FileUtil.saveUrlAs(photoUrl, filePath + fileName,"GET");    
        System.out.println("Run ok!/n<BR>Get URL file " + file);    
  
    }  
}
