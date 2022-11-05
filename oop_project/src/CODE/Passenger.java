package CODE;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Passenger{
	private String name;
	private String surname;
	private String password; // password nasýl eklenecek
	private int age;
	private char gender;
	private static int passengerIDCounter =0;
	private int passengerID;
	private double passengerPoint =0;
	private boolean memberShip;
	private ArrayList<Ticket> tickets;
	private String email;
	private String identityNumber;
	private double point;
	private ArrayList<Flight> flights;
	private String userName;
	
	public Passenger(String name, String surname, int age, char gender, String email, String idNo, String password) { // age, gender kontrolü yapýlacak mainde
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
		this.email = email;
		passengerIDCounter++;
		passengerID=passengerIDCounter;
		memberShip=false;
		tickets = new ArrayList<Ticket>();
		identityNumber = idNo;
		this.password = password;
		point = 0;
		this.userName = name;
	}
	public void getFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	
	public void listTickets() {
		for (Ticket ticket : tickets) {
			System.out.println(ticket.toString());
		}
	}
	
	public double buyingticket(int flightID, String ticketClass) {
		Flight f = null;
		for (Flight flight : flights) {
			if(flightID == flight.getFlightID())
				f = flight;
		}
		Ticket ticket = new Ticket(f, ticketClass, "Credit Card", f.getFlightDate(), getMemberShip());
		tickets.add(ticket);	
		setPoint(getPoint()+(ticket.getPrice()*0.05));
		return ticket.getPrice();
	}
	
	public void ticketExchange(Ticket ticket, int flightID, String ticketClass) {
		Flight f = null;
		for (Flight flight : flights) {
			if(flightID == flight.getFlightID())
				f = flight;
		}
		ticket.setTicketClass(ticketClass);
		ticket.setFlight(f);
	}

	public void ticketCancellation(int ticketID) {
		Ticket t = null;
		for (Ticket ticket : tickets) {
			if(ticketID== ticket.getTicketID())
				t = ticket;
		}
		t.getFlight().getPlane().ticketCancellation(t.getSeat());
		tickets.remove(t);
		setPoint(getPoint()-(t.getPrice()*0.05));
	}

	public Ticket ticketInquiry(int ticketID) { 
		Ticket t = null;
		for (Ticket ticket : tickets) {
			if(ticketID== ticket.getTicketID())
				t = ticket;
		}
		return t;
	}

	public boolean checkIn(int ticketID) throws ParseException {
		Ticket t = null;
		for (Ticket ticket : tickets) {
			if(ticketID== ticket.getTicketID())
				t = ticket;
		}
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat spf = new SimpleDateFormat(pattern);
		Date today = new Date();
		spf.format(today);
		Date flightDate = spf.parse(t.getFlight().getFlightDate());
		if(today.compareTo(flightDate) == 0) {  
			t.setCheckIn(true);
			return true;
		}
		return false;
	}

	public boolean control(String idNo) {
		if (getIdentityNumber() == idNo) {
			return true; 
		}
		return false;
	}
	
	public void getClub() {
		setMemberShip(true);
	}
	
	public void leaveClub() {
		setMemberShip(false);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}

	public double getPassengerPoint() {
		return passengerPoint;
	}

	public void setPassengerPoint(double passengerPoint) {
		this.passengerPoint = passengerPoint;
	}

	public boolean getMemberShip() {
		return memberShip;
	}

	public void setMemberShip(boolean memberShip) {
		this.memberShip = memberShip;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static int getPassengerIDCounter() {
		return passengerIDCounter;
	}

	public static void setPassengerIDCounter(int passengerIDCounter) {
		Passenger.passengerIDCounter = passengerIDCounter;
	}
	
	
}
