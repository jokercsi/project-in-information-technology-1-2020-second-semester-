package swing_example_1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class swing4 implements ActionListener{
	private JFrame mainFrame;
	private Container contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton clearButton;
	
	public swing4() {
		mainFrame = new JFrame("java sameple for event handling");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(320, 200);
		contentPane = mainFrame.getContentPane();
		textField = new JTextField();
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		addButton = new JButton("Add");
		clearButton = new JButton("Delete");
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
		buttonPane = new JPanel();
		buttonPane.add(addButton);
		buttonPane.add(clearButton);
		contentPane.add(textField, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		mainFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addButton) {
			textArea.append(textField.getText() + "\n");
		}
		if(event.getSource() == clearButton) {
			textArea.setText(null);
		}
	}
	
	public static void main(String[] args) {
		new swing4();
	}
}
