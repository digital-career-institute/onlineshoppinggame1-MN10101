package onlineshoppinggame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define the abstract class VirtualItem
abstract class VirtualItem {
	int itemID;
	String name;
	double price;
	double discountedPrice;
	int points;

	// Constructor for initializing attributes
	VirtualItem(int itemID, String name, double price, double discountedPrice, int points) {
		this.itemID = itemID;
		this.name = name;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.points = points;
	}

	// Override toString method to provide formatted string representation
	@Override
	public String toString() {
		return String.format("ID: %d, Name: %s, Price: %.2f, Discounted Price: %.2f, Points: %d", itemID, name, price,
				discountedPrice, points);
	}
}

// Define the PowerUp subclass
class PowerUp extends VirtualItem {
	String effect;

	// Constructor for PowerUp
	PowerUp(int itemID, String name, double price, double discountedPrice, int points, String effect) {
		super(itemID, name, price, discountedPrice, points);
		this.effect = effect;
	}

	// Override toString method to include PowerUp-specific information
	@Override
	public String toString() {
		return super.toString() + ", Effect: " + effect;
	}
}

// Define the Decoration subclass
class Decoration extends VirtualItem {
	String theme;

	// Constructor for Decoration
	Decoration(int itemID, String name, double price, double discountedPrice, int points, String theme) {
		super(itemID, name, price, discountedPrice, points);
		this.theme = theme;
	}

	// Override toString method to include Decoration-specific information
	@Override
	public String toString() {
		return super.toString() + ", Theme: " + theme;
	}
}

// Main class for the shopping game
public class ShoppingGame {
	public static void main(String[] args) {
		// Initialize the cart and points
		List<VirtualItem> cart = new ArrayList<>();
		int points = 100;
		
		// Main game loop
		while (true) {
			menu(); 
			int select = input("Enter your choice: "); 
			if (select == 5)
				break; 
			VirtualItem item = select <= 3 ? getItem(select) : null;
			if (item != null && points >= item.price) {
				cart.add(item);
				points -= item.price;
				System.out.println("Item added to the cart!"); 
			} else if (item != null){
				System.out.println("Not enough points. Please Try again."); 
																			
			}
		}
		displayCart(cart); 
		displayPoints(cart); 
		applyDiscount(cart); 
		displayRemainingPoints(points); 
	}

	// Display the shopping menu
	private static void menu() {
		System.out.println(
				"Shopping Menu:\n1. Buy PowerUp\n2. Buy Decoration\n3. Display Shopping Cart\n4. Apply Discount\n5. Quit");
	}

	// Get user input
	private static int input(String p) {
		System.out.print(p);
		return new Scanner(System.in).nextInt();
	}

	// Get the selected item
	private static VirtualItem getItem(int select) {
		return select == 1 ? new PowerUp(1, "Double Points PowerUp", 20.0, 0, 0, "Double Points")
				: new Decoration(2, "Space Theme Decoration", 15.0, 0, 5, "Space");
	}

	// Display the shopping cart
	private static void displayCart(List<VirtualItem> cart) {
		System.out.println("Shopping Cart:");
		cart.forEach(item -> System.out.println(item + ", Discounted Price: " + item.discountedPrice));
	}

	// Display the total points earned
	private static void displayPoints(List<VirtualItem> cart) {
		System.out.println("Total Points Earned: " + cart.stream().mapToInt(item -> item.points).sum() + " P");
	}

	// Apply discount to the cart
	private static void applyDiscount(List<VirtualItem> cart) {
		double discount = cart.size() >= 3 ? 0.1 : 0.0;
		cart.forEach(item -> item.discountedPrice = item.price * (1 - discount));
		System.out.println("Discount Applied: " + (discount * 100) + "%");
	}

	// Display the remaining points
	private static void displayRemainingPoints(int points) {
		System.out.println("Remaining Points: " + points + " P");
	}
}
