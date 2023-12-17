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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;


// IN-GAME SCREEN CLASS
public class InGameScreen implements GameView
{
	private JFrame window;
	private Color windowColor = new Color(0xFFC2D1);
	private JButton[] choices = new JButton[4];
	private JPanel choicesButtonPanel;
	private JPanel upperPanel = new JPanel();
	private JPanel viewPanel = new JPanel();
	private JLabel backgroundLabel = new JLabel();
	private JLabel countDownLabel;
	private JLabel difficultyText, timerText, correctAnswersText;
	private JLabel iSeeText = new JLabel("I See...");
	private JLabel image;
	private JTextArea infoText = new JTextArea();
	private Font choicesButtonFont = new Font("Arial", Font.BOLD, 40);
	private Font countDownFont = new Font("Arial", Font.BOLD, 400);
	private ImageIcon background;
	private Timer countDownTimer;
	private GameDifficulty difficulty;
	private int countDownNumber = 3;
	private int timerSeconds = 20;
	private QuizItem[] quizItems;
	
	// Constructor
	public InGameScreen(JFrame frame, GameDifficulty level)
	{
		window = frame;
		difficulty = level;
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
		
		// Call the function to initialize the quiz items
		initializeQuizItems();
		
		// Countdown
		initializeCountdown();
		countDown();
	}
	
	private void initializeCountdown()
	{
		countDownLabel = new JLabel("", SwingConstants.CENTER);
		countDownLabel.setFont(countDownFont);
		countDownLabel.setForeground(Color.BLACK);
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
					
					// Call the function to initialize the text and image panel
					initializeViewPanel();
					
					// Call the function to initialize the in-game buttons
					initializeButtons();
					
					// Call the function to initialize the upper panel
					initializeUpperPanel();
					
					window.getContentPane().add(backgroundLabel);
					
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
		final int buttonWidth = 500;
		final int buttonHeight = 100;
		final int padding = 15;
		
		// Initialize the choice button panel
		choicesButtonPanel = new JPanel();
		choicesButtonPanel.setLayout(new GridLayout(2,2,padding,padding));
		choicesButtonPanel.setBackground(new Color(0,0,0,0));
		choicesButtonPanel.setBounds(
			(window.getWidth() / 2) - buttonWidth,
			500,
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
			GameViewManager.getInstance().switchToResultsScreen(difficulty);
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
			GameViewManager.getInstance().switchToResultsScreen(difficulty);
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
			GameViewManager.getInstance().switchToResultsScreen(difficulty);
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
			GameViewManager.getInstance().switchToResultsScreen(difficulty);
		});
		choicesButtonPanel.add(choices[3]);
		
