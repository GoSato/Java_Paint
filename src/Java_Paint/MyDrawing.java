package Java_Paint;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.*;

public class MyDrawing implements Cloneable{
	private int x, y, w, h;
	private Color lineColor, fillColor;
	private int lineWidth;
	private int Size;
	
	boolean isSelected;
	Shape region;
	final int SIZE = 7;
	
	public boolean shadow = false;
	public boolean linePattern = false;
	
	
	public MyDrawing(){
		x = y = 0;
		w = h = 0;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
		this.lineWidth = lineWidth;
		isSelected = false;
	}
	
	public static void main(String[] argv){
		new MyDrawing();
	}
	
	public void draw(Graphics g){
		/****選択状態のときに****/
		if(isSelected){
			g.setColor(Color.black);
			g.fillRect(x+w/2-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2,y+h/2-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w/2-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2,y+h/2-SIZE/2,SIZE,SIZE);
			g.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
		}
	}
	
	public boolean getSelected(){
		return isSelected;
	}
	
	public void setSelected(boolean isSelected){
		this.isSelected = isSelected;
	}
	
	/****containsメソッド****/
	public boolean contains(int x, int y){
		/****(x,y)が含まれるか否かをbooleanで返す****/
		return region.contains(x,y);
	}
	
	/****setRegionメソッド****/
	public void setRegion(){
		region = new Rectangle(x,y,w,h);
	}
	
	public void move(int dx, int dy){
		this.x = dx;
		this.y = dy;
		setRegion();
	
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h){
		this.w = w;
		this.h = h;
	}
	
	public void setFillColor(Color color0){
		this.fillColor = color0;
	}
	
	public void setLineColor(Color color1){
		this.lineColor = color1;
	}	

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}
	
	public void setX(int change){
		this.x = change;
	}
	
	public Color getFillColor(){
		return fillColor;
	}
	public Color getLineColor(){
		return lineColor;
	}
	
	public int getSize(){
		return Size;
	}
	
	public void setLinePattern(boolean b){
		linePattern = b;
	}
	public boolean getLinePattern(){
		return linePattern;
	}
	
	public boolean getShadow(){
		return shadow;
	}

	public void setShadow(boolean b) {
		shadow = b;
	}
	
	public void setBreak(boolean b) {
		linePattern = b;
	}
	
	public int getLineWidth(){
		return lineWidth;
	}
	
	public void setLineWidth(int lineWidth){
		this.lineWidth = lineWidth;
	}

	public MyDrawing clone(){
		try{
			return (MyDrawing)super.clone();
		}catch (CloneNotSupportedException e){
			return null;
		}
	}
}
