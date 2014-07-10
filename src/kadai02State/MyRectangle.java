package kadai02State;

import java.awt.*;

import javax.swing.JOptionPane;

public class MyRectangle extends MyDrawing {
	public MyRectangle(int xpt, int ypt, int w, int h){
		super();
		setLocation(xpt, ypt);
	}
	
	public void draw(Graphics g){
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();

		if(w < 0){
			x +=w;
			w *= -1;
		}
		if(h < 0){
			y +=h;
			h *= -1;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(getLinePattern())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		
		
		if(getShadow()){
			g2.setColor(Color.black);
			g2.fillRect(x+4, y+4, w, h);
			g2.setColor(Color.black);
			g2.drawRect(x+4, y+4, w, h);
		}
		
		g2.setColor(getFillColor());
		g2.fillRect(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawRect(x, y, w, h);
		
		super.draw(g2);

	}
}
