package com.example.DTO;

public class StageDTO {
	
	private Long theater_id;
	
	private Long stage_id;
	
    private int number;
    
	private int numberOfChairs;

	public Long getTheater_id() {
		return theater_id;
	}

	public void setTheater_id(Long theater_id) {
		this.theater_id = theater_id;
	}

	public Long getStage_id() {
		return stage_id;
	}

	public void setStage_id(Long stage_id) {
		this.stage_id = stage_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumberOfChairs() {
		return numberOfChairs;
	}

	public void setNumberOfChairs(int numberOfChairs) {
		this.numberOfChairs = numberOfChairs;
	}
	
	

	public StageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StageDTO(Long theater_id, Long stage_id, int number, int numberOfChairs) {
		super();
		this.theater_id = theater_id;
		this.stage_id = stage_id;
		this.number = number;
		this.numberOfChairs = numberOfChairs;
	}
	
	

}
