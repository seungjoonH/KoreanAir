package view;

import java.awt.BorderLayout;
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
	
	public My() {
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

		JPanel appbarPanel = new JPanel(new BorderLayout());
		appbarPanel.setPreferredSize(new Dimension(Main.WIDTH, 50));
		
		JPanel backButtonPanel = new JPanel(new BorderLayout());
		JButton backButton = new JButton("뒤로");
		backButton.addActionListener(this);
		
		ImageIcon logo = new ImageIcon("./asset/logo.png");
		Image resizedImage = logo.getImage().getScaledInstance(200, 25, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));
		
		backButtonPanel.add(backButton, BorderLayout.WEST);
		
		appbarPanel.add(backButtonPanel, BorderLayout.WEST);
		appbarPanel.add(logoLabel, BorderLayout.CENTER);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT - 50));
		
		JLabel backImage;
		ImageIcon back = new ImageIcon("./asset/background.png");
		Image backImg = back.getImage().getScaledInstance(Main.WIDTH, Main.HEIGHT, Image.SCALE_DEFAULT);
		backImage = new JLabel(new ImageIcon(backImg));
		backImage.setLocation(0, 0);
		backImage.setSize(Main.WIDTH, Main.HEIGHT);
		
		JButton myResButton = new JButton("예약정보");
		myResButton.setBounds(400,200,200,100);
		myResButton.setFocusable(false);
		myResButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myResButton.addActionListener(this);
		
		JButton myInfoButton = new JButton("회원정보");
		myInfoButton.setBounds(400,350,200,100);
		myInfoButton.setFocusable(false);
		myInfoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myInfoButton.addActionListener(this);
		
		contentPanel.add(myResButton);
		contentPanel.add(myInfoButton);
		contentPanel.add(backImage);

		panel.add(appbarPanel, BorderLayout.NORTH);
		panel.add(contentPanel, BorderLayout.CENTER);
		
		add(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("뒤로")) Main.gotoPage(new Home());
		else if (e.getActionCommand().equals("예약정보")) Main.gotoPage(new MyReservation(new Flight(null)));
		else if (e.getActionCommand().equals("회원정보")) Main.gotoPage(new MyInfo());
	}
}
