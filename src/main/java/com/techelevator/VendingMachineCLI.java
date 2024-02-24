package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */

public class VendingMachineCLI {

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	private final Scanner userInput = new Scanner(System.in);

	public void run() {
		// Initialized VendingMachineItems list and filled it from "main.csv" file:
		List<VendingMachineItems> itemList;
		itemList = loadInventory("main.csv");

		// Program runs as long as vending machine is on:
		boolean isOn = true;
		while(isOn) {

		// This will print out for the main menu with 3 options:
		System.out.println("\nMAIN MENU\n" + "(1) Display Vending Machine Items\n" + "(2) Purchase\n" + "(3) Exit");
		System.out.print("Input: ");
		// Store user input:
		String menuInput = userInput.nextLine();

		// Menu Input 1: Display Vending Machine Items:
		if (menuInput.equals("1")) {
			for (VendingMachineItems item : itemList)
				System.out.println(item);
		}

		// Menu Input 2: Display Purchase Options:
		else if (menuInput.equals("2")) {
			// Helps to determine if the next purchase will be discounted:
			int purchaseMade = 0;
			// Store user's current balance (default = 0);
			BigDecimal currentBalance = new BigDecimal(0);
			// Helps the program to run as long as transaction is not complete:
			boolean isTransactionComplete = false;
			while (!isTransactionComplete) {

				// Display Purchase Menu:
				System.out.println("\nPURCHASE MENU\n" + "Current Money Provided: $" + currentBalance + "\n\n" + "(1) Feed Money\n" + "(2) Select Product\n" + "(3) Finish Transaction");
				System.out.print("Input : ");

				// Store user input:
				String subMenuInput = userInput.nextLine();

				// Purchase Menu Input 1 - Feed Money:
				if (subMenuInput.equals("1")) {
					try {
						System.out.print("Enter whole dollar amount: ");

						// Store user input (Only whole dollar amount greater than 0);
						String dollarAmount = userInput.nextLine();

						// Convert user input to BigDecimal:
						int numDollarAmount = Integer.parseInt(dollarAmount);
						BigDecimal bigDollarAmount = new BigDecimal(numDollarAmount);

						// Check if user input is greater than 0:
						if (numDollarAmount >= 0) {
							currentBalance = currentBalance.add(BigDecimal.valueOf(numDollarAmount));
							// Keep logs for the added money and user's current balance:
							logTransaction("FEED MONEY:", bigDollarAmount, currentBalance);
						}
						else System.out.println("\nInvalid Money Input!");
					}
					catch (Exception e) {
						System.out.println("\nInvalid Money Input!");
					}
				}

				// Purchase Menu Input 2 - Select Product:
				else if (subMenuInput.equals("2")) {
					// BOGODO discount declared as BigDecimal:
					BigDecimal discount = new BigDecimal("-1");
					System.out.println("\nChoose a product\n");

					for (VendingMachineItems item : itemList) {
						System.out.println(item);
					}
					System.out.print("\nChoose item by entering the item code: ");

					// Store user input:
					String itemSelection = userInput.nextLine();
					// Make user input all uppercase:
					String itemSelectionUpperCase = itemSelection.toUpperCase();

					// Check if user input is valid:
					boolean itemNotFound = true;

					for (VendingMachineItems currentItem : itemList) {

						// Combine name and code into one string for logs:
						String nameAndCode = currentItem.getItemName() + " " + currentItem.getItemCode();

						// Determines if the item is on discount:
						boolean onDiscount = false;

						// Declare current price:
						BigDecimal currentPrice = new BigDecimal(String.valueOf(currentItem.getItemPrice()));

						// Update current price if it is on discount:
						if (purchaseMade % 2 == 1) {
							onDiscount = true;
							currentPrice = currentItem.getItemPrice().add(discount);
						}

						// Determines if user can afford the item:
						boolean canAfford = false;
						if (currentBalance.compareTo(currentPrice) >= 0) {
							canAfford = true;
						}

						// Helps to see if user have enough balance to purchase:
						if (currentItem.getItemCode().equals((itemSelectionUpperCase)) && canAfford && currentItem.getItemQuantity() > 0) {
							currentBalance = currentBalance.subtract(currentPrice);
							if (onDiscount) {
								System.out.println("\nHere is your discounted " + currentItem.getItemName() + " at $" + (currentItem.getItemPrice().add(discount) + ". Your balance is $" + currentBalance + "."));
							} else {
								System.out.println("\nHere is your " + currentItem.getItemName() + " at $" + currentItem.getItemPrice() + ". Your balance is $" + currentBalance + ".");
								System.out.println("Your next item is eligible for the BOGODO discount!\n");
							}
							if (currentItem.getItemCategory().equals("Munchy")) {
								System.out.println("Crunch Crunch, Yum!");
							} else if (currentItem.getItemCategory().equals("Gum")) {
								System.out.println("Chew Chew, Yum!");
							} else if (currentItem.getItemCategory().equals("Drink")) {
								System.out.println("Glug Glug, Yum!");
							} else if (currentItem.getItemCategory().equals("Candy")) {
								System.out.println("Yummy Yummy, So Sweet!");
							}
							purchaseMade = purchaseMade + 1;
							// This will decrease the stock quantity of purchased item by 1.
							currentItem.decreaseQuantity();
							itemNotFound = false;
							// Keep logs for the transaction made with price:
							logTransaction(nameAndCode, currentPrice, currentBalance);
						}

						// If you don't have enough balance to buy the item but the item is in stock:
						if (currentItem.getItemCode().equals(itemSelectionUpperCase) && currentItem.getItemQuantity() > 0 && !canAfford) {
							System.out.println("\nYou don't have enough funds for " + currentItem.getItemName() + ".");
							itemNotFound = false;
						}
						// If item is out of stock
						else if (currentItem.getItemCode().equals(itemSelectionUpperCase) && currentItem.getItemQuantity() == 0) {
							System.out.println("\n" + currentItem.getItemName() + " are out of stock.");
							itemNotFound = false;
						}
					}
					if (itemNotFound) {
						System.out.println("\nInvalid input, try again!");
					}
				}
				// Purchase Menu Input 3 - Finish Transaction:
				// Change is dispensed and balance is reset to 0, customer returned to "main" menu:
				else if (subMenuInput.equals("3")) {
					System.out.println("Transaction is complete");
					System.out.println(getChange(currentBalance));
					isTransactionComplete = true;
					// Keep logs for the given change and user's remaining balance:
					logTransaction("GIVE CHANGE:", currentBalance, new BigDecimal("0.00"));
				}

				// Check if user input is valid in the purchase menu options:
				else {
					System.out.println("Invalid input!");
				}

			}
		}

		// Exit the program and set everything to default:
		else if (menuInput.equals("3")) {
			System.exit(1);
		}

		// Check if user input is valid in the main menu options:
		else {
			System.out.println("Incorrect input, try again.");
		}

		}
	}

