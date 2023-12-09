package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


// MAIN MENU SCREEN CLASS
public class MainMenu implements GameView
{
	private JFrame window; //Stores the frame used by the program
	private Color windowColor = new Color(0xAEC6CF); //Color to be used as background
	private JPanel mainMenuButtonsPanel = new JPanel(); //JPanel to store main menu buttons
	private JButton startButton = new JButton("Play"); //'Play' button
	private JButton exitButton = new JButton("Exit"); //'Exit' button
	private Font mainMenuButtonFont = new Font("Arial", Font.BOLD, 40); //Font to be used by the button texts
	
	// Constructor
	public MainMenu(JFrame frame)
	{
		window = frame;
	}
	
	// Initialize Main Menu screen
	@Override
	public void initialize()
	{
		// Remove all previous components
		window.getContentPane().removeAll();

		// Set background color to 'Pastel Blue' 
		window.getContentPane().setBackground(windowColor);
		
		// Call the function to initialize main menu buttons 
		initializeButtons();
		
		// Cleans the view
		window.revalidate();
		window.repaint();
	}
	// Initialize main menu buttons
	private void initializeButtons()
	{
		// Set button size and count
		final int buttonWidth = 400;
		final int buttonHeight = 100;
		final int buttonCount = 2;
		final int verticalGap = 20;
		
		// Initialize main menu panel
		mainMenuButtonsPanel.setLayout(new GridLayout(buttonCount,1,0,verticalGap));
		mainMenuButtonsPanel.setBackground(windowColor);
		mainMenuButtonsPanel.setBounds(
			(window.getWidth() / 2) - (buttonWidth / 2),
//			(window.getHeight() / 2) - (buttonHeight * buttonCount) / 2,
			400,
			buttonWidth,
			buttonHeight * buttonCount + verticalGap
		);
		
		// Initialize 'Play' button
		startButton.setSize(buttonWidth, buttonHeight);
		startButton.setFont(mainMenuButtonFont);
		startButton.setFocusPainted(false);
		startButton.setBackground(new Color(0xEEEEEE));
		startButton.addActionListener(
		e -> {
			GameViewManager.getInstance().switchToInGameScreen();
		});
		mainMenuButtonsPanel.add(startButton);
		
		// Initialize 'Exit' button
		exitButton.setSize(buttonWidth, buttonHeight);
		exitButton.setFont(mainMenuButtonFont);
		exitButton.setFocusPainted(false);
		exitButton.setBackground(new Color(0xEEEEEE));
		exitButton.addActionListener(
		e -> {
			GameViewManager.getInstance().exitGame();
		});
		mainMenuButtonsPanel.add(exitButton);
		
		window.getContentPane().add(mainMenuButtonsPanel);
	}
	
	// Select difficulty screen
	private void selectDifficulty()
	{
		
	}
}
