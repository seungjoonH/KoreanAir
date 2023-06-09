package view.page.theme;

import javax.swing.JLabel;

import global.Global;
import view.Window;

import java.awt.*;

public class LightState implements ThemeState {
	private static LightState lightState = new LightState();
	private LightState() {}
	public static LightState getInstance() {
		return lightState;
	}
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
	@Override
	public void toggleState() {
		ThemeMode.setState(DarkState.getInstance());
	}
}
