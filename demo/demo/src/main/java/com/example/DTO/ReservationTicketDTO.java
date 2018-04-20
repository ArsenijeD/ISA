package com.example.DTO;

public class ReservationTicketDTO {
	
	private Long user_id;
	
	private Long projection_id;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getProjection_id() {
		return projection_id;
	}

	public void setProjection_id(Long projection_id) {
		this.projection_id = projection_id;
	}

	
	
	public ReservationTicketDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationTicketDTO(Long user_id, Long projection_id) {
		super();
		this.user_id = user_id;
		this.projection_id = projection_id;
	}
	
	

}
