package Java_Paint;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Vector;

public class StateManager {
	
	State state;	
	Mediator mediator;
	
	boolean shadow;
	boolean linePattern;
	int lineWidth;
	
	public StateManager(MyCanvas canvas){
		mediator = canvas.getMediator();
		state = new RectButton.RectState(this);
	}

	public void addDrawing(MyDrawing d){
		d.shadow = this.shadow;
		//d.setLineWidth(lineWidth);
		mediator.addDrawing(d);
	}
	
	public void setState(State s){
		state = s;
	}
	
	public void mouseDown(int x1, int y1){
		state.mouseDown(x1,y1);
		mediator.repaint();
	}
	
	public void mouseDrag(int x2, int y2){
		state.mouseDrag(x2, y2);
		mediator.repaint();
	}
	
	public void mouseUp(int x3, int y3){
		state.mouseUp(x3, y3);
	}

//	public void lineChoice(int lineWidth){
//		this.lineWidth = lineWidth;
//	}
}
