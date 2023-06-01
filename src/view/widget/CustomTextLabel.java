package view.widget;

import javax.swing.*;
import java.awt.*;

public class CustomTextLabel extends JLabel {

    public CustomTextLabel(String text) { super(text); }
    public CustomTextLabel(String text, int size) {
        super(text);
        setFont(new Font(getFont().getFamily(), Font.PLAIN, size));
    }
    public CustomTextLabel(String text, Color color) {
        super(text);
        Font font = getFont();
        setFont(new Font(font.getFamily(), Font.PLAIN, font.getSize()));
        setForeground(color);
    }
    public CustomTextLabel(String text, int size, Color color) {
        super(text);
        Font font = getFont();
        if (size == -1) size = font.getSize();
        setFont(new Font(font.getFamily(), Font.PLAIN, size));
        setForeground(color);
    }
    public CustomTextLabel(String text, int size, int style) {
        super(text);
        Font font = getFont();
        if (size == -1) size = font.getSize();
        setFont(new Font(font.getFamily(), style, size));
    }
    public CustomTextLabel(String text, int size, Color color, int style) {
        super(text);
        setFont(new Font(getFont().getFamily(), style, size));
        setForeground(color);
    }
}