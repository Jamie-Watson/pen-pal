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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class outputTextWindow implements ActionListener, ChangeListener{

	//declaring all needed instance variables for this class
	JFrame outputFrame;
	JButton colourSettingsButton, wordButton, TTSButton, backButton;
	JTextField wordText;
	JLabel wordTextLabel, fontSizeLabel;
	JTextPane outputText, wordDef;
	JScrollPane scrollText, scrollDef;
	GridBagConstraints gbc = new GridBagConstraints();
	Font dyslexicFont;
	JSlider fontSize;
	Color backgroundColor, foregroundColor;
	
	//constructor for the output text class
	//has three parameters one for the foreground colour, one for the background colour, and one for the current text in the program
	outputTextWindow(String s, Color bg, Color fg){
		
		//object that is created will have the background and foreground colours specified in the constructor
		this.backgroundColor=bg;
		this.foregroundColor=fg;
		
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
		outputFrame = new JFrame("Better Now?");
		outputFrame.setSize(750,750);
		outputFrame.setLocationRelativeTo(null);
		outputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//also defining relevant grid bag layout properties
		outputFrame.setLayout(new GridBagLayout());
		outputFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//setting properties for the grid bag constraints which will be used for all elements in the frame
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.fill = GridBagConstraints.BOTH;
		
		//creating a button that will speak the current text when pressed
		//setting all relevant properties for the TTS button
		TTSButton = new JButton("Read This To Me");
		TTSButton.setFont(dyslexicFont.deriveFont(12f));
		TTSButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		TTSButton.setBackground(backgroundColor);
		TTSButton.setForeground(foregroundColor);
		TTSButton.addActionListener(this);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridheight=2;
		gbc.gridwidth =2;
		gbc.insets=new Insets(10,10,10,10);
		outputFrame.add(TTSButton, gbc);
		
		//creating a button that will go to the colour settings window when pressed
		//setting all relevant properties for the colour settings button
		colourSettingsButton = new JButton("Colour Settings");
		colourSettingsButton.addActionListener(this);
		colourSettingsButton.setFont(dyslexicFont.deriveFont(12f));
		colourSettingsButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		colourSettingsButton.setBackground(backgroundColor);
		colourSettingsButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=12;
		gbc.gridy=0;
		gbc.gridheight=2;
		gbc.gridwidth=2;
		gbc.insets=new Insets(10,10,10,10);
		outputFrame.add(colourSettingsButton, gbc);
		
		//creating a button that will go back to the input text window when pressed
		//setting all relevant properties for the back button
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		backButton.setFont(dyslexicFont.deriveFont(12f));
		backButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		backButton.setBackground(backgroundColor);
		backButton.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=10;
		gbc.gridy=0;
		gbc.gridheight=2;
		gbc.gridwidth=2;
		gbc.insets=new Insets(10,10,10,10);
		outputFrame.add(backButton, gbc);
		
		//creating a text pane which will contain an easier version of the previously entered text
		//setting all relevant properties for the text pane
		outputText = new JTextPane();
		outputText.setText(s);
		outputText.setFont(dyslexicFont);
		outputText.setEditable(false);
		outputText.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		outputText.setBackground(backgroundColor);
		outputText.setForeground(foregroundColor);
		
		//adding that text pane to a scroll pane in case the text is too big
		scrollText = new JScrollPane(outputText);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.gridwidth=8;
		gbc.gridheight=10;
		gbc.insets=new Insets(0,0,0,0);
		outputFrame.add(scrollText, gbc);
		
		//creating a label that will highlight the dictionary feature
		//setting all relevant properties for the label
		wordTextLabel = new JLabel("What's this word?", SwingConstants.CENTER);
		wordTextLabel.setFont(dyslexicFont.deriveFont(20f));
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=10;
		gbc.gridy=3;
		gbc.gridwidth=4;
		gbc.gridheight=2;
		gbc.insets=new Insets(10,10,10,10);
		outputFrame.add(wordTextLabel, gbc);
		
		//creating a text field that will act as a search bar for the dictionary
		//setting all relevant properties and adding it to the frame
		wordText = new JTextField();
		wordText.setFont(dyslexicFont.deriveFont(12f));
		wordText.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		wordText.setBackground(backgroundColor);
		wordText.setForeground(foregroundColor);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx = 11;
		gbc.gridy=5;
		gbc.gridheight=2;
		gbc.gridwidth=2;
		outputFrame.add(wordText,gbc);
		
		//creating a button that will act as a confirm button for the dictionary search
		//setting all relevant properties for the "search button"
		wordButton = new JButton("Look it up!");
		wordButton.setFont(dyslexicFont.deriveFont(15f));
		wordButton.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		wordButton.setBackground(backgroundColor);
		wordButton.setForeground(foregroundColor);
		wordButton.addActionListener(this);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=11;
		gbc.gridy=7;
		gbc.gridheight=2;
		gbc.gridwidth=2;
		gbc.insets=new Insets(10,10,10,10);
		outputFrame.add(wordButton,gbc);
		
		//creating a label that will highlight the changing font size feature
		//setting all relevant properties for the label
		fontSizeLabel = new JLabel("Font Size", SwingConstants.CENTER);
		fontSizeLabel.setFont(dyslexicFont.deriveFont(15f));
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=4;
		gbc.gridy=1;
		gbc.gridheight=1;
		gbc.gridwidth=2;
		outputFrame.add(fontSizeLabel, gbc);
		
		//creating a slider that will allow the user to change the font size
		//setting all relevant properties for the slider
		fontSize = new JSlider(0, 70, 50);
		fontSize.setPaintTicks(true);
		fontSize.setPaintLabels(true);
		fontSize.setMajorTickSpacing(10);
		fontSize.addChangeListener(this);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=3;
		gbc.gridy=0;
		gbc.gridheight=1;
		gbc.gridwidth=4;
		outputFrame.add(fontSize,gbc);
		
		//creating a small text pane that will deliver the results of the dictionary feature
		//setting all relevant properties for the text pane
		wordDef= new JTextPane();
		wordDef.setFont(dyslexicFont.deriveFont(12f));
		wordDef.setBorder(BorderFactory.createLineBorder(foregroundColor, 3));
		wordDef.setBackground(backgroundColor);
		wordDef.setForeground(foregroundColor);
		wordDef.setEditable(false);
		
		//adding the text pane to a scroll pane 
		scrollDef = new JScrollPane(wordDef);
		
		//setting all grid bag properties and adding it to the frame
		gbc.gridx=10;
		gbc.gridy=9;
		gbc.gridheight=4;
		gbc.gridwidth=4;
		gbc.insets=new Insets(10,10,0,10);
		outputFrame.add(scrollDef, gbc);
		
		//adding all the needed spacers to ensure a nice looking GUI
		insertSquareSpacers(14);
		
		//frame will be visible
		outputFrame.setVisible(true);
		
	}
	
	//method that places the spacers in a diagonal like fashion
	public void insertSquareSpacers(int sideLength) {
		
		//loops so that a panel is placed so that there will be at least one element in every column and row
		for(int i = 0; i<sideLength; i++) {
			gbc.gridx=i;
			gbc.gridy=i;
			gbc.gridwidth =1;
			gbc.gridheight =1;
			outputFrame.add(new JPanel(), gbc);
		}
	}
	
	//action listener that will look for when a button is pressed
	public void actionPerformed(ActionEvent e) {
		
		//if the confirm word search button is pressed
		if(e.getSource()==wordButton) {
			
			//the word will be found in the dictionary
			findWord();
		}
		
		//if the back button is pressed
		if(e.getSource()==backButton) {
			
			//this frame will disappear and an input frame will appear
			outputFrame.setVisible(false);
			inputTextWindow newWindow = new inputTextWindow(backgroundColor, foregroundColor);
		}
		
		//if the colour settings button is pressed
		if(e.getSource()==colourSettingsButton) {
			
			//this frame will disappear and the colour settings frame will appear
			outputFrame.setVisible(false);
			recColourPalette runSettings= new recColourPalette(outputText.getText(), backgroundColor, foregroundColor);
		}
		
		//if the TTS button is pressed
		if(e.getSource()==TTSButton) {
			
			//the voice will speak the text
			TextToSpeechClass.talk(outputText.getText());
		}
	}
	
	//find word method that will find a word in the dictionary using web scraping
	public void findWord() {
		
		//declaring needed variables
		String word = wordText.getText();
		String finalOut;
		
		//try-catch to ensure no errors while reading the data from the dictionary website
		 try {
			 
			 //looking at the dictionary page for that specific word
             Document doc = Jsoup.connect("https://www.merriam-webster.com/dictionary/" + word).timeout(6000).get();
             
             //looking at specific part of the page that stores the data for the definitions of the word and storing all definitions
             Elements def = doc.select("span.dtText");
             
             //formatting for output
             finalOut = (word.substring(0,1)).toUpperCase() + word.substring(1) + "\n";
             
             //sometimes the same definition will repeat but these two variables will help fix that issue
             String currentDef = "";
             String lastDef = "";
             
             //looping through all definitions
             for (int i = 0; i < def.size(); i++) {
            
            
            	currentDef = def.get(i).text();
            	currentDef.replaceAll("[\\n\\t ]", "");
           	  
            	//comparing previous definition to current one to ensure no duplicates
           	 	if(currentDef.compareTo(lastDef)!=0) {
           	 		finalOut +=("\n" + (i+1) + ")" + currentDef + '\n');
           	 	}
           	  
           	 	lastDef = currentDef;
             }
             
             
             
           } 
		 
		 	catch (IOException e) {
		 		
		 		//will display that the word does not exist if the word does not exist
		 		finalOut=("Not a word.");
          
           }
		 
		 //setting the text pane to display all the definitions
		 wordDef.setText(finalOut);
	}

	@Override
	//change listener that will look for when the user moves the slider
	public void stateChanged(ChangeEvent e) {
		
		//if the user moves the slider the size of the font will change relative to the position of the slider
		outputText.setFont(dyslexicFont.deriveFont((float)fontSize.getValue()));
		
	}
}
