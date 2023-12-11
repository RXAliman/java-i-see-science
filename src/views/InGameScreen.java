package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


// IN-GAME SCREEN CLASS
public class InGameScreen implements GameView
{
	private JFrame window;
	private Color windowColor = new Color(0xFFC2D1);
	private JButton[] choices = new JButton[4];
	private JPanel choicesButtonPanel;
	private JLabel backgroundLabel = new JLabel();
	private JLabel countDownLabel;
	private Font choicesButtonFont = new Font("Arial", Font.BOLD, 40);
	private Font countDownFont = new Font("Arial", Font.BOLD, 400);
	private ImageIcon background;
	private Timer countDownTimer;
	private int countDownNumber = 3;
	
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
		
		// Call the function to initialize graphics
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
		
		// Countdown
		initializeCountdown();
		countDown();
	}
	
	private void initializeCountdown()
	{
		countDownLabel = new JLabel("", SwingConstants.CENTER);
		countDownLabel.setFont(countDownFont);
		countDownLabel.setForeground(Color.WHITE);
		countDownLabel.setSize(400,400);
		countDownLabel.setLocation(
			window.getWidth() / 2 - countDownLabel.getWidth() / 2,
			window.getHeight() / 2 - countDownLabel.getHeight() / 2
		);
		
		window.getContentPane().add(countDownLabel);
	}
	
	private void countDown()
	{
		countDownLabel.setText("" + countDownNumber);
		
		countDownTimer = new Timer(1000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				countDownLabel.setText("" + --countDownNumber);
				
				if (countDownNumber <= 0)
				{
					countDownTimer.stop();
					
					window.getContentPane().remove(countDownLabel);
					
					// Call the function to initialize the in-game buttons
					initializeButtons();
					
					// Cleans the view
					window.revalidate();
					window.repaint();
				}
			}
		});
		countDownTimer.start();
	}
	
	private void initializeGraphics() throws Exception
	{
		final int backgroundScale = 25; // In percentage

		BufferedImage[] buffer = {
			ImageIO.read(this.getClass().getResourceAsStream("/images/background.png")),
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
	}
	
	// Initialize In-game buttons
	private void initializeButtons()
	{	
		// Set button size
		final int buttonWidth = 400;
		final int buttonHeight = 100;
		final int padding = 10;
		
		// Initialize the choice button panel
		choicesButtonPanel = new JPanel();
		choicesButtonPanel.setLayout(new GridLayout(2,2,padding,padding));
		choicesButtonPanel.setBackground(new Color(0,0,0,0));
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
		window.getContentPane().add(backgroundLabel);
	}
}
