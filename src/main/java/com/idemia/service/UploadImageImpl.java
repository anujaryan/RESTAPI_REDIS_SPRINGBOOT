package com.idemia.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.idemia.util.Base64EncodeDecode;



@Service
public class UploadImageImpl implements UploadImage ,Serializable{

  private static final String REDIS_INDEX_KEY="IMAGEBASE64";
	
	
	private RedisTemplate<String, Object> redistemplate;
	private HashOperations  hashoperation;
	
	UploadImageImpl(RedisTemplate<String, Object> redistemplate){
		this.redistemplate=redistemplate;
		this.hashoperation=redistemplate.opsForHash();
	}
	private static final long serialVersionUID = 1L;

	
	public void convertBase64(MultipartFile[] file) throws IOException {
		if(file.length==1) {
			
			 String encodeimage=Base64EncodeDecode.encodeImageto64(file[0].getBytes()); 
			 hashoperation.put(REDIS_INDEX_KEY, file[0].getOriginalFilename(), encodeimage);
			  System.out.println("###########################################");
			  System.out.println(file[0].getOriginalFilename());
			  System.out.println(redistemplate.opsForHash().size(REDIS_INDEX_KEY));
			  System.out.println( "PutMember:Done" );
		}else {
			for(MultipartFile array:file) {
				   String encodeimage=Base64EncodeDecode.encodeImageto64(array.getBytes()); 
				   hashoperation.put(REDIS_INDEX_KEY, array.getOriginalFilename(), encodeimage);
					  System.out.println("###########################################");
					  System.out.println(array.getOriginalFilename());
					  System.out.println(redistemplate.opsForHash().size(REDIS_INDEX_KEY));
					  System.out.println( "PutMember:Done" );
			}
		}
		
		
	}

	@Override
	public String getImage(String imageName) {
		// TODO Auto-generated method stub
		
		 long start1 = System.nanoTime();
		 String image= (String) hashoperation.get(REDIS_INDEX_KEY,imageName);
		   long end1 = System.nanoTime();
		   System.out.println("#######################################################");
		   System.out.println("Time in milliseconds......"+TimeUnit.NANOSECONDS.toMillis(end1 - start1));
		   return image;
	}

	@Override
	public LinkedHashMap<Object, Object> getAllProduct() {
		// TODO Auto-generated method stub	
	@SuppressWarnings("unchecked")
	LinkedHashMap<Object, Object> list=(LinkedHashMap<Object, Object>) hashoperation.entries(REDIS_INDEX_KEY);
	return list;		
	}

	@Override
	public String deleteImage(String imagename) {
		// TODO Auto-generated method stub
		
		hashoperation.delete(REDIS_INDEX_KEY, imagename);
		return "Image Deleted Successfully!";
	}
	
	
}
