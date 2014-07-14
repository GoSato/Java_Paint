package Java_Paint;

import java.awt.*;

public class MyTriangle extends MyDrawing {
	public MyTriangle(int xpt, int ypt, int w, int h){
		super();
		setLocation(xpt, ypt);
	}
	
	public void draw(Graphics g){
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		int xPoints[] = {x, x + w/2, x+w, x + w/2};
		int yPoints[] = {y, y + h, y, y - h};

		int xPoints1[] = {x+4, x+4 + w/2, x+4+w, x+4 + w/2};
		int yPoints1[] = {y+4, y+4 + h, y+4, y+4 - h};
		
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
		
		Polygon polygon1 = new Polygon(xPoints, yPoints, 3);
		
		if(getShadow()){
			Polygon polygon2 = new Polygon(xPoints1, yPoints1, 3);
			
			g2.setColor(Color.black);
			g2.fill(polygon2);
			g2.setColor(getLineColor());
			g2.draw(polygon2);
		}
		
		g2.setColor(getFillColor());
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlfa()));
		g2.fill(polygon1);
		g2.setColor(getLineColor());
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlfa()));
		g2.draw(polygon1);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));

		super.draw(g2);
	}
}
