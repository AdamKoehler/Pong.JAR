/**
* Name: Adam Koehler
* File: Ball.java
* Description: (insert description)
 */
import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
	
Random random;
int xVelocity;
int yVelocity;

Ball(int xPosition, int yPosition, int width, int height, int INITIAL_SPEED){
	super(xPosition, yPosition, width, height);
	random = new Random();
	int randomXDirection = random.nextInt(2); 
	int randomYDirection = random.nextInt(2); 
	// x-axis
	if (randomXDirection == 0) { // if 0 go left. if 1 go right.
		randomXDirection--;
		setXDirection(randomXDirection * INITIAL_SPEED);
	}
	else
		setXDirection(1 * INITIAL_SPEED);
	
	// y-axis
	if (randomYDirection == 0) {
		randomYDirection--; // if 0 go down. if 1 go up.
		setYDirection(randomYDirection * INITIAL_SPEED);
	}
	else setYDirection(1 * INITIAL_SPEED);
}
public void setXDirection(int randomXDirection){
	xVelocity = randomXDirection;
}
public void setYDirection(int randomYDirection){
	yVelocity = randomYDirection;
}
public void move() {
	x+= xVelocity;
	y+= yVelocity;
}

public void draw(Graphics g) {
	g.setColor(Color.WHITE);
	g.fillOval(x, y, height, width);
}
}
