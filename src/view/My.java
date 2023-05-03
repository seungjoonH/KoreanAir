package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;
import model.Flight;

public class My extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton myResButton, myInfoButton ;
	public My() {
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		
		setLayout(null);
		
		JLabel backImage;
		ImageIcon back= new ImageIcon("./asset/background.png");
		Image backImg = back.getImage().getScaledInstance(Main.WIDTH, Main.HEIGHT, Image.SCALE_DEFAULT);
		backImage = new JLabel(new ImageIcon(backImg));
		backImage.setLocation(0,60);
		backImage.setSize(Main.WIDTH,Main.HEIGHT);
		
		
		ImageIcon logo = new ImageIcon("./asset/logo.png");
		Image resizedImage = logo.getImage().getScaledInstance(200, 25, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));
		logoLabel.setLocation(400,20);
		logoLabel.setSize(200,25);
		
		myResButton = new JButton("예약정보");
		myResButton.setBounds(400,200,200,100);
		myResButton.setFocusable(false);
		myResButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myResButton.addActionListener(this);
		
		myInfoButton = new JButton("회원정보");
		myInfoButton.setBounds(400,350,200,100);
		myInfoButton.setFocusable(false);
		myInfoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myInfoButton.addActionListener(this);
		
		add(myResButton);
		add(myInfoButton);
		add(logoLabel);
		add(backImage);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==myResButton) {Main.gotoPage(new MyReservation(new Flight(null)));}
		else if(e.getSource()==myInfoButton) {Main.gotoPage(new MyInfo());}
	}
}
