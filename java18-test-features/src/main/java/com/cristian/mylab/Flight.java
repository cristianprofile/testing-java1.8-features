package com.cristian.mylab;

import java.time.LocalDate;

public class Flight {
	
 private String code;
 private String destination;
 private LocalDate date;
 private Integer numPassengers;
 private Integer numSeats;
 private Double price;
 private Duration duration;
 
 
 
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public Integer getNumPassengers() {
	return numPassengers;
}
public void setNumPassengers(Integer numPassengers) {
	this.numPassengers = numPassengers;
}
public Integer getNumSeats() {
	return numSeats;
}
public void setNumSeats(Integer numSeats) {
	this.numSeats = numSeats;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Duration getDuration() {
	return duration;
}
public void setDuration(Duration duration) {
	this.duration = duration;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Flight [code=");
	builder.append(code);
	builder.append(", destination=");
	builder.append(destination);
	builder.append(", date=");
	builder.append(date);
	builder.append(", numPassengers=");
	builder.append(numPassengers);
	builder.append(", numSeats=");
	builder.append(numSeats);
	builder.append(", price=");
	builder.append(price);
	builder.append(", duration=");
	builder.append(duration);
	builder.append("]");
	return builder.toString();
}
 
 
 
 
}
