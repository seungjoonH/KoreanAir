package view.page;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.Serial;
import java.util.List;

import javax.swing.*;

import model.dao.ReservationDAOFactory;
import model.reservation.Reservation;
import model.user.User;
import view.Window;
import view.page.route.Route;
import view.page.theme.ThemeMode;

public class MyPage extends Page {
	@Serial
	private static final long serialVersionUID = 1L;

	@Override
	protected String getTitle() { return "마이페이지"; }

	public MyPage() {
		super(null, null, true);
	}
	public MyPage(JComponent left) {
		super(left, null, true);
	}
	public MyPage(JComponent left, JComponent right) {
		super(left, right, true);
	}
	public MyPage(JComponent left, JComponent right, boolean displayTitle) {
		super(left, right, displayTitle);
	}

	@Override
	protected void buildContent() {
		final int btnW = 200;
		final int btnH = 100;
		
		final int btnPanelW = Window.WIDTH / 2;
		final int btnPanelH = 100;
		final int btnPanelLTx = Window.WIDTH / 4;
		final int btnPanelLTy = Window.HEIGHT / 2 - btnPanelH;
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		
		JButton myResButton = new JButton("예약정보");
		myResButton.setPreferredSize(new Dimension(btnW, btnH));
		myResButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		List<Reservation> reservations = ReservationDAOFactory.getFactory()
				.getReservationByUid(User.getUid());

		boolean reserved = reservations.size() > 0;
		myResButton.addActionListener(reserved ? new Route() : this);

		JButton myInfoButton = new JButton("회원정보");
		myInfoButton.setPreferredSize(new Dimension(btnW, btnH));
		myInfoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myInfoButton.addActionListener(new Route());
		
		buttonPanel.add(myResButton, BorderLayout.WEST);
		buttonPanel.add(myInfoButton, BorderLayout.EAST);
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

		if (c.equals("예약정보")) msg = "예약 정보가 없습니다!\n 먼저 예약해주세요.";

		if (msg == null) return;
		JOptionPane.showMessageDialog(this, msg);
	}
}