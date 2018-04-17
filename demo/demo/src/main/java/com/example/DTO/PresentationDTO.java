package com.example.DTO;

public class PresentationDTO {
	
	private Long theater_id;
	
	private Long stage_id;
	
	private Long performance_id;
	
	private String date;
	
	private String time;
	
	private int discount;
	
	
	
	private Long presentation_id;		// zbog izmenee
	
	private Long old_stage_id;		// zbog izmene

	

	
	
	public Long getPresentation_id() {
		return presentation_id;
	}

	public void setPresentation_id(Long presentation_id) {
		this.presentation_id = presentation_id;
	}

	public Long getOld_stage_id() {
		return old_stage_id;
	}

	public void setOld_stage_id(Long old_stage_id) {
		this.old_stage_id = old_stage_id;
	}

	
	
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

	public Long getPerformance_id() {
		return performance_id;
	}

	public void setPerformance_id(Long performance_id) {
		this.performance_id = performance_id;
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

	
	
	
	public PresentationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PresentationDTO(Long theater_id, Long stage_id, Long performance_id, String date, String time,
			int discount) {
		super();
		this.theater_id = theater_id;
		this.stage_id = stage_id;
		this.performance_id = performance_id;
		this.date = date;
		this.time = time;
		this.discount = discount;
	}
	
	
	

}
