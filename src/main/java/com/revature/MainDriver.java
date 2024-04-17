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
                    System.out.println("Please enter your desired username: ");
                    String potentialUsername = scanner.nextLine();
                    System.out.println("Please enter your desired password: ");
                    String potentialPassword = scanner.nextLine();
                    User potentialUser = new User();
                    potentialUser.setUsername(potentialUsername);
                    potentialUser.setPassword(potentialPassword);
                    userController.register(potentialUser);
                }
                else if(userInput.equals("2")){
                    System.out.println("You chose to log in to an account!");
                    System.out.println("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    UsernamePasswordAuthentication upa = new UsernamePasswordAuthentication();
                    upa.setUsername(username);
                    upa.setPassword(password);
                    userController.authenticate(upa);
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
