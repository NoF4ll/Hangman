import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class hangmanMethoden {
	static int tries = 0;

	static int getTries() {
		return tries;
	}
	
	public static void schwierigkeit() {
		Scanner sc = new Scanner(System.in);
		boolean ok = true;
		int pick = 0;
		while (ok) {
			System.out.println("Welchen Schwierigkeitsgrad möchten sie wählen?");
			System.out.println("Wähle eine Zahl zwischen 1 ...3");
			pick = sc.nextInt();

			if (pick == 1) {
				tries = 5;
				System.out.println("Schwierigkeitsgrad schwer gewählt Sie haben " + tries + " versuche!");
				ok = false;

			}
			if (pick == 2) {
				tries = 10;
				System.out.println("Schwierigkeitsgrad mittel gewählt Sie haben " + tries + " versuche!");
				ok = false;
			}
			if (pick == 3) {
				tries = 15;
				System.out.println("Schwierigkeitsgrad leicht gewählt Sie haben " + tries + " versuche!");
				ok = false;
			} 
			sc.close();
		}
	}
	public static void gamePlay(int tries) {

		int answers = 0;
		int win = 0;
		int counter = 0;
		String zeile = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader("hangmanWort.txt"));
			zeile = in.readLine();
			in.close();
		} catch (Exception e) {
		}

		for (int i = 0; i < zeile.length(); i++) {
			System.out.print("_");
		}
		char guessing[] = zeile.toCharArray();
		char letters[] = new char[guessing.length];

		for (int i = 0; i < letters.length; i++) {
			letters[i] = 0;
		}

		Scanner sc = new Scanner(System.in);

		while (answers < guessing.length) {
			String s = sc.nextLine();
			char word = s.charAt(0);
			for (int i = 0; i < guessing.length; i++) {
				if ((Character.toLowerCase(guessing[i]) == word) && letters[i] == 0) {
					letters[i] = word;
					counter++;
					answers++;
				}
			}
			if (counter < 1) {
				tries--;
				System.out.println(tries + " lives left");
			}
			if (tries == 0) {
				System.out.println("You lost");
				break;
			}
			counter = 0;
			for (int i = 0; i < letters.length; i++) {
				if (letters[i] == 0) {
					System.out.print("_");
				} else {
					System.out.print(letters[i]);
				}
			}
			System.out.println();
		}
		sc.close();
		for (int i = 0; i < letters.length; i++) {
			if (Character.toLowerCase(letters[i]) == Character.toLowerCase(guessing[i])) {
				win++;
			}
		}
		if (win == letters.length) {
			System.out.println("You won");
		}
	}
}
