package org.generation.italy.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {

	private String title;
	private LocalDate date;
	private int availableSeats;
	private int takenSeats;
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Event(String title, String dateStr, int availableSeats) throws Exception {
		setTitle(title);
		setDate(dateStr);
		setAvailableSeats(availableSeats);
		setTakenSeats();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	
	public void setDate(String dateStr) throws Exception {
		
		dateStr = dateStr.replace("/", "-");
		
		LocalDate date = LocalDate.parse(dateStr);
		
		if(LocalDate.now().isAfter(date)) {
			throw new Exception("The event date is not valid.");
		}
		this.date = date;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	private void setAvailableSeats(int availableSeats) throws Exception {
		
		if(availableSeats <= 0) {
			throw new Exception("The available seats number must be positive.");
		}
		
		this.availableSeats = availableSeats;
	}
	public int getAvailableSeats() {
		return this.availableSeats;
	}
	
	private void setTakenSeats() {
		this.takenSeats = 0;
	}
	public int getTakenSeats() {
		return this.takenSeats;
	}
	
	public void reserveSeat(Integer num) throws Exception {
		
		if(!(num instanceof Integer)) {
			throw new Exception("Please insert a valid number");
		}
		
		if(LocalDate.now().isAfter(date)) {
			throw new Exception("The event is passed.");
		}
		this.availableSeats -= num;
		
		if(this.availableSeats < 0) {
			this.availableSeats = 0;
			throw new Exception("There aren't enough seats to reserve.");
		}
		
		this.takenSeats += num;
	}
	
	public void cancelReservedSeat(Integer num) throws Exception {
		
		if(!(num instanceof Integer)) {
			throw new Exception("Please insert a valid number");
		}
		
		if(LocalDate.now().isAfter(date)) {
			throw new Exception("The event is passed.");
		}
		this.takenSeats -= num;
		
		if(this.takenSeats < 0) {
			this.takenSeats = 0;
			throw new Exception("There are no reserved seats yet.");
		}
		
		this.availableSeats += num;
	}
	
	public String getFormattedDate() {
		return dateFormat.format(getDate());
	}
	
	public boolean equals(Event e) {
		if (this.getTitle().equals(e.getTitle()) && this.getDate().equals(e.getDate()))
			return true;
					
		return false;
	}
	
	@Override
	public String toString() {
		return getFormattedDate() + " - " + getTitle();
	}
}
