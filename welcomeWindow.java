
//getting all needed imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;

public class welcomeWindow implements ActionListener{

	//declaring all needed instance variables
	JFrame welcomeFrame;
	JButton goButton;
	JLabel welcomeText;
	Font welcomeFont;
	JPanel welcomePanel;
	Color backgroundColor, textColor;
	
	//constructor for the welcome window class
	//has two parameters one for the foreground colour and one for the background colour
	welcomeWindow(Color bg, Color fg){
		
		//object that is created will have the background and foreground colours specified in the constructor
		this.backgroundColor = bg;
		this.textColor = fg;
		
		//creating the frame for this window and setting all necessary properties
		welcomeFrame = new JFrame("Welcome to Pen-Pal");
		
		//this class is special as it is the only one that does not use grid bag layout as a manager
		welcomeFrame.setLayout(null);
		
		welcomeFrame.setSize(750,750);
		welcomeFrame.setResizable(false);
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//getting the image that will be used as the icon image for the frame
		BufferedImage logo = null;
		
		//try-catch to ensure no errors while reading the file
		try {
			logo = ImageIO.read(new File("Pen-Pal_Logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//setting the image as the icon
		welcomeFrame.setIconImage(logo);
		
		//creating the font that will be used for all of this window
		//try-catch to ensure no errors while reading the font file
		try {
			
			//setting general parameters for the font
			welcomeFont = Font.createFont(Font.TRUETYPE_FONT, new File("welcomeFont.ttf")).deriveFont(50f);
			
			//creating the graphics environment and registering the font so it can be used
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("welcomeFont.ttf")));
		}
		
		catch (IOException | FontFormatException e) {
			
		}
		
		//creating an image icon that will be added to a label
		ImageIcon logoIcon = new ImageIcon("Pen-Pal_Logo.png");
		
		//creating some welcome text that will greet the user
		//setting all necessary parameters
		welcomeText = new JLabel("<html>Welcome<br>" + "&nbsp; to" + "<br> Pen-Pal</html>");
		welcomeText.setFont(welcomeFont);
		welcomeText.setIcon(logoIcon);
		welcomeText.setHorizontalTextPosition(JLabel.CENTER);
		welcomeText.setVerticalTextPosition(JLabel.TOP);
		welcomeText.setBounds(210,0, 375, 375);
	
		//creating a start button and setting all necessary parameters
		goButton = new JButton("Start");
		goButton.setFont(welcomeFont.deriveFont(35f));
		goButton.setBounds(253, 400, 200, 200);
		goButton.setBorder(BorderFactory.createLineBorder(textColor, 4));
		goButton.setBackground(backgroundColor);
		goButton.addActionListener(this);
		goButton.setBackground(bg);
		
		//adding previous two element to a panel that will be added to the main frame
		welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 750, 750);
		welcomePanel.setLayout(null);
		welcomePanel.add(goButton);
		welcomePanel.add(welcomeText);
		
		//adding the previous panel to the main frame 
		//setting all necessary parameters for the main frame
		welcomeFrame.add(welcomePanel);
		welcomeFrame.getContentPane().setForeground(textColor);
		welcomeFrame.setVisible(true);
	}
	
	//action listener that will look for when a button is pressed
	public void actionPerformed (ActionEvent e) {
		
		//when the go button is pressed
		if (e.getSource() == goButton) {
			
			//this frame will be set to false and the input window will appear
			welcomeFrame.setVisible(false);
			inputTextWindow runInputWindow = new inputTextWindow(backgroundColor, textColor);
		}
		
	}
	
}
