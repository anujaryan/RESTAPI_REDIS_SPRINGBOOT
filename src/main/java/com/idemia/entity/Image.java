package com.idemia.entity;

import java.io.Serializable;

public class Image implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Image() {
		
	}

	private String imagename;
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	private String encodeImage;
	
	public String getEncodeImage() {
		return encodeImage;
	}
	public void setEncodeImage(String encodeImage) {
		this.encodeImage = encodeImage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
