package view.page.theme;

import view.page.route.Route;

import javax.swing.*;
import java.awt.*;

public class ThemeMode {
    private static ThemeState state = RealTimeState.getInstance();

    public static void toggleState() {
        state.toggleState();
        String msg = state.mode() + "로 전환되었습니다.";
        JOptionPane.showMessageDialog(Route.getThisPage(), msg);
    }
    public static void setState(ThemeState st) { state = st; }
    public static JLabel getBackground() { return state.getBackground(); }
    public static Color getBackgroundColor() { return state.getBackgroundColor(); }
    public static Color getFontColor() { return state.getFontColor(); }

}
