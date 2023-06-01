package view.page.theme;

import view.page.route.Route;

import javax.swing.*;
import java.awt.*;

public class ThemeMode {
    enum StateType { LIGHT, DARK, REALTIME };
    private static ThemeState state = new RealTimeState();

    public static void toggleState() {
        String msg = null;

        switch (state.getType()) {
            case LIGHT -> setState(new DarkState());
            case DARK -> setState(new RealTimeState());
            case REALTIME -> setState(new LightState());
        }

        msg = state.mode() + "로 전환되었습니다.";
        JOptionPane.showMessageDialog(Route.getThisPage(), msg);
    }
    public static void setState(ThemeState st) { state = st; }
    public static JLabel getBackground() { return state.getBackground(); }
    public static Color getBackgroundColor() { return state.getBackgroundColor(); }
    public static Color getFontColor() { return state.getFontColor(); }


}
