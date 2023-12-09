package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


// IN-GAME SCREEN CLASS
public class InGameScreen implements GameView
{
	private JFrame window;
	private Color windowColor = new Color(0xFFC2D1);
	private JButton[] choices = new JButton[4];
	private JPanel choicesButtonPanel;
	private Font choicesButtonFont = new Font("Arial", Font.BOLD, 40);
	
	// Constructor
	public InGameScreen(JFrame frame)
	{
		window = frame;
	}
	
	// Initialize In-game screen
	@Override
	public void initialize()
	{
		// Remove all previous components
		window.getContentPane().removeAll();
		
		// Set background color to 'Pastel Pink'
		window.getContentPane().setBackground(windowColor);
		
		// Call the function to initialize the in-game buttons
		initializeButtons();
		
		// Cleans the view
		window.revalidate();
		window.repaint();
	}
	
	// Initialize In-game buttons
	public void initializeButtons()
	{
		// Set button size
		final int buttonWidth = 400;
		final int buttonHeight = 100;
		final int padding = 10;
		
		// Initialize the choice button panel
		choicesButtonPanel = new JPanel();
		choicesButtonPanel.setLayout(new GridLayout(2,2,padding,padding));
		choicesButtonPanel.setBackground(windowColor);
		choicesButtonPanel.setBounds(
			(window.getWidth() / 2) - buttonWidth,
			(window.getHeight() / 2) - (buttonHeight * 2) / 2,
			buttonWidth * 2,
			buttonHeight * 2 + padding
		);
		
		// Initialize the value of choice buttons
		choices[0] = new JButton();
		choices[0].setText("A");
		choices[0].setSize(buttonWidth, buttonHeight);
		choices[0].setFont(choicesButtonFont);
		choices[0].setFocusPainted(false);
		choices[0].setBackground(new Color(0xEEEEEE));
		choices[0].addActionListener(
		e -> {
			GameViewManager.getInstance().switchToResultsScreen();
		});
		choicesButtonPanel.add(choices[0]);
		
		choices[1] = new JButton();
		choices[1].setText("B");
		choices[1].setSize(buttonWidth, buttonHeight);
		choices[1].setFont(choicesButtonFont);
		choices[1].setFocusPainted(false);
		choices[1].setBackground(new Color(0xEEEEEE));
		choices[1].addActionListener(
		e -> {
			GameViewManager.getInstance().switchToResultsScreen();
		});
		choicesButtonPanel.add(choices[1]);
		
		choices[2] = new JButton();
		choices[2].setText("C");
		choices[2].setSize(buttonWidth, buttonHeight);
		choices[2].setFont(choicesButtonFont);
		choices[2].setFocusPainted(false);
		choices[2].setBackground(new Color(0xEEEEEE));
		choices[2].addActionListener(
		e -> {
			GameViewManager.getInstance().switchToResultsScreen();
		});
		choicesButtonPanel.add(choices[2]);
		
		choices[3] = new JButton();
		choices[3].setText("D");
		choices[3].setSize(buttonWidth, buttonHeight);
		choices[3].setFont(choicesButtonFont);
		choices[3].setFocusPainted(false);
		choices[3].setBackground(new Color(0xEEEEEE));
		choices[3].addActionListener(
		e -> {
			GameViewManager.getInstance().switchToResultsScreen();
		});
		choicesButtonPanel.add(choices[3]);
		
		// Add the choice button panel to JFrame/window
		window.getContentPane().add(choicesButtonPanel);
	}
}
