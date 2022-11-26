package org.generation.italy.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueEventProgram {
	
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
	public static class StringComparator implements Comparator<Event> {

		@Override
		public int compare(Event o1, Event o2) {
			return o1.getTitle().length() - o2.getTitle().length();
		}
		
		
	}

	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Set<Event> uniqueEvents;
	
	public UniqueEventProgram() {
		this.uniqueEvents = new HashSet<>();
		
	}
	
	public Set<Event> getUniqueEvents() {
		return this.uniqueEvents;
	}
	
	public void addUniqueEvent(Event e) {
		this.uniqueEvents.add(e);
	}
	
	public void getUniqueEventsInADate(String date) {
		
		LocalDate parsedDate = LocalDate.parse(date);
		
		Set<Event> filteredEvents = new HashSet<>();
		
		for(int i = 0; i < this.uniqueEvents.size(); i++) {
			Event e = (Event) this.uniqueEvents.toArray()[i];
			
			if(e.getDate().equals(parsedDate)) {
				filteredEvents.add(e);
			}
		}
		
		for(int i = 0; i < filteredEvents.size(); i++) {
			Event e = (Event) filteredEvents.toArray()[i];
			System.out.println(i+1 + " - " + e.getTitle() );
		}
	}
	public int countUniqueEvents() {
		
		return this.uniqueEvents.size();
	}
	
	public void removeAllUniqueEventsFromProgram() {
		uniqueEvents.removeAll(uniqueEvents);
	}
	
	public String getFormattedDate(Event e) {
		return dateFormat.format(e.getDate());
	}
	
	public List<Event> getOrderedEventList() {
		
		System.out.println( (countUniqueEvents() == 0 ? "Empty program" : "Events in this list: " +countUniqueEvents()));
		
		List<Event> uniqueEventsList = new ArrayList<>(uniqueEvents); 
		
		uniqueEventsList.sort(new DateComparator());
		
		uniqueEvents = new HashSet<>(uniqueEventsList);
		
		for(int i = 0; i < uniqueEventsList.size(); i++) {
			Event e = (Event) uniqueEventsList.toArray()[i];
			System.out.println(i+1 + ". " + getFormattedDate(e) + " - " + e.getTitle() );
		}
		
		return uniqueEventsList;
	}
	
	public Event getHighestSeatsNumberEvent() {
		
		int maxValue = Integer.MIN_VALUE;
		Event selectedEvent = null;
		
		for(Event e : uniqueEvents) {
			int totalSeats = e.getAvailableSeats() + e.getTakenSeats();
			if(totalSeats > maxValue) {
				maxValue = totalSeats;
				selectedEvent = e;
			}
		}
		return selectedEvent;
	}
	
	public Event getLowestSeatsNumberEvent() {
		
		int minValue = Integer.MAX_VALUE;
		Event selectedEvent = null;
		
		for(Event e : uniqueEvents) {
			int totalSeats = e.getAvailableSeats() + e.getTakenSeats();
			if(totalSeats < minValue) {
				minValue = totalSeats;
				selectedEvent = e;
			}
		}
		return selectedEvent;
	}
	
	public List<Event> orderedPrint() {
		
		List<Event> uniqueEventsList = new ArrayList<>(uniqueEvents); 
		
		uniqueEventsList.sort(new StringComparator());
		
		return uniqueEventsList;
	}
	
	public Event getFirstUpcomingEvent() {
		return Collections.min(uniqueEvents, new DateComparator());
	}
	
	public Event getLastUpcomingEvent() {
		return Collections.max(uniqueEvents, new DateComparator());
	}
}
