package view.page;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.user.User;
import view.Window;
import view.page.route.Route;
import view.page.theme.ThemeMode;
import view.widget.button.LoginButton;
import view.widget.widget.ProfileWidget;

public class HomePage extends Page {

	@Override
	protected String getTitle() { return "홈"; }

	public HomePage() {
		super(null, new LoginButton(), false);
		appbar.setLeftWidget(new ProfileWidget());
	}
	public HomePage(JComponent left) {
		super(left, new LoginButton(), false);
	}
	public HomePage(JComponent left, JComponent right) {
		super(left, right, false);
	}
	public HomePage(JComponent left, JComponent right, boolean displayTitle) {
		super(left, right, displayTitle);
	}


	@Override
	protected void buildContent() {
		appbar.setLeftWidget(new ProfileWidget());

		final int btnW = 200;
		final int btnH = 100;
		
		final int btnPanelW = Window.WIDTH / 2;
		final int btnPanelH = 100;
		final int btnPanelLTx = Window.WIDTH / 4;
		final int btnPanelLTy = Window.HEIGHT / 2 - btnPanelH;

		JPanel buttonPanel = new JPanel(new BorderLayout());

		String leftText = "항공편조회/" + (User.isLoggedUserAdmin() ? "수정" : "예약");
		String rightText = User.isLoggedUserAdmin() ? "항공편생성" : "마이페이지";

		JButton searchButton = new JButton(leftText);
		searchButton.setPreferredSize(new Dimension(btnW, btnH));
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new Route());
		
		JButton myButton = new JButton(rightText);
		myButton.setPreferredSize(new Dimension(btnW, btnH));
		myButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myButton.addActionListener(User.isLogged() ? new Route() : this);
		
		buttonPanel.add(searchButton, BorderLayout.WEST);
		buttonPanel.add(myButton, BorderLayout.EAST);
		buttonPanel.setBounds(btnPanelLTx, btnPanelLTy, btnPanelW, btnPanelH);
		buttonPanel.setOpaque(false);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(buttonPanel);
		panel.add(ThemeMode.getBackground());
		panel.setBackground(ThemeMode.getBackgroundColor());

		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		String msg = null;

		if (c.equals("마이페이지")) msg = "먼저 로그인 해주세요!";

		if (msg == null) return;
		JOptionPane.showMessageDialog(this, msg);
	}

}