import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;

public class GUI implements ActionListener{

	private JFrame frame;
	private JPanel panel;

	// Problem 5 objects
	private JButton problem5Button;
	private JLabel problem5Label;
	private JTextArea problem5TextArea;

	// Problem 6 objects
	private JLabel problem6Label;
	private JTextArea problem6TextArea1;
	private JTextArea problem6TextArea2;
	private JTextArea problem6TextArea3;
	private JButton problem6Button;
	private JTextArea problem6TextAreaOutput;

	// Problem 7 objects
	private JLabel problem7Label;
	private JButton problem7Button;
	private JTextArea problem7TextArea;

	// Problem 8 objects (need SQL code)
	private JLabel problem8Label;
	private JButton problem8Button;
	private JTextArea problem8TextArea;

	// Problem 9 objects
	private JLabel problem9Label;
	private JButton problem9Button;
	private JTextArea problem9TextArea;

	// Insert objects
	private JLabel insertLabel;
	private JButton insertButton;
	private JTextArea insertTextMovieNameScore;
	private JTextArea insertTextMovieCast;

	// Output Box
	private JTextArea outputBox;
	private JLabel outputLabel;
	private JScrollPane outputsbr;

	public GUI() {
		// Create frame
		frame = new JFrame();

		// Create panel
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		//Insets (spacing between elements)
		c.insets = new Insets(3,3,3,3);

		//Create the master output text box
		outputBox = new JTextArea("This is the Results Box!", 10, 30);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.gridheight = 4; //Make the results box taller without stretching y grids

		//adds a permanent vertical scrollbar to the text box
		outputBox.setLineWrap(true);
		outputsbr = new JScrollPane(outputBox);
		outputsbr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(outputsbr, c);

		//Reset gridheight for all other boxes to 1
		c.gridheight = 1;

		//Problem 5 objects
		problem5Label = new JLabel("Get highest movie score with actors name (Use the Results box!)");
		problem5Label.setHorizontalAlignment(SwingConstants.CENTER);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(problem5Label, c);

		problem5Button = new JButton("Execute");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		problem5Button.addActionListener(this);
		panel.add(problem5Button, c);

		// Problem 6 objects
		problem6Label = new JLabel("Find movies with key word(s)");
		problem6Label.setHorizontalAlignment(SwingConstants.CENTER);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(problem6Label, c);

		problem6Button = new JButton("Execute");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		problem6Button.addActionListener(this);
		panel.add(problem6Button, c);

		//Create 3 text areas for user input
		problem6TextArea1 = new JTextArea(2, 10);
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		panel.add(problem6TextArea1, c);
		problem6TextArea1.setText("");

		problem6TextArea2 = new JTextArea(2, 10);
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 7;
		panel.add(problem6TextArea2, c);
		problem6TextArea2.setText("");

		problem6TextArea3 = new JTextArea(2, 10);
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 8;
		panel.add(problem6TextArea3, c);
		problem6TextArea3.setText("");


		// Problem 7 objects
		problem7Label = new JLabel("Find 10 most popular actors");
		problem7Label.setHorizontalAlignment(SwingConstants.CENTER);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(problem7Label, c);

		problem7Button = new JButton("Execute");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		problem7Button.addActionListener(this);
		panel.add(problem7Button, c);

		// Problem 8 objects
		problem8Label = new JLabel("Find group of actors who have been in the most movies together");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(problem8Label, c);

		problem8Button = new JButton("Execute");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		problem8Button.addActionListener(this);
		panel.add(problem8Button, c);

		// Problem 9 objects
		problem9Label = new JLabel("Find lowest scoring movie");
		problem9Label.setHorizontalAlignment(SwingConstants.CENTER);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(problem9Label, c);

		problem9Button = new JButton("Execute");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		problem9Button.addActionListener(this);
		panel.add(problem9Button, c);

		// Insert objects
		insertLabel = new JLabel("Insert CSV files");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		insertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(insertLabel, c);

		insertButton = new JButton("Insert");
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		insertButton.addActionListener(this);
		panel.add(insertButton, c);

		insertTextMovieNameScore = new JTextArea("Provide path to movie-name-score.txt", 2, 10);
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		panel.add(insertTextMovieNameScore, c);

		insertTextMovieCast = new JTextArea("Provide path to movie-cast.txt", 2, 10);
		c.weightx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		panel.add(insertTextMovieCast, c);

		// Add panel to frame and display
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Databases Group Project");
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Build GUI
		new GUI();
	}

	//Execute button action events
	@Override
	public void actionPerformed(ActionEvent e) {
		// Problem 5
		// Select highest movie score with actors name
		if(e.getSource()==problem5Button) {

			String output = "";
			String input = outputBox.getText();
			if(input != "") {
				try {
					output = ConnectDB.Select5(input);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

				if(output != "") {
					outputBox.setText(output);
				}
				else {
					outputBox.setText("No results.");
				}
			}
			else {
				outputBox.setText("Please enter an actors name before execution.");
			}
		}
		// Problem 6
		if(e.getSource()==problem6Button) {
			// Get and check user input
			String output = "";
			String keyword1 = problem6TextArea1.getText().trim();
			String keyword2 = problem6TextArea2.getText().trim();
			String keyword3 = problem6TextArea3.getText().trim();

			try {
				output = ConnectDB.Select6(keyword1, keyword2, keyword3);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			// Print results
			outputBox.setText(output);
		}
		// Problem 7
		if(e.getSource()==problem7Button) {
			String output = "";

			try {
				output = ConnectDB.Select7();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			// Print results
			outputBox.setText(output);
		}

		// Problem 8
		if(e.getSource()==problem8Button) {
			String output = "";

			try {
				output = ConnectDB.Select8();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Print results
			outputBox.setText(output);
		}

		// Problem 9
		if(e.getSource()==problem9Button) {
			String output = "";

			try {
				output = ConnectDB.Select9();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Print results
			outputBox.setText(output);
		}
		if(e.getSource()==insertButton) {
			String pathToMNS = insertTextMovieNameScore.getText();
			String pathToMC = insertTextMovieCast.getText();
			try {
				ConnectDB.InsertTextFiles(pathToMNS, pathToMC);
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
