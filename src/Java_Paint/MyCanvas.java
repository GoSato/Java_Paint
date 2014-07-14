package Java_Paint;

import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyCanvas extends JPanel{
	Mediator mediator;
	StateManager stateManager;
	
	public MyCanvas(){
		setFocusable(true);
		this.addKeyListener(new actionKeyListener());
		this.mediator = new Mediator(this);
		setBackground(Color.white);	
	}
	
	public Mediator getMediator(){
		return mediator;
	}
	
	public void paint (Graphics g){
		super.paint(g);
		
		Enumeration<MyDrawing> e = mediator.drawingsElements();
		while (e.hasMoreElements()){
			MyDrawing d = e.nextElement();
			d.draw(g);
		}
	}
	
	public class actionKeyListener implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
				for(int i=0;i<mediator.selectedDrawings.size();i++){
					mediator.removeDrawing(mediator.selectedDrawings.elementAt(i));
				}
				mediator.repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getModifiersEx() == InputEvent.META_DOWN_MASK && e.getKeyCode() == KeyEvent.VK_X){
				mediator.cut();
			}
			if(e.getModifiersEx() == InputEvent.META_DOWN_MASK && e.getKeyCode() == KeyEvent.VK_C){
				mediator.copy();
			}
			if(e.getModifiersEx() == InputEvent.META_DOWN_MASK && e.getKeyCode() == KeyEvent.VK_V){
				mediator.paste();
			}
			if(e.getModifiersEx() == InputEvent.META_DOWN_MASK && e.getKeyCode() == KeyEvent.VK_UP){
				mediator.topline();
			}
			if(e.getModifiersEx() == InputEvent.META_DOWN_MASK && e.getKeyCode() == KeyEvent.VK_DOWN){
				mediator.underline();
			}
			if(e.getModifiersEx() == InputEvent.META_DOWN_MASK && e.getKeyCode() == KeyEvent.VK_LEFT){
				mediator.sizeline();
			}
			if(e.getModifiersEx() == InputEvent.META_DOWN_MASK && e.getKeyCode() == KeyEvent.VK_RIGHT){
				mediator.sizeline1();
			}
			if(e.getModifiers() == e.SHIFT_MASK){
				mediator.shift = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			mediator.shift = false;
			
		}
	}
}
