package com.revature;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

import com.revature.controller.UserController;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import com.revature.utilities.ConnectionUtil;

public class MainDriver {

    public static  int loggedInUserId = 0;
    public static Boolean loggenIn = false;

    public static UserDao userDao = new UserDao();
    public static UserService userService = new UserService(userDao);
    public static UserController userController = new UserController(userService);

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
                            int userChoice = scanner.nextInt();
                            switch(userChoice){
                                case 1:
                                    System.out.println("You chose to get all of your planets");
                                    break;
                                case 2:
                                    System.out.println("You chose to get planet by name");
                                    break;
                                case 3:
                                    System.out.println("You chose to get planet by id");
                                    break;
                                case 4:
                                    System.out.println("You chose to add a planet");
                                    break;
                                case 5:
                                    System.out.println("You chose to remove a planet");
                                    break;
                                case 6:
                                    System.out.println("You chose to get all of your moons");
                                    break;
                                case 7:
                                    System.out.println("You chose to get all of your moons belonging to a planet");
                                    break;
                                case 8:
                                    System.out.println("You chose to get a moon by name");
                                    break;
                                case 9:
                                    System.out.println("You chose to get a moon by id");
                                    break;
                                case 10:
                                    System.out.println("You chose to add a moon");
                                    break;
                                case 11:
                                    System.out.println("You chose to remove a moon");
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
