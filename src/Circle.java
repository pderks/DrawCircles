// Assignment #: 7
//         Name: Parker Derks
//    StudentID: 1204804363
//      Lecture: MWF 10:30 - 11:20 AM
//  Description: This class creates circles with an x and y coordinate,
//				 a diameter and a color

import java.awt.*;

public class Circle {
	
	private int x;
	private int y;
	private int diameter;
	private Color color;
	
	//constructor for a circle taking in an x and y coordinate,
	//a diameter and a color
	public Circle(int x, int y, int diameter, Color color)
	{
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.color = color;
	}
	
	//method that draws the circle
	public void draw(Graphics page)
	{
		page.setColor(color);
		page.fillOval(x, y, diameter, diameter);
		page.drawOval(x, y, diameter, diameter);
	}

}
