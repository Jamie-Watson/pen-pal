
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class recColourPalette implements ActionListener{

	//declaring all needed instance variables for this class
	JFrame colourWindow;
	GridBagConstraints gbc = new GridBagConstraints();
	Color colourOptions[][] = {{Color.BLACK, new Color(231,226,220)}, {new Color(231,226,220), new Color(177,159,199)}, {new Color(231,226,220), new Color(112,149,61)},
			{new Color(231,226,220), new Color(73,93,104)}, {new Color(231,226,220), new Color(48,52,48)}, {new Color(231,226,220), new Color(77,57,48)}};
	JButton colours[];
	JButton backButton, customButton;
	String textHolder;
	Color foregroundColor;
	Color backgroundColor;
	Font dyslexicFont;
	
	//constructor for the colour palette class
	//has three parameters one for the foreground colour, one for the background colour, and one for the current text in the program
	recColourPalette(String s, Color bg, Color fg){
		
		//object that is created will have the background and foreground colours specified in the constructor as well as the current text
		this.foregroundColor = fg;
		this.backgroundColor = bg;
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
		colourWindow = new JFrame("Use some of our palettes!");
		colourWindow.setSize(750,750);
		colourWindow.setLocationRelativeTo(null);
		colourWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		colourWindow.getContentPane().setBackground(backgroundColor);
		
		//also defining relevant grid bag layout properties
		colourWindow.setLayout(new GridBagLayout());
		colourWindow.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//setting properties for the grid bag constraints which will be used for all elements in the frame
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//creating a button that will head back to the output window when pressed
		//setting all relevant properties for the button
		backButton=new JButton("Back");
		backButton.addActionListener(this);
		backButton.setFont(dyslexicFont.deriveFont(12f));
		backButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		backButton.setBackground(backgroundColor);
		backButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.gridwidth=2;
		gbc.gridheight=2;
		gbc.insets=new Insets(10,10,10,10);
		colourWindow.add(backButton, gbc);
		
		//creating a button that will head to the custom colour window when pressed
		//setting all relevant properties for the custom colour button
		customButton=new JButton("Custom");
		customButton.addActionListener(this);
		customButton.setFont(dyslexicFont.deriveFont(12f));
		customButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		customButton.setBackground(backgroundColor);
		customButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=5;
		gbc.gridy=0;
		gbc.gridwidth=2;
		gbc.gridheight=2;
		gbc.insets=new Insets(10,10,10,10);
		colourWindow.add(customButton, gbc);
		
		//creating six new buttons that will change the colour scheme to one of the preset colour palette
		colours=new JButton[6];
		
		//creating some integer variables that will help with the placement of the siz buttons
		int xcoord=0;
		int ycoord=3;
		
		//creating a for loop that will make all of the buttons and place them correctly
		for(int i=0;i<6;i++) {
			
			//setting all relevant properties for all six buttons
			colours[i]= new JButton("Palette " + (i+1));
			colours[i].addActionListener(this);
			colours[i].setForeground(colourOptions[i][0]);
			colours[i].setBackground(colourOptions[i][1]);
			colours[i].setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
			
			//setting all grid bag properties and adding it to the frame
			gbc.gridx=xcoord;
			gbc.gridy=ycoord;
			gbc.gridwidth=3;
			gbc.gridheight=2;
			gbc.insets=new Insets(10,10,10,10);
			colourWindow.add(colours[i], gbc);
			
			//changing the x and y coordinates when necessary to ensure the buttons are placed correctly
			xcoord+=3;
			
			if(xcoord>6) {
				xcoord=0;
				ycoord+=3;
			}
		}
		
		//adding all the needed spacers to ensure a nice looking GUI
		insertSquareSpacers(9);
		
		//setting the window to be visible 
		colourWindow.setVisible(true);
		
	}
	
	//method that places the spacers in a diagonal like fashion
	public void insertSquareSpacers(int sideLength) {
		
		//loops so that a panel is placed so that there will be at least one element in every column and row
		for(int i = 0; i<sideLength; i++) {
			gbc.gridx=i;
			gbc.gridy=i;
			gbc.gridwidth =1;
			gbc.gridheight =1;
			colourWindow.add(new JLabel(), gbc);
		}
	}

	@Override
	//action listener that will look for when a button is pressed
	public void actionPerformed(ActionEvent e) {
		
		//if the back button is pressed
		if (e.getSource()==backButton) {
			
			//the output window will appear and this window will disappear
			outputTextWindow run = new outputTextWindow(textHolder, backgroundColor, foregroundColor);
			colourWindow.setVisible(false);
		}
		
		//if the custom colours button is pressed
		if(e.getSource()==customButton) {
			
			//this window will disappear and the colour options window will appear
			colourWindow.setVisible(false);
			foregroundBackgroundWindow run = new foregroundBackgroundWindow(textHolder, backgroundColor, foregroundColor);
		}
		
		//if the colour palette buttons are pressed
		//for loop will cycle through each option to find which button was pressed
		for (int i =0; i<6; i++) {
			if (e.getSource()==colours[i]){
				
			//background and foreground colours will change accordingly
			this.backgroundColor=colourOptions[i][1];
			this.foregroundColor=colourOptions[i][0];
			
			//this window will disappear and reappear to update all colours in the window
			colourWindow.setVisible(false);
			recColourPalette updatedColours = new recColourPalette(textHolder, backgroundColor, foregroundColor);
			}
		}
		
	}
	
}
