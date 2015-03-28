// Assignment #: 7
//         Name: Parker Derks
//    StudentID: 1204804363
//      Lecture: MWF 10:30 - 11:20 AM
//  Description: The Assignment 7 creates a WholePanel that is
//  an extension of JPanel, add it to its content, and set
//  a size for the applet.

import javax.swing.*;

public class Assignment7 extends JApplet
{
	 public void init()
	  {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    
			public void run(){
			 // create a WholePanel object and add it to the applet
	    WholePanel wholePanel = new WholePanel();
	    getContentPane().add(wholePanel);
	
	    //set applet size to 400 X 400
	    setSize (400, 400);
			}
		 });
	  }

}
