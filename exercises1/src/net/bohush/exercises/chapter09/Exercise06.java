package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise06 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String string = input.next();
		System.out.print(countLetters(string) + " letters in string.");

	}

	public static int countLetters(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isAlphabetic(str.charAt(i))) {
				count++;
			}
		}
		return count;
	}
}
