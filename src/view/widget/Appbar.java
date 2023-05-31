package view.widget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.Serial;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Global;
import view.Window;

public class Appbar extends JPanel {
	@Serial
	private static final long serialVersionUID = 1L;
	public static final int height = 40;

	private final JPanel leftWidget = new JPanel();
	private JPanel centerWidget = new JPanel();
	private final JPanel rightWidget = new JPanel();

	public Appbar() {
		super(new BorderLayout());
		setInit();
		build();
	}
	
	private void setInit() {
		setPreferredSize(new Dimension(Window.WIDTH, height));
		
		leftWidget.setLayout(new FlowLayout(FlowLayout.LEFT));
		rightWidget.setLayout(new FlowLayout(FlowLayout.RIGHT));
		leftWidget.setPreferredSize(new Dimension(180, 30));
		rightWidget.setPreferredSize(new Dimension(180, 30));
		
		Image resizedImage = Global.logo.getImage().getScaledInstance(220, 30, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));
		centerWidget.add(logoLabel);
	}
	
	private void build() {
		removeAll();
		add(leftWidget, BorderLayout.WEST);
		add(centerWidget, BorderLayout.CENTER);
		add(rightWidget, BorderLayout.EAST);
	}
	
	public void setLeftWidget(JComponent widget) {
		leftWidget.removeAll(); leftWidget.add(widget); build();
	}
	public void setRightWidget(JComponent widget) {
		rightWidget.removeAll(); rightWidget.add(widget); build();
	}
	public void displayTitle(String title) { 
		centerWidget = new JPanel();
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("LucidaGrande", Font.BOLD, 20));
		centerWidget.add(titleLabel); build();
	}
}
