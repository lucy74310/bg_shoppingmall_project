package com.cafe24.shoppingmall.frontend.dto;

public class JSONResult2<T> {
	private String result;  //success, fail
	private String message; //if fail, set
	private T data;    //if success, set

	public JSONResult2() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static JSONResult2 fail(String message) {
		return new JSONResult2("fail", message, null);
	}
	
	
	public JSONResult2(String result, String message, T data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public T getData() {
		return data;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
