package view.page;

import java.time.LocalDateTime;
import javax.swing.JLabel;

public class Background implements BackgroundState {
	private BackgroundState nightBackground;
	private BackgroundState daytimeBackground;

	private BackgroundState state;

	public Background() {
		this.daytimeBackground = new DaytimeBackground(this);
		this.nightBackground = new NightBackground(this);

		this.state =
				LocalDateTime.now().getHour() > 5 && LocalDateTime.now().getHour() < 17
				? daytimeBackground
				: nightBackground;
	}

	public void setBackgroundState(BackgroundState state) {
		this.state = state;
	}

	@Override
	public JLabel display() {
		return state.display();
	}

	public BackgroundState getDaytimeBackground() {
		return daytimeBackground;
	}

	public void setDaytimeBackground(BackgroundState daytimeBackground) {
		this.daytimeBackground = daytimeBackground;
	}

	public BackgroundState getNightBackground() {
		return nightBackground;
	}

	public void setNightBackground(BackgroundState nightBackground) {
		this.nightBackground = nightBackground;
	}

	public BackgroundState getState() {
		return state;
	}

	public void setState(BackgroundState state) {
		this.state = state;
	}
}
