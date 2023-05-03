package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.UserManagementController;
import main.Main;
import main.Sex;
import model.Ticket;
import model.User;

public class Register extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static User newcomer;
	
	private JTextField idField = new JTextField(20);
	private JTextField passwordField = new JPasswordField(20);
	private JTextField nameField = new JTextField(20);
	private JComboBox<String> sexCombo = new JComboBox<String>();
	private JTextField birthField = new JTextField(20);
	private JTextField emailField = new JTextField(20);
	private JTextField phoneField = new JTextField(20);
	private JTextField passportNoField = new JTextField(20);
	
	public Register() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

		JLabel idLabel = new JLabel("아이디 :");
		JLabel passwordLabel = new JLabel("비밀번호 :");
		JLabel nameLabel = new JLabel("이름 :");
		JLabel sexLabel = new JLabel("성별 :");
		JLabel birthLabel = new JLabel("생년월일(YYYYMMDD) :");
		JLabel emailLabel = new JLabel("이메일 :");
		JLabel phoneLabel = new JLabel("휴대폰(- 생략) :");
		JLabel passportNoLabel = new JLabel("여권번호(영문 1자리 + 숫자 8자리) :");

		JButton registerButton = new JButton("회원가입");
		registerButton.addActionListener(this);

		JPanel headPanel = new JPanel(new BorderLayout());

		JPanel backButtonPanel = new JPanel(new BorderLayout());
		JButton backButton = new JButton("뒤로");
		backButton.addActionListener(this);
		backButtonPanel.add(backButton, BorderLayout.WEST);

		JLabel titleLabel = new JLabel("회원가입", SwingConstants.CENTER);
		titleLabel.setFont(titleLabel.getFont().deriveFont(20f));

		headPanel.add(backButtonPanel, BorderLayout.WEST);
		headPanel.add(titleLabel, BorderLayout.CENTER);

		JPanel registerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		registerPanel.add(idLabel, c);
		c.gridy = 1;
		registerPanel.add(passwordLabel, c);
		c.gridy = 2;
		registerPanel.add(nameLabel, c);
		c.gridy = 3;
		registerPanel.add(sexLabel, c);
		c.gridy = 4;
		registerPanel.add(birthLabel, c);
		c.gridy = 5;
		registerPanel.add(emailLabel, c);
		c.gridy = 6;
		registerPanel.add(phoneLabel, c);
		c.gridy = 7;
		registerPanel.add(passportNoLabel, c);
		
		c.gridx = 1; c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		registerPanel.add(idField, c);
		c.gridy = 1;
		registerPanel.add(passwordField, c);
		c.gridy = 2;
		registerPanel.add(nameField, c);
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_START;

		String[] sexs = { "남성", "여성" };
		DefaultComboBoxModel<String> sexModel = new DefaultComboBoxModel<String>(sexs);
		sexCombo = new JComboBox<String>(sexModel);
		registerPanel.add(sexCombo, c);
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_END;
		registerPanel.add(birthField, c);
		c.gridy = 5;
		registerPanel.add(emailField, c);
		c.gridy = 6;
		registerPanel.add(phoneField, c);
		c.gridy = 7;
		registerPanel.add(passportNoField, c);
		c.gridx = 0; c.gridy = 8;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		registerPanel.add(registerButton, c);
		
		panel.setLayout(new BorderLayout());
		panel.add(headPanel, BorderLayout.NORTH);
		panel.add(registerPanel, BorderLayout.CENTER);
		
		add(panel);
	}
	
	private boolean allEntered() {
		return (newcomer.getId() != null
			&& newcomer.getPassword() != null
			&& newcomer.getName() != null
			&& newcomer.getSex() != null
			&& newcomer.getBirth() != null
			&& newcomer.getPassportNo() != null
		);
	}
	
	public boolean validateField() {
		String msg = "";
		boolean result = true;
	    String birthText = birthField.getText();

	    if (idField.getText().length() < 2 || idField.getText().length() > 10) {
	    	msg = "2~10글자의 아이디만 사용가능 합니다."; result = false;
	    }
	    else if (passwordField.getText().length() < 2 || passwordField.getText().length() > 10) {
	    	msg = "2~10글자의 비밀번호만 사용가능 합니다."; result = false;
	    }
	    else if (nameField.getText().length() < 1 || nameField.getText().length() > 10) {
	    	msg = "1~10글자의 이름만 사용가능 합니다."; result = false;
	    }
	    else if (birthText.length() != 10 || !birthText.matches("\\d{4}-\\d{2}-\\d{2}")) {
	    	msg = "YYYY-MM-DD 형식의 생년월일을 입력하세요."; result = false;
	    }
	    else if (!emailField.getText().matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}")) {
	        msg = "이메일을 잘못 입력했습니다"; result = false;
	    }
	    else if (!phoneField.getText().matches("\\d{11}")) {
	        msg = "전화번호를 잘못 입력했습니다."; result = false;
	    }
	    else if (!passportNoField.getText().matches("\\w\\d{8}")) {
	        msg = "여권번호를 잘못 입력했습니다.";  result = false;
	    }
	    
	    if (!result) JOptionPane.showMessageDialog(this, msg);
	    
	    return result;
	}
	
	public void setNewcomer() {
		newcomer = new User(
			null,
			idField.getText(),
			passwordField.getText(),
			nameField.getText(),
			null,
			null,
			passportNoField.getText(),
			phoneField.getText(),
			emailField.getText(),
			0,
			new Vector<Ticket>()
		);
		
		String selectedSex = (String) sexCombo.getSelectedItem();
		if (selectedSex.equals("남성")) newcomer.setSex(Sex.MALE);
		else if (selectedSex.equals("여성")) newcomer.setSex(Sex.FEMALE);
		
		String birthString = birthField.getText();
		if (!birthString.equals("")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			newcomer.setBirth(LocalDate.parse(birthString, formatter));
		}
	}
	
	public boolean register() {
		if (!validateField()) return false;
		setNewcomer();
		
		boolean result = true;
		boolean existId = UserManagementController.getUserById(newcomer.getId()) != null;
		
		String msg = "성공적으로 회원가입 되었습니다";
		if (existId) { msg = "이미 존재하는 아이디 입니다."; result = false; }
		
		JOptionPane.showMessageDialog(this, msg);
		return result;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("뒤로")) Main.gotoPage(new Login());
		else if (e.getActionCommand().equals("회원가입") && register()) {
			UserManagementController.addUser(newcomer);
			Main.gotoPage(new Login());
		}
	}
}