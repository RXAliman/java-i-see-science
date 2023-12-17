package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


// RESULTS SCREEN CLASS
public class ResultsScreen implements GameView
{
	private JFrame window;
	private Color windowColor = new Color(0x77DD77);
	private JPanel resultsTextPanel, resultsButtonPanel;
	private JLabel youGotText, resultScore;
	private JLabel backgroundLabel = new JLabel();
	private JButton mainMenuButton = new JButton(new ImageIcon(getClass().getResource("/images/home_icon.png")));
	private JButton retryButton = new JButton(new ImageIcon(getClass().getResource("/images/retry_icon.png")));
	private JButton nextDifficultyButton = new JButton(new ImageIcon(getClass().getResource("/images/forward_icon.png")));
	private Font resultsButtonFont = new Font("Arial", Font.BOLD, 20);
	private ImageIcon background;
	private GameDifficulty difficulty;
	
	// Constructor
	public ResultsScreen(JFrame frame, GameDifficulty level)
	{
		window = frame;
		difficulty = level;
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
		
		// Call the function to initialize the results text
		initializeText();
		
		// Call the function to initialize graphics
		try
		{
			initializeGraphics();
			window.getContentPane().add(backgroundLabel);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(
				window,
				"An error has occured while loading the Main Menu.",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			e.printStackTrace();
			GameViewManager.getInstance().exitGame();
		}
		
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
			550,
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
			GameViewManager.getInstance().switchToInGameScreen(difficulty);
		});
		resultsButtonPanel.add(retryButton);
		
		// Add button to proceed to the next difficulty (if difficulty is Easy and Medium)
		if (difficulty != GameDifficulty.HARD)
		{
			nextDifficultyButton.setFocusPainted(false);
			nextDifficultyButton.setBackground(new Color(0xEEEEEE));
			nextDifficultyButton.setFont(resultsButtonFont);
			nextDifficultyButton.addActionListener(
			e -> {
				GameViewManager.getInstance().switchToInGameScreen(
					difficulty == GameDifficulty.EASY ? GameDifficulty.MEDIUM : GameDifficulty.HARD
				);
			});
			resultsButtonPanel.add(nextDifficultyButton);
		}
		
		// Add results button panel to JFrame/Window
		window.getContentPane().add(resultsButtonPanel);
	}
	
	// Initialize results text
	void initializeText()
	{
		final int panelWidth = 800;
		final int panelHeight = 450;
		
		resultsTextPanel = new JPanel();
		resultsTextPanel.setBounds(
			(window.getWidth() / 2) - (panelWidth / 2),
			100,
			panelWidth, panelHeight
		);
		resultsTextPanel.setBackground(new Color(0,0,0,0));
		resultsTextPanel.setLayout(new BoxLayout(resultsTextPanel, BoxLayout.Y_AXIS));
		
		youGotText = new JLabel("You've Got", SwingConstants.CENTER);
		youGotText.setFont(new Font("Arial", Font.PLAIN, 80));
		youGotText.setForeground(Color.BLACK);
		youGotText.setMaximumSize(new Dimension(panelWidth,130));
		resultsTextPanel.add(youGotText);
		
		resultScore = new JLabel("10/10", SwingConstants.CENTER);
		resultScore.setFont(new Font("Arial", Font.PLAIN, 300));
		resultScore.setForeground(Color.BLACK);
		resultScore.setMaximumSize(new Dimension(panelWidth,300));
		resultsTextPanel.add(resultScore);
		
		window.getContentPane().add(resultsTextPanel);
	}
	
	private void initializeGraphics() throws Exception
	{
		final int backgroundScale = 25; // In percentage

		BufferedImage[] buffer = {
			ImageIO.read(this.getClass().getResourceAsStream("/images/background.png")),
		};
		
		Image backgroundImg = buffer[0].getScaledInstance(
			(int)((float)backgroundScale/100 * buffer[0].getWidth()),
			(int)((float)backgroundScale/100 * buffer[0].getHeight()),
			Image.SCALE_FAST
		);
		background = new ImageIcon(backgroundImg);
		
		backgroundLabel.setBounds(
			window.getWidth() / 2 - background.getIconWidth() / 2,
			window.getHeight() / 2 - background.getIconHeight() / 2,
			background.getIconWidth(),
			background.getIconHeight()
		);
		backgroundLabel.setIcon(background);
	}
}