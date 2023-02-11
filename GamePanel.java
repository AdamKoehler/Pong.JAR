/**
* Name: Adam Koehler
* File: GamePanel.java
* Description: The GameFrame wraps around the panel. The panel is the canvas that the game is displayed on.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1200;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); // corresponding proportionally to GAME_WIDTH.
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 16;
	static final int PADDLE_WIDTH = 15;
	static final int PADDLE_HEIGHT = 100;
	static final int INITIAL_SPEED = 5;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void newBall() {
		// values given to ball {int x-axis positioning, int y-axis height, int ball width, int ball height, int speed}
		random = new Random();
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER, INITIAL_SPEED);
	}
	
	public void newPaddles() {
		// values being passed to the paddle {int x axis location, int y axis location, int width of paddle, int height of paddle, int paddle id (1 & 2)}
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
		
	}
	public void draw (Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		
	}
	public void move() {
		// improves the movement of ball and paddles. 
		
		paddle1.move();
		paddle2.y = ball.y;
		ball.move();
		
	}
	
	public void checkCollision() {
	
		// stop paddles at edge of the display.
		if (paddle1.y <= 0) { // bottom edge.
			paddle1.y = 0;
		}
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) { // top edge.
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		if (paddle2.y <= 0) { // bottom edge.
			paddle2.y = 0;
		}
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) { // top edge.
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT; 
		}
		//-----------------------------------------------------------
		// stops ball from going over the Y edges of panel.
		if(ball.y <= 0) { // bottom edge
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= (GAME_HEIGHT - BALL_DIAMETER)) { // top edge
			ball.setYDirection(-ball.yVelocity);
		}
		//------------------------------------------------------------
		// Stops ball from going past paddles.
		// .intersects returns boolean value if rectangle or other shape classes intersect.
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if (ball.yVelocity > 0) {
				ball.yVelocity++;
			}
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if (ball.yVelocity > 0) {
				ball.yVelocity++;
			}
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		//-----------------------------------------------------------
		// score
		if (ball.x >= (GAME_WIDTH-BALL_DIAMETER)) { // ball crosses right side of panel player 1 scores.
			score.player1++;
			newBall();
			newPaddles();
			}
		if (ball.x <= 0) { // ball crosses left side of panel player 2 scores.
			score.player2++;
			newBall();
			newPaddles();
			}
		
		
	}
	
	public void run() {
		// This is the game loop that will refresh the canvas and show the user a refreshed graphic.
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint(); // java awt class method used for quickly refreshing the graphic.
				delta--;
			}
		}
	}	
		
	
	public class AL extends KeyAdapter{ // action listener
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
