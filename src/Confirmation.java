/*
 *  Liam Armstrong and Jacob D.
 *  Java Confirmation GUI
 *  January 2016
 *  
 *  Presents user with a list of questions selected based on their criteria
 *  User then selects the question's they'd like to be asked, as well as are allowed to add one more topic
 *  to the list of selectable question
 *  
 *  StartOptions -> CONFIRMATION -> Quiz -> Results
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Confirmation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField WordFilter;
	private String filter; 
	private final JScrollPane scrollPane = new JScrollPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirmation frame = new Confirmation();
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
	public Confirmation() {
		setTitle("Confirmation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//makes JPanels for organizing
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(201, 13, 420, 75);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBounds(428, 684, 432, 56);
		contentPane.add(ButtonPanel);
		ButtonPanel.setLayout(null);
		
		//Sets title
		JLabel titleLabel = new JLabel("Please Select your Questions");
		titleLabel.setBounds(86, 5, 250, 23);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 19));
		titlePanel.add(titleLabel);
		
		//Makes a text field for user entered text
		WordFilter = new JTextField();
		WordFilter.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		WordFilter.setHorizontalAlignment(SwingConstants.CENTER);
		WordFilter.setText("Topic");
		WordFilter.setBounds(108, 45, 116, 23);
		titlePanel.add(WordFilter);
		WordFilter.setColumns(10);
		
		//adds a listener to clear text on text field when clicked
		WordFilter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				WordFilter.setText("");
			}
		});
		
		//makes a button
		JButton AddButton = new JButton("Add");
		AddButton.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		AddButton.setBounds(239, 45, 97, 23);
		titlePanel.add(AddButton);
		
		//makes last button
		JButton TakeQuiz = new JButton("Take Quiz");
		TakeQuiz.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		TakeQuiz.setBounds(323, 13, 97, 25);
		ButtonPanel.add(TakeQuiz);
		scrollPane.setBounds(42, 105, 796, 566);
		contentPane.add(scrollPane);
		
		//makes a list model to fill the JList
		DefaultListModel<String> Model = new DefaultListModel<String>();
		for(int i = 0; i < JavaQuiz.WordFilterList_2.size(); i++)
		{
			Model.addElement(JavaQuiz.WordFilterList_2.get(i).getQuestion());
		}
		
		//makes a list of Questions for confirmation
		JList<String> QuestionList = new JList<String>(Model);
		QuestionList.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		scrollPane.setViewportView(QuestionList);
		
		// adds action listener to AddButton
		AddButton.addActionListener(new ActionListener() { // adds action listener to AddButton
			public void actionPerformed(ActionEvent e) {
				filter = WordFilter.getText();//reads in user-entered filter word
				if (filter.equals(null) || filter.equals("Enter a word filter")) {//checks the user entered a filter word
					final JPanel errorPanel2 = new JPanel();
					JOptionPane.showMessageDialog(errorPanel2, "Please enter a word to filter", "Error",
							JOptionPane.ERROR_MESSAGE); // returns error screen if user didn't enter a filter 
				} else {
					JavaQuiz.wordFilter(filter, JavaQuiz.DifficultySortList_1, JavaQuiz.WordFilterList_2);//adds new set of questions into pool based on topic word
					AddButton.setEnabled(false);//disables button after one use
					WordFilter.setEnabled(false);//disables text field after one use
					Model.clear();
					for(int i = 0; i < JavaQuiz.WordFilterList_2.size(); i++)
					{
						Model.addElement(JavaQuiz.WordFilterList_2.get(i).getQuestion());
					}
				}

		}});
		//adds action listener to bottom button
		TakeQuiz.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				//when button is pressed move all confirmed question to final ArrayList
				for(int i = 0; i < QuestionList.getSelectedIndices().length; i++)
				{
					JavaQuiz.finalList.add(JavaQuiz.WordFilterList_2.get(QuestionList.getSelectedIndices()[i]));
				}
				//Closes this window and shows the next one
				Quiz.main(null);
				dispose();
			}

		});
	}
}
