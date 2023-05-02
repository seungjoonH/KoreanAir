package view;

import java.util.Scanner;

public class Page {
	public static String prompt(String string) {
		System.out.print("\n " + string + " > ");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
}
