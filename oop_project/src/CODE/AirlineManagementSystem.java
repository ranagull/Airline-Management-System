package CODE;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.*;

public class AirlineManagementSystem {
	static AirlineManager manager = AirlineManager.getInstance();

	public static void readPassenger() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("passenger.txt"), "UTF-8");

		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			String[] input = line.split(",");
			manager.addPassenger(input[0], input[1], Integer.parseInt(input[2]), input[3].charAt(0), input[4], input[5],
					input[6]);
		}
		sc.close();
	}

	public static void writeFilePassenger(String fileName) throws IOException {

		FileWriter myWriter = new FileWriter(fileName);
		for (int i = 0; i < manager.getPassengerList().size(); i++) {
			myWriter.write(manager.getPassengerList().get(i).getName() + ","
					+ manager.getPassengerList().get(i).getSurname() + "," 
					+ manager.getPassengerList().get(i).getAge()+ "," 
					+ manager.getPassengerList().get(i).getGender() + ","
					+ manager.getPassengerList().get(i).getEmail() + ","
					+ manager.getPassengerList().get(i).getIdentityNumber() + ","
					+ manager.getPassengerList().get(i).getPassword() + "\n");
		}
		myWriter.close();
	}

	public static void readAirport() throws FileNotFoundException {
		Scanner scn = new Scanner(new File("airport.txt"), "UTF-8");
		String line;
		
		while (scn.hasNextLine()) {
			line = scn.nextLine();
			manager.addAirport(line);
		}
		scn.close();
	}

	public static void writeFileAirport(String fileName) throws IOException {

		FileWriter myWriter = new FileWriter(fileName);
		for (int i = 0; i < manager.getAirportList().size(); i++) {
			myWriter.write(manager.getAirportList().get(i).getAirportName() + "\n");
		}
		myWriter.close();
	}

	public static void readPlane() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("plane.txt"), "UTF-8");

		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			String[] input = line.split(",");
			manager.addPlane(Integer.parseInt(input[0]), Integer.parseInt(input[1]), input[2]);
		}
		sc.close();
	}

	public static void writeFilePlane(String fileName) throws IOException {

		FileWriter myWriter = new FileWriter(fileName);
		for (int i = 0; i < manager.getPlaneList().size(); i++) {
			myWriter.write(manager.getPlaneList().get(i).getAge() + "," 
						+ manager.getPlaneList().get(i).getSeats().length + ","
							+ manager.getPlaneList().get(i).getType() + "\n");
		}
		myWriter.close();
	}

	public static void readFlight() throws FileNotFoundException {
		Scanner sca = new Scanner(new File("flight.txt"), "UTF-8");
		String line;
		while (sca.hasNextLine()) {
			line = sca.nextLine();
			String[] input = line.split(",");
			manager.addFlight(Integer.parseInt(input[0]), input[1], input[2], input[3], input[4], input[5], Integer.parseInt(input[6]),
					input[7].charAt(0));
		}
		sca.close();
	}

	public static void writeFileFlight(String fileName) throws IOException {

		FileWriter myWriter = new FileWriter(fileName);
		for (int i = 0; i < manager.getFlightList().size(); i++) {
			myWriter.write(manager.getFlightList().get(i).getPlane().getPlaneID() + "," 
					+ manager.getFlightList().get(i).getSource() + "," 
					+ manager.getFlightList().get(i).getDestination()+ "," 
					+ manager.getFlightList().get(i).getFlightDate() + ","
					+ manager.getFlightList().get(i).getDepartureTime() + ","
					+ manager.getFlightList().get(i).getArrivalTime() + ","
					+ manager.getFlightList().get(i).getFlightGate() + ","
					+ manager.getFlightList().get(i).getDestinationType() + "\n");
		}

		myWriter.close();
	}

	public static void readEmployee() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("employee.txt"), "UTF-8");
		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			String[] input = line.split(",");
			manager.addEmployee(input[0], input[1], input[2], Integer.parseInt(input[3]), input[4].charAt(0),
					Integer.parseInt(input[5]), input[6], input[7]);
		}
		sc.close();
	}

	public static void writeFileEmployee(String fileName) throws IOException {

		FileWriter myWriter = new FileWriter(fileName);
		for (int i = 0; i < manager.getEmployeeList().size(); i++) {
			myWriter.write(manager.getEmployeeList().get(i).getName() + ","
					+ manager.getEmployeeList().get(i).getSurname() + "," + manager.getEmployeeList().get(i).getEmail()
					+ "," + manager.getEmployeeList().get(i).getAge() + ","
					+ manager.getEmployeeList().get(i).getGender() + "," + manager.getEmployeeList().get(i).getSalary()
					+ "," + manager.getEmployeeList().get(i).getField() + ","
					+ manager.getEmployeeList().get(i).getIdentityNumber() + "\n");
		}
		myWriter.close();
	}

	public static void save() throws IOException {

		writeFilePlane("plane.txt");
		writeFileFlight("flight.txt");
		writeFileAirport("airport.txt");
		writeFilePassenger("passenger.txt");
		writeFileEmployee("employee.txt");
	}
	public static void read() throws IOException{
		readAirport();
		readPlane();
		readFlight();
		readEmployee();
		readPassenger();
	}

	public static void main(String[] args) throws IOException {

		read();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(manager);
					frame.setTitle(" AIRLINE MANAGEMENT SYSTEM");
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
