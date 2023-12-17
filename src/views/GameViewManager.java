package views;

import javax.swing.JFrame;

// SINGLETON CLASS TO MANAGE GAME VIEWS
public class GameViewManager
{
	// Ensures singleton implementation
	private static GameViewManager instance;
	private GameViewManager() { }
	
	private JFrame window;
	private GameView currentView;
	
	// Getter method to access the singleton class object
	public static synchronized GameViewManager getInstance()
	{
		if (instance == null) instance = new GameViewManager();
		return instance;
	}
	
	// Sets the main frame/window of the game
	public void setMainFrame(JFrame frame)
	{
		window = frame;
	}
	
	// Switch current view to the Main Menu
	public void switchToMainMenu()
	{
		// Set currentView to 'MainMenu' screen
		currentView = new MainMenu(window);
		currentView.initialize();
	}
	
	// Switch current view to 'In-game' screen
	public void switchToInGameScreen(GameDifficulty difficulty)
	{
		// Set currentView to 'InGameScreen' view
		currentView = new InGameScreen(window, difficulty);
		currentView.initialize();
	}
	
	// Switch current view to 'Results' screen
	public void switchToResultsScreen(GameDifficulty difficulty)
	{
		// Set currentView to 'InGameScreen' view
		currentView = new ResultsScreen(window, difficulty);
		currentView.initialize();
	}
	
	// Exits the game
	public void exitGame()
	{
		window.dispose();
	}
}
