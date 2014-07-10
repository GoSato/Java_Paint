package Java_Paint;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RectButton extends JButton{
	StateManager stateManager;
	
	public RectButton(StateManager stateManager){
		super("Rectangle");
		addActionListener(new RectListener());
		this.stateManager = stateManager;
	}
	
	class RectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new RectState(stateManager));
			stateManager.mediator.resetSelectd();
			stateManager.mediator.canvas.repaint();
		}
	}
	
	public static class RectState extends State{
		int x1, y1;
		StateManager stateManager;
		MyRectangle rect;
		
		
		public RectState(StateManager stateManager){
			this.stateManager = stateManager;
		}
		
		public void mouseDown(int x, int y){
			
			rect = new MyRectangle(x,y,0,0);
			
			x1 = x;
			y1 = y;
			
			rect.setLinePattern(stateManager.linePattern);
			stateManager.addDrawing(rect);
		}
		
		public void mouseUp(int x, int y){
			rect.setRegion();
		}
		
		public void mouseDrag(int x, int y){
			rect.setSize(x-x1,y-y1);
		}
	}

}