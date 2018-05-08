package com.exist.ecc.core.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private MultipartFile multipartFile;

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
}
