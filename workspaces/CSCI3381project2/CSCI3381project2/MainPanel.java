package CSCI3381project2;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainPanel extends JPanel {
	
	private final int WIDTH = 800;
	private final int HEIGHT = 500;
	
	private final char[] pass = {'p','a','s','s','w','o','r','d'};
	
	TextArea textArea;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButton_0;
	private JRadioButton radioButton_2;
	private JRadioButton radioButton_4;
	private JTextField searchTextField;
	private JPasswordField passwordField;
	private JTextField textField;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	JRadioButtonMenuItem rdbtnmntmTweetId_1;
	JCheckBox chckbxNewCheckBox;
	private TextArea textArea_1;
	private JLabel lblNewLabel_2;
	
	public MainPanel() {
		
		TweetCollection collection = new TweetCollection(
				"./CSCI3381project2/trainingProcessed.txt", 1600000);
		
		setLayout(null);
		super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		JLabel lblNewLabel = new JLabel("Tweet DATA!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(329, 11, 117, 28);
		add(lblNewLabel);
		
		radioButton_0 = new JRadioButton("0");
		radioButton_0.setSelected(true);
		buttonGroup.add(radioButton_0);
		radioButton_0.setBounds(10, 419, 39, 23);
		add(radioButton_0);
		
		radioButton_2 = new JRadioButton("2");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(51, 419, 39, 23);
		add(radioButton_2);
		
		radioButton_4 = new JRadioButton("4");
		buttonGroup.add(radioButton_4);
		radioButton_4.setBounds(102, 419, 39, 23);
		add(radioButton_4);
		
		JButton btnNewButton = new JButton("Change Tweet Polarity");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioButton_0.isSelected() && rdbtnmntmTweetId_1.isSelected())
				{
					collection.getTweetById(Integer.parseInt(searchTextField.getText())).setPolarity(0);
				} else if(radioButton_2.isSelected() && rdbtnmntmTweetId_1.isSelected())
				{
					collection.getTweetById(Integer.parseInt(searchTextField.getText())).setPolarity(2);
				} else if(radioButton_4.isSelected() && rdbtnmntmTweetId_1.isSelected())
				{
					collection.getTweetById(Integer.parseInt(searchTextField.getText())).setPolarity(4);
				}
				if(chckbxNewCheckBox.isSelected()) {
					textArea_1.setText(collection.getTweetById(Integer.parseInt(searchTextField.getText())).toString());
				}
				else if (!chckbxNewCheckBox.isSelected()){
					Tweet tweet = collection.getTweetById(Integer.parseInt(searchTextField.getText()));
					textArea_1.setText("[polarity: " + tweet.getPolarity() + "] "
							+ tweet.getUser() + ": " + tweet.getText());
				}
			}
		});
		btnNewButton.setBounds(10, 449, 150, 23);
		add(btnNewButton);
		
		textArea = new TextArea();
		textArea.setBounds(20, 45, 751, 160);
		add(textArea);
		textArea.setText(collection.toString());
		
		searchTextField = new JTextField();
		searchTextField.setBounds(20, 244, 242, 20);
		add(searchTextField);
		searchTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Selected Tweet");
		lblNewLabel_1.setBounds(20, 275, 140, 14);
		add(lblNewLabel_1);
		
		chckbxNewCheckBox = new JCheckBox("Display Tweet ID");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBounds(20, 369, 140, 23);
		add(chckbxNewCheckBox);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(30, 211, 101, 22);
		add(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Search By");
		menuBar.add(mnNewMenu_1);
		
		rdbtnmntmTweetId_1 = new JRadioButtonMenuItem("Tweet ID");
		rdbtnmntmTweetId_1.setSelected(true);
		buttonGroup_1.add(rdbtnmntmTweetId_1);
		mnNewMenu_1.add(rdbtnmntmTweetId_1);
		
		JRadioButtonMenuItem rdbtnmntmUserId = new JRadioButtonMenuItem("User ID");
		buttonGroup_1.add(rdbtnmntmUserId);
		mnNewMenu_1.add(rdbtnmntmUserId);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnmntmTweetId_1.isSelected() && chckbxNewCheckBox.isSelected()) {
					textArea_1.setText(collection.getTweetById(Integer.parseInt(searchTextField.getText())).toString());
				}
				else if (rdbtnmntmTweetId_1.isSelected() && !chckbxNewCheckBox.isSelected()){
					Tweet tweet = collection.getTweetById(Integer.parseInt(searchTextField.getText()));
					textArea_1.setText("[polarity: " + tweet.getPolarity() + "] "
							+ tweet.getUser() + ": " + tweet.getText());
				}
				else if (rdbtnmntmUserId.isSelected() && chckbxNewCheckBox.isSelected())
				{
					Set<Long> user = collection.getTweetIdsByUser(searchTextField.getText());
					String s = "";
					for (Long l: user){
						Tweet tweet = collection.getTweetById(l);
						s += tweet.toString() + "\n";
					}
					textArea_1.setText(s);
				}
				else if (rdbtnmntmUserId.isSelected() && !chckbxNewCheckBox.isSelected()){
					Set<Long> user = collection.getTweetIdsByUser(searchTextField.getText());
					String s = "";
					for (Long l: user){
						Tweet tweet = collection.getTweetById(l);
						s += "[polarity: " + tweet.getPolarity() + "] "
								+ tweet.getUser() + ": " + tweet.getText() + "\n";
					}
					textArea_1.setText(s);
				}
			}
		});
		
		btnSearch.setBounds(158, 210, 89, 23);
		add(btnSearch);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(520, 401, 270, 20);
		add(passwordField);
		
		JLabel lblEnterThePassword = new JLabel("Enter the Password to Write");
		lblEnterThePassword.setBounds(506, 376, 265, 14);
		add(lblEnterThePassword);
		
		textField = new JTextField();
		textField.setBounds(521, 450, 269, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnWrite = new JButton("Write");
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Arrays.equals(passwordField.getPassword(), pass))
				{
					textField.setText("Writting");
					collection.rewriteFile("./CSCI3381project2/testProcessed.txt");
					textArea.setText(collection.toString());
					textField.setText("Sucessfully Wrote");
				}
				else
				{
					textField.setText(passwordField.getPassword() + "incorrect password");
				}
			}
		});
		btnWrite.setBounds(682, 369, 89, 23);
		add(btnWrite);
		
		textArea_1 = new TextArea();
		textArea_1.setBounds(20, 295, 751, 64);
		add(textArea_1);
		
		lblNewLabel_2 = new JLabel("GUI By: Ian Lawrence");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(482, 11, 202, 24);
		add(lblNewLabel_2);
	}
}
