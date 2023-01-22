package cubelite.entities;

import java.util.HashMap;
import java.util.Map;

import cubelite.util.GamCalc;

public class Player extends CubeBase{
	
	public double xSlide, ySlide, xSlow, ySlow; 
	public int slashCD;
	
	public Player(){
		//x y width height xvel yvel
		super(380, 380, 40, 40, 8, 8);
		xSlide = 0; 
		ySlide = 0; 
		slashCD = 0;
		xSlow = 0;
		ySlow = 0;
	}
	
	public void borderCheck() {
		if(x <= 0 || x >= 800 - width) {
			xSlide = 0; 
			xSlow = 0;
		}
		if(y <= 0 || y >= 800 - height) {
			ySlide = 0;
			ySlow = 0;
		}
	}
	
	public void move(HashMap<String, Boolean> keys) {
		
		for(Map.Entry<String, Boolean> entry : keys.entrySet()) {
			if(entry.getValue() == false) continue;
			switch(entry.getKey()) {
			case "w":
				y -= yvel;
				if(y < 0) y = 0; 
				break;
			case "s":
				y += yvel;
				if(y > 800 - height) y = 800 - height;
				break;
			case "a":
				x -= xvel;
				if(x < 0) x = 0;
				break;
			case "d":
				x += xvel;
				if(x > 800 - height) x = 800 - height;
				break;
			}
		}
		this.x += xSlide;
		this.y += ySlide; 
	}
}
