package kadai02State;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TriangleButton extends JButton{
	StateManager stateManager;
	
	public TriangleButton(StateManager stateManager){
		super("Triangle");	
		addActionListener(new TriangleListener());
		this.stateManager = stateManager;
	}
	
	class TriangleListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new TriangleState(stateManager));
			stateManager.mediator.resetSelectd();
			stateManager.mediator.canvas.repaint();
		}
	}
	
	public static class TriangleState extends State{
		int x1, y1;
		MyTriangle triangle;
		StateManager stateManager;
		
		public TriangleState(StateManager stateManager){
			this.stateManager = stateManager;
		}
		
		public void mouseDown(int x, int y){
			triangle = new MyTriangle(x,y,0,0);
			x1 = x;
			y1 = y;
			stateManager.addDrawing(triangle);
		}
		
		public void mouseUp(int x, int y){
			triangle.setRegion();
		}
		
		public void mouseDrag(int x, int y){
			triangle.setSize(x-x1,y-y1);
		}
	}

}