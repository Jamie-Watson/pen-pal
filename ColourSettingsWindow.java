
//getting all needed imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class ColourSettingsWindow implements ActionListener{

	//declaring all needed instance variables of this class
	JFrame colourWindow;
	GridBagConstraints gbc = new GridBagConstraints();
	Color colourOptions[] = {new Color(221,50,50), new Color(236,0,140), new Color(250,193,204), new Color(255, 182, 173), new Color(246, 139,105), new Color(228, 62,34), 
			new Color(251,175,95),new Color(250,207,99), new Color(255,239,108), new Color(206,215,92), new Color(210,239,219), new Color(161,206,94), new Color(150,154,82),
			new Color(219,223,195), new Color(174,209,233), new Color(162,208,207), new Color(26,134,168), new Color(0,145,156), new Color(0,101,169), new Color(0,43,84),
			new Color(152,83,150), new Color(128,84,98), new Color(57,18,66), new Color(204,193,219), new Color(227,223,214), new Color(178,177,165), new Color(88,87,75),
			new Color(197, 171, 137), new Color(87,44,41), new Color(0,0,0), new Color(255,242,236), new Color(179, 142,80), new Color(255,226,147), new Color(200,201,208)};
	JButton colours[];
	JButton backButton;
	Boolean foreground = false;
	Color foregroundColor, backgroundColor;
	String textHolder;
	Boolean isForeground;
	Font dyslexicFont;
	
	//constructor for the colour settings class
	//has four parameters one for the foreground colour, one for the background colour, one for the current text in the program, 
	//and a boolean to indicate whether the foreground or background is changing
	ColourSettingsWindow(String s, Color bg, Color fg, boolean isFg){
		
		//object that is created will have the background and foreground colours, the current text, and the boolean value specified in the constructor
		this.foregroundColor=fg;
		this.backgroundColor=bg;
		this.textHolder = s;
		this.isForeground=isFg;
		
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
		colourWindow = new JFrame("Choose the colour you would like to use!");
		colourWindow.setSize(750,750);
		colourWindow.setLocationRelativeTo(null);
		colourWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		colourWindow.setLayout(new GridBagLayout());
		
		//also defining relevant grid bag layout properties
		colourWindow.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		colourWindow.getContentPane().setBackground(backgroundColor);
		
		//setting properties for the grid bag constraints which will be used for all elements in the frame
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//creating the back button which will head back to the page indicating whether the foreground or background is changing
		//setting all relevant properties for the back button
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		backButton.setFont(dyslexicFont.deriveFont(12f));
		backButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		backButton.setBackground(backgroundColor);
		backButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.insets= new Insets(10,10,10,10);
		colourWindow.add(backButton, gbc);
		
		//creating all 34 colour option buttons
		colours=new JButton[34];
		
		//creating some integer variables that will help with the placement of these 34 buttons
		int xcoord=1;
		int ycoord=1;
		for(int i=0;i<34;i++) {
			
			//setting the buttons to have a name with many space characters which seems to help with the spacing using grid bag layout
			colours[i]=new JButton("<html> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</html>");
			
			//setting all other relevant properties of the buttons
			colours[i].setBorder(BorderFactory.createLineBorder(foregroundColor, 2));
			colours[i].setBackground(colourOptions[i]);
			colours[i].addActionListener(this);
			
			//setting all grid bag properties and adding it to the frame
			gbc.gridx=xcoord;
			gbc.gridy=ycoord;
			colourWindow.add(colours[i],gbc);
			
			//changing the integer variables to correctly space the buttons
			xcoord++;
			if (xcoord>6){
				xcoord=0;
				ycoord++;
			}
			
		}
		
		//adding all the needed spacers to ensure a nice looking GUI
		insertSquareSpacers(7);
		
		//setting the window to be visible 
		colourWindow.setVisible(true);
		
	}
	
	//action listener that will look for when a button is pressed
	public void actionPerformed(ActionEvent e) {
		
		//if the back button is pressed
		if(e.getSource()==backButton) {
			
			//this window will disappear and the foreground background settings window will appear
			colourWindow.setVisible(false);
			foregroundBackgroundWindow run = new foregroundBackgroundWindow(textHolder, backgroundColor, foregroundColor);
			
		}
		
		//if any of the colour buttons are pressed
		//for loop will cycle through all options to find pressed button
		for(int i = 0; i<34; i++) {
			if (e.getSource()==colours[i]) {
				
				//if the foreground boolean is true
				if(isForeground) {
					
					//the foreground colour will change to colour selected
					foregroundColor = colourOptions[i];
					ColourSettingsWindow updateColours = new ColourSettingsWindow(textHolder, backgroundColor, foregroundColor, true);
				}
				
				//if the foreground boolean is false
				else {
					
					//the background colour will change to colour selected
					backgroundColor = colourOptions[i];
					ColourSettingsWindow updateColours = new ColourSettingsWindow(textHolder, backgroundColor, foregroundColor, false);
				}
				
			}
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
			colourWindow.add(new JLabel(), gbc);
		}
	}
	
	
	
}
