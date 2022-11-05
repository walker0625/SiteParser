package com.minwoo.siteparser.dto;

public class ParsingInput {
	
	private String url;
	private String type;
	private String size;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ParsingInput [url=" + url + ", type=" + type + ", size=" + size + "]";
	}
	
}
