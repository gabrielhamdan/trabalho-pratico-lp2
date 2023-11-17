package dominio;

import java.util.Scanner;

public class Util {
	private static Scanner scanner = new Scanner(System.in);
	
	public static int digitaInt() {
		return digitaInt("");
	}
	
	public static int digitaInt(String msg) {
		if(!msg.equals(""))
			imprimeMsg(msg);
			
		return scanner.nextInt();
	}

	public static String digitaString() {
		return digitaString("");
	}
	
	public static String digitaString(String msg) {
		if(!msg.equals(""))
			imprimeMsg(msg);
		
		return scanner.nextLine();
	}

	public static float digitaFloat() {
		return digitaFloat("");
	}
	
	public static float digitaFloat(String msg) {
		if(!msg.equals(""))
			imprimeMsg(msg);
		
		return scanner.nextFloat();
	}
	
	public static void imprimeMsg(String msg) {
		System.out.print(msg);
	}
}
