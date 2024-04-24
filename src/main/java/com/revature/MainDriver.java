package com.revature;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

import com.revature.controller.MoonController;
import com.revature.controller.PlanetController;
import com.revature.controller.UserController;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.MoonDao;
import com.revature.repository.PlanetDao;
import com.revature.repository.UserDao;
import com.revature.service.MoonService;
import com.revature.service.PlanetService;
import com.revature.service.UserService;
import com.revature.utilities.ConnectionUtil;

public class MainDriver {

    public static  int loggedInUserId = 0;
    public static Boolean loggenIn = false;

    public static UserDao userDao = new UserDao();
    public static PlanetDao planetDao = new PlanetDao();
    public static MoonDao moonDao = new MoonDao();
    public static UserService userService = new UserService(userDao);
    public static MoonService moonService = new MoonService(moonDao);
    public static PlanetService planetService = new PlanetService(planetDao);
    public static UserController userController = new UserController(userService);
    public static MoonController moonController = new MoonController(moonService);
    public static PlanetController planetController = new PlanetController(planetService);

    public static void main(String[] args) {
        // TODO: implement main method to initialize layers and run the application
        try(Scanner scanner = new Scanner(System.in)){
            boolean userIsActive = true;
            while(userIsActive){
                System.out.print("Enter 1 to register for an account, 2 log in to your account, or 0 to quit: ");
                String userInput = scanner.nextLine();
                if(userInput.equals("1")){
                    System.out.println("You chose to register an account!");
                    System.out.print("Please enter your desired username: ");
                    String potentialUsername = scanner.nextLine();
                    System.out.print("Please enter your desired password: ");
                    String potentialPassword = scanner.nextLine();
                    User potentialUser = new User();
                    potentialUser.setUsername(potentialUsername);
                    potentialUser.setPassword(potentialPassword);
                    userController.register(potentialUser);
                }
                else if(userInput.equals("2")){
                    System.out.println("You chose to log in to an account!");
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    UsernamePasswordAuthentication upa = new UsernamePasswordAuthentication();
                    upa.setUsername(username);
                    upa.setPassword(password);
                    Boolean validCredentials = userController.authenticate(upa);
                    if(validCredentials){
                        loggenIn = true;
                        while(loggenIn){
                            System.out.println("\nUsername: " + upa.getUsername() + "        id: " + loggedInUserId + " \nWelcome to the Planetarium " + upa.getUsername() + "!");
                            System.out.println("You have 11 actions to chose from:" +
                                    "\n1: Get all of your planets\n2: Get a planet by name\n3: Get a planet by id" +
                                    "\n4: Add a planet\n5: Remove a planet(The moons belonging to the planet will be removed as well)" +
                                    "\n6: Get all of your moons\n7: Get all moons belonging to a planet\n8: Get moon by name" +
                                    "\n9: Get moon by id\n10: Add a moon\n11: Remove a moon");
                            System.out.print("Enter the number associated with the action you want to perform or any other key to log out: ");
                            String userChoice = scanner.nextLine();
                            switch(userChoice){
                                case "1":
                                    System.out.println("You chose to get all of your planets");
                                    planetController.getAllPlanets(loggedInUserId);
                                    break;
                                case "2":
                                    System.out.println("You chose to get planet by name");
                                    planetController.getAllPlanets(loggedInUserId);
                                    System.out.print("Enter the name of the planet you want to get: ");
                                    String planetName = scanner.nextLine();
                                    planetController.getPlanetByName(loggedInUserId,planetName);
                                    break;
                                case "3":
                                    System.out.println("You chose to get planet by id");
                                    planetController.getAllPlanets(loggedInUserId);
                                    System.out.print("Enter the planetId of the planet you want to get: ");
                                    String planetId = scanner.nextLine();
                                    planetController.getPlanetByID(loggedInUserId,Integer.parseInt(planetId));
                                    break;
                                case "4":
                                    System.out.println("You chose to add a planet");
                                    System.out.print("Enter the name(less than or 30 characters) of the new planet: ");
                                    String newPlanetName = scanner.nextLine();
                                    Planet newPlanet = new Planet();
                                    newPlanet.setName(newPlanetName);
                                    newPlanet.setOwnerId(loggedInUserId);
                                    planetController.createPlanet(loggedInUserId,newPlanet);
                                    break;
                                case "5":
                                    System.out.println("You chose to remove a planet. Keep in mind all the moons belonging to the planet will be removed as well");
                                    planetController.getAllPlanets(loggedInUserId);
                                    System.out.print("Enter the planetId of the planet you want to remove: ");
                                    planetId = scanner.nextLine();
                                    planetController.deletePlanet(Integer.parseInt(planetId), loggedInUserId);
                                    break;
                                case "6":
                                    System.out.println("You chose to get all of your moons");
                                    moonController.getAllMoons(loggedInUserId);
                                    break;
                                case "7":
                                    System.out.println("You chose to get all of your moons belonging to a planet");
                                    planetController.getAllPlanets(loggedInUserId);
                                    System.out.print("Enter the planetId of the planet you want to get the moons of: ");
                                    planetId = scanner.nextLine();
                                    moonController.getPlanetMoons(Integer.parseInt(planetId),loggedInUserId);

                                    break;
                                case "8":
                                    System.out.println("You chose to get a moon by name");
                                    moonController.getAllMoons(loggedInUserId);
                                    System.out.print("Enter the name of the moon you want to get: ");
                                    String moonName = scanner.nextLine();
                                    moonController.getMoonByName(moonName,loggedInUserId);
                                    break;
                                case "9":
                                    System.out.println("You chose to get a moon by id");
                                    moonController.getAllMoons(loggedInUserId);
                                    System.out.print("Enter the moonId of the moon you want to get: ");
                                    String moonId = scanner.nextLine();
                                    moonController.getMoonById(Integer.parseInt(moonId),loggedInUserId);

                                    break;
                                case "10":
                                    System.out.println("You chose to add a moon");
                                    planetController.getAllPlanets(loggedInUserId);
                                    System.out.print("Enter the planetId of the planet you want to add a moon for: ");
                                    planetId = scanner.nextLine();
                                    System.out.print("Enter the name(less than or 30 characters) of the new moon: ");
                                    moonName = scanner.nextLine();
                                    Moon newMoon = new Moon();
                                    newMoon.setName(moonName);
                                    newMoon.setMyPlanetId(Integer.parseInt(planetId));
                                    moonController.createMoon(loggedInUserId,newMoon);
                                    break;
                                case "11":
                                    System.out.println("You chose to remove a moon");
                                    moonController.getAllMoons(loggedInUserId);
                                    System.out.print("Enter the moonId of the moon you want to remove: ");
                                    moonId =scanner.nextLine();
                                    moonController.deleteMoon(Integer.parseInt(moonId),loggedInUserId);
                                    break;
                                default:
                                    System.out.println("You chose to log out.");
                                    loggenIn = false;
                            }
                        }

                    }
                    else System.out.println("Username/Password combo invalid, please try again");
                }
                else if(userInput.equals("0")){
                    System.out.println("Thank you for visiting!");
                    userIsActive = false;
                }
                else System.out.println("Invalid input, please enter again");
            }
        }
    }

}
