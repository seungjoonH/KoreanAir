package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import model.User;

public class MyInfo extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public MyInfo() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		
		JPanel appbarPanel = new JPanel(new BorderLayout());
		
		ImageIcon logo = new ImageIcon("./asset/logo.png");
		Image resizedImage = logo.getImage().getScaledInstance(200, 25, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));

		JPanel backButtonPanel = new JPanel(new BorderLayout());
		JButton backButton = new JButton("뒤로");
		backButton.addActionListener(this);
		backButtonPanel.add(backButton, BorderLayout.WEST);
		
		JPanel submitButtonPanel = new JPanel(new BorderLayout());
		JButton submitButton = new JButton("저장");
		submitButton.addActionListener(this);
		submitButtonPanel.add(submitButton, BorderLayout.EAST);

		appbarPanel.add(backButtonPanel, BorderLayout.WEST);
		appbarPanel.add(logoLabel, BorderLayout.CENTER);
		appbarPanel.add(submitButton, BorderLayout.EAST);
		
		JLabel idLabel = new JLabel("아이디: ");
		JTextField idTextField = new JTextField(20);
		JLabel passwordLabel = new JLabel("비밀번호: ");
		JPasswordField passwordField = new JPasswordField(20);
		JLabel nameLabel = new JLabel("이름: ");
		JTextField nameTextField = new JTextField(20);
		JLabel genderLabel = new JLabel("성별: ");
		JTextField genderTextField = new JTextField(20);
		JLabel birthLabel = new JLabel("생년월일: ");
		JTextField birthTextField = new JTextField(20);
		JLabel passportLabel = new JLabel("여권번호: ");
		JTextField passportTextField = new JTextField(20);
		JLabel mileageLabel = new JLabel("보유 마일리지: ");
		JTextField mileageTextField = new JTextField(20);
		
		User user = Main.getUser();
		
		idTextField.setText(user.getId());
		passwordField.setText(user.getPassword());
		nameTextField.setText(user.getName());
		genderTextField.setText(user.getSex().name());
		birthTextField.setText(user.getBirth().toString());
		passportTextField.setText(user.getPassportNo());
		mileageTextField.setText(user.getMileagePoint() + "");
		
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(8, 2));
		
		contentPanel.add(idLabel);
		contentPanel.add(idTextField);
		contentPanel.add(passwordLabel);
		contentPanel.add(passwordField);
		contentPanel.add(nameLabel);
		contentPanel.add(nameTextField);
		contentPanel.add(genderLabel);
		contentPanel.add(genderTextField);
		contentPanel.add(birthLabel);
		contentPanel.add(birthTextField);
		contentPanel.add(passportLabel);
		contentPanel.add(passportTextField);
		contentPanel.add(mileageLabel);
		contentPanel.add(mileageTextField);
		contentPanel.add(new JLabel());
		
		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		wrapperPanel.add(contentPanel, BorderLayout.CENTER);
		
		panel.setLayout(new BorderLayout());
		panel.add(appbarPanel, BorderLayout.NORTH);
		panel.add(wrapperPanel, BorderLayout.CENTER);
		
		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("뒤로")) Main.gotoPage(new My());
	}
}
