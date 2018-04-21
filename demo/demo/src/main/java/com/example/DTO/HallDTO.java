package com.example.DTO;



public class HallDTO {
	
	private Long cinema_id;
	
	private Long hall_id;
	
    private int number;
    
	private int numberOfSeats;

	

	
	public Long getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(Long cinema_id) {
		this.cinema_id = cinema_id;
	}

	

	public Long getHall_id() {
		return hall_id;
	}

	public void setHall_id(Long hall_id) {
		this.hall_id = hall_id;
	}

	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	
	public HallDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HallDTO(Long cinema_id, int number, int numberOfSeats) {
		super();
		this.cinema_id = cinema_id;
		this.number = number;
		this.numberOfSeats = numberOfSeats;
	}

	
	
	
	

}