		// Add the choice button panel to JFrame/window
		window.getContentPane().add(choicesButtonPanel);
	}
	
	private void initializeViewPanel()
	{
		final int panelWidth = (int)(window.getWidth() * 0.8);
		final int panelHeight = 350;
		
		viewPanel.setBounds(
			window.getWidth() / 2 - panelWidth / 2,
			120,
			panelWidth,
			panelHeight
		);
		viewPanel.setBackground(new Color(0,0,0,0));
		viewPanel.setLayout(new GridLayout(1,2));
		
		iSeeText.setFont(new Font("Arial", Font.BOLD, 80));
		iSeeText.setVerticalAlignment(SwingConstants.CENTER);
		iSeeText.setHorizontalAlignment(SwingConstants.CENTER);
		iSeeText.setForeground(Color.BLACK);
		viewPanel.add(iSeeText);
		
		image = new JLabel(quizItems[0].getImage());
		viewPanel.add(image);
		
//		infoText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam magna ipsum, dapibus vitae justo id, ultrices vehicula ante. Nam nec ultricies ante, et fringilla ligula. Nulla ultrices erat sed velit porttitor, et dignissim felis convallis. Vestibulum ut est vitae tellus bibendum commodo vel in elit. Vestibulum a urna nunc. Aenean.");
//		infoText.setForeground(Color.BLACK);
//		infoText.setBackground(Color.WHITE);
//		infoText.setFont(new Font("Arial", Font.PLAIN, 30));
//		infoText.setLineWrap(true);
//		infoText.setWrapStyleWord(true);
//		infoText.setEditable(false);
//		infoText.setHighlighter(null);
//		viewPanel.add(infoText);
		
		// temporary
//		iSeeText.setText("Image");
//		viewPanel.add(iSeeText);
		
		window.getContentPane().add(viewPanel);
	}
	
	private void initializeUpperPanel()
	{
		final int fontSize = 40;
		
		upperPanel.setBounds(0,40,window.getWidth(),60);
		upperPanel.setBackground(new Color(0,0,0,0));
		upperPanel.setLayout(new GridLayout(1,3));
		
		difficultyText = new JLabel("Difficulty: " + 
			(difficulty == GameDifficulty.EASY ? "Easy" : difficulty == GameDifficulty.MEDIUM ? "Medium" : difficulty == GameDifficulty.HARD ? "Hard" : ""),
			SwingConstants.CENTER);
		difficultyText.setFont(new Font("Arial", Font.PLAIN, fontSize));
		difficultyText.setForeground(Color.BLACK);
		upperPanel.add(difficultyText);
		
		timerText = new JLabel("" + timerSeconds, SwingConstants.CENTER);
		timerText.setFont(new Font("Arial", Font.PLAIN, fontSize));
		timerText.setForeground(Color.BLACK);
		upperPanel.add(timerText);
		
		correctAnswersText = new JLabel("0/10", SwingConstants.CENTER);
		correctAnswersText.setFont(new Font("Arial", Font.PLAIN, fontSize));
		correctAnswersText.setForeground(Color.BLACK);
		upperPanel.add(correctAnswersText);
		
		window.getContentPane().add(upperPanel);
	}
	
	// Initialize the quiz items
	private void initializeQuizItems()
	{
		// If the selected difficulty is EASY, load quiz items for easy level
		if (difficulty == GameDifficulty.EASY)
		{
			quizItems = new QuizItem[] {
				new QuizItem("Chemical Change","",new ImageIcon(getClass().getResource("/images/easy/easy_Chemical Change.jpg"))),
				new QuizItem("Crust","",new ImageIcon(getClass().getResource("/images/easy/easy_Crust.jpg"))),
				new QuizItem("Electrical Energy","",new ImageIcon(getClass().getResource("/images/easy/easy_Electrical Energy.jpg"))),
				new QuizItem("Force","",new ImageIcon(getClass().getResource("/images/easy/easy_Force.jpg"))),
				new QuizItem("Friction","",new ImageIcon(getClass().getResource("/images/easy/easy_Friction.jpg"))),
				new QuizItem("Gravity","",new ImageIcon(getClass().getResource("/images/easy/easy_Gravity.jpg"))),
				new QuizItem("Inner Core","",new ImageIcon(getClass().getResource("/images/easy/easy_Inner Core.jpg"))),
				new QuizItem("Invertebrates","",new ImageIcon(getClass().getResource("/images/easy/easy_Invertebrates.jpg"))),
				new QuizItem("Kinetic Energy","",new ImageIcon(getClass().getResource("/images/easy/easy_Kinetic Energy.jpg"))),
				new QuizItem("Law of Acceleration","",new ImageIcon(getClass().getResource("/images/easy/easy_Law of Acceleration.jpg"))),
				new QuizItem("Mantle","",new ImageIcon(getClass().getResource("/images/easy/easy_Mantle.jpg"))),
				new QuizItem("Natural Disaster","",new ImageIcon(getClass().getResource("/images/easy/easy_Natural Disaster.jpg"))),
				new QuizItem("Outer Core","",new ImageIcon(getClass().getResource("/images/easy/easy_Outer Core.jpg"))),
				new QuizItem("Photosynthesis","",new ImageIcon(getClass().getResource("/images/easy/easy_Photosynthesis.jpg"))),
				new QuizItem("Physical Change","",new ImageIcon(getClass().getResource("/images/easy/easy_Physical Change.jpg"))),
				new QuizItem("Potential Energy","",new ImageIcon(getClass().getResource("/images/easy/easy_Potential Energy.jpg"))),
				new QuizItem("Reflection","",new ImageIcon(getClass().getResource("/images/easy/easy_Reflection.jpg"))),
				new QuizItem("Refraction","",new ImageIcon(getClass().getResource("/images/easy/easy_Refraction.jpg"))),
				new QuizItem("Sound Energy","",new ImageIcon(getClass().getResource("/images/easy/easy_Sound Energy.jpg"))),
				new QuizItem("Winter","",new ImageIcon(getClass().getResource("/images/easy/easy_Winter.jpg"))),
			};
		}
		// Else if MEDIUM, load quiz items for medium level
		else if (difficulty == GameDifficulty.MEDIUM)
		{
			quizItems = new QuizItem[] {
				new QuizItem("Abiotic Factors","",new ImageIcon(getClass().getResource("/images/medium/medium_Abiotic Factors.jpg"))),
				new QuizItem("Air Resistance","",new ImageIcon(getClass().getResource("/images/medium/medium_Air Resistance.jpg"))),
				new QuizItem("Avalanche","",new ImageIcon(getClass().getResource("/images/medium/medium_Avalanche.jpg"))),
				new QuizItem("Biotic Factors","",new ImageIcon(getClass().getResource("/images/medium/medium_Biotic Factors.jpg"))),
				new QuizItem("Chlorophyll","",new ImageIcon(getClass().getResource("/images/medium/medium_Chlorophyll.jpg"))),
				new QuizItem("Conduction","",new ImageIcon(getClass().getResource("/images/medium/medium_Conduction.jpg"))),
				new QuizItem("DOST","",new ImageIcon(getClass().getResource("/images/medium/medium_DOST.jpg"))),
				new QuizItem("Drought","",new ImageIcon(getClass().getResource("/images/medium/medium_Drought.jpg"))),
				new QuizItem("Erosion","",new ImageIcon(getClass().getResource("/images/medium/medium_Erosion.jpg"))),
				new QuizItem("Flammable","",new ImageIcon(getClass().getResource("/images/medium/medium_Flammable.jpg"))),
				new QuizItem("Food Chain","",new ImageIcon(getClass().getResource("/images/medium/medium_Food Chain.jpg"))),
				new QuizItem("Food Web","",new ImageIcon(getClass().getResource("/images/medium/medium_Food Web.jpg"))),
				new QuizItem("Germination","",new ImageIcon(getClass().getResource("/images/medium/medium_Germination.jpg"))),
				new QuizItem("Helium","",new ImageIcon(getClass().getResource("/images/medium/medium_Helium.jpg"))),
				new QuizItem("Lahar","",new ImageIcon(getClass().getResource("/images/medium/medium_Lahar.jpg"))),
				new QuizItem("Lemur","",new ImageIcon(getClass().getResource("/images/medium/medium_Lemur.jpg"))),
				new QuizItem("Metamorphosis","",new ImageIcon(getClass().getResource("/images/medium/medium_Metamorphosis.jpg"))),
				new QuizItem("Plant Cell","",new ImageIcon(getClass().getResource("/images/medium/medium_Plant Cell.jpg"))),
				new QuizItem("Radioactive","",new ImageIcon(getClass().getResource("/images/medium/medium_Radioactive.jpg"))),
				new QuizItem("Vaporization","",new ImageIcon(getClass().getResource("/images/medium/medium_Vaporization.jpg"))),
			};
		}
		// Otherwise load quiz items for hard level
		else
		{
			quizItems = new QuizItem[] {
				new QuizItem("African Wild Dog","",new ImageIcon(getClass().getResource("/images/hard/hard_African Wild Dog.jpg"))),
				new QuizItem("Bioluminescence","",new ImageIcon(getClass().getResource("/images/hard/hard_Bioluminescence.jpg"))),
				new QuizItem("Central Nervous System","",new ImageIcon(getClass().getResource("/images/hard/hard_Central Nervous System.jpg"))),
				new QuizItem("Elliptical Galaxy","",new ImageIcon(getClass().getResource("/images/hard/hard_Elliptical Galaxy.jpg"))),
				new QuizItem("Erythrocytes","",new ImageIcon(getClass().getResource("/images/hard/hard_Erythrocytes.jpg"))),
				new QuizItem("Fennec Fox","",new ImageIcon(getClass().getResource("/images/hard/hard_Fennec Fox.jpg"))),
				new QuizItem("Igneous Rocks","",new ImageIcon(getClass().getResource("/images/hard/hard_Igneous Rocks.jpg"))),
				new QuizItem("Integumentary System","",new ImageIcon(getClass().getResource("/images/hard/hard_Integumentary System.jpg"))),
				new QuizItem("Irregular Galaxy","",new ImageIcon(getClass().getResource("/images/hard/hard_Irregular Galaxy.jpg"))),
				new QuizItem("Leukocytes","",new ImageIcon(getClass().getResource("/images/hard/hard_Leukocytes.jpg"))),
				new QuizItem("Neil Armstrong","",new ImageIcon(getClass().getResource("/images/hard/hard_Neil Armstrong.jpg"))),
				new QuizItem("Nikola Tesla","",new ImageIcon(getClass().getResource("/images/hard/hard_Nikola Tesla.jpg"))),
				new QuizItem("Nimbostratus","",new ImageIcon(getClass().getResource("/images/hard/hard_Nimbostratus.jpg"))),
				new QuizItem("Pygmy Marmoset","",new ImageIcon(getClass().getResource("/images/hard/hard_Pygmy Marmoset.jpg"))),
				new QuizItem("Rafflesia Arnoldii","",new ImageIcon(getClass().getResource("/images/hard/hard_Rafflesia Arnoldii.jpg"))),
				new QuizItem("Sclera","",new ImageIcon(getClass().getResource("/images/hard/hard_Sclera.jpg"))),
				new QuizItem("Seismograph","",new ImageIcon(getClass().getResource("/images/hard/hard_Seismograph.jpg"))),
				new QuizItem("Snow Leopard","",new ImageIcon(getClass().getResource("/images/hard/hard_Snow Leopard.jpg"))),
				new QuizItem("Spiral Galaxy","",new ImageIcon(getClass().getResource("/images/hard/hard_Spiral Galaxy.jpg"))),
				new QuizItem("Wildfire","",new ImageIcon(getClass().getResource("/images/hard/hard_Wildfire.jpg"))),
			};
		}
	}
}
