/*
 *  Liam Armstrong and Jacob D.
 *  Java StartOptions GUI
 *  January 2016
 *  
 *  Offers the user a chance to narrow down imported questions through difficulty or word sort
 *  word sort stacks so search for: loops, for would only add questions with both words
 *  adding a second topic is avalible in next GUI
 *  
 *  STARTOPTIONS -> Confirmation -> Quiz -> Results
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StartOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField WordFilter;
	private String filter = " ";
	private ArrayList<String> filterList = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartOptions frame = new StartOptions();
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
	public StartOptions() {
		setTitle("Start");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// creates title
		JLabel Title = new JLabel("Please Select Your Quiz Options");
		Title.setFont(new Font("Liberation Sans", Font.PLAIN, 21));
		Title.setBounds(57, 13, 346, 50);
		contentPane.add(Title);

		// creates label
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setFont(new Font("Liberation Sans", Font.PLAIN, 16));
		lblDifficulty.setBounds(55, 75, 113, 31);
		contentPane.add(lblDifficulty);

		// creates text field
		WordFilter = new JTextField();
		WordFilter.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		WordFilter.setText("Enter a word filter");
		WordFilter.setBounds(180, 85, 115, 22);
		contentPane.add(WordFilter);
		WordFilter.setColumns(10);

		// adds a listener to clear text on text field when clicked
		WordFilter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				WordFilter.setText("");
			}
		});

		// creates easy check box
		JCheckBox Easy = new JCheckBox("Easy");
		Easy.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Easy.setBounds(55, 110, 113, 25);
		contentPane.add(Easy);

		// creates medium check box
		JCheckBox Medium = new JCheckBox("Medium");
		Medium.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Medium.setBounds(55, 145, 113, 25);
		contentPane.add(Medium);

		// creates Hard check box
		JCheckBox Hard = new JCheckBox("Hard");
		Hard.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Hard.setBounds(55, 180, 113, 25);
		contentPane.add(Hard);

		// creates Next button
		JButton Next = new JButton("Next");
		Next.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		Next.setBounds(339, 218, 81, 22);
		contentPane.add(Next);

		// add the listener to the button to handle the "pressed" event
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Easy.isSelected()) {
					// adds easy questions if selected
					JavaQuiz.DifficultySortList_1.addAll(JavaQuiz.Easy);
				}
				if (Medium.isSelected()) {
					// adds medium questions if selected
					JavaQuiz.DifficultySortList_1.addAll(JavaQuiz.Medium);
				}

				if (Hard.isSelected()) {
					// adds hard questions if selected
					JavaQuiz.DifficultySortList_1.addAll(JavaQuiz.Hard);
				}

				if (!Easy.isSelected() && !Medium.isSelected() && !Hard.isSelected()) {
					// shows error if one check box isn't selected
					final JPanel errorPanel = new JPanel();
					JOptionPane.showMessageDialog(errorPanel, "Please select at least one difficulty", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (filterList != null && !filterList.isEmpty()) {
						// Filters for words entered
						JavaQuiz.wordFilterArray(filterList, JavaQuiz.DifficultySortList_1, JavaQuiz.WordFilterList_2);
					} else {
						// if the user didn't enter a filter, add all words to
						// next array list
						JavaQuiz.WordFilterList_2.addAll(JavaQuiz.DifficultySortList_1);
					}
					// launch next frame
					Confirmation.main(null);
					// close this frame
					dispose();
				}
			}

		});

		// creates button
		JButton FilterButton = new JButton("Go");
		FilterButton.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		FilterButton.setBounds(305, 85, 60, 22);
		contentPane.add(FilterButton);

		// creates text field
		JTextArea FilterList = new JTextArea();
		FilterList.setBounds(180, 126, 185, 79);
		contentPane.add(FilterList);
		FilterList.setEditable(false);

		// adds action listener to button
		FilterButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				filter = WordFilter.getText();// reads in user-entered filter
												// word
				// checks if the user entered a word
				if (filter.equals(null) || filter.equals("Enter a word filter")) {
					final JPanel errorPanel2 = new JPanel();
					JOptionPane.showMessageDialog(errorPanel2, "Please enter a word to filter", "Error",
							JOptionPane.ERROR_MESSAGE);
					// shows an error screen if the user didn't enter a filter
				} else {
					filterList.add(filter);// adds filtered word to array list
											// for filtering later
					FilterList.append(filter + "\n");// Adds filtered word to
														// GUI
					WordFilter.setText("");// Resets Text Field
					FilterButton.setText("And");// Re-Titles the button
				}
			}

		});
	}
}