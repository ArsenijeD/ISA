package com.example.DTO;

public class RatingTheaterDTO {

	private Long userID;
	
	private Long theaterID;
	
	private int mark;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getTheaterID() {
		return theaterID;
	}

	public void setTheaterID(Long theaterID) {
		this.theaterID = theaterID;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	
	public RatingTheaterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
