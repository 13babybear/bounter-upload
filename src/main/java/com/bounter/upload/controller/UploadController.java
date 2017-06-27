package com.bounter.upload.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bounter.upload.entity.ResponseData;
import com.bounter.upload.oss.OSSUtil;

/**
 * Restful 文件上传控制器
 * @author simon
 *
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
	
	//阿里云上的图片文件存放目录，如果为空则存放在顶层bucket下
	private static final String OSS_IMG_DIR = "imgs/";
	
	
	@RequestMapping(value = "/image",method = RequestMethod.POST)
	public ResponseData<String> imageUpload(@RequestParam(value = "file")MultipartFile file){
		ResponseData<String> responseData = new ResponseData<>();
	    if (!file.isEmpty()) {
	    	//生成带目录的完整目标文件名
	    	String targetFileName = OSS_IMG_DIR + file.getOriginalFilename();
            // 调用OSS工具上传文件到阿里云OSS
            try {
				OSSUtil.simpleUploadFile(file.getInputStream(), targetFileName);
				responseData.setData("上传成功！");
				responseData.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				responseData.setSuccess(false);
				responseData.setErrorMsg("上传失败！");
			}
        } else {
        	responseData.setSuccess(false);
			responseData.setErrorMsg("上传文件不能为空！");
        }
	    
	    return responseData;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public ResponseData<String> imageDelete(@RequestParam(value = "targetFileName")String targetFileName){
		ResponseData<String> responseData = new ResponseData<>();
		
		//生成带目录的完整目标文件名
    	targetFileName = OSS_IMG_DIR + targetFileName;
        // 调用OSS工具上传文件到阿里云OSS
        try {
			OSSUtil.deleteFile(targetFileName);
			responseData.setData("删除成功！");
			responseData.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setSuccess(false);
			responseData.setErrorMsg("删除失败！");
		}
        
        return responseData; 
	}

}
