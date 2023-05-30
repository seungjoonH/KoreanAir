package view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.JComponent;
import javax.swing.JPanel;

import main.Main;
import view.Window;
import view.page.route.Route;
import view.widget.Appbar;

public abstract class Page extends JPanel implements ActionListener {
	@Serial
	private static final long serialVersionUID = 1L;
	
	protected Appbar appbar = new Appbar();
	private final JComponent left;
	private final JComponent right;
	private final boolean displayTitle;

	protected abstract String getTitle();

	protected Page(JComponent left, JComponent right, boolean displayTitle) { 
		super(new BorderLayout());
		this.left = left; this.right = right;
		this.displayTitle = displayTitle;
		build();
	}

	public void refresh() {
		removeAll();
		build();
		Main.build();
	}

	protected final void build() {
		setInit();
		setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		buildAppbar();
		buildContent();
	}

	protected void setInit() {}
	
	protected void buildAppbar() {
		String title = displayTitle ? getTitle() : null;

		if (left != null) appbar.setLeftWidget(left);
		if (right != null) appbar.setRightWidget(right);
		if (title != null) appbar.displayTitle(title);
		add(appbar, BorderLayout.NORTH);
	}
	
	protected abstract void buildContent();

	protected void goBack() { Route.goBack(); }
	protected void goTo(Page page) { Route.goTo(page); }
}