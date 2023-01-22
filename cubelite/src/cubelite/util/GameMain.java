package cubelite.util;

import cubelite.entities.Enemy;
import cubelite.entities.Player;
import cubelite.events.KeyEvents;
import cubelite.events.MouseEvents;
import cubelite.setup.Main;
import cubelite.setup.ScreenFrame;

public class GameMain {
	
	public ScreenFrame SFrame;
	public GameFrame GFrame;
	public KeyEvents keyevents;
	public MouseEvents mouseevents;
	public GamCalc gamcalc;
	public Enemy enemy;
	public Player player;
	
	public boolean isEnemyAlive;
	public double slashVector[];
	public int points;
	public double timer;
	public double timerDefault;
	
	public GameMain(){
		SFrame = new ScreenFrame();
		GFrame = new GameFrame();
		keyevents = new KeyEvents();
		mouseevents = new MouseEvents();
		
		SFrame.add(keyevents);
		GFrame.addMouseListener(mouseevents);
		SFrame.add(GFrame);
		startGame();
	}
	
	public void framerate(int fps) {
		try {
			Thread.sleep(1000/fps);
		}catch(InterruptedException e){
			return;
		}
	}
	
	public void createEnemy() {
		int[] location = GamCalc.generateEnemyLocation();
		enemy = new Enemy(location[0], location[1]);
		System.out.println(location[0] + " " + location[1]);
		isEnemyAlive = true;
	}
	
	public void slash(int x, int y) {	
		if(player.slashCD > 0) return;
		player.slashCD = 60;
		
		int centeredx = player.x + player.width/2;
		int centeredy = player.y + player.height/2;
		slashVector = GamCalc.basisVector(x, y, centeredx, centeredy);
		double[] slideForce = GamCalc.calculateSlideForce(slashVector);
		player.xSlide = slideForce[0];
		player.ySlide = slideForce[1];
		player.xSlow = slideForce[2];
		player.ySlow = slideForce[3];
		
		GFrame.drawSlash(player.x, player.y, slashVector);
		//mittaa etäisyys pisteeseen. Ympyrä!!
	}
	
	public void slashCheck() {
		double[] hitLocation = new double[2];
		hitLocation[0] = (double) (player.x + player.width/2 + slashVector[0]);
		hitLocation[1] = (double) (player.y + player.height/2 + slashVector[1]);
		
		if(Math.sqrt(Math.pow(hitLocation[0] - (enemy.x + enemy.width/2), 2)
				+ Math.pow(hitLocation[1] - (enemy.y + enemy.height/2), 2)) < 40) {
			points++;
			GFrame.updatePoints();
			timer = timerDefault;
			timerDefault *= 0.95;
			isEnemyAlive = false;
		}
	}
	
	public void iteration() {
		if(mouseevents.clicked) {
			mouseevents.clicked = false;
			slash(mouseevents.x, mouseevents.y);
		}
		if(!isEnemyAlive) createEnemy();
		if(timer < 0) GFrame.gameOn = false;
		player.move(keyevents.keys);
		player.borderCheck();
		GFrame.player_values(player.x, player.y, player.width, player.height);
		GamCalc.playerValues(player.x, player.y, player.width, player.height);
		GFrame.enemyValues(enemy.x, enemy.y, enemy.width, enemy.height);
		
		GFrame.repaint();
		
		if(player.slashCD >= 40) slashCheck();
		
		if(player.xSlide != 0) player.xSlide -= player.xSlow;
		if(player.ySlide != 0) player.ySlide -= player.ySlow;
		
		if(player.slashCD == 50) {
			player.xSlide = 0;
			player.ySlide = 0;
		}
		
		if(player.slashCD > 0)player.slashCD--;
		timer -= (double)1/(double)60;
		GFrame.updateTimer(timer);
		framerate(60);
		if(true)iteration();
	}
	
	public void startGame() {
		
		player = new Player();
		isEnemyAlive = false;
		points = 0;
		timerDefault = 3;
		timer = timerDefault;
		iteration();
	}
}