	// Create a list with vending machine items:
	public List<VendingMachineItems> loadInventory(String filename) {
		List<VendingMachineItems> inventory = new ArrayList<>();
		File input = new File(filename);
		if(input.exists() && input.isFile()){
			try(Scanner inputScanner = new Scanner(input)) {
				while (inputScanner.hasNextLine()){
					String currentLine = inputScanner.nextLine();
					String[] splitValue = currentLine.split(",");
					String itemCode = splitValue[0];
					String itemName = splitValue[1];
					BigDecimal itemPrice = new BigDecimal(splitValue[2]);
					String itemCategory = splitValue[3];

					if (itemCategory.equals("Drink")) {
						Drink current = new Drink(itemCode, itemName, itemPrice, 5);
						inventory.add(current);
					}
					else if (itemCategory.equals("Candy")) {
						Candy current = new Candy(itemCode, itemName, itemPrice, 5);
						inventory.add(current);
					}
					else if (itemCategory.equals("Gum")) {
						Gum current = new Gum(itemCode, itemName, itemPrice, 5);
						inventory.add(current);
					}
					else if (itemCategory.equals("Munchy")) {
						Munchy current = new Munchy(itemCode, itemName, itemPrice, 5);
						inventory.add(current);
					}
				}
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return inventory;
	}

	// Method to calculate the remaining change:
	public String getChange(BigDecimal balance){
		BigDecimal res[];
		int quarters;
		int dimes;
		int nickels;
		String change;

		res = balance.divideAndRemainder((new BigDecimal("0.25")));
		quarters = res[0].intValue();
		res = res[1].divideAndRemainder((new BigDecimal("0.1")));
		dimes = res[0].intValue();
		res = res[1].divideAndRemainder((new BigDecimal("0.05")));
		nickels = res[0].intValue();

		change = "Your change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels.";
		return change;
	}

	// Method to keep the logs:
	public void logTransaction(String menu, BigDecimal money1, BigDecimal balance){
		try(PrintWriter logAppend = new PrintWriter(new FileOutputStream("log.txt", true))){
			LocalDateTime currentTime = LocalDateTime.now();
			logAppend.println(currentTime + " " + menu + " " + "$" + money1 + " $" + balance);

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
