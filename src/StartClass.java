//the sole purpose of this file is to allow an applet to run as an application
//so it can be exported as a runnable jar file
//also in assignment7.mf I changed the second line to read Main-Class: StartClass
public class StartClass {

	public static void main(String[] args) 
	{
		Assignment7 theApp = new Assignment7();
		theApp.init();
		theApp.start();
		
		javax.swing.JFrame window = new javax.swing.JFrame("Circles");
		window.setContentPane(theApp);
		window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setSize(1000, 600);
		window.setVisible(true);
	}

}
