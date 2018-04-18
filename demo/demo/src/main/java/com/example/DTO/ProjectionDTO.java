package com.example.DTO;

public class ProjectionDTO {
	
	
	private Long cinema_id;
	
	private Long hall_id;
	
	private Long film_id;
	
	private String date;
	
	private String time;
	
	private int discount;
	
	
	private Long projection_id;		// zbog izmenee
	
	private Long old_hall_id;		// zbog izmene

	
	public Long getProjection_id() {
		return projection_id;
	}

	public void setProjection_id(Long projection_id) {
		this.projection_id = projection_id;
	}
	
	
	public Long getOld_hall_id() {
		return old_hall_id;
	}

	public void setOld_hall_id(Long old_hall_id) {
		this.old_hall_id = old_hall_id;
	}

	
	
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

	public Long getFilm_id() {
		return film_id;
	}

	public void setFilm_id(Long film_id) {
		this.film_id = film_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	

	public ProjectionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectionDTO(Long cinema_id, Long hall_id, Long film_id, String date, String time, int discount) {
		super();
		this.cinema_id = cinema_id;
		this.hall_id = hall_id;
		this.film_id = film_id;
		this.date = date;
		this.time = time;
		this.discount = discount;
	}

	
	
	
	
}
