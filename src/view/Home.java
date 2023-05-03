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

public class Home extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JButton logButton, searchButton, myButton ;
	
	public Home() {
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		
		setLayout(null);
		
		JLabel backImage;
		ImageIcon back = new ImageIcon("./asset/background.png");
		Image backImg = back.getImage().getScaledInstance(Main.WIDTH, Main.HEIGHT, Image.SCALE_DEFAULT);
		backImage = new JLabel(new ImageIcon(backImg));
		backImage.setLocation(0,60);
		backImage.setSize(Main.WIDTH, Main.HEIGHT);
		
		
		JLabel profileLabel = new JLabel();
		
		if (Main.isLogged()) {
			profileLabel = new JLabel(Main.getUser().getName() 
				+ (Main.isAdmin() ? " 관리자" : " ") 
				+ "님 환영합니다");
			profileLabel.setLocation(10,20);
			profileLabel.setSize(150,30);
		}
		

		ImageIcon logo = new ImageIcon("./asset/logo.png");
		Image resizedImage = logo.getImage().getScaledInstance(200, 25, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));
		logoLabel.setLocation(400,20);
		logoLabel.setSize(200,25);
		
		
		logButton = new JButton(Main.isLogged() ? "로그아웃" : "로그인");
		logButton.addActionListener(this);
		logButton.setBounds(900,10,100,40);
		
		//appbarPanel.setLayout(new BorderLayout());
		//setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		
		searchButton = new JButton("항공편조회");
		searchButton.setBounds(200,200,200,100);
		searchButton.setFocusable(false);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(this);
		
		myButton = new JButton("마이페이지");
		myButton.setBounds(600,200,200,100);
		myButton.setFocusable(false);
		myButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myButton.addActionListener(this);
		
		add(myButton);
		add(searchButton);
		add(profileLabel);
		add(logButton);
		add(logoLabel);
	    add(backImage);
	}
	public void logout() { Main.setUser(null); }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("로그인")) Main.gotoPage(new Login());
		else if (e.getActionCommand().equals("로그아웃")) { logout(); Main.gotoPage(new Home()); }
		else if(e.getSource()==searchButton) { Main.gotoPage(new Search()); }
		else if(e.getSource()==myButton) {
			if (Main.isLogged())
				Main.gotoPage(new My());
			else 
				Main.gotoPage(new Login());
		}
	}
}