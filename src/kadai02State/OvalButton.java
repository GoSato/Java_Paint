package kadai02State;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class OvalButton extends JButton{
	StateManager stateManager;
	
	public OvalButton(StateManager stateManager){
		super("Oval");
		
		addActionListener(new OvalListener());
		
		this.stateManager = stateManager;
	}
	
	class OvalListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new OvalState(stateManager));
			stateManager.mediator.resetSelectd();
			stateManager.mediator.canvas.repaint();
		}
	}
	
	public static class OvalState extends State{
		int x1, y1;
		MyOval oval;
		StateManager stateManager;
		
		public OvalState(StateManager stateManager){
			this.stateManager = stateManager;
		}
		
		public void mouseDown(int x, int y){
			oval = new MyOval(x, y, 0, 0);
			x1 = x;
			y1 = y;
			
			oval.setLinePattern(stateManager.linePattern);
			stateManager.addDrawing(oval);
		}
		
		public void mouseUp(int x, int y){
			oval.setRegion();
		}
		
		public void mouseDrag(int x, int y){
			oval.setSize(x-x1, y-y1);
		}
	}

}