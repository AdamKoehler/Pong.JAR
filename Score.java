/**
* Name: Adam Koehler
* File: Score.java
* Description: (insert description)
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
	
static int GAME_WIDTH;
static int GAME_HEIGHT;
static int player1;
static int player2;


Score (int GAME_WIDTH, int GAME_HEIGHT){
	// score class needs to know the original width and length of panel so that the scoreboard is proportional to other game object
	 Score.GAME_WIDTH = GAME_WIDTH;
	 Score.GAME_HEIGHT = GAME_HEIGHT;
}



public void draw(Graphics g) {
	g.setColor(Color.WHITE);
	g.setFont(new Font("Consola", Font.PLAIN, 60));
	g.drawLine(GAME_WIDTH/2 , 0, GAME_WIDTH/2, GAME_HEIGHT);
	g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2) - 85, 50);
	g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10),(GAME_WIDTH/2) + 20, 50);
	
}

}
