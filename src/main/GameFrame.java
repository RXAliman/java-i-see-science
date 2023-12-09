package main;

import java.awt.Toolkit;

import javax.swing.JFrame;


// JAVA WINDOW CLASS
@SuppressWarnings("serial")
public class GameFrame extends JFrame
{	
	// Constructor
	public GameFrame()
	{
		// Initialize window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("I SciEncE");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setResizable(false);
		getContentPane().setLayout(null);
	}
	
	// Get the screen width
	@Override
	public int getWidth()
	{
		return (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	
	// Get the screen height
	@Override
	public int getHeight()
	{
		return (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
}
