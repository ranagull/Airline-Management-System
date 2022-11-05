package CODE;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ticket {

	private String ticketClass; // ?????
	private static int ticketIDCounter = 0;
	private int ticketID;
	private double price;
	private boolean checkIn;
	private int seat;
	private Flight flight;

	Ticket(Flight flight, String ticketClass,
			String paymentType, String date, boolean membership) {
		this.flight = flight;
		this.ticketClass = ticketClass;
		this.price = price(flight.getDestinationType(), membership);
		ticketIDCounter++;
		this.ticketID = ticketIDCounter;
		checkIn = false;
		seat = flight.getPlane().getSeat();
	}

	public int price(char destinationType, boolean premium) {
		int price = 500;
		if(destinationType == ('D'))
			price = 300;
				
		if(premium)
			return price-(price/5);
		return price;
	}
	public int calculationLuggWeight(int luggageWeight) {
		if (ticketClass.equals("Business"))
			return (luggageWeight - 40) * 10;
		else {
			return (luggageWeight - 20) * 10;
		}
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public String getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}
}
