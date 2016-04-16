package com.ch.main;
/**
 * @author: ch
 * @date:2016年4月16日 下午5:51:41
 * 
 */
public class MAttribute {

	private String url;
	
	private String name;
	
	

	public MAttribute(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "name: " + name + " url: " + url;
	}
	
}
