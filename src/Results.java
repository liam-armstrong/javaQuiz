/*
 *  Liam Armstrong and Jacob D.
 *  Java Quiz Results GUI
 *  January 2016
 *  
 *  The last of four, shows results and presents user with option to finish, retry, or print quiz to file
 *  
 *  StartOptions -> Confirmation -> Quiz -> RESULTS
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Results extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Results frame = new Results();
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
	public Results() {
		setTitle("Results");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//creates title
		JLabel Title = new JLabel("You Scored:");
		Title.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setIcon(null);
		Title.setBounds(130, 30, 150, 42);
		contentPane.add(Title);
		
		//creates print button
		JButton Print = new JButton("Print Quiz and Answers");
		Print.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Print.setBounds(20, 205, 165, 25);
		contentPane.add(Print);
		
		//prints quiz and answers to file
		Print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JavaQuiz.PrintQuizAnswers();
				JavaQuiz.PrintQuiz();
				//displays confirmation dialog box
				JOptionPane.showMessageDialog(null, "Text files are saved to the Eclipse Workspace");
			}
		});
		
		//creates finish button
		JButton Finish = new JButton("Finish");
		Finish.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Finish.setBounds(315, 205, 97, 25);
		contentPane.add(Finish);
		
		//adds action listener to finish button
		Finish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//when pressed, close window and system
				dispose();
				System.exit(0);
			}
		});
		
		//creates results label with results
		JLabel Results = new JLabel((JavaQuiz.correctAmount + 1) + " / " + (JavaQuiz.count + 1));
		Results.setHorizontalAlignment(SwingConstants.CENTER);
		Results.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
		Results.setBounds(130, 105, 150, 42);
		contentPane.add(Results);
		
		//creates retry button
		JButton Retry = new JButton("Retry");
		Retry.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Retry.setBounds(206, 205, 97, 25);
		contentPane.add(Retry);
		
		//adds action listener to retry button
		Retry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//relaunchs quiz GUI, closes, and resets counts
				Quiz.main(null);
				dispose();
				JavaQuiz.correctAmount = 0;
				JavaQuiz.count = 0;
			}
		});
	}

}
