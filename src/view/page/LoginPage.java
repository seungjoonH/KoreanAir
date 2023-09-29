package view.page;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.user.User;
import util.logger.Logger;
import view.page.route.Route;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;

public class LoginPage extends Page {
	public enum LoginState { NO_MEM, PW_INCRT, SUCCESS }

	@Override
	protected String getTitle() { return "로그인"; }
	
	private JTextField idField;
	private JTextField pwField;

	public LoginPage() {
		super(null, null, true);
	}
	public LoginPage(JComponent left) {
		super(left, null, true);
	}
	public LoginPage(JComponent left, JComponent right) {
		super(left, right, true);
	}
	public LoginPage(JComponent left, JComponent right, boolean displayTitle) {
		super(left, right, displayTitle);
	}

	@Override
	protected void buildContent() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(ThemeMode.getBackgroundColor());

		Color fontColor = ThemeMode.getFontColor();

		JLabel idLabel = new CustomTextLabel("아이디:", fontColor);
		JLabel passwordLabel = new CustomTextLabel("비밀번호:", fontColor);

		idField = new JTextField(20);
		pwField = new JPasswordField(20);
		
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(this);
		JButton registerButton = new JButton("회원가입");
		registerButton.addActionListener(new Route());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		buttonPanel.setOpaque(false);

		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; 
		formPanel.add(idLabel, c);
		
		c.gridx = 0; c.gridy = 1;
		formPanel.add(passwordLabel, c);
		
		c.gridx = 1; c.gridy = 0;
		formPanel.add(idField, c);
		
		c.gridx = 1; c.gridy = 1;
		formPanel.add(pwField, c);
		
		c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
		formPanel.add(buttonPanel, c);

		formPanel.setOpaque(false);
		panel.add(formPanel);

		add(panel, BorderLayout.CENTER);
	}
	
	public void login() {
		String id = idField.getText();
		String pw = pwField.getText();
		
		LoginState state = User.login(id, pw);
		
		String name = "";
		
		if (state == LoginState.SUCCESS) {
			name = User.getName();
			boolean isAdmin = User.isLoggedUserAdmin();
			name += isAdmin ? " 관리자" : "";
		}
		
		String[] msgs = {
			"존재하지 않는 회원입니다.", 
			"비밀번호가 틀립니다.", 
			name + "님 로그인 되었습니다."
		};
		
		JOptionPane.showMessageDialog(this, msgs[state.ordinal()]);
		if (state == LoginState.SUCCESS) {
			Route.goHome();
			Logger.get().log("Logged in");
		}
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("로그인")) login();
	}
}
