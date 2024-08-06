//getting all needed imports

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class inputTextWindow implements ActionListener{

	//declaring all needed instance variables
	JFrame inputFrame;
	JTextField inputText;
	JButton confirmButton;
	JScrollPane scrollText;
	JLabel inputTextLabel;
	GridBagConstraints gbc = new GridBagConstraints();
	Font niceFont;
	Color backgroundColor, foregroundColor;
	
	//constructor for the input text class
	//has two parameters one for the foreground colour and one for the background colour
	inputTextWindow(Color bg, Color fg){
		
		//object that is created will have the background and foreground colours specified in the constructor
		this.backgroundColor=bg;
		this.foregroundColor=fg;
		
		//creating the frame for this window and setting all necessary properties
		inputFrame = new JFrame("Enter Your Text");
		inputFrame.setSize(750,750);
		inputFrame.setLocationRelativeTo(null);
		inputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//also defining relevant grid bag layout properties
		inputFrame.setLayout(new GridBagLayout());
		inputFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//setting properties for the grid bag constraints which will be used for all elements in the frame
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//creating the font that will be used for all of this window
		//try-catch to ensure no errors while reading the font file
		try {
			
			//setting general parameters for the font
			niceFont = Font.createFont(Font.TRUETYPE_FONT, new File("welcomeFont.ttf")).deriveFont(48f);
			
			//creating the graphics environment and registering the font so it can be used
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("welcomeFont.ttf")));
		}
		
		catch (IOException | FontFormatException e) {
			
		}
		
		//creating a text label which explains the input text
		//setting all relevant properties
		inputTextLabel = new JLabel("Enter Some Text", SwingConstants.CENTER);
		inputTextLabel.setFont(niceFont);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.gridwidth=3;
		inputFrame.add(inputTextLabel,gbc);
		
		//creating a text field which the user can enter text into
		//setting all relevant properties for the text field
		inputText = new JTextField(SwingConstants.CENTER);
		inputText.setBorder(BorderFactory.createLineBorder(foregroundColor, 4));
		inputText.setBackground(backgroundColor);
		inputText.setForeground(foregroundColor);
		inputText.setFont(new Font("SERIF",Font.PLAIN,25));
		inputText.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollText= new JScrollPane(inputText);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.gridwidth=5;
		gbc.gridheight=3;
		inputFrame.add(scrollText,gbc);
		
		//creating a confirm button which the user will press when they have finished typing
		//setting all relevant properties for the button
		confirmButton = new JButton("Make This Easier");
		confirmButton.setFont(niceFont.deriveFont(25f));
		confirmButton.addActionListener(this);
		confirmButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 4));
		confirmButton.setBackground(backgroundColor);
		confirmButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=2;
		gbc.gridy=5;
		gbc.gridheight=1;
		gbc.gridwidth=3;
		gbc.insets= new Insets(10,10,10,10);
		inputFrame.add(confirmButton,gbc);
		
		//placing all needed spacers for the grid bag layout
		insertSquareSpacers(7);
		
		//set the frame to be visible
		inputFrame.setVisible(true);
		
	}
	
	//action listener that will look for when a button is pressed
	public void actionPerformed(ActionEvent e) {
		
		//if the confirm button is pressed
		if (e.getSource()==confirmButton) {
			
			//the frame will disappear and the output frame will appear
			inputFrame.setVisible(false);
			outputTextWindow runOutputWindow = new outputTextWindow(inputText.getText(), backgroundColor, foregroundColor);
		}
	}
	
	//method that places the spacers in a diagonal like fashion
	public void insertSquareSpacers(int sideLength) {
		
		//loops so that a panel is placed so that there will be at least one element in every column and row
		for(int i = 0; i<sideLength; i++) {
			gbc.gridx=i;
			gbc.gridy=i;
			gbc.gridwidth =1;
			gbc.gridheight =1;
			inputFrame.add(new JLabel(), gbc);
		}
	}
	
}
