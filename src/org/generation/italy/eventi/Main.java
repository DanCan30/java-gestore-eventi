package org.generation.italy.eventi;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		boolean mainMenu = true;
		
		System.out.println("Set a name for an Event Program: ");
		String userProgramName = sc.nextLine();
		EventProgram ep = new EventProgram(userProgramName.substring(0,1).toUpperCase() + userProgramName.substring(1));
		
		System.out.println("Event Program " + ep.getTitle() + " created successfully.");
		
		while(mainMenu) {
			System.out.println("\n\nWhat's next?"
					+ "\n0-See all the events in the program"
					+ "\n1-Add a new event"
					+ "\n2-Check all the events in a specific date"
					+ "\n3-Delete all the events in the program");
			int userMenuChoice = sc.nextInt();
	
			if(userMenuChoice == 0) {
				mainMenu = false;
				ep.getOrderedEventList();
				System.out.println("\nWhat's next?"
						+ "\n0-Main menu"
						+ "\n1-Book a seat"
						+ "\n2-Cancel a booking");
				int OrderedListChoice = sc.nextInt();
				
				if(OrderedListChoice == 0) {
					mainMenu = true;
				} else if (OrderedListChoice == 1) {

					bookSeats(mainMenu, sc, ep);
				} else if (OrderedListChoice == 2) {

					removeSeats(mainMenu, sc, ep);
	
				} else {
					System.err.println("Invalid input.");
					mainMenu = true;
				}
			}
			else if(userMenuChoice == 1) {
				createNewEvent(mainMenu, sc, ep);
			}
			else if(userMenuChoice == 2) {
				System.out.println("Select a date (yyyy-mm-dd): ");
				String userDate = sc.next();
				ep.getEventsInADate(userDate);
			} else if(userMenuChoice == 3) {
				ep.removeAllEventsFromProgram();
				System.out.println("All events removed from the program.");
			} else {
				System.err.println("Invalid input.");
			}
			mainMenu = true;
		}
		
	}
	
	public static void createNewEvent(boolean menu, Scanner sc, EventProgram program) {

		menu = false;
		System.out.println("----------");
		System.out.print("Add a new event title: ");
		String title = sc.next();
		System.out.print("Set a date for your event (yyyy-mm-dd): ");
		String date = sc.next();
		System.out.print("Set the amount of available seats: ");
		int availableSeats = sc.nextInt();
		Event e;
		
		try {
			e = new Event(title, date, availableSeats);
			System.out.println("Your event has been created successfully. Here the details: ");
			System.out.println(e);
			System.out.println("Available seats: " + e.getAvailableSeats());
			program.addEvent(e);
			
		} catch(Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			menu = true;
		}
	}
	
	public static void bookSeats(boolean menu, Scanner sc, EventProgram program) {
		
		if(program.countEvents() == 0) {
			System.out.println("The event list is empty.");
			menu = true;
		} else {
			System.out.println("Select an event: ");
			program.getOrderedEventList();
			int eventChosen = sc.nextInt();
			try {
				
				Event e = program.getOrderedEventList().get(eventChosen - 1);
				System.out.println("How many seats do you want to take? ");
				int seatsToReserve = sc.nextInt();
				e.reserveSeat(seatsToReserve);
				System.out.println("You booked " + seatsToReserve + " seats."
						+ "\nSeats reserved: " + e.getTakenSeats()
						+ "\nSeats available: " + e.getAvailableSeats());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public static void removeSeats(boolean menu, Scanner sc, EventProgram program) {
		
		if(program.countEvents() == 0) {
			System.out.println("The event list is empty.");
			menu = true;
		} else {
			System.out.println("Select an event: ");
			program.getOrderedEventList();
			int eventChosen = sc.nextInt();
			try {
				
				Event e = program.getOrderedEventList().get(eventChosen - 1);
				System.out.println("How many reserved seats do you want to cancel? ");
				int seatsToCancel = sc.nextInt();
				e.cancelReservedSeat(seatsToCancel);
				System.out.println("You removed " + seatsToCancel + " reserved seats."
						+ "\nSeats reserved: " + e.getTakenSeats()
						+ "\nSeats available: " + e.getAvailableSeats());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	
}
//}
//
//if(createdEvent) {
//	
//	System.out.println("Do you want to reserve some seats? (type y for yes, everything else will be considered a no)");
//	String userBookingChoice = sc.next();
//	int userReservedSeats = 0;
//	
//	if(userBookingChoice.toLowerCase().equals("y")) {
//		System.out.println("How many seats do you need? ");
//		try {
//			
//		userReservedSeats = sc.nextInt();
//		e.reserveSeat(userReservedSeats);
//		System.out.println("You booked " + userReservedSeats + " seats."
//				+ "\nSeats reserved: " + e.getTakenSeats()
//				+ "\nSeats available: " + e.getAvailableSeats());
//		} catch (Exception ex) {
//			System.err.println(ex.getMessage());
//			return;
//		}
//		
//		System.out.println("Do you want to cancel some reserved seats? (type y for yes, everything else will be considered a no)");
//		String userCancelChoice = sc.next();
//		int userRemovedSeats = 0;
//		
//		if(userCancelChoice.toLowerCase().equals("y")) {
//			System.out.println("How many reserved seats do you want to remove? ");
//			try {
//				
//				userRemovedSeats = sc.nextInt();
//			e.cancelReservedSeat(userRemovedSeats);
//			System.out.println("You removed " + userRemovedSeats + " reserved seats."
//					+ "\nSeats reserved: " + e.getTakenSeats()
//					+ "\nSeats available: " + e.getAvailableSeats());
//			} catch (Exception ex) {
//				System.err.println(ex.getMessage());
//			}
//		}
//	}
//}
