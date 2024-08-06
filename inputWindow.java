import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class inputWindow {

	JFrame inputFrame;
	JTextField userText;
	JLabel textLabel;
	JButton b1, b2, b3, b4, b5;
	JPanel inputPanel;
	Font welcomeFont;
	GridBagConstraints gbc = new GridBagConstraints();
	
	inputWindow(){
		
		inputFrame = new JFrame("Enter Your Text");
		
		inputPanel = new JPanel();
		inputPanel.setSize(750,750);
		inputPanel.setLayout(new GridBagLayout());
		inputPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//get this working at home.
		inputFrame.setSize(750,750);
		//inputFrame.setResizable(false);
		inputFrame.setLocationRelativeTo(null);
		inputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		BufferedImage logo = null;
		
		try {
			logo = ImageIO.read(new File("Pen-Pal_Logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inputFrame.setIconImage(logo);
		
		
		try {
			welcomeFont = Font.createFont(Font.TRUETYPE_FONT, new File("welcomeFont.ttf")).deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("welcomeFont.ttf")));
		}
		
		catch (IOException | FontFormatException e) {
			
		}
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.fill = GridBagConstraints.BOTH;
		
		b1 = new JButton("1");
		gbc.gridx = 0;
		gbc.gridy=0;
		gbc.gridheight =2;
		inputPanel.add(b1, gbc);
		
		b2 = new JButton("2");
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth=2;
		inputPanel.add(b2, gbc);
		
		
		b3 = new JButton("3");
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		inputPanel.add(b3, gbc);
		
		
		b4 = new JButton("4");
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		inputPanel.add(b4, gbc);
		
		b5 = new JButton("5");
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.gridwidth=4;
		gbc.gridheight = 1;
		inputPanel.add(b5, gbc);
		
		
		insertSquareSpacers(5);
		/*
		insertSpacer(0,0);
		insertSpacer(1,1);
		insertSpacer(2,2);
		insertSpacer(3,3);
		insertSpacer(4,4);
		*/
		
		inputFrame.add(inputPanel);
		
	}
	
	public void insertSpacer(int x,int y) {
	
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=1;
		gbc.gridheight = 1;
		inputPanel.add(new JPanel(), gbc);
	}
	
	public void insertSquareSpacers(int sideLength) {
		for(int i = 0; i<sideLength; i++) {
			gbc.gridx=i;
			gbc.gridy=i;
			gbc.gridwidth =1;
			gbc.gridheight =1;
			inputPanel.add(new JPanel(), gbc);
		}
	}
	
}
