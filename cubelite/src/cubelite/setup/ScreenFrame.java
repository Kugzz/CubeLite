package cubelite.setup;

import javax.swing.JFrame;

public class ScreenFrame extends JFrame{
	
	public ScreenFrame(){
		this.setSize(800, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Cube Lite");
	}
}
