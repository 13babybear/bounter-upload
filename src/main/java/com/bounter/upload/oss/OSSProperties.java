package com.bounter.upload.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云对象存储服务OSS配置实体
 * @author simon
 *
 */
@Component
public class OSSProperties {
	@Value("${oss.endpoint}")
	private String endpoint;
	
	@Value("${oss.accesskeyid}")
	private String accessKeyId;
	
	@Value("${oss.accesskeysecret}")
	private String accessKeySecret;
	
	@Value("${oss.bucketname}")
	private String bucketName;

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
}
