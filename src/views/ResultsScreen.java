package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


// RESULTS SCREEN CLASS
public class ResultsScreen implements GameView
{
	private JFrame window;
	private Color windowColor = new Color(0x77DD77);
	private JPanel resultsButtonPanel;
	private JButton mainMenuButton = new JButton("To Main Menu");
	private JButton retryButton = new JButton("Retry");
	private JButton nextDifficultyButton = new JButton(">>");
	private Font resultsButtonFont = new Font("Arial", Font.BOLD, 20);
	
	// Constructor
	public ResultsScreen(JFrame frame)
	{
		window = frame;
	}
	
	// Initialize results screen
	@Override
	public void initialize()
	{
		// Remove all previous components
		window.getContentPane().removeAll();
		
		// Set background color to 'Pastel Green'
		window.getContentPane().setBackground(windowColor);
		
		// Call the function to initialize the results buttons
		initializeButtons();
		
		// Cleans the view
		window.revalidate();
		window.repaint();
	}
	
	// Initialize results buttons
	private void initializeButtons()
	{	
		// Set button size and count
		final int buttonWidth = 200;
		final int buttonHeight = 100;
		final int buttonCount = 3;
		final int padding = 20;
		
		// Initialize the results button panel
		resultsButtonPanel = new JPanel();
		resultsButtonPanel.setLayout(new GridLayout(1,buttonCount,padding,padding));
		resultsButtonPanel.setBackground(new Color(0,0,0,0));
		resultsButtonPanel.setBounds(
			(window.getWidth() / 2) - (buttonWidth * buttonCount / 2),
			500,
			buttonWidth * buttonCount,
			buttonHeight
		);
		
		// Add button going back to Main Menu screen
		mainMenuButton.setFocusPainted(false);
		mainMenuButton.setBackground(new Color(0xEEEEEE));
		mainMenuButton.setFont(resultsButtonFont);
		mainMenuButton.addActionListener(
		e -> {
			GameViewManager.getInstance().switchToMainMenu();
		});
		resultsButtonPanel.add(mainMenuButton);
		
		// Add retry button to retry the game on current difficulty
		retryButton.setFocusPainted(false);
		retryButton.setBackground(new Color(0xEEEEEE));
		retryButton.setFont(resultsButtonFont);
		retryButton.addActionListener(
		e -> {
			GameViewManager.getInstance().switchToInGameScreen();
		});
		resultsButtonPanel.add(retryButton);
		
		// Add button to proceed to the next difficulty
		nextDifficultyButton.setFocusPainted(false);
		nextDifficultyButton.setBackground(new Color(0xEEEEEE));
		nextDifficultyButton.setFont(resultsButtonFont);
		resultsButtonPanel.add(nextDifficultyButton);
		
		// Add results button panel to JFrame/Window
		window.getContentPane().add(resultsButtonPanel);
	}
}
