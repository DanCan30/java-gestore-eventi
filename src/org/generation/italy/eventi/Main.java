package org.generation.italy.eventi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		
		boolean createdEvent = false;
		Scanner sc = new Scanner(System.in);
		Event e = null;

		while(!createdEvent) {
			System.out.println("----------");
			System.out.print("Add a new event title: ");
			String title = sc.next();
			System.out.print("Set a date for your event (yyyy-mm-dd): ");
			String date = sc.next();
			System.out.print("Set the amount of available seats: ");
			int availableSeats = sc.nextInt();
			
			try {
				e = new Event(title, date, availableSeats);
				createdEvent = (e == null ? false : true);
				System.out.println("Your event has been created successfully. Here the details: ");
				System.out.println(e);
				System.out.println("Available seats: " + e.getAvailableSeats());
				
			} catch(Exception ex) {
				System.err.println(ex.getMessage());
			}		
			
		}
		
		if(createdEvent) {
			
			System.out.println("Do you want to reserve some seats? (type y for yes, everything else will be considered a no)");
			String userBookingChoice = sc.next();
			int userReservedSeats = 0;
			
			if(userBookingChoice.toLowerCase().equals("y")) {
				System.out.println("How many seats do you need? ");
				try {
					
				userReservedSeats = sc.nextInt();
				e.reserveSeat(userReservedSeats);
				System.out.println("You booked " + userReservedSeats + " seats."
						+ "\nSeats reserved: " + e.getTakenSeats()
						+ "\nSeats available: " + e.getAvailableSeats());
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
					return;
				}
				
				System.out.println("Do you want to cancel some reserved seats? (type y for yes, everything else will be considered a no)");
				String userCancelChoice = sc.next();
				int userRemovedSeats = 0;
				
				if(userCancelChoice.toLowerCase().equals("y")) {
					System.out.println("How many reserved seats do you want to remove? ");
					try {
						
						userRemovedSeats = sc.nextInt();
					e.cancelReservedSeat(userRemovedSeats);
					System.out.println("You removed " + userRemovedSeats + " reserved seats."
							+ "\nSeats reserved: " + e.getTakenSeats()
							+ "\nSeats available: " + e.getAvailableSeats());
					} catch (Exception ex) {
						System.err.println(ex.getMessage());
					}
				}
			}
		}
		
		
	}
}
