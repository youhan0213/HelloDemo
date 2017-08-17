package com.springmvc.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.model.FileBean;
import com.springmvc.result.APIResult;
import com.springmvc.result.MessageResult;

@Controller
@RequestMapping("/file")
public class fileUpload {
	
	@RequestMapping("/upload")
	public APIResult fileUpload(MultipartFile file,HttpServletRequest req,HttpServletResponse resp) {
		FileBean fb = null;
		if(!file.isEmpty()) {
			try {  
                //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字   
                FileOutputStream os = new FileOutputStream("D:/"+file.getOriginalFilename());  
                InputStream in = file.getInputStream();  
                int b = 0;  
                while((b=in.read())!=-1){ //读取文件   
                    os.write(b);  
                }  
                os.flush(); //关闭流   
                in.close();  
                os.close();  
                  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
			
		}
		
		return new MessageResult("success");
		
	}

}
