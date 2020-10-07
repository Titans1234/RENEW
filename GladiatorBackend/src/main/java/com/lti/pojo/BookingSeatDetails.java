package com.lti.pojo;

public class BookingSeatDetails {
	private String seatNo;

	private String seatClass;

	
	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	@Override
	public String toString() {
		return "BookingSeatDetails [seatNo=" + seatNo + "]";
	}

}
