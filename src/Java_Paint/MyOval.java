package Java_Paint;

import java.awt.*;

public class MyOval extends MyDrawing {
	public MyOval(int xpt, int ypt, int w, int h){
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
			g2.fillOval(x+4, y+4, w, h);
			g2.setColor(Color.black);
			g2.drawOval(x+4, y+4, w, h);
		}
		
		g2.setColor(getFillColor());
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlfa()));
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlfa()));
		g2.drawOval(x, y, w, h);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		
		super.draw(g2);
		
	}
}
