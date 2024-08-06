
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

public class foregroundBackgroundWindow implements ActionListener{

	//declaring all needed instance variables
	JFrame optionFrame;
	JButton foregroundButton, backgroundButton, backButton;
	GridBagConstraints gbc = new GridBagConstraints();
	Color foregroundColor, backgroundColor;
	String textHolder;
	Font dyslexicFont;

	//constructor for the colour settings class
	//has three parameters one for the foreground colour, one for the background colour, and one for the current text in the program
	foregroundBackgroundWindow(String s, Color bg, Color fg){
		
		//object that is created will have the background and foreground colours specified in the constructor as well as the current text
		this.foregroundColor=fg;
		this.backgroundColor=bg;
		this.textHolder=s;
		
		//creating the font that will be used for all of this window
		//try-catch to ensure no errors while reading the font file
		try {
			
			//setting general parameters for the font
			dyslexicFont = Font.createFont(Font.TRUETYPE_FONT, new File("DyslexicFont.otf")).deriveFont(50f);
			
			//creating the graphics environment and registering the font so it can be used
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("DyslexicFont.otf")));
		}
		
		catch (IOException | FontFormatException e) {
			
		}
		
		//creating the frame for this window and setting all necessary properties
		optionFrame = new JFrame("Change foreground or background!");
		optionFrame.setSize(750,750);
		optionFrame.setLocationRelativeTo(null);
		optionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		optionFrame.setLayout(new GridBagLayout());
		
		//also defining relevant grid bag layout properties
		optionFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		optionFrame.getContentPane().setBackground(backgroundColor);
		
		//setting properties for the grid bag constraints which will be used for all elements in the frame
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//creating a button that will open the colour options window for the foreground
		//setting all relevant properties of the button
		foregroundButton = new JButton("Change Foreground");
		foregroundButton.addActionListener(this);
		foregroundButton.setFont(dyslexicFont.deriveFont(12f));
		foregroundButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		foregroundButton.setBackground(backgroundColor);
		foregroundButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.insets= new Insets(10,10,10,10);
		optionFrame.add(foregroundButton, gbc);
		
		//creating a back button that will head to the colour palette window when pressed
		//setting all relevant properties for the back button
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		backButton.setFont(dyslexicFont.deriveFont(12f));
		backButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		backButton.setBackground(backgroundColor);
		backButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.insets= new Insets(10,10,10,10);
		optionFrame.add(backButton, gbc);
		
		//creating a button that will open the colour options window for the background
		//setting all relevant properties of the button
		backgroundButton = new JButton("Change Background");
		backgroundButton.addActionListener(this);
		backgroundButton.setFont(dyslexicFont.deriveFont(12f));
		backgroundButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		backgroundButton.setBackground(backgroundColor);
		backgroundButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=3;
		gbc.gridy=2;
		gbc.insets= new Insets(10,10,10,10);
		optionFrame.add(backgroundButton, gbc);
		
		//adding all the needed spacers to ensure a nice looking GUI
		insertSquareSpacers(5);
		
		//setting the window to be visible 
		optionFrame.setVisible(true);
	}

	@Override
	
	//action listener that will look for when a button is pressed
	public void actionPerformed(ActionEvent e) {
		
		//if the back button is pressed
		if(e.getSource()==backButton) {
			
			//this window will disappear and the colour palette window will appear
			optionFrame.setVisible(false);
			recColourPalette run = new recColourPalette(textHolder, backgroundColor, foregroundColor);
		}
		
		//if the background button is pressed
		if(e.getSource()==backgroundButton) {
			
			//this window will disappear and the background colour settings window will appear
			optionFrame.setVisible(false);
			ColourSettingsWindow run = new ColourSettingsWindow(textHolder, backgroundColor, foregroundColor, false);
		}
		
		//if the foreground button is pressed
		if(e.getSource()==foregroundButton) {
			
			//this window will disappear and the foreground colour settings window will appear
			optionFrame.setVisible(false);
			ColourSettingsWindow run = new ColourSettingsWindow(textHolder, backgroundColor, foregroundColor, true);
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
			optionFrame.add(new JLabel(), gbc);
		}
	}

	}
