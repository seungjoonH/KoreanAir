package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.UserManagementController;
import main.Main;
import main.Pages;
import model.User;

public class Login extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField idField;
	private JPasswordField passwordField;

	public Login() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		
		JLabel idLabel = new JLabel("아이디:");
		JLabel passwordLabel = new JLabel("비밀번호:");

		idField = new JTextField(20);
		passwordField = new JPasswordField(20);

		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(this);

		// 뒤로 가기 버튼 생성
		JPanel headPanel = new JPanel(new BorderLayout());
		JButton backButton = new JButton("뒤로");
		backButton.addActionListener(this);
		backButton.setPreferredSize(new Dimension(50, 20));
		headPanel.add(backButton, BorderLayout.WEST);

		JPanel loginPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		loginPanel.add(idLabel, c);
		c.gridy = 1;
		loginPanel.add(passwordLabel, c);
		c.gridx = 1; c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		loginPanel.add(idField, c);
		c.gridy = 1;
		loginPanel.add(passwordField, c);
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		loginPanel.add(loginButton, c);

		panel.setLayout(new BorderLayout());
		panel.add(headPanel, BorderLayout.NORTH);
		panel.add(loginPanel, BorderLayout.CENTER);

		add(panel);
	}
	
	public boolean login(String id, String password) {
		User user = UserManagementController.getUserById(id);
		String msg;
		if (user != null) {
			if (password.equals(user.getPassword())) {
				Main.setUser(user);
				return true;
			}
			msg = "비밀번호가 틀립니다";
		}
		else msg = "'" + id + "' 님이 존재하지 않습니다";
		
		JOptionPane.showMessageDialog(this, msg);
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idField.getText();
		String password = new String(passwordField.getPassword());

		if (e.getActionCommand().equals("뒤로")) Main.changeLevel(Pages.MENU);
		else if (e.getActionCommand().equals("로그인") && login(id, password)) Main.changeLevel(Pages.MENU);
	}


}