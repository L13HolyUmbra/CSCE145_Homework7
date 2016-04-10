/**
 * 
 * @author Dion de Jong
 * @version November 12, 2013
 * Create a mine maze game that allows the user to 
 * choose a path in a randomly generated grid with
 * 2 mines placed somewhere. The user must reach from start to finish without 
 * hitting a mine. 
 */

//imports 
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//main class name
public class MineField extends JFrame implements ActionListener  {

	//Create the jLabel
	private JLabel Clearinfo; 
	private JLabel Instructions; 

	//create the instances of all the buttons
	private JButton B0; 
	private JButton B1; 
	private JButton B2; 
	private JButton B3; 
	private JButton B4; 
	private JButton B5; 
	private JButton B6; 
	private JButton B7; 
	private JButton B8; 
	private JButton B9; 
	private JButton B10; 
	private JButton B11; 
	private JButton B12; 
	private JButton B13; 
	private JButton B14; 
	private JButton B15; 
	private JButton B16; 
	private JButton B17; 
	private JButton B18; 
	private JButton B19; 
	private JButton B20; 
	private JButton B21; 
	private JButton B22; 
	private JButton B23; 
	private JButton B24; 
	private JButton Clear;
	private JButton Done;
	private JButton Start;
	private JButton Finish;
	private JButton Mine1;
	private JButton Mine2; 

	//create the instance Button array that starts off with the generic buttons in every index
	private JButton[][] ButtonArray = {{B0,B1,B2,B3,B4},{B5,B6,B7,B8,B9},{B10,B11,B12,B13,B14},{B15,B16,B17,B18,B19},{B20,B21,B22,B23,B24}};
	
	//create a string array for the path the user takes that will later test if the user hit a mine.
	public String[] path = new String[69];
	int pathIndex = 0;

	//create a generic dimension used for sizes later
	Dimension d = new Dimension(500,500); 
	
