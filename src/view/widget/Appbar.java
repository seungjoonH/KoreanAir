package view.widget;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Global;
import view.Window;
import view.page.route.Route;
import view.page.theme.ThemeMode;

public class Appbar extends JPanel implements MouseListener {
	@Serial
	private static final long serialVersionUID = 1L;
	public static final int height = 40;

	private JPanel panel;
	private final JPanel leftWidget = new JPanel();
	private JPanel centerWidget = new JPanel();
	private final JPanel rightWidget = new JPanel();

	public Appbar() {
		super(new BorderLayout());
		setInit(); build();
	}
	
	private void setInit() {
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(Window.WIDTH, height));
		panel.setBackground(ThemeMode.getBackgroundColor());

		leftWidget.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftWidget.setPreferredSize(new Dimension(180, 30));
		leftWidget.setOpaque(false);
		rightWidget.setLayout(new FlowLayout(FlowLayout.RIGHT));
		rightWidget.setPreferredSize(new Dimension(180, 30));
		rightWidget.setOpaque(false);
		
		Image resizedImage = Global.logo.getImage().getScaledInstance(220, 30, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));
		logoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logoLabel.addMouseListener(this);
		centerWidget.removeAll();
		centerWidget.add(logoLabel);
		centerWidget.setOpaque(false);

		repaint();
	}

	public void build() {
		panel.setBackground(ThemeMode.getBackgroundColor());
		panel.add(leftWidget, BorderLayout.WEST);
		panel.add(centerWidget, BorderLayout.CENTER);
		panel.add(rightWidget, BorderLayout.EAST);

		add(panel);
	}
	
	public void setLeftWidget(JComponent widget) {
		widget.setOpaque(false);
		leftWidget.removeAll(); leftWidget.add(widget); build();
	}
	public void setRightWidget(JComponent widget) {
		widget.setOpaque(false);
		rightWidget.removeAll(); rightWidget.add(widget); build();
	}
	public void displayTitle(String title) {
		centerWidget = new JPanel();
		centerWidget.setOpaque(false);
		Color fontColor = ThemeMode.getFontColor();
		JLabel titleLabel = new CustomTextLabel(title, 20, fontColor, Font.BOLD);
		centerWidget.removeAll();
		centerWidget.add(titleLabel);
		build();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ThemeMode.toggleState();
		Route.getThisPage().refresh();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
