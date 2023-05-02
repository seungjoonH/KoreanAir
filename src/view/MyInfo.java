package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Main;

public class MyInfo extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public MyInfo() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		
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
		
		String id = "johndoe";
		String password = "********";
		String name = "John Doe";
		String gender = "남성";
		String birth = "1990년 1월 1일";
		String passport = "AB123456";
		String mileage = "10000 마일";
		
		// 가져온 사용자 정보를 UI에 적용
		idTextField.setText(id);
		passwordField.setText(password);
		nameTextField.setText(name);
		genderTextField.setText(gender);
		birthTextField.setText(birth);
		passportTextField.setText(passport);
		mileageTextField.setText(mileage);
		
		// 확인 버튼
		JButton confirmButton = new JButton("확인");
		confirmButton.addActionListener(this);
		
		// JPanel에 UI 구성 요소 추가
		panel.setLayout(new GridLayout(8, 2));
		panel.add(idLabel);
		panel.add(idTextField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(nameLabel);
		panel.add(nameTextField);
		panel.add(genderLabel);
		panel.add(genderTextField);
		panel.add(birthLabel);
		panel.add(birthTextField);
		panel.add(passportLabel);
		panel.add(passportTextField);
		panel.add(mileageLabel);
		panel.add(mileageTextField);
		panel.add(new JLabel());
		panel.add(confirmButton);
		
		add(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: 사용자 정보 변경 로직 구현
	}
}
