package kadai02State;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings = null;

	Vector<MyDrawing> buffer = null;
	boolean shadow = false;
	boolean linePattern = false;
	boolean shift = false;
	MyDrawing select = null;

	public Mediator(MyCanvas canvas){
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
		selectedDrawings = new Vector<MyDrawing>();
	}

	public Enumeration<MyDrawing> drawingsElements(){
		return drawings.elements();
	}

	public void addDrawing(MyDrawing d){
		d.shadow = this.shadow;
		d.linePattern = this.linePattern;
		drawings.add(d);
		canvas.repaint();
	}

	//返り値の型をVectorにする
	public Vector<MyDrawing> getSelectedDrawing(){
		return selectedDrawings;
	}

	public void move(int dx, int dy){
		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).move(dx, dy);
			}
		}
	}

	public void repaint(){
		canvas.repaint();
	}

	public void setSelected(int x, int y){
		if(shift == true){	
			for(MyDrawing d : drawings){
				d.setRegion();
				if(d.contains(x, y)){
					setSelectedDrawing(d);
				}
			}
			if(selectedDrawings.size() != 0){
				for(int i=0;i<selectedDrawings.size();i++){
					selectedDrawings.elementAt(i).isSelected = true;
				}
			}
		}else{
			select = null;
			for(MyDrawing d : drawings){
				d.setRegion();
				if(d.contains(x, y)){
					select = d;
				}
			}
			if(select != null){
				setSelectedDrawing(select);
			}
			if(selectedDrawings.size() != 0){
				for(MyDrawing q : selectedDrawings)
					q.isSelected = true;
			}
		}
	}

	public void resetSelectd(){
		if(shift == false){
			for(MyDrawing d : drawings){
				d.isSelected = false;
			}

			if(selectedDrawings.size() != 0){
				selectedDrawings.clear();
			}
		}
	}

	public void setSelectedDrawing(MyDrawing d){
		selectedDrawings.add(d);
	}

	public void setFillColor(Color color){
		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).setFillColor(color);
			}
		}
	}
	public void setLineColor(Color color1){
		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).setLineColor(color1);
			}
		}
	}

	public void lineChoice(int lw) {
		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).setLineWidth(lw);
			}
		}
	}

	//shadowはboolean型	
	public void dropCheck(int stateChange) {

		if(stateChange == ItemEvent.SELECTED){
			shadow = true;
		}else{
			shadow = false;
		}
		if(selectedDrawings != null){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).setShadow(shadow);
			}
		}
	}

	public void breakCheck(int state){
		if(state == ItemEvent.SELECTED)
			linePattern = true;
		else
			linePattern = false;
		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).setBreak(linePattern);
			}
		}
	}	


	//cut & copy & paste 
	public void clearBuffer(){
		buffer = null;
	}

	public void copy(){
		if(selectedDrawings.size() != 0){
			clearBuffer();
			buffer = (Vector<MyDrawing>) selectedDrawings.clone();
		}
	}

	public void cut(){
		clearBuffer();
		buffer = (Vector<MyDrawing>) selectedDrawings.clone();
		for(int i=0;i<selectedDrawings.size();i++){
			removeDrawing(selectedDrawings.elementAt(i));
		}
	}

	public void paste(){
		Vector<MyDrawing> clone = new Vector<MyDrawing>();

		for(int i=0;i<buffer.size();i++){
			clone.add(buffer.elementAt(i).clone());

			int x = clone.elementAt(i).getX();
			int y = clone.elementAt(i).getY();
			if(x < 500 ){
				clone.elementAt(i).setLocation(x+300, y);
			}else{
				clone.elementAt(i).setLocation(x-300,y);
			}
			addDrawing(clone.elementAt(i));
		}
		resetSelectd();
		//setSelectedDrawing(clone);
		canvas.repaint();	
	}

	public void removeDrawing(MyDrawing d){
		drawings.remove(d);
	}

	public void topline() {
		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).move(selectedDrawings.elementAt(i).getX(), 100);
			}
		}
		canvas.repaint();
	}

	public void underline() {
		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){

				selectedDrawings.elementAt(i).move(selectedDrawings.elementAt(i).getX(), 400 - selectedDrawings.elementAt(i).getH());
			}
		}
		canvas.repaint();
	}

	public void sizeline() {
		int dumy = 0;
		MyDrawing buffer;

		if(selectedDrawings.size() != 0){
			for(int i=0;i<selectedDrawings.size();i++){
				selectedDrawings.elementAt(i).move(selectedDrawings.elementAt(i).getX(), 400 - selectedDrawings.elementAt(i).getH());
			}

			for(int i=0;i<selectedDrawings.size();i++){
				for(int j=i+1;j<selectedDrawings.size();j++){
					if(selectedDrawings.elementAt(i).getH() < selectedDrawings.elementAt(j).getH()){
						
						buffer = selectedDrawings.elementAt(i);
						selectedDrawings.removeElementAt(i);
						selectedDrawings.insertElementAt(selectedDrawings.elementAt(j-1), i);
						selectedDrawings.removeElementAt(j);
						selectedDrawings.insertElementAt(buffer, j);

						dumy = selectedDrawings.elementAt(i).getX();
						selectedDrawings.elementAt(i).setX(selectedDrawings.elementAt(j).getX());
						selectedDrawings.elementAt(j).setX(dumy);
					}
				}
			}
		}
		canvas.repaint();	
	}

	public void sizeline1() {

	}


}
