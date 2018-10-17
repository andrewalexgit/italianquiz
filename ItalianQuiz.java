import java.io.*;
import java.util.*;
import java.math.*;

class ItalianQuiz {

	public static Scanner input = new Scanner(System.in);
	public static int score = 0;

	public static void main(String[] args) throws IOException {

		boolean isRunning = true;
		Words wordsDatabase = new Words();
		Random rand = new Random();
		System.out.printf("Loaded %d words and definitions !\n", wordsDatabase.getWordCount());
		System.out.printf("%d words are verbs\n", wordsDatabase.getVerbCount());
        int num;
        int count = 0;
		
		do {
			System.out.println();
			System.out.println("`e` for ENG to ITL, `i` for ITL to ENG, `v` for verb drills, `l` load words, `q` to quit");
			switch(input.next()) {
				case "e":
					count++;
					num = rand.nextInt(wordsDatabase.getWordCount());
					askEngToItl(wordsDatabase, num);
				break;

				case "i":
					count++;
					num = rand.nextInt(wordsDatabase.getWordCount());
					askItlToEng(wordsDatabase, num);
				break;

				case "q":
					System.out.printf("You got %d correct out of %d questions\n", score, count);
					isRunning = !isRunning;
				break;

				case "v":
					count++;
					num = rand.nextInt(wordsDatabase.getVerbCount());
					askVerbDrill(wordsDatabase, num, rand.nextInt(3));
				break;

				default:
					System.out.println("Invalid command");
				break;
			}
		} while(isRunning);
	}

	public static void askEngToItl(Words wordsDatabase, int index) throws IOException {
		String question = wordsDatabase.getWordEng(index);
		System.out.printf("What does the word %s mean in Italian? ", question);
		if (wordsDatabase.checkItlToEng(input.next())) {
			System.out.println("Nice job!");
			score++;
		} else {
			System.out.printf("Sorry that is incorrect, the answer is %s \n", wordsDatabase.getWordItl(question));
		}
	}

	public static void askItlToEng(Words wordsDatabase, int index) {
		String question = wordsDatabase.getWordItl(index);
		System.out.printf("What does the word %s mean in English? ", question);
		if (wordsDatabase.checkEngToItl(input.next())) {
			System.out.println("Nice job!");
			score++;
		} else {
			System.out.printf("Sorry that is incorrect, the answer is %s \n", wordsDatabase.getWordEng(question));
		}
	}

	public static void askVerbDrill(Words wordsDatabase, int index, int type) {
		String ans = "";
		String word = wordsDatabase.getVerb(index);
		String question = word;
		word = word.substring(0, (word.length() - 3));
		switch (type) {
			case 0:
				word += "o";
				System.out.println("`I "+ wordsDatabase.getWordEng(question) + "` `io ____`");
				ans = input.next();
			break;

			case 1:
				word += "i";
				System.out.println("`You "+ wordsDatabase.getWordEng(question) + "` `tu ____`");
				ans = input.next();
			break;

			case 2:
				word += "a";
				System.out.println("`He "+ wordsDatabase.getWordEng(question) + "` `lui ____`");
				ans = input.next();
			break;

			case 3:
				word += "a";
				System.out.println("`She "+ wordsDatabase.getWordEng(question) + "` `lei ____`");
				ans = input.next();
			break;

			default:
				System.out.println("Error");
			break;
		}

		if (word.equalsIgnoreCase(ans)) {
			System.out.println("Nice job!");
			score++;
		} else {
			System.out.println("Sorry thats incorrect, the answer is "+ word);
		}
		
	}
}
