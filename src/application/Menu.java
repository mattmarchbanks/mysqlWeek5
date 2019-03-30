package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.DinosaurDao;
import entity.Dinosaur;

public class Menu {
	
	private DinosaurDao dinosaurDao = new DinosaurDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display dinosaurs",
			"Display one dinosaur",
			"Add a dinosaur",
			"Delete a dinosaur");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayDinosaurs();
				} else if (selection.equals("2")) {
					displayDinosaur();
				} else if (selection.equals("3")) {
					createDinosaur();
				} else if (selection.equals("4")) {
					deleteDinosaur();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue");
			scanner.nextLine();
		} while (!selection.contentEquals("-1"));
	}

	private void printMenu() {
		System.out.println("Select an Option:\n-------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayDinosaurs() throws SQLException {
		List<Dinosaur> dinosaurs = dinosaurDao.getDinosaurs();
		for (Dinosaur dinosaur : dinosaurs) {
			System.out.println(dinosaur.getId() + ". Species: "+ dinosaur.getSpeciesName());
			System.out.println("Diet: " + dinosaur.getDiet());
			System.out.println("Stance: " + dinosaur.getStance());
			System.out.println();
		}
	}
	
	private void displayDinosaur() throws SQLException {
		System.out.println("Enter dinosaur id: ");
		int id = Integer.parseInt(scanner.nextLine());
		Dinosaur dinosaur = dinosaurDao.getDinosaurById(id);
		System.out.println(dinosaur.getId() + ". Species name: " + dinosaur.getSpeciesName());
		System.out.println("Diet: " + dinosaur.getDiet());
		System.out.println("Stance: " + dinosaur.getStance());
		
	}
	
	private void createDinosaur() throws SQLException {
		System.out.println("Enter new dinosaur species:");
		String speciesName = scanner.nextLine();
		System.out.println("Enter new dinosaur diet:");
		String diet = scanner.nextLine();
		System.out.println("Enter new dinosaur stance:");
		String stance = scanner.nextLine();
		dinosaurDao.createNewDinosaur(speciesName, diet, stance);
		
	}
	
	private void deleteDinosaur() throws SQLException {
		System.out.print("Enter dinosaur id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		dinosaurDao.deleteDinosaurById(id);
	}
	
}
