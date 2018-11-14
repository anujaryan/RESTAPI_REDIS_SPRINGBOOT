package com.idemia.service;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;




public interface UploadImage {

	public void convertBase64(MultipartFile[] array) throws IOException;
	@Cacheable("Image")
	public String getImage(String imageName);
	public LinkedHashMap<Object, Object> getAllProduct();
	@CacheEvict("Image")
	public String deleteImage(String imagename);
}
