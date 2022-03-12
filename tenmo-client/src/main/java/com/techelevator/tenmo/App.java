package com.techelevator.tenmo;

import java.util.Scanner;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountService accountService = new AccountService(API_BASE_URL);
    private final TransferService transferService = new TransferService(API_BASE_URL);
    private final UserService userService = new UserService(API_BASE_URL + "tenmo_user");
    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
		// TODO Auto-generated method stub
        BigDecimal balance = accountService.getBalance(currentUser);
        System.out.println("Your current balance is: " + balance);
	}

	private void viewTransferHistory() {
        Transfer[] transferArray = transferService.getTransfers(currentUser);
        List<Transfer> transferList = new ArrayList<>();
        Transfer transfer = new Transfer();
        for(int i = 0; i < transferArray.length; i++) {
            transferList.add(transferArray[i]);
        }
        System.out.println("Here are your recorded transfers:\n" + transferList);
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub

	}

    @RequestMapping
	private void sendBucks() {
        User[] userArray = userService.getUsers(currentUser);
        List<User> userList = new ArrayList<>();
        User user = new User();
        for(int i = 0; i < userArray.length; i++) {
            userList.add(userArray[i]);
        }
        System.out.println(userList);
        int accountTo = (consoleService.promptForInt("Please enter the user you are sending money to: "));
        User userTo = (userArray[accountTo]);
        BigDecimal amountSent = (consoleService.promptForBigDecimal("Please enter the amount you wish to send: "));
		transferService.transferSend(currentUser, userTo, amountSent);
		
	}

	private void requestBucks() {
		// TODO Auto-generated method stub

	}

}
