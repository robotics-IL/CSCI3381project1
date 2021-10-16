package CSCI3381project2;

import javax.swing.JFrame;

public class MainFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tweet Collection");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		
		frame.getContentPane().add(new MainPanel());
		
		frame.pack();
		frame.setVisible(true);

	}

}
