package view.page.theme;

import javax.swing.JLabel;

import global.Global;
import view.Window;

import java.awt.*;

public class DarkState implements ThemeState {
	public ThemeMode.StateType getType() { return ThemeMode.StateType.DARK; }
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

}
