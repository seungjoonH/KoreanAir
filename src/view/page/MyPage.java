package view.page;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Global;
import view.Window;
import view.page.route.Route;
import view.page.Page;

public class MyPage extends Page {
	@Serial
	private static final long serialVersionUID = 1L;

	@Override
	protected String getTitle() { return "마이페이지"; }
	
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
		myResButton.addActionListener(new Route());
		
		JButton myInfoButton = new JButton("회원정보");
		myInfoButton.setPreferredSize(new Dimension(btnW, btnH));
		myInfoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myInfoButton.addActionListener(new Route());
		
		buttonPanel.add(myResButton, BorderLayout.WEST);
		buttonPanel.add(myInfoButton, BorderLayout.EAST);
		buttonPanel.setBounds(btnPanelLTx, btnPanelLTy, btnPanelW, btnPanelH);
		buttonPanel.setOpaque(false);

		JLabel backgroundLabel = new JLabel(Global.background);
		backgroundLabel.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(buttonPanel);
		panel.add(backgroundLabel);
		
		add(panel);
	}
}