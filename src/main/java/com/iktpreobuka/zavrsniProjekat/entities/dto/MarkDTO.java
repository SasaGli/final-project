package com.iktpreobuka.zavrsniProjekat.entities.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MarkDTO {
	@Min(value=1,message="Mark must be higher than 0")
	@Max(value=5,message="Mark must be Lower or equal to 5")
	private Integer mark;

	public MarkDTO(
			@Min(value = 1, message = "Mark must be higher than 0") @Max(value = 5, message = "Mark must be Lower or equal to 5") Integer mark) {
		super();
		this.mark = mark;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public MarkDTO() {
		super();
	}
	
	

}
