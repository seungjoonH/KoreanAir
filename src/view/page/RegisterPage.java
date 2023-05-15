package view.page;

import java.awt.event.ActionEvent;
import java.io.Serial;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import model.enums.RegisterState;
import model.user.User;
import view.listener.Route;

public class RegisterPage extends CustomerInfoPage {
	@Serial
	private static final long serialVersionUID = 1L;

	RegisterState state;

	@Override
	protected String getTitle() { return "회원가입"; }
	
	public RegisterPage(JComponent left, JComponent right, boolean displayTitle) {
		super(left, right, displayTitle); 
	}

	@Override
	protected String getSubmitButtonText() { return "회원가입"; }

	public void register() {
		if (!validateField()) state = RegisterState.INVLD_IPT;
		setUser();

		String msg = "성공적으로 회원가입 되었습니다.";
		state = User.register(formedUser);
		
		if (state == RegisterState.DUP_ID) msg = "이미 존재하는 아이디 입니다.";
		
		JOptionPane.showMessageDialog(this, msg);
	}

	protected void submit() { register(); }

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (state == RegisterState.SUCCESS) Route.getRoute().goBack();
	}
}