package view.page.theme;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class RealTimeState implements ThemeState {
    public ThemeMode.StateType getType() { return ThemeMode.StateType.REALTIME; }
    public String mode() { return "실시간모드"; }
    private static ThemeState getStateByTime() {
        int hour = LocalDateTime.now().getHour();
        return hour > 5 && hour < 17 ? new LightState() : new DarkState();
    }
    @Override
    public JLabel getBackground() { return getStateByTime().getBackground(); }
    @Override
    public Color getBackgroundColor() { return getStateByTime().getBackgroundColor(); }
    @Override
    public Color getFontColor() { return getStateByTime().getFontColor(); }
}
