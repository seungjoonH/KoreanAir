package view.widget;

import view.Window;

import javax.swing.*;
import java.awt.*;

public class HeaderTypeLabel extends JLabel {
    public HeaderTypeLabel(String text, boolean bold) {
        super(text);
        setFont(getFont().deriveFont(20f));
        if (bold) setFont(getFont().deriveFont(Font.BOLD));
    }
}
