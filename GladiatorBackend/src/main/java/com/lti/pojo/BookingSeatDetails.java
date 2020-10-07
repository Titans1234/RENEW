package com.lti.pojo;

public class BookingSeatDetails {
	private String seatNo;

//	private String seatClass;

	
	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	
	@Override
	public String toString() {
		return "BookingSeatDetails [seatNo=" + seatNo + "]";
	}

}
