package view.page;

import java.awt.event.ActionEvent;
import java.io.Serial;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import model.user.Customer;
import model.user.User;
import util.logger.Logger;

public class RegisterPage extends CustomerInfoPage {
	public enum RegisterState { DUP_ID, INVLD_IPT, SUCCESS }

	@Serial
	private static final long serialVersionUID = 1L;

	RegisterState state;

	@Override
	protected String getTitle() { return "회원가입"; }

	public RegisterPage() {
		super(null, null, true);
	}
	public RegisterPage(JComponent left) {
		super(left, null, true);
	}
	public RegisterPage(JComponent left, JComponent right) {
		super(left, right, true);
	}
	public RegisterPage(JComponent left, JComponent right, boolean displayTitle) {
		super(left, right, displayTitle);
	}

	@Override
	protected String getSubmitButtonText() { return "회원가입"; }

	public void register() {
		if (!validateField()) {
			state = RegisterState.INVLD_IPT;
			return;
		}
		setUser();

		String msg = "성공적으로 회원가입 되었습니다.";
		state = User.register(formedUser);
		if (state == RegisterState.DUP_ID) msg = "이미 존재하는 아이디 입니다.";
		JOptionPane.showMessageDialog(this, msg);

		Logger.get().log("Registered");
	}

	@Override
	protected void setUser() {
		String[] csvList = getCSV();
		csvList[0] = User.getNextUid();
		csvList[csvList.length - 1] = "0";
		formedUser = new Customer(csvList);
	}

	protected void submit() { register(); }

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (state == RegisterState.SUCCESS) goBack();
	}
}
