package Java_Paint;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.geom.Area;

import javax.swing.*;

public class SelectButton extends JButton{

	StateManager stateManager;
	Mediator mediator;

	public SelectButton(StateManager stateManager){
		super("Select");
		addActionListener(new SelectListener());
		this.stateManager = stateManager;
	}

	class SelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new SelectState(stateManager));
		}
	}

	public static class SelectState extends State{

		int x0[] = new int[10];
		int y0[] = new int[10];
		int x1, y1;
		int x2, y2;
		StateManager stateManager;
		MyRectangle selectRect;
		Vector<MyDrawing> d;

		public SelectState(StateManager stateManager){
			this.stateManager = stateManager;
		}

		public void mouseDown(int x, int y){
			d = stateManager.mediator.drawings;
			x1 = x;
			y1 = y;

			stateManager.mediator.resetSelectd();

			stateManager.mediator.setSelected(x,y);

			if(stateManager.mediator.selectedDrawings.size() != 0){
				for(int i=0;i<stateManager.mediator.selectedDrawings.size();i++){
					x0[i] = stateManager.mediator.getSelectedDrawing().elementAt(i).getX();
					y0[i] = stateManager.mediator.getSelectedDrawing().elementAt(i).getY();
				}
			}
		}

		public void mouseUp(int x, int y){}

		public void mouseDrag(int x, int y){

			x2 = x;
			y2 = y;
			
			for(int i=0;i<stateManager.mediator.selectedDrawings.size();i++){
				stateManager.mediator.selectedDrawings.elementAt(i).move(x0[i] + x2 - x1, y0[i] + y2 - y1);
			}
			stateManager.mediator.repaint();
		}
	}

}