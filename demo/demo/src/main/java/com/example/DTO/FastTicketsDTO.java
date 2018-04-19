package com.example.DTO;

public class FastTicketsDTO {
	
	private Long hall_id;
	
	private Long projection_id;
	
	private int fast_tickets_number;

	public Long getHall_id() {
		return hall_id;
	}

	public void setHall_id(Long hall_id) {
		this.hall_id = hall_id;
	}

	public Long getProjection_id() {
		return projection_id;
	}

	public void setProjection_id(Long projection_id) {
		this.projection_id = projection_id;
	}

	public int getFast_tickets_number() {
		return fast_tickets_number;
	}

	public void setFast_tickets_number(int fast_tickets_number) {
		this.fast_tickets_number = fast_tickets_number;
	}

	public FastTicketsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FastTicketsDTO(Long hall_id, Long projection_id, int fast_tickets_number) {
		super();
		this.hall_id = hall_id;
		this.projection_id = projection_id;
		this.fast_tickets_number = fast_tickets_number;
	}
	
	
	

}
