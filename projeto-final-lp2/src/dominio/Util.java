package dominio;

import java.util.Scanner;

public class Util {
	private static Scanner scanner = new Scanner(System.in);
	
	private static void consomeChar() {
		scanner.nextLine();
	}
	
	public static int digitaInt() {
		return digitaInt("");
	}
	
	public static int digitaInt(String msg) {
		imprimeMsg(msg);
			
		int i = scanner.nextInt();
		
		consomeChar();
		
		return i;
	}

	public static String digitaString() {
		return digitaString("");
	}
	
	public static String digitaString(String msg) {
		imprimeMsg(msg);
		
		return scanner.nextLine();
	}

	public static float digitaFloat() {
		return digitaFloat("");
	}
	
	public static float digitaFloat(String msg) {
		imprimeMsg(msg);
		
		float f = scanner.nextFloat();
		
		consomeChar();
		
		return f;
	}
	
	public static void imprimeMsg(String msg) {
		imprimeMsg(msg, false);
	}
	
	public static void imprimeMsg(String msg, boolean novaLinha) {
		if(msg.equals(""))
			return;
		
		if(novaLinha)
			System.out.println(msg);
		else
			System.out.print(msg);
	}
}
