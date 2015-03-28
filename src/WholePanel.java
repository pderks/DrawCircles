// Assignment #: 7
//         Name: Parker Derks
//    StudentID: 1204804363
//      Lecture: MWF 10:30 - 11:20 AM
//  Description: Creates a panel where circles can be drawn
//				 using different colors. You can undo drawing
//				 a circle and you can erase all of the circles.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; // to use listener interfaces
import java.util.ArrayList;

public class WholePanel extends JPanel
{
   private ArrayList circleList;
   private Circle[] backup;
   private Color currentColor;
   private Canvas canvas;
   private JPanel topPanel;
   private JButton undo;
   private JComboBox chooseColor;
   private Point clickPt;
   private Point radiusPt;
   private Point releasePt;
   private int centerX;
   private int centerY;
   private int dragRadius;
   private int diameter;
   private boolean dragMouse;
   private JLabel message = new JLabel();

   public WholePanel()
    {
      // here we use black to draw a circle
      currentColor = Color.black;

      circleList = new ArrayList();
      topPanel = new JPanel();
      message.setText("Click and Drag to create circles!!");

      //creates a new buttons, adds them to the panel, and adds an action listener
      undo = new JButton("Undo");
      topPanel.add(undo);
      undo.addActionListener(new UndoButtonListener());
      
      JButton erase = new JButton("Erase");
      topPanel.add(erase);
      erase.addActionListener(new EraseButtonListener());
           
      //creates a combo box, adds it to the panel, and adds items to it
      chooseColor = new JComboBox();
      chooseColor.addActionListener(new ColorListener());
      chooseColor.addItem("Black");
      chooseColor.addItem("White");
      chooseColor.addItem("Red");
      chooseColor.addItem("Orange");
      chooseColor.addItem("Yellow");
      chooseColor.addItem("Green");
      chooseColor.addItem("Blue");
      chooseColor.addItem("Magenta");
      chooseColor.addItem("Pink");
      chooseColor.addItem("Gray");
      topPanel.add(chooseColor);
      
      //creates a canvas with a mouse listener and a mouse moution listener
      canvas = new Canvas();
      canvas.addMouseListener(new PointListener());
      canvas.addMouseMotionListener(new PointListener());
      canvas.add(message); //Jlabel is for the undo button
      
      JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, canvas);

      setLayout(new BorderLayout());
      add(sp);

     }


    private class Canvas extends JPanel
     {
      public void paintComponent(Graphics page)
       {
         super.paintComponent(page);

         setBackground(Color.white);
         
        //draws all circles in the circle list on the canvas 
         for(int i = 0; i < circleList.size(); i++)
         {
        	 Circle tempC = (Circle) circleList.get(i);
        	 tempC.draw(page);
        	 
         }
         
         //checks to see if mouse is dragged and draws an unfilled circle
         if(dragMouse == true)
         {
        	 page.setColor(Color.black);
        	 page.drawOval(clickPt.x, clickPt.y, 2, 2);
        	 page.drawOval(clickPt.x - dragRadius, clickPt.y - dragRadius, 2*dragRadius, 2*dragRadius);
         }
       }
     } //end of Canvas class

    private class PointListener implements MouseListener, MouseMotionListener
     {
                 public void mousePressed(MouseEvent event)
                  {
                	 clickPt = event.getPoint(); //gets the point where the mouse was clicked
                	 message.setText(""); //resets message label
                  }
                 public void mouseReleased(MouseEvent event)
                  {
                	 //gets the point where the mouse is released
                     releasePt = event.getPoint();
                     int x = releasePt.x;
                     int y = releasePt.y;
                     
                     //calculates the radius on diameter from where mouse is clicked to where it is released
                     int radius = (int) Math.sqrt(Math.pow((double)(x-clickPt.x),2)+Math.pow((double)(y-clickPt.y),2));
                     diameter = 2*radius;
                     
                     //gives center point of the circle in the x and y coordinate
                     centerX = clickPt.x - radius;
                     centerY = clickPt.y - radius;
                     
                     //creates a new circle and adds it to the circle list
                     Circle circle = new Circle(centerX, centerY, diameter, currentColor);
                     circleList.add(circle);
                    
                     dragMouse = false; //resets the dragMouse flag back to false
                	 repaint(); //repaints the canvas
                  }
                 public void mouseClicked(MouseEvent event) {}
                 public void mouseEntered(MouseEvent event) {}
                 public void mouseExited(MouseEvent event) {}
                 public void mouseDragged(MouseEvent event)
                   {
                     //gets the point the mouse is dragged to
                	 radiusPt = event.getPoint();
                	 int x = radiusPt.x;
                	 int y = radiusPt.y;
                	 
                	 //calculates the radius of the where the mouse is dragged to
                	 dragRadius = (int) Math.sqrt(Math.pow((double)(x-clickPt.x),2)+Math.pow((double)(y-clickPt.y),2));
                	 
                	 dragMouse = true; //sets dragMouse flag to true
                	 repaint(); //repaints the canvas
                   }
                 public void mouseMoved(MouseEvent event) {}

     } //end of PointListener
    
    private class UndoButtonListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent event)
    	{
    		try
    		{
	    		if(circleList.size() == 0)
	    		{
	    			//adds all circles from the backup array to the circleList
	    			for(int i = 0; i < backup.length; i++)
	    			{
	    				circleList.add(backup[i]);
	    			}
	    				
	    			backup =  null; //erases the backup array
	    		}
	    			
	    		else
	    		{
	    			//removes the last circle added to circleList
	    			circleList.remove(circleList.size() - 1);
	    		}
    		}
    		
    		//displays a message if the undo action cant be performed
    		catch(NullPointerException e)
    		{
    			message.setText("Can't undo");
    			message.setForeground(Color.red);
    		}
    			
    		repaint(); //repaints the canvas
    		
    	}
    }
    
    private class EraseButtonListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent event)
    	{
    		//creates an array to back up the circles
    		backup = new Circle[circleList.size()];
    		
    		//stores all circles from circleList into the backup array
    		for(int i = 0; i < circleList.size(); i++)
    		{
    			Circle tempCir = (Circle)circleList.get(i);
    			backup[i] = tempCir;
    		}
    		
    		circleList.clear(); //erases the circleList
    		
    		repaint(); //repaints the canvas
    	}
    }
    
    private class ColorListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent event)
    	{
    		//sets currentColor equal to the color that is currently selected
    		if(chooseColor.getSelectedIndex() == 0)
           	 currentColor = Color.black;
            if(chooseColor.getSelectedIndex() == 1)
           	 currentColor = Color.white;
            if(chooseColor.getSelectedIndex() == 2)
           	 currentColor = Color.red;
            if(chooseColor.getSelectedIndex() == 3)
           	 currentColor = Color.orange;
            if(chooseColor.getSelectedIndex() == 4)
           	 currentColor = Color.yellow;
            if(chooseColor.getSelectedIndex() == 5)
           	 currentColor = Color.green;
            if(chooseColor.getSelectedIndex() == 6)
           	 currentColor = Color.blue;
            if(chooseColor.getSelectedIndex() == 7)
           	 currentColor = Color.magenta;
            if(chooseColor.getSelectedIndex() == 8)
           	 currentColor = Color.pink;
            if(chooseColor.getSelectedIndex() == 9)
           	 currentColor = Color.gray;
    	}
    }

} // end of Whole Panel Class