package org.generation.italy.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EventProgram {
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static class DateComparator implements Comparator<Event> {

		@Override
		public int compare(Event o1, Event o2) {

			if(o1.getDate().isAfter(o2.getDate()))
				return 1;
			else if(o2.getDate().isAfter(o1.getDate()))
				return -1;
			return 0;
		}
		
	}

	private String title;
	private List<Event> events;
	
	public EventProgram(String title) {
		setTitle(title);
		this.events = getEvents();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	
	public List<Event> getEvents() {
		return this.events;
	}
	
	public void addEvent(Event e) {
		this.events.add(e);
	}
	
	public void getEventsInADate(String date) {
		
		LocalDate parsedDate = LocalDate.parse(date);
		
		List<Event> filteredEvents = new ArrayList<>();
		
		for(int i = 0; i < this.events.size(); i++) {
			Event e = (Event) this.events.toArray()[i];
			
			if(e.getDate().equals(parsedDate)) {
				filteredEvents.add(e);
			}
		}
		
		for(int i = 0; i < filteredEvents.size(); i++) {
			Event e = (Event) events.toArray()[i];
			System.out.println(i+1 + " - " + e.getTitle() );
		}
	}
	
	public int countEvents() {
		
		return this.events.size();
	}
	
	public void removeAllEventsFromProgram() {
		events.removeAll(events);
	}
	
	public String getFormattedDate(Event e) {
		return dateFormat.format(e.getDate());
	}
	
	public List<Event> getOrderedEventList() {
		
		System.out.println("Program title: " 
		+ getTitle() + " - " 
		+ (countEvents() == 0 ? "Empty program" : countEvents()));
		
		events.sort(new DateComparator());
		
		for(int i = 0; i < events.size(); i++) {
			Event e = (Event) events.toArray()[i];
			System.out.println(i+1 + ". " + getFormattedDate(e) + " - " + e.getTitle() );
		}
		
		return events;
	}
}
