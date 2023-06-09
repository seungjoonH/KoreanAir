package view.page.theme;

import com.sun.net.httpserver.Request;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class RealTimeState implements ThemeState {
    private static RealTimeState realTimeState = new RealTimeState();
    private RealTimeState() {}
    public static RealTimeState getInstance() {
        return realTimeState;
    }
    public String mode() { return "실시간모드"; }
    private static ThemeState getStateByTime() {
        int hour = LocalDateTime.now().getHour();
        return hour > 5 && hour < 17 ? LightState.getInstance() : DarkState.getInstance();
    }
    @Override
    public JLabel getBackground() { return getStateByTime().getBackground(); }
    @Override
    public Color getBackgroundColor() { return getStateByTime().getBackgroundColor(); }
    @Override
    public Color getFontColor() { return getStateByTime().getFontColor(); }
    public void toggleState() {
        ThemeMode.setState(LightState.getInstance());
    }
}
