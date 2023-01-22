package cubelite.events;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import cubelite.setup.Main;

public class KeyEvents extends JComponent{
	
	public HashMap<String,Boolean> keys = new HashMap<String,Boolean>();
	Action toggle_on_keys_action;
	Action toggle_off_keys_action;
	
	public KeyEvents(){
		toggle_on_keys_action = new Toggle_On_Keys_Action();
		toggle_off_keys_action = new Toggle_Off_Keys_Action();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed W"), "PressKeyAction");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed S"), "PressKeyAction");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed A"), "PressKeyAction");
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed D"), "PressKeyAction");
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released W"), "ReleaseKeyAction");
		this.getInputMap().put(KeyStroke.getKeyStroke("released S"), "ReleaseKeyAction");
		this.getInputMap().put(KeyStroke.getKeyStroke("released A"), "ReleaseKeyAction");
		this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "ReleaseKeyAction");
		
		this.getActionMap().put("PressKeyAction", toggle_on_keys_action);
		this.getActionMap().put("ReleaseKeyAction", toggle_off_keys_action);
		
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed R"), "restart");
		this.getActionMap().put("restart", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.restart();
			}
		});
		
		keys.put("w", false);
		keys.put("s", false);
		keys.put("a", false);
		keys.put("d", false);
	}
	

	public class Toggle_On_Keys_Action extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			keys.put(e.getActionCommand(), true);
		}	
	}
	public class Toggle_Off_Keys_Action extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			keys.put(e.getActionCommand(), false);
		}
		
	}
	
}
