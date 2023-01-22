package cubelite.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameFrame extends JPanel{
	
	double player_x, player_y, slashvectorx, slashvectory;
	int player_width, player_height, slash_frames, slashplayerx, slashplayery;
	int enemyX, enemyY, enemyWidth, enemyHeight; 
	int points;
	Color player_color, enemyColor;
	boolean gameOn;
	double timer;
	
	public GameFrame(){
		player_color = Color.green;
		enemyColor = Color.red;
		slash_frames = 0; 
		points = 0;
		this.setBackground(Color.black);
		gameOn = true;
	}
	
	public void drawSlash(int playerx, int playery, double vector[]){
		slash_frames = 20;
		slashplayerx = playerx;
		slashplayery = playery;
		slashvectorx = vector[0];
		slashvectory = vector[1];
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g);
		
		if(!gameOn) {
			g2.setColor(Color.white);
			Font font = new Font("Serif", Font.PLAIN, 96);
			g2.setFont(font);
			g2.drawString("Final score: " + Integer.toString(points), 120, 350);
			return;
		}
		
		if(slash_frames > 0) {
			slash_frames--;
			double angle = GamCalc.calculateAngle(slashvectorx, slashvectory);
			g2.setPaint(Color.white);
			g2.setStroke(new BasicStroke(5));
			g2.drawArc((int)player_x - player_width, (int)player_y - player_height, player_width * 3, player_height * 3, (int)angle - 20, 40);
		}
		
		g2.setPaint(enemyColor);
		g2.fillRect(enemyX, enemyY, enemyWidth, enemyHeight);
		g2.setPaint(player_color);
		g2.fillRect((int)player_x, (int)player_y, player_width, player_height);
		
		g2.setColor(Color.white);
		Font font = new Font("Serif", Font.PLAIN, 96);
		g2.setFont(font);
		g2.drawString(Integer.toString(points), 370, 100);
		g2.setFont(new Font("Serif", Font.PLAIN, 50));
		g2.drawString(Double.toString(timer).substring(0, 4), 40, 40);

	}
	
	public void player_values(double x, double y, int width, int height) {
		player_x = x;
		player_y = y;
		player_width = width;
		player_height = height;
	}
	
	public void enemyValues(int x, int y, int width, int height) {
		enemyX = x;
		enemyY = y;
		enemyWidth = width;
		enemyHeight = height; 
	}
	
	public void updatePoints() {
		if(gameOn)points++;
	}
	
	public void updateTimer(double timer) {
		this.timer = timer;
	}
	
}
