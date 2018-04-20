package com.example.DTO;

public class ReservationTicketDTO {
	
	private Long user_id;
	
	private Long projection_id;

	private Long ticket_id;

	
	public Long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(Long ticket_id) {
		this.ticket_id = ticket_id;
	}

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
