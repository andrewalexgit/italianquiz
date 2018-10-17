import java.io.*;
import java.util.*;

public class Words {

	private File FILE;
	private Scanner sc;
	private HashMap<String, String> wordsAndDef = new HashMap<String, String>();
	private List<String> keys;
	private List<String> verbs = new ArrayList<>();
	private int numWords;
	private int verbCount;
	
	Words(String customFile) throws IOException {
		FILE = new File(customFile);
		sc = new Scanner(FILE);
		numWords = 0;
		verbCount = 0;
		while(sc.hasNext()) {
			wordsAndDef.put(sc.next(), sc.next());
			numWords++;
		}

		keys = new ArrayList<>(wordsAndDef.keySet());

		for (String s : keys) {
			if (s.substring((s.length() - 3), s.length()).equals("ere") || 
				s.substring((s.length() - 3), s.length()).equals("are") ||
				s.substring((s.length() - 3), s.length()).equals("ire")) {
				verbs.add(s);
				verbCount++;
			}
		}
	}

	Words() throws IOException {
		FILE = new File("words.text");
		sc = new Scanner(FILE);
		numWords = 0;
		verbCount = 0;
		while(sc.hasNext()) {
			wordsAndDef.put(sc.next(), sc.next());
			numWords++;
		}

		keys = new ArrayList<>(wordsAndDef.keySet());

		for (String s : keys) {
			if (s.substring((s.length() - 3), s.length()).equals("ere") || 
				s.substring((s.length() - 3), s.length()).equals("are") ||
				s.substring((s.length() - 3), s.length()).equals("ire")) {
				verbs.add(s);
				verbCount++;
			}
		}
	}

	public int getWordCount() {
		return numWords;
	}

	public int getVerbCount() {
		return verbCount;
	}

	public String getDefinition(String word) {
		return wordsAndDef.get(word);
	}

	public boolean checkEngToItl(String word) {

		boolean isFound = false;

		for (String s : keys) {
			if (wordsAndDef.get(s).equals(word)) {
				isFound = true;
			}
		}

		return isFound;

	}

	public boolean checkItlToEng(String word) {

		if (wordsAndDef.get(word) != null) {
			return true;
		} else {
			return false;
		}
	}

	public String getWordEng(int index) {
		return wordsAndDef.get(keys.get(index));
	}

	public String getWordItl(int index) {
		return keys.get(index);
	}

	public String getWordEng(String word) {
		return wordsAndDef.get(word);
	}

	public String getWordItl(String word) {

		String t = "Not found";
		
		for (String s : keys) {
			if (wordsAndDef.get(s).equals(word)) {
				t = s;
			}
		}

		return t;

	}

	public String getVerb(int index) {
		return verbs.get(index);
	}

}