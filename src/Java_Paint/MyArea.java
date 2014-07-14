package Java_Paint;

import java.awt.*;
import java.awt.geom.Area;
import java.util.Vector;
import java.awt.Shape;

public class MyArea extends MyDrawing {
	
	Area a1,a2;
	MyDrawing object1;
	MyDrawing object2;
	Mediator mediator;
	Mediator selectedDrawings;
	
	public void add(Vector<MyDrawing> selectedDrawings, State state) {
		System.out.println(state);
		//System.out.println(selectedDrawings.elementAt(0));
		//System.out.println(selectedDrawings.elementAt(1));
		
		a1 = new Area();
		a2 = new Area((Shape) selectedDrawings.elementAt(1));
		
		a2.add(a1);
		
		//System.out.println("OK");
	
	}
		
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.RED);
		g2.fill(a2);
		g2.setColor(Color.BLACK);
		g2.draw(a2);
	}
	

}
