package com.cristian.mylab;

public class Duration {
	private Integer hours;
	private Integer minutes;

	
	
	
	public Duration() {
		super();
	}

	public Duration(Integer hours, Integer minutes) {
		super();
		this.hours = hours;
		this.minutes = minutes;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Duration [hours=");
		builder.append(hours);
		builder.append(", minutes=");
		builder.append(minutes);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
