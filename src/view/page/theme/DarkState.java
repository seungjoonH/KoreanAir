package view.page.theme;

import javax.swing.JLabel;

import global.Global;
import view.Window;

import java.awt.*;

public class DarkState implements ThemeState {
	private static DarkState darkState = new DarkState();
	private DarkState() {}
	public static DarkState getInstance() {
		return darkState;
	}
	public String mode() { return "다크모드"; }
	@Override
	public JLabel getBackground() {
		JLabel label = new JLabel(Global.nightBackground);
		label.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
		return label;
	}
	@Override
	public Color getBackgroundColor() { return Color.DARK_GRAY; }
	@Override
	public Color getFontColor() { return Color.WHITE; }
	@Override
	public void toggleState() {
		ThemeMode.setState(RealTimeState.getInstance());
	}
}
