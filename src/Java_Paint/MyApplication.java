package Java_Paint;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import java.awt.Color;

import javax.swing.JColorChooser;

import Java_Paint.RectButton.RectState;

public class MyApplication extends JFrame implements ActionListener{
	StateManager stateManager;
	MyCanvas canvas;
	Vector<MyDrawing> d;
	JComboBox lineChoice;
	JComboBox fillColor;
	JComboBox lineColor;
	JComboBox alfaChoice;
	JComboBox actionChoice;

	MyRectangle selectRectangle;

	private JMenuBar menuBar;
	private JMenu colorMenu;
	private JMenuItem redItem,blueItem,greenItem;

	Mediator med;

	public MyApplication(){

		super("My Paint Application");

		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		med = canvas.getMediator();

		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());

		stateManager = new StateManager(canvas);

		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		/*
	 	DiamondButton diamondButton = new DiamondButton(stateManager);
		jp.add(diamondButton);
		 */
		TriangleButton triangleButton = new TriangleButton(stateManager);
		jp.add(triangleButton);

		SelectButton selectButton = new SelectButton(stateManager);
		jp.add(selectButton);

		//影をつける
		JCheckBox dropCheck = new JCheckBox("drop shadow");
		dropCheck.addItemListener(new DropCheckListener());
		jp.add(dropCheck);

		JCheckBox breakCheck = new JCheckBox("break line");
		breakCheck.addItemListener(new BreakCheckListener());
		jp.add(breakCheck);

		/****線の種類を変更****/
		String[] LW = {"1", "2", "3"};
		JComboBox lwChoice = new JComboBox(LW);
		jp.add(lwChoice);
		lwChoice.addActionListener(this);
		lineChoice = lwChoice;

		String[] Fill = {"Red","Blue","Green","OtherColor"};
		JComboBox FillChoice = new JComboBox(Fill);
		fillColor = FillChoice;
		FillChoice.addActionListener(new FillColorListener());
		jp.add(new JLabel("fill:"));
		jp.add(FillChoice);

		String[] Line = {"Red","Blue","Green","OtherColor"};
		JComboBox LineChoice = new JComboBox(Line);
		lineColor = LineChoice;
		LineChoice.addActionListener(new LineColorListener());
		jp.add(new JLabel("line:"));
		jp.add(LineChoice);

		//透明度
		String[] alfa = {"10","30","60","100"};
		JComboBox AlfaChoice = new JComboBox(alfa);
		alfaChoice = AlfaChoice;
		AlfaChoice.addActionListener(new AlfaListener());
		jp.add(new JLabel("alfa:"));
		jp.add(AlfaChoice);

		//重なり用
		JButton overlap = new JButton("OverLap");
		overlap.addActionListener(new OverlapCheckListener());
		jp.add(overlap);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);

		canvas.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				canvas.requestFocusInWindow();
				stateManager.mouseDown(e.getX(), e.getY());
			}

			public void mouseReleased(MouseEvent e){
				canvas.requestFocusInWindow();
				stateManager.mouseUp(e.getX(), e.getY());
			}
		});

		canvas.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				canvas.requestFocusInWindow();
				stateManager.mouseDrag(e.getX(), e.getY());
			}
		});

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

	}



	public Dimension getPreferredSize(){
		return new Dimension(1300,700);
	}

	public static void main(String[] args){
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}

	//影用
	class DropCheckListener implements ItemListener {
		public void itemStateChanged(ItemEvent e){
			med.dropCheck(e.getStateChange());
			med.repaint();
		}
	}

	//破線用
	class BreakCheckListener implements ItemListener{
		public void itemStateChanged(ItemEvent e){
			med.breakCheck(e.getStateChange());
			med.repaint();
		}
	}

	//線の太さ
	public void actionPerformed(ActionEvent e) {	
		int lw = Integer.parseInt((String)lineChoice.getSelectedItem());
		med.lineChoice(lw);
		canvas.repaint();
	}

	//塗り色
	class FillColorListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			Color fillcolor;

			if("Red".equals((String)fillColor.getSelectedItem())){
				med.setFillColor(Color.red);
			}
			else if("Blue".equals((String)fillColor.getSelectedItem())){
				med.setFillColor(Color.blue);
			}
			else if("Green".equals((String)fillColor.getSelectedItem())){
				med.setFillColor(Color.green);
			}
			else{
				JColorChooser colorChooser = new JColorChooser();
				fillcolor = colorChooser.showDialog(canvas, "色の選択", Color.white);
				med.setFillColor(fillcolor);
			}
			canvas.repaint();
		}
	}

	//線色
	class LineColorListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			Color linecolor;

			if("Red".equals((String)lineColor.getSelectedItem())){
				med.setLineColor(Color.red);
			}
			else if("Blue".equals((String)lineColor.getSelectedItem())){
				med.setLineColor(Color.blue);
			}
			else if("Green".equals((String)lineColor.getSelectedItem())){
				med.setLineColor(Color.green);
			}
			else{
				JColorChooser colorChooser = new JColorChooser();
				linecolor = colorChooser.showDialog(canvas, "色の選択", Color.white);
				med.setLineColor(linecolor);
			}
			canvas.repaint();
		}
	}

	//透明度
	class AlfaListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			if("10".equals((String)alfaChoice.getSelectedItem())){
				med.setAlfa(0.1);
			}
			if("30".equals((String)alfaChoice.getSelectedItem())){
				med.setAlfa(0.3);
			}
			if("60".equals((String)alfaChoice.getSelectedItem())){
				med.setAlfa(0.6);
			}
			if("100".equals((String)alfaChoice.getSelectedItem())){
				med.setAlfa(1);
			}

			canvas.repaint();
		}
	}

	//重なり変更用
	class OverlapCheckListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			med.ChangeOverlap();
			med.repaint();
		}
	}

}


