package com.touna.leeo.roketmq;


public class CreditDetailMqVo {
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	private CreditDetail data;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CreditDetail getData() {
		return data;
	}
	public void setData(CreditDetail data) {
		this.data = data;
	}
}
