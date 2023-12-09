package main;

import javax.swing.SwingUtilities;

import views.GameViewManager;


// PROGRAM ENTRY CLASS
public class Main
{	
	// Main entry point
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(
		// Anonymous function to instantiate the Game Window
		() -> {
			GameFrame window = new GameFrame();
			
			GameViewManager.getInstance().setMainFrame(window);
			GameViewManager.getInstance().switchToMainMenu();
			
			window.setVisible(true);
		});
	}
}
