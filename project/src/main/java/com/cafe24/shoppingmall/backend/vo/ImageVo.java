package com.cafe24.shoppingmall.backend.vo;

public class ImageVo {
	private Long no;
	private String url;
	private char is_main;
	private Long product_no;
	
	public ImageVo() {
	}

	public ImageVo(String url, char is_main) {
		this.url = url;
		this.is_main = is_main;
	}
	
	@Override
	public String toString() {
		return "ImageVo [no=" + no + ", url=" + url + ", is_main=" + is_main + ", product_no=" + product_no + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public char getIs_main() {
		return is_main;
	}
	public void setIs_main(char is_main) {
		this.is_main = is_main;
	}
	public Long getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Long product_no) {
		this.product_no = product_no;
	}
	
	
}
