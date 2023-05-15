package view.widget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Global;
import main.Main;
import view.page.Page;

public class Appbar extends JPanel implements ActionListener {
	@Serial
	private static final long serialVersionUID = 1L;
	public static final int height = 40;

	private final JPanel leftWidget = new JPanel();
	private JPanel centerWidget = new JPanel();
	private final JPanel rightWidget = new JPanel();
	
	private Page backPage;
	public void setBackPage(Page page) { backPage = page; }
	
	protected JButton getBackButton() {
		JButton backButton = new JButton("뒤로");
		backButton.addActionListener(this);
		return backButton;
	}
	
	public Appbar() {
		super(new BorderLayout());
		setInit();
		build();
	}
	
	private void setInit() {
		setPreferredSize(new Dimension(Main.WIDTH, height));
		
		leftWidget.setLayout(new FlowLayout(FlowLayout.LEFT));
		rightWidget.setLayout(new FlowLayout(FlowLayout.RIGHT));
		leftWidget.setPreferredSize(new Dimension(160, 40));
		rightWidget.setPreferredSize(new Dimension(160, 40));
		
		Image resizedImage = Global.logo.getImage().getScaledInstance(220, 30, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));
		centerWidget.add(logoLabel);
	}
	
	private void build() {
		add(leftWidget, BorderLayout.WEST);
		add(centerWidget, BorderLayout.CENTER);
		add(rightWidget, BorderLayout.EAST);
	}
	
	public void setLeftWidget(JComponent widget) { leftWidget.add(widget); build(); }
	public void setRightWidget(JComponent widget) { rightWidget.add(widget); build(); }
	public void displayTitle(String title) { 
		centerWidget = new JPanel();
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		centerWidget.add(titleLabel); build(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main m = Main.getMain();
		if (e.getActionCommand().equals("뒤로") && backPage != null) m.gotoPage(backPage);
		
	}

}
