package view.page;

import javax.swing.JLabel;

import global.Global;

public class NightBackground implements BackgroundState {
	private final Background background;

	public NightBackground(Background background){
        this.background = background;
}

	@Override
	public JLabel display() {
		return new JLabel(Global.nightBackground);
	}
}
