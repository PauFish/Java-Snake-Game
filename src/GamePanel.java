
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE=25; //how big objects are in the game
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; //calculate how many objects fit in the screen
	static final int DELAY = 75; //Game Speed 80 slower 70 faster
	
	final int x[]= new int[GAME_UNITS]; //all the coordinates of X
	final int y[]= new int[GAME_UNITS];
	
	int bodyParts=6; //will start with 6 bodyParts = snake length
	int applesEaten; //will start 0
	int appleX; //where apple appears X axis
	int appleY; //where apple appears Y axis
	char direction = 'R'; //snake goes Right when starts the game
	boolean running = false; // "press a bottom to start"
	
	Timer timer; //declare a timer
	Random random; // Instance of the class Random
	
	GamePanel(){
			random= new Random();
			this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
			this.setBackground(Color.black);
			this.setFocusable(true);
			this.addKeyListener(new MyKeyAdapter());
			startGame()
;		}
	
	//method
	public void startGame() {
		newApple(); //1 thing at start - call a new apple to create a new apple on the screen.
		running = true;
		timer = new Timer(DELAY,this); // how fast game runs,   *this* because we use action listener interface
		timer.start(); //starts timer
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		for(int i =0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) { //draw a grid
			 g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			 g.drawLine(0, i*UNIT_SIZE, SCREEN_HEIGHT, i*UNIT_SIZE );
		}
		g.setColor(Color.red);//set apple color
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //Shape and apple size
		
		//draw snake head and body
		for(int i = 0; i<bodyParts;i++) {
			//snake head
			if(i==0){
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);	
			}
			//snake Body
			else {
				g.setColor(new Color(45,180,0));
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);	
			}
		}
	}
	public void newApple() {
		
		//TO PLACE AN APPLE RANDOMLY 
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //random int number from 1 to the end of the width
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}	
	public void move() {
		for(int i = bodyParts;i>0;i--){
			x[i] = x[i-1]; //shifting coordinates by 1
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0]=y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0]=y[0]+UNIT_SIZE;
			break;
		case 'L':
			x[0]=x[0]-UNIT_SIZE;
			break;
		case 'R':
			x[0]=x[0]+UNIT_SIZE;
			break;
			
		}
	}
	public void checkApple() { 
		
	}
	public void checkCollisions() {
		
	}
	public void gameOver(Graphics g) {
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) { //if game is running == true
			move();
			checkApple();
			checkCollisions();
			
			
		}
		//if is not running, repaint=restart 
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void KeyPressed(KeyEvent e) {
			
		}
	}
	

}
