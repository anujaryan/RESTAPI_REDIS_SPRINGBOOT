package com.idemia.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.idemia.entity.Image;
import com.idemia.model.Response;
import com.idemia.service.UploadImage;




@RestController
@RequestMapping("/base64endcodeservice")
public class ImageBase64Controller {

	
	private UploadImage  uploadImageService;
	
	ImageBase64Controller(UploadImage uploadImageService){
		this.uploadImageService=uploadImageService;
	}
	
	
	@RequestMapping(value="/convertBase64Image",method=RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response> uploadImage64bit(@RequestParam("file") MultipartFile[] file) {
		Response response=new Response();
		int count=0;
		try {
		
			uploadImageService.convertBase64(file);
			 response.setMessage("file upload successfully");
			 response.setResponsecode("000");
			
		}catch(Exception e) {
			e.printStackTrace();
			 response.setMessage("file upload failed");
			    response.setResponsecode("001");
		}
		   
		 
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="convertBase64Image/{imagename}" ,method=RequestMethod.GET)
	public Image getProduct(@PathVariable("imagename") String imagename) {
		String encodeimage=uploadImageService.getImage(imagename);
		Image image=new Image();
		if(encodeimage!=null) {
			image.setEncodeImage(encodeimage);
			image.setImagename(imagename);
		}else {
			image.setEncodeImage("Image not found");
			image.setImagename(imagename);
		}
		return image;	
	}
	
	@RequestMapping(value="convertBase64ImageAll",method=RequestMethod.GET)
	public LinkedHashMap<Object, Object> getAllProduct(){
		
        LinkedHashMap<Object, Object> list=uploadImageService.getAllProduct();      
		return list;
	}
	
	@RequestMapping(value="convertBase64Image/{imagename}",method=RequestMethod.DELETE)
	public Response deleteImage(@PathVariable("imagename") String imagename){
		
		//return  new ResponseEntity<>(redistemplate.opsForHash().entries(REDIS_INDEX_KEY),HttpStatus.OK);
		Response response=new Response();
		String message=uploadImageService.deleteImage(imagename);
		if(message!=null) {
			response.setMessage(message);
			response.setResponsecode("000");
		
		}else {
			response.setMessage("Image Deletion Failed");
			response.setResponsecode("001");
		}
		return response;
	}
	
	
}
