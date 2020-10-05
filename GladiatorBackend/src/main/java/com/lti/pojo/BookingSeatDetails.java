package com.lti.pojo;

public class BookingSeatDetails {
	private int seatNo;

	private String seatClass;

	public int getSeatNo() {
		return seatNo;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "BookingSeatDetails [seatNo=" + seatNo + "]";
	}

}
