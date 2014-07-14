package Java_Paint;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DiamondButton extends JButton{
	StateManager stateManager;
	
	public DiamondButton(StateManager stateManager){
		super("Diamond");
		
		addActionListener(new DiamondListener());
		
		this.stateManager = stateManager;
	}
	
	class DiamondListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new DiamondState(stateManager));
		}
	}
	
	public static class DiamondState extends State{
		int x1, y1;
		MyDiamond diamond;
		StateManager stateManager;
		
		public DiamondState(StateManager stateManager){
			this.stateManager = stateManager;
		}
		
		public void mouseDown(int x, int y){
			diamond = new MyDiamond(x,y,0,0);
			x1 = x;
			y1 = y;
			
			diamond.setLinePattern(stateManager.linePattern);
			stateManager.addDrawing(diamond);
		}
		
		public void mouseUp(int x, int y){
			diamond.setRegion();
		}
		
		public void mouseDrag(int x, int y){
			diamond.setSize(x-x1, y-y1);
		}
	}

}