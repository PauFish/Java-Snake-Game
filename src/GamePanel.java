
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
		if(running) {
			/*
			//draw a grid
			for(int i =0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) { 
				 g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				 g.drawLine(0, i*UNIT_SIZE, SCREEN_HEIGHT, i*UNIT_SIZE );
			}
			*/
				//APPLE
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
			//draw score
			g.setColor(Color.blue);
			g.setFont(new Font("Ink Free", Font.BOLD, 40)); // 75= font size
			FontMetrics metrics = getFontMetrics(g.getFont()); //align text center		
			g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()); //align score top of the screen and font size changed to 40
			
		}else {
			gameOver(g); //g = graphics
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
		//snake growing
		if((x[0] == appleX) && (y[0] == appleY)){ //x[0] and y [0] snake head
			bodyParts++;
			applesEaten++; //score
			newApple(); //create a random new apple
			
		}
	}
	public void checkCollisions() {
		
		
		//checks if head collides with body
		for (int i = bodyParts;i>0;i--) {
			if((x[0]==x[i])&&(y[0] ==y[i])) {
			running = false; //gameOver
			
			}
			}
		//check if head touches left border
		if(x[0]<0) {
			running = false;
		}
		//check if head touches right border
		if(x[0]>SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches TOP border
		if(y[0]<0) {
			running = false;
		}
		//check if head touches bottom border
		if(x[0]>SCREEN_HEIGHT) {
			running = false;
		}
		//stop timer if gameOver
		if(!running) {
			timer.stop();
		}
		
		
	}
	public void gameOver(Graphics g) {
		//Game Over Text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75)); // 75= font size
		FontMetrics metrics1 = getFontMetrics(g.getFont()); //align text center		
		g.drawString("Game Over",(SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2); //align GAMEOVER center of the screen
		
		//Score
				g.setColor(Color.red);
				g.setFont(new Font("Ink Free", Font.BOLD, 40)); // 75= font size
				FontMetrics metrics2 = getFontMetrics(g.getFont()); //align text center		
				g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics2.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()); //align score top of the screen
				
		
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
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			//move 90º
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;	
				
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;	
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;	
			}
		}
	}
	

}