	//The constructor for the Minefield game. 
	public MineField()
	{
		//Set the size of the window and call the initialize method that will create the game. 
		super("Mine maze");
		this.setSize(600,600);
		this.initialize();
		this.setVisible(true); 	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	//the initialize method creates all the neccessary data, arrays, buttons, and adds all the characteristics to all of these to make them work.
	public void initialize()
	{
		//Create the content pane that will hold everything.
		Container c = this.getContentPane(); 
		c.setLayout(null);
		c.setBackground(new Color(255, 127, 0));
		c.setForeground(new Color(0,0,0));
		c.setVisible(true);

		//create the panel that holds the buttons, it sits inside your content pane. 
		JPanel ButtonPanel = new JPanel(); 
		Dimension d = new Dimension(500,500);
		ButtonPanel.setPreferredSize(d); 
		ButtonPanel.setLayout(new GridLayout(5,5)); 
		ButtonPanel.setVisible(true); 



		//use nested for loops to give basic characteristics to the generic buttons. 
		for(int row=0; row<5;row++)
		{
			for(int column = 0; column <5; column++)
			{
				ButtonArray[row][column] = new JButton(); 
				ButtonArray[row][column].setBounds(115*row,95*column,115,95);
				ButtonArray[row][column].setActionCommand("B" +Integer.toString(5*row+column));
				ButtonArray[row][column].addActionListener(this);
				ButtonArray[row][column].setBackground(Color.WHITE);
				c.add(ButtonArray[row][column]);
			}
		}

		//generate specific random values for each "special" button (Start, Finish and the 2 mines)
		//The random values should be created only if they aren't the same as the other special buttons' values
		//i.e. You shouldn't be able to place start and finish in the same place or set a mine on the start button. 
		int valuexStart = (int)(Math.random()*5); 
		int valueyStart = (int)(Math.random()*5); 
		ButtonArray[valuexStart][valueyStart].setText("Start");

		int valuexFinish = (int)(Math.random()*5); 
		int valueyFinish = (int)(Math.random()*5); 
		while (valuexFinish == valuexStart && valueyFinish == valueyStart)
		{
			valuexFinish = (int)(Math.random()*5); 
			valueyFinish = (int)(Math.random()*5); 
		}
		ButtonArray[valuexFinish][valueyFinish].setText("Finish");

		int valuexMine1 = (int)(Math.random()*5); 
		int valueyMine1 = (int)(Math.random()*5); 
		while (valuexMine1 == valuexStart && valueyMine1 == valueyStart || valuexFinish == valuexMine1 && valueyFinish == valueyMine1)
		{
			valuexMine1 = (int)(Math.random()*5); 
			valueyMine1 = (int)(Math.random()*5); 
		}
		this.Mine1 = ButtonArray[valuexMine1][valueyMine1];
		this.Mine1.setActionCommand("Mine1");

		int valuexMine2 = (int)(Math.random()*5); 
		int valueyMine2 = (int)(Math.random()*5); 
		while (valuexMine2 == valuexStart && valueyMine2 == valueyStart || valuexFinish == valuexMine2 && valueyFinish == valueyMine2 || valuexMine2 == valuexMine1 && valueyMine2 == valueyMine1 )
		{
			valuexMine2 = (int)(Math.random()*5); 
			valueyMine2 = (int)(Math.random()*5); 
		}
		this.Mine2 = ButtonArray[valuexMine2][valueyMine2];
		this.Mine2.setActionCommand("Mine2");

		//create the two other special buttons that will recreate the game in the JPanel. 
		//These buttons sit inside of the content pane. 
		this.Clear = new JButton();
		this.Clear.setBounds(200, 500, 100, 30);
		this.Clear.setActionCommand("Clear");
		this.Clear.addActionListener(this);
		this.Clear.setText("Clear");
		c.add(Clear); 

		this.Done = new JButton();
		this.Done.setBounds(300, 500, 100, 30);
		this.Done.setActionCommand("Done");
		this.Done.addActionListener(this);
		this.Done.setText("Done");
		c.add(Done);

		//Create a label that will remind users how to start a new game. 
		this.Clearinfo = new JLabel();
		this.Clearinfo.setText("Select: clear to begin Game. When you finished your path click done to see if you won");
		this.Clearinfo.setBounds(00,550,900,100);
		c.add(Clearinfo);
		
		this.Instructions = new JLabel();
		this.Instructions.setText("Start from the start button and end at the finish button."
				+ "You may only move horizontally or vertically. DO NOT MOVE DIAGONALLY");
		this.Instructions.setBounds(00,600,900,100);
		c.add(Instructions);

	}

	//use the action listeners and commands to see what the user has pressed
	public void actionPerformed(ActionEvent event){
		String source = event.getActionCommand();		

		
		//System.out.println(source + " " + pathIndex);
		//If the user clicks mine1, be sure to specifically add this to the path array
		if(source.equals("Mine1"))
		{
			String btn = (String)event.getActionCommand();
			this.Mine1.setBackground(Color.green);
			path[pathIndex] = btn;
			pathIndex++;
		}

		//If the user clicks mine2, be sure to specifically add this to the path array
		else if(source.equals("Mine2"))
		{
			String btn = (String)event.getActionCommand();
			this.Mine2.setBackground(Color.green);
			path[pathIndex] = btn;
			pathIndex++;
		}

		//Otherwise the user will be clicking just a regular button (the start and finish aren't counted as special
		//The program just adds the regular button value to the array
		else 
		{
			String btn = (String)event.getActionCommand();
			path[pathIndex] = btn;
			pathIndex++;
		}

		//When the user clicks each regular button, turn it green. 
		for(int row=0; row<5;row++)
		{
			for(int column = 0; column <5; column++)
			{
				if (source.equals("B"+ Integer.toString(5*row+column)))
				{
					ButtonArray[row][column].setBackground(Color.green);
				}
			}
		}

		//Commands for if the user clicks clear. It resets a lot of the values and essentially resets the game.
		if(source.equals("Clear"))
		{
			//begin the game
			//reset the basic Grid of buttons
			for(int row=0; row<5;row++)
			{
				for(int column = 0; column <5; column++)
				{
					ButtonArray[row][column].setBackground(Color.WHITE);
					ButtonArray[row][column].setText("");
					ButtonArray[row][column].setActionCommand("B" +Integer.toString(5*row+column));
				}
			}
			
			//reassign the random special buttons
			int valuexStart = (int)(Math.random()*5); 
			int valueyStart = (int)(Math.random()*5); 
			ButtonArray[valuexStart][valueyStart].setText("Start");

			int valuexFinish = (int)(Math.random()*5); 
			int valueyFinish = (int)(Math.random()*5); 
			while (valuexFinish == valuexStart && valueyFinish == valueyStart)
			{
				valuexFinish = (int)(Math.random()*5); 
				valueyFinish = (int)(Math.random()*5); 
			}
			ButtonArray[valuexFinish][valueyFinish].setText("Finish");

			int valuexMine1 = (int)(Math.random()*5); 
			int valueyMine1 = (int)(Math.random()*5); 
			while (valuexMine1 == valuexStart && valueyMine1 == valueyStart || valuexFinish == valuexMine1 && valueyFinish == valueyMine1)
			{
				valuexMine1 = (int)(Math.random()*5); 
				valueyMine1 = (int)(Math.random()*5); 
			}
			this.Mine1 = ButtonArray[valuexMine1][valueyMine1];
			this.Mine1.setActionCommand("Mine1");

			int valuexMine2 = (int)(Math.random()*5); 
			int valueyMine2 = (int)(Math.random()*5); 
			while (valuexMine2 == valuexStart && valueyMine2 == valueyStart || valuexFinish == valuexMine2 && valueyFinish == valueyMine2 || valuexMine2 == valuexMine1 && valueyMine2 == valueyMine1 )
			{
				valuexMine2 = (int)(Math.random()*5); 
				valueyMine2 = (int)(Math.random()*5); 
			}
			this.Mine2 = ButtonArray[valuexMine2][valueyMine2];
			this.Mine2.setActionCommand("Mine2");

			//reset the array and index for the array
			for (int i =0; i <path.length; i++)
			{
				path[i] = null; 
			}

			pathIndex = 0; 

		}


		//instance boolean to see if the user won. 
		boolean Winner = true; 

		//if the user clicked a mine somewhere in their path to finish, the array tests for it and changes the boolean to false if there is a mine.
		for (int i =0; i < path.length; i ++)
		{
			if (path[i] == ("Mine1") || path[i] == ("Mine2"))
			{
				Winner = false; 
			}
		}

		//when the user clicks done it will tell them if they won or lost and reveal where the mines were
		if(source.equals("Done"))
		{
			this.Mine1.setBackground(Color.red);
			this.Mine2.setBackground(Color.red);
			if (Winner == true)
			{
				JOptionPane.showMessageDialog(null, "Yay you don't go boom");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You blew up");
			}


		}

		//			{
		//			if (!path[0].equals("Start"))
		//			{
		//				JOptionPane.showMessageDialog(null, "You did not click on start first. In order to win a game, you must click start first");
		//			}
		//			
		//			else if (path[path.length].equals("Finish"))


	}

	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//create an object of the game to be played. 
		MineField theGame = new MineField();


	}
} 




/* 
 * 3. set text when clicking on a new tile
 * 
 * 4. Create an array for the path you take, 
 * create a for loop for each item in path 
 * 
 * if item == mine || 
 * 
 */
