package swing_example_1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class swing5 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Graphic");
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyPanel1_3 panel = new MyPanel1_3();
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}

class MyPanel1_3 extends JPanel {
	
	public MyPanel1_3() {
		setBackground(Color.white); //background = white
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillRect(50, 100, 100, 100);
		g.drawRect(200, 100, 100, 100);
		
		g.setColor(Color.black);
		g.drawLine(350, 100, 450, 200);
		
		g.setColor(Color.gray);
		g.fillOval(50, 240, 100, 100);
		g.drawOval(200, 240, 100, 100);
		
		g.setColor(Color.green);
		g.fillArc(350, 240, 100, 100, 30, 300);
		
		g.setColor(Color.black);
		g.drawString("Welcome to java", 10, 20);
	}
}
