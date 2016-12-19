/*
 *  Liam Armstrong and Jacob D.
 *  Java Quiz GUI
 *  January 2016
 *  
 *  Presents user with questions they selected and takes an answer
 *  Stores whether answer is correct or not and gives results in next GUI
 *  
 *  StartOptions -> Confirmation -> QUIZ -> Results
 */


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Quiz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int i = 0; //uses this for a sudo loop of the whole class

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz frame = new Quiz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Quiz() {
		setTitle("Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//makes a scrollpane for the question area
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 30, 523, 385);
		contentPane.add(scrollPane);
		
		//makes a text area for displaying the question
		JTextArea QuestionArea = new JTextArea();
		QuestionArea.setFont(new Font("Liberation Sans", Font.PLAIN, 16));
		scrollPane.setViewportView(QuestionArea);
		QuestionArea.setEditable(false);
		
		//makes a label for counting which question the user's on
		JLabel Amount = new JLabel((i + 1) + " / " + JavaQuiz.finalList.size());
		Amount.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Amount.setHorizontalAlignment(SwingConstants.RIGHT);
		Amount.setBounds(509, 490, 56, 16);
		contentPane.add(Amount);
		
		//adds first question to text area
		QuestionArea.append(JavaQuiz.finalList.get(i).getQuestion() + "\n\n");
		QuestionArea.append(JavaQuiz.finalList.get(i).getAnswers());
		
		//makes A button
		JButton A = new JButton("A");
		A.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		A.setBounds(25, 455, 100, 25);
		contentPane.add(A);
		
		//adds action listener to a button
		A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the sudo loop has run enough times, close this window and move on to the next
				if (i >= JavaQuiz.finalList.size() - 1) {
					Results.main(null);
					dispose();
				} else {
					//check user answer
					JavaQuiz.CheckAnswer(i, 'A');
					//adds to sudo for loop
					i++;
					//resets and refills the text area
					QuestionArea.setText(null);
					QuestionArea.append(JavaQuiz.finalList.get(i).getQuestion() + "\n\n");
					QuestionArea.append(JavaQuiz.finalList.get(i).getAnswers());
					//adds to the amount label
					Amount.setText((i + 1) + " / " + JavaQuiz.finalList.size());
					//if first time running add an extra too sudo loop (nessesary to sync display and actionlisteners
					if (i == 0) {
						i++;
					}
				}
			}
		});
		
		//make B button
		JButton B = new JButton("B");
		B.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		B.setBounds(135, 455, 100, 25);
		contentPane.add(B);
		
		//adds action listener to a button
		B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the sudo loop has run enough times, close this window and move on to the next
				if (i >= JavaQuiz.finalList.size() - 1) {
					Results.main(null);
					dispose();
				} else {
					//check user answer
					JavaQuiz.CheckAnswer(i, 'B');
					//adds to sudo for loop
					i++;
					//resets and refills the text area
					QuestionArea.setText(null);
					QuestionArea.append(JavaQuiz.finalList.get(i).getQuestion() + "\n\n");
					QuestionArea.append(JavaQuiz.finalList.get(i).getAnswers());
					//adds to the amount label
					Amount.setText((i + 1) + " / " + JavaQuiz.finalList.size());
					//if first time running add an extra too sudo loop (nessesary to sync display and actionlisteners
					if (i == 0) {
						i++;
					}
				}
			}
		});
		
		//creates C button
		JButton C = new JButton("C");
		C.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		C.setBounds(245, 455, 100, 25);
		contentPane.add(C);

		//adds action listener to a button
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the sudo loop has run enough times, close this window and move on to the next
				if (i >= JavaQuiz.finalList.size() - 1) {
					Results.main(null);
					dispose();
				} else {
					//check user answer
					JavaQuiz.CheckAnswer(i, 'C');
					//adds to sudo for loop
					i++;
					//resets and refills the text area
					QuestionArea.setText(null);
					QuestionArea.append(JavaQuiz.finalList.get(i).getQuestion() + "\n\n");
					QuestionArea.append(JavaQuiz.finalList.get(i).getAnswers());
					//adds to the amount label
					Amount.setText((i + 1) + " / " + JavaQuiz.finalList.size());
					//if first time running add an extra too sudo loop (nessesary to sync display and actionlisteners
					if (i == 0) {
						i++;
					}
				}
			}
		});
		
		//creates D button
		JButton D = new JButton("D");
		D.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		D.setBounds(355, 455, 100, 25);
		contentPane.add(D);

		//adds action listener to a button
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the sudo loop has run enough times, close this window and move on to the next
				if (i >= JavaQuiz.finalList.size() - 1) {
					Results.main(null);
					dispose();
				} else {
					//check user answer
					JavaQuiz.CheckAnswer(i, 'D');
					//adds to sudo for loop
					i++;
					//resets and refills the text area
					QuestionArea.setText(null);
					QuestionArea.append(JavaQuiz.finalList.get(i).getQuestion() + "\n\n");
					QuestionArea.append(JavaQuiz.finalList.get(i).getAnswers());
					//adds to the amount label
					Amount.setText((i + 1) + " / " + JavaQuiz.finalList.size());
					//if first time running add an extra too sudo loop (nessesary to sync display and actionlisteners
					if (i == 0) {
						i++;
					}
				}
			}
		});
		
		//make E button
		JButton E = new JButton("E");
		E.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		E.setBounds(465, 455, 100, 25);
		contentPane.add(E);

		//adds action listener to a button
		E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the sudo loop has run enough times, close this window and move on to the next
				if (i >= JavaQuiz.finalList.size() - 1) {
					Results.main(null);
					dispose();
				} else {
					//check user answer
					JavaQuiz.CheckAnswer(i, 'E');
					//adds to sudo for loop
					i++;
					//resets and refills the text area
					QuestionArea.setText(null);
					QuestionArea.append(JavaQuiz.finalList.get(i).getQuestion() + "\n\n");
					QuestionArea.append(JavaQuiz.finalList.get(i).getAnswers());
					//adds to the amount label
					Amount.setText((i + 1) + " / " + JavaQuiz.finalList.size());
					//if first time running add an extra too sudo loop (nessesary to sync display and actionlisteners
					if (i == 0) {
						i++;
					}
				}
			}
		});
	}
}