package org.generation.italy.eventi;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Concert extends Event {
	
	private LocalTime hour;
	private BigDecimal price;

	public Concert(String title, String dateStr, int availableSeats, String hour, int price) throws Exception {
		
		super(title, dateStr, availableSeats);
		setHour(hour);
		setPrice(price);
	}
	
	public void setHour(String hour) {
		
		String fixedHour = hour.replace(".", ":");
		
		this.hour = LocalTime.parse(fixedHour);
		
	}
	public String getHour() {
		return this.hour.toString();
	}
	
	public void setPrice(int price) {
		this.price = new BigDecimal(price);
	}
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public BigDecimal getFormattedPrice() {
		return this.price.setScale(2);
	}

	@Override
	public String toString() {
		return getHour() + " " + super.toString() + " " + getFormattedPrice() + "€";
	}
}
