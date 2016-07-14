package com.xjau.cinema.domain;


public class Cinema {

	
	private String cinema_name;
	private String areaName;
	private double ticketPrice;

	public Cinema(String cinema_name, String areaName, double ticketPrice) {
		super();
		this.cinema_name = cinema_name;
		this.areaName = areaName;
		this.ticketPrice = ticketPrice;
	}
	
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	@Override
	public String toString() {
		return cinema_name + "   "+ areaName + "   ￥" + ticketPrice;
	}
	
	
	
	
	
}
