package com.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@RequestMapping("method2")
@ResponseBody
public class method2 {
	
	@RequestMapping("upload")
	 public String method2(@RequestParam MultipartFile file,
             @RequestParam String messageContent  ) { 
             //多个参数的话只要多个@RequestParam即可，注意参数名要和表单里面的属性名一致
      JSONObject json =new JSONObject();
      System.out.println(messageContent);
         String orgiginalFileName = "";  
         int m =new Random().nextInt(100)+10;
         System.out.println("m="+m);
         String path="D:/"+m+"b.txt";
         try {  
             File newFile =new File(path);
             file.transferTo(newFile);

             String fileName = file.getName();  
             InputStream inputStream = file.getInputStream();  
             String content = file.getContentType();  
             orgiginalFileName = file.getOriginalFilename();  
            System.out.println("fileName: "+fileName+", inputStream: "+ inputStream  
                         +"\r\n content: "+content+", orgiginalFileName: ="+ orgiginalFileName  
                         +"\r\n projectName: ");      
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
         json.put("flag", true);
         json.put("message", "success");
         System.out.println(json.toJSONString());
         return json.toJSONString();  
     }  
	

}
