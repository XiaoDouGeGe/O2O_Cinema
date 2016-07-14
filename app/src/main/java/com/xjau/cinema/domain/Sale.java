package com.xjau.cinema.domain;

public class Sale {

	private String payAccout;
	private String payCinema;
	private String payFilm;
	private String payShowTime;
	private String seats;
	
	public Sale(String payAccout, String payCinema, String payFilm,
			String payShowTime, String seats) {
		super();
		this.payAccout = payAccout;
		this.payCinema = payCinema;
		this.payFilm = payFilm;
		this.payShowTime = payShowTime;
		this.seats = seats;
	}

	public String getPayAccout() {
		return payAccout;
	}

	public void setPayAccout(String payAccout) {
		this.payAccout = payAccout;
	}

	public String getPayCinema() {
		return payCinema;
	}

	public void setPayCinema(String payCinema) {
		this.payCinema = payCinema;
	}

	public String getPayFilm() {
		return payFilm;
	}

	public void setPayFilm(String payFilm) {
		this.payFilm = payFilm;
	}

	public String getPayShowTime() {
		return payShowTime;
	}

	public void setPayShowTime(String payShowTime) {
		this.payShowTime = payShowTime;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return payAccout + "-" + payCinema + "-" + payFilm + "-" + payShowTime
				+ "-" + seats;
	}
	
	
	
}
