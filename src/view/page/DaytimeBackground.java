package view.page;

import javax.swing.JLabel;

import global.Global;

public class DaytimeBackground implements BackgroundState {
	private final Background background;
	
	public DaytimeBackground(Background background){
        this.background = background;
}

	@Override
	public JLabel display() {
		return new JLabel(Global.daytimeBackground);
	}
}
