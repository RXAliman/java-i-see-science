package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


// MAIN MENU SCREEN CLASS
public class MainMenu implements GameView
{
	private JFrame window; //Stores the frame used by the program
	private Color windowColor = new Color(0xAEC6CF); //Color to be used as background
	private JPanel mainMenuButtonsPanel = new JPanel(); //JPanel to store main menu buttons
	private JPanel difficultyPanel = new JPanel();
	private JButton startButton = new JButton("Play"); //'Play' button
	private JButton exitButton = new JButton("Exit"); //'Exit' button
	private JButton easyButton, mediumButton, hardButton, backButton;
	private JLabel backgroundLabel = new JLabel();
	private JLabel appNameLabel = new JLabel();
	private JLabel selectDifficultyText = new JLabel("Select Difficulty", SwingConstants.CENTER);
	private Font mainMenuButtonFont = new Font("Arial", Font.BOLD, 40); //Font to be used by the button texts
	private ImageIcon background, appName;
	
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

		// Call the function to initialize main menu buttons 
		initializeButtons();
		
		// Call the function to initialize main menu images
		try
		{
			initializeGraphics();
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
		
		// Set background color to 'Pastel Blue' 
		window.getContentPane().setBackground(windowColor);
		
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
		mainMenuButtonsPanel.setBackground(new Color(0,0,0,0));
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
			selectDifficulty();
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
	
	private void initializeGraphics() throws Exception
	{
		final int backgroundScale = 25; // In percentage
		final int appNameScale = 25;

		BufferedImage[] buffer = {
			ImageIO.read(this.getClass().getResourceAsStream("/images/background.png")),
			ImageIO.read(this.getClass().getResourceAsStream("/images/I SciEncE.png")),
		};
		
//		System.out.printf("Buffered Image: %d x %d\n", buffer[0].getWidth(), buffer[0].getHeight());
//		System.out.printf("Scaled Image: %f x %f\n", (double)scale/100 * buffer[0].getWidth(), (double)scale/100 * buffer[0].getHeight());
		
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
		
		Image appNameImg = buffer[1].getScaledInstance(
			(int)((float)appNameScale/100 * buffer[1].getWidth()),
			(int)((float)appNameScale/100 * buffer[1].getHeight()),
			Image.SCALE_FAST
		);
		appName = new ImageIcon(appNameImg);
		
		appNameLabel.setBounds(
			window.getWidth() / 2 - appName.getIconWidth() / 2,
			150,
			appName.getIconWidth(),
			appName.getIconHeight()
		);
		appNameLabel.setIcon(appName);
		
		window.getContentPane().add(appNameLabel);
		window.getContentPane().add(backgroundLabel);
	}
	
	// Select difficulty screen
	private void selectDifficulty()
	{
		window.getContentPane().remove(mainMenuButtonsPanel);
		window.getContentPane().remove(backgroundLabel);
		window.getContentPane().remove(appNameLabel);
		
		// Set button size and count
		final int buttonWidth = 300;
		final int buttonHeight = 100;
		final int buttonCount = 4;
		final int verticalGap = 10;
		
		// Initialize difficulty buttons panel
		difficultyPanel.setLayout(new GridLayout(buttonCount,1,0,verticalGap));
		difficultyPanel.setBackground(new Color(0,0,0,0));
		difficultyPanel.setBounds(
			(window.getWidth() / 2) - (buttonWidth / 2),
//			(window.getHeight() / 2) - (buttonHeight * buttonCount) / 2,
			250,
			buttonWidth,
			buttonHeight * buttonCount + verticalGap
		);
		
		easyButton = new JButton("Easy");
		easyButton.setSize(buttonWidth, buttonHeight);
		easyButton.setFont(mainMenuButtonFont);
		easyButton.setFocusPainted(false);
		easyButton.setBackground(new Color(0xEEEEEE));
		easyButton.addActionListener(
		e -> {
			GameViewManager.getInstance().switchToInGameScreen();
		});
		difficultyPanel.add(easyButton);
		
		mediumButton = new JButton("Medium");
		mediumButton.setSize(buttonWidth, buttonHeight);
		mediumButton.setFont(mainMenuButtonFont);
		mediumButton.setFocusPainted(false);
		mediumButton.setBackground(new Color(0xEEEEEE));
		mediumButton.addActionListener(
		e -> {
			GameViewManager.getInstance().switchToInGameScreen();
		});
		difficultyPanel.add(mediumButton);
		
		hardButton = new JButton("Hard");
		hardButton.setSize(buttonWidth, buttonHeight);
		hardButton.setFont(mainMenuButtonFont);
		hardButton.setFocusPainted(false);
		hardButton.setBackground(new Color(0xEEEEEE));
		hardButton.addActionListener(
		e -> {
			GameViewManager.getInstance().switchToInGameScreen();
		});
		difficultyPanel.add(hardButton);
		
		backButton = new JButton("Back");
		backButton.setSize(buttonWidth, buttonHeight);
		backButton.setFont(mainMenuButtonFont);
		backButton.setFocusPainted(false);
		backButton.setBackground(new Color(0xEEEEEE));
		backButton.addActionListener(
		e -> {
			GameViewManager.getInstance().switchToMainMenu();
		});
		difficultyPanel.add(backButton);
		
		selectDifficultyText.setFont(mainMenuButtonFont);
		selectDifficultyText.setForeground(Color.WHITE);
		selectDifficultyText.setSize(400,50);
		selectDifficultyText.setLocation(
			window.getWidth() / 2 - selectDifficultyText.getWidth() / 2,
			150
		);
		
		window.getContentPane().add(difficultyPanel);
		window.getContentPane().add(selectDifficultyText);
		window.getContentPane().add(backgroundLabel);
		
		window.revalidate();
		window.repaint();
	}
}
