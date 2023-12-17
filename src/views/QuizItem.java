package views;

import javax.swing.ImageIcon;

public class QuizItem
{
	private String name, description;
	private ImageIcon image;
	
	public QuizItem(String name, String description, ImageIcon image)
	{
		this.name = name;
		this.description = description;
		this.image = image;
	}
	
	public String getName() { return this.name; }
	public String getDescription() { return this.description; }
	public ImageIcon getImage() { return this.image; }
}
