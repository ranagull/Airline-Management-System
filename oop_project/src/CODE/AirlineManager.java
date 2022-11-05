package CODE;

import java.util.ArrayList;
import java.util.Random;

public class AirlineManager {

	private String name;
	private String password;
	private ArrayList<Employee> employeeList;
	private ArrayList<Passenger> passengerList;
	private ArrayList<Plane> planeList;
	private ArrayList<Flight> flightList;
	private ArrayList<FlightWorker> flightWorkerList;
	private ArrayList<Airport> airportList;

	private static AirlineManager instance;

	public static AirlineManager getInstance() {
		if (instance == null) {
			instance = new AirlineManager();
		}
		return instance;
	}

	private AirlineManager() {
		name = "admin";
		password = "deuceng";
		employeeList = new ArrayList<Employee>();
		planeList = new ArrayList<Plane>();
		passengerList = new ArrayList<Passenger>();
		flightList = new ArrayList<Flight>();
		airportList = new ArrayList<Airport>();
		flightWorkerList = new ArrayList<FlightWorker>();
	}

	public boolean addEmployee(String name, String surname, String email, int age, char gender, int salary,
			String field, String idNo) {

		for (Employee employee : employeeList) {
			if (employee.control(idNo)) {
				return false;
			}
		}
		if (field.equals("FlightWorker")) {
			Employee employee = new FlightWorker(name, surname, email, age, gender, salary, field, idNo, flightList);
			employeeList.add(employee);
			return true;
		} else {
			Employee employee = new MaintenanceWorker(name, surname, email, age, gender, salary, field, idNo,
					planeList);
			employeeList.add(employee);
			return true;
		}
	}

	public boolean deleteEmployee(int idNo) {
		for (Employee employee : employeeList) {

			if (employee.control(idNo)) {
				employeeList.remove(employee);
				return true;
			}
		}
		return false;
	}

	public Passenger addPassenger(String name, String surname, int age, char gender, String email, String idNo,
			String password) { // sign

		for (Passenger passenger : passengerList) {
			if (passenger.control(idNo))
				return null;
		}
		Passenger passenger = new Passenger(name, surname, age, gender, email, idNo, password);
		passengerList.add(passenger);
		passenger.getFlights(flightList);
		return passenger;
	}

	public boolean addPlane(int age, int seatNumber, String type) {
		Plane plane = new Plane(age, seatNumber, type);
		planeList.add(plane);
		return true;
	}

	public boolean deletePlane(int idNo) {
		Plane p = null;
		for (Plane plane : planeList) {
			if (plane.getPlaneID() == idNo) {
				p = plane;
				break;
			}
		}
		for (Flight flight : flightList) {
			if (flight.getPlane().getPlaneID() == idNo)
				deleteFlight(flight.getFlightID());
		}
		planeList.remove(p);
		return true;
	}

	public boolean addFlight(int planeID, String source, String destination, String flightDate, String departureTime,
			String arrivalTime, int flightGate, char destinationType) {
		Plane p = null;
		for (Plane plane : planeList) {
			if (planeID == plane.getPlaneID())
				p = plane;
		}

		Flight flight = new Flight(p, source, destination, flightDate, departureTime, arrivalTime, flightGate,
				destinationType);
		flightList.add(flight);

		return true;
	}

	public boolean deleteFlight(int flightID) {
		Flight f = null;
		for (Flight flight : flightList) {
			if (flightID == flight.getFlightID()) {
				f = flight;
				break;
			}
		}

		if (f != null) {
			for (Passenger passenger : passengerList) {
				for (Ticket ticket : passenger.getTickets()) {
					if (ticket.getFlight().getFlightID() == flightID) {
						passenger.getTickets().remove(ticket);
					}
				}
			}
			flightList.remove(f);
		}
		return true;
	}

	public boolean addAirport(String airportName) {
		for (Airport airport : airportList) {
			if (airport.control(airportName))
				return false;
		}

		Airport airport = new Airport(airportName);
		airportList.add(airport);
		return true;
	}

	public boolean deleteAirport(int airportID) {
		Airport a = null;
		for (Airport airport : airportList) {
			if (airport.getAirportCode() == airportID) {
				a = airport;
				break;
			}
		}
		for (Flight flight : flightList) {
			if (flight.getSource().equals(a.getAirportName()) || flight.getDestination().equals(a.getAirportName()))
				deleteFlight(flight.getFlightID());
		}
		airportList.remove(a);
		return true;
	}

	public boolean controlLoginPassenger(String name, String password) {
		for (Passenger passenger : passengerList) {
			if (passenger.getName().equals(name) && passenger.getPassword().equals(password))
				return true;
		}
		return false;
	}

	public int controlWorker(String name, String password) { // eger wroker adminse manager employee ise type
		if (!name.equals("admin"))
			for (Employee employee : employeeList) {
				if (employee.getName().equals(name) && employee.getPassword().equals(password))
					if (employee.getField().equals("FlightWorker")) {
						return 1;
					} else if (employee.getField().equals("MaintanenceWorker"))
						return -1;
			}
		else if(name.equals("admin") && password.equals("deuceng"))
			return 0; 
		return 2; // yok
		
	}

	public Employee getEmployee(String name, String password) {
		for (Employee employee : employeeList) {
			if (employee.getName().equals(name) && employee.getPassword().equals(password))
				return employee;
		}
		return null;
	}

	public Passenger getPassenger(String name, String password) {
		for (Passenger passenger : passengerList) {
			if (passenger.getName().equals(name) && passenger.getPassword().equals(password))
				return passenger;
		}
		return null;
	}

	public ArrayList<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	public ArrayList<Plane> getPlaneList() {
		return planeList;
	}

	public void setPlaneList(ArrayList<Plane> planeList) {
		this.planeList = planeList;
	}

	public ArrayList<Flight> getFlightList() {
		return flightList;
	}

	public void setFlightList(ArrayList<Flight> flightList) {
		this.flightList = flightList;
	}

	public ArrayList<FlightWorker> getFlightWorkerList() {
		return flightWorkerList;
	}

	public void setFlightWorkerList(ArrayList<FlightWorker> flightWorkerList) {
		this.flightWorkerList = flightWorkerList;
	}

	public ArrayList<Airport> getAirportList() {
		return airportList;
	}

	public void setAirportList(ArrayList<Airport> airportList) {
		this.airportList = airportList;
	}

	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
