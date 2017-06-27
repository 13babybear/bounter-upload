package com.bounter.upload.oss;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.oss.OSSClient;

/**
 * 阿里云OSS工具类
 * @author simon
 *
 */
public class OSSUtil {

	//注入OSS配置类
	@Autowired
	private OSSProperties ossProps;
	
	private static OSSUtil ossUtil;
	
	@PostConstruct
	public void init() {
		ossUtil = this;
		ossUtil.ossProps= this.ossProps;
	}
    
    /**
     * 简单上传文件
     * @param inputStream		文件输入流
     * @param targetFileName	上传后的完整文件名，包含目录结构,如：imgs/logo.png
     * @throws Exception
     */
    public static void simpleUploadFile(InputStream inputStream, String targetFileName) throws Exception {
    	// 创建OSSClient实例
    	OSSClient ossClient = new OSSClient(ossUtil.ossProps.getEndpoint(), ossUtil.ossProps.getAccessKeyId(), ossUtil.ossProps.getAccessKeySecret());
    	
    	// 上传文件流
    	ossClient.putObject(ossUtil.ossProps.getBucketName(), targetFileName, inputStream);
    	
    	// 关闭client
    	ossClient.shutdown();
    }
    
    /**
     * 删除文件
     * @param targetFileName	云上完整文件名，包含目录结构，如：imgs/logo.png
     */
    public static void deleteFile(String targetFileName) {
    	// 创建OSSClient实例
    	OSSClient ossClient = new OSSClient(ossUtil.ossProps.getEndpoint(), ossUtil.ossProps.getAccessKeyId(), ossUtil.ossProps.getAccessKeySecret());
    	
    	// 删除文件
    	ossClient.deleteObject(ossUtil.ossProps.getBucketName(), targetFileName);
    	
    	// 关闭client
    	ossClient.shutdown();
    }
    
    
	public static void main(String[] args) throws Exception {
//		InputStream in = new FileInputStream("logo.png");
//		String targetFileName = "imgs/logo.png";
//		simpleUploadFile(in,targetFileName);
		deleteFile("imgs/logo.png");
	}

}
