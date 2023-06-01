package view.page.theme;

import javax.swing.JLabel;

import global.Global;
import view.Window;

import java.awt.*;

public class LightState implements ThemeState {
	public ThemeMode.StateType getType() { return ThemeMode.StateType.LIGHT; }
	public String mode() { return "라이트모드"; }
	@Override
	public JLabel getBackground() {
		JLabel label = new JLabel(Global.daytimeBackground);
		label.setBounds(0, 0, Window.WIDTH, Window.HEIGHT);
		return label;
	}
	@Override
	public Color getBackgroundColor() { return new Color(0xf9f9f9); }
	@Override
	public Color getFontColor() { return Color.BLACK; }
}
