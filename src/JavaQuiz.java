
/*
 *  Liam Armstrong and Jacob D.
 *  Java Quiz driver class
 *  January 2016
 *  
 *  Reads in (Line by line) a file of CSVs, and organizes them into Question objects
 *  Organizes Question objects into an ArrayList, then prints them
 *  adds total correct answer and prints when all questions have been asked
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JavaQuiz {
	//Declares a file to read in from
	static File JavaQuiz;
	// makes ArrayLists of objects
	static ArrayList<Question> finalList = new ArrayList<Question>();
	static ArrayList<Question> Easy = new ArrayList<Question>();
	static ArrayList<Question> Medium = new ArrayList<Question>();
	static ArrayList<Question> Hard = new ArrayList<Question>();
	static ArrayList<Question> DifficultySortList_1 = new ArrayList<Question>();
	static ArrayList<Question> WordFilterList_2 = new ArrayList<Question>();
	static int QAmount;
	static int count;// for printing total questions answered
	static int correctAmount;// for printing correct answers
	// Initializes scanner for reading the lines from the file
	static Scanner file;
	// Initializes scanner for counting the lines
	static Scanner counter;

	public static void main(String[] args) {
		FileChooser();
		TryCatch();
		Counter();
		Import();
		StartOptions.main(null);
	}

	@SuppressWarnings("resource")
	public static void TryCatch() {
		try {
			//creates file scanners with delimiter
			file = new Scanner(new BufferedReader(new FileReader(JavaQuiz))).useDelimiter("\\s*,\\s*");
			counter = new Scanner(new BufferedReader(new FileReader(JavaQuiz))).useDelimiter("\\s*,\\s*");
		}

		catch (Exception exception) {
			exception.printStackTrace();// prints error if there is one
			System.out.println("File invalid. Please double check file for errors.");
			System.exit(0);// exits if file is invalid
		}
		//check that file is real
		if (!JavaQuiz.exists() && !JavaQuiz.isDirectory())
		{
			System.out.println("File cannot be found. Please double check file for existance");
			System.exit(0);// exits if file cannot be found
		}
	}

	public static void Counter() {
		int CSVAmount = 0;
		while (counter.hasNext())// for counting the amount of CSVS
		{
			counter.next();// Moves to the next line
			CSVAmount++;// Adds to the CSVs
		}
		QAmount = CSVAmount / 8;// Gets total amount of questions
	}

	public static void wordFilter(String search, ArrayList<Question> src, ArrayList<Question> dst) {
		//runs length of array list
		for (int j = 0; j < src.size(); j++) {
			//if question has word, move to destination array list
			if (src.get(j).getQuestion().contains(search)) {
				dst.add(src.get(j));
			}
		}
	}

	public static void wordFilterArray(ArrayList<String> search, ArrayList<Question> src, ArrayList<Question> dst) {
		//runs for length of filter word list times size of source array
		for (int i = 0; i < search.size(); i++) {
			//runs length of array list
			for (int j = 0; j < src.size(); j++) {
				//if question has word, move to destination array list
				if (src.get(j).getQuestion().contains(search.get(i))) {
					dst.add(src.get(j));
				}
			}
		}
	}

	public static void Import() {
		String tempQuestion;// for temporary storage in importing
		String tempAnswers = null;
		String tempAnsA;// for temporary storage in importing
		String tempAnsB;// for temporary storage in importing
		String tempAnsC;// for temporary storage in importing
		String tempAnsD;// for temporary storage in importing
		String tempAnsE;// for temporary storage in importing
		char tempCorAns;// for temporary storage in importing
		String tempDiffString;// for temporary storage in importing
		int tempDiff;// for temporary storage in importing
		for (int i = 0; i < QAmount; i++) {
			tempQuestion = file.next();// takes in question
			tempAnsA = file.next();
			tempAnsB = file.next();
			tempAnsC = file.next();
			tempAnsD = file.next();
			tempAnsE = file.next();
			// reads in each answer individually and then adds them into one
			// for Questions with less answers, just remove the temporary above
			// and below
			tempAnswers = tempAnsA + "\n" + tempAnsB + "\n" + tempAnsC + "\n" + tempAnsD + "\n" + tempAnsE;
			//turns correct answer into a char for comparison
			tempCorAns = file.next().toUpperCase().charAt(0);
			//takes in difficulty
			tempDiffString = file.next();
			//if a difficulty wasn't entered, default it to 1
			if(tempDiffString == null){
				tempDiff = 1;
			}
			//turns difficulty into an integer
				tempDiff = Integer.parseInt(tempDiffString.trim());
			//depending on difficulty, sorts questions into specific lists
			switch (tempDiff) {
			case 1:
			case 2:
			case 3:
			case 4:
				Easy.add(new MultipleChoice(tempQuestion, tempAnswers, tempCorAns, tempDiff));
				break;
			case 5:
			case 6:
			case 7:
				Medium.add(new MultipleChoice(tempQuestion, tempAnswers, tempCorAns, tempDiff));
				break;
			case 8:
			case 9:
			case 10:
				Hard.add(new MultipleChoice(tempQuestion, tempAnswers, tempCorAns, tempDiff));
				break;
			}
			// puts temporary strings, char and integer and puts into a Question
			// objects, sorted in arrays of difficulty
		}
		//closes scanners now that they're not needed
		file.close();
		counter.close();
	}

	public static void CheckAnswer(int num, char ans) {
		//if the answer was correct, add to count and correctAmount
		if(Character.toLowerCase(ans) == Character.toLowerCase(finalList.get(num).getCorrect())){
			correctAmount++;
			count++;
		}
		//if answer was wrong, just add to count
		else {
			count++;
		}
	}
	
	//prints the answer keys to a file
	public static void PrintQuizAnswers() {
		//Initializes writer 
		BufferedWriter writer = null;
		try {
			//makes the writer in a try catch
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("UnitQuizAnswers.txt"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//prints each answer on one line
		for (int i = 0; i < finalList.size(); i++) {
			try {
				//prints the correct answer
				writer.write(finalList.get(i).getCorrect());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				//prints a new line
				((BufferedWriter) writer).newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//prints the quiz to a file
		public static void PrintQuiz() {
			//Initializes writer 
			BufferedWriter writer = null;
			try {
				//makes the writer in a try catch
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("UnitQuizAnswers.txt"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < finalList.size(); i++) {
				try {
					//prints the question
					writer.write(finalList.get(i).getQuestion());
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					//prints a new line
					((BufferedWriter) writer).newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					//prints the answers
					writer.write(finalList.get(i).getAnswers());
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					//prints a new line
					((BufferedWriter) writer).newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	public static void FileChooser() {
		//Initializes file chooser
		JFileChooser chooser = new JFileChooser();
		//specifies text file
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
		chooser.setFileFilter(filter);
		// opens file chooser
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			//prints name of file
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
		}
		// makes file the chosen one
		JavaQuiz = chooser.getSelectedFile();
	}
}
