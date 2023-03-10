/**a
* Name: Adam Koehler
* File: GameFrame.java
* Description: conatins the panel. hosts the minimize and exit button.
 */
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

	GamePanel panel;
	
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); // autofits around the panel
		this.setVisible(true);
		this.setLocationRelativeTo(null); // makes program appear in middle of screen by default.
	}
}
