package view.page.theme;
import javax.swing.JLabel;
import java.awt.*;

public interface ThemeState {
	ThemeMode.StateType getType();
	String mode();
	JLabel getBackground();
	Color getBackgroundColor();
	Color getFontColor();
}