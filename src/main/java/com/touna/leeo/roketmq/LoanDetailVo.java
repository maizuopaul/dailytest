package com.touna.leeo.roketmq;



public class LoanDetailVo {
	private LoanDetail data;

	public LoanDetail getData() {
		return data;
	}

	public void setData(LoanDetail data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LoanDetailVo [data=" + data + "]";
	}
}
