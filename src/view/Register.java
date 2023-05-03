package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import model.User;

public class Register extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField idField;
	private JPasswordField passwordField;
	private JTextField nameField;
	JComboBox<String> sexCombo;
	private JTextField birthField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField passportNoField;
	
	public Register() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

		// TODO: 여기 채우세요
		JLabel idLabel = new JLabel("아이디 :");
		JLabel passwordLabel = new JLabel("비밀번호 :");
		JLabel nameLabel = new JLabel("이름 :");
		JLabel sexLabel = new JLabel("성별 :");
		JLabel birthLabel = new JLabel("생년월일(YYMMDD) :");
		JLabel emailLabel = new JLabel("이메일 :");
		JLabel phoneLabel = new JLabel("휴대폰(- 생략) :");
		JLabel passportNoLabel = new JLabel("여권번호(영문 1자리 + 숫자 8자리) :");

		idField = new JTextField(20);
		passwordField = new JPasswordField(20);
		nameField = new JTextField(20);
		birthField = new JTextField(20);
		emailField = new JTextField(20);
		phoneField = new JTextField(20);
		passportNoField = new JTextField(20);
		
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
		String[] sexClasses = { "남성", "여성" };
		DefaultComboBoxModel<String> sexModel = new DefaultComboBoxModel<>(sexClasses);
		sexCombo = new JComboBox<>(sexModel);
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
	
	public boolean register(String id, String password, String name, String sex, String birth, String email, String phone, String passportNo) {
		boolean result = false;
		User user = UserManagementController.getUserById(id);
		String msg;
		if(!id.equals("") && !password.equals("") && !name.equals("") && !birth.equals("") && !email.equals("") && !phone.equals("") && !passportNo.equals("")) {
			if (user == null) {
				msg = id + "\n" + name + "\n" + sex + "\n" + birth + "\n" + email + "\n" + phone + "\n" + passportNo + "\n" + "회원가입 완료!!!";
				result = true;
			}
			else msg = "이미 존재하는 ID입니다!";
		}
		else
			msg = "입력되지 않은 회원정보가 존재합니다.";
		
		JOptionPane.showMessageDialog(this, msg);
		return result;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idField.getText();
		String password = new String(passwordField.getPassword());
		String name = nameField.getText();
		String sex = sexCombo.getSelectedItem().toString();
		String birth = birthField.getText();
		String email = emailField.getText();
		String phone = phoneField.getText();
		String passportNo = passportNoField.getText();

		if (e.getActionCommand().equals("뒤로")) Main.gotoPage(new Login());
		else if (e.getActionCommand().equals("회원가입") && register(id, password, name, sex, birth, email, phone, passportNo)) Main.gotoPage(new Login());
	}
}