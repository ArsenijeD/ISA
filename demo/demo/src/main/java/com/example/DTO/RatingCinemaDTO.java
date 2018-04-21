package com.example.DTO;

public class RatingCinemaDTO {
	
	private Long userID;
	
	private Long cinemaID;
	
	private int mark;

	
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(Long cinemaID) {
		this.cinemaID = cinemaID;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}


	public RatingCinemaDTO(Long userID, Long cinemaID, int mark) {
		super();
		this.userID = userID;
		this.cinemaID = cinemaID;
		this.mark = mark;
	}

	public RatingCinemaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
