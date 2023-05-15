package view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serial;

import javax.swing.JComponent;
import javax.swing.JPanel;

import main.Main;
import view.widget.Appbar;

public abstract class Page extends JPanel {
	@Serial
	private static final long serialVersionUID = 1L;
	
	protected Appbar appbar = new Appbar();

	protected abstract String getTitle();
	
	protected Page(JComponent left, JComponent right, boolean displayTitle) { 
		super(new BorderLayout());
		setInit();
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		buildAppbar(left, right, displayTitle ? getTitle() : null);
		build();
	}

	protected void setInit() {}
	
	protected void buildAppbar(JComponent left, JComponent right, String title) {
		if (left != null) appbar.setLeftWidget(left);
		if (right != null) appbar.setRightWidget(right);
		if (title != null) appbar.displayTitle(title);
		add(appbar, BorderLayout.NORTH);
	}
	
	protected abstract void build();
}