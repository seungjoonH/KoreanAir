package view;

import global.Global;
import view.page.HomePage;
import view.page.route.Route;

import javax.swing.*;

// 프로그램 실행 시 최초 빌드되는 창 위젯
public class Window extends JFrame {
    
    public static int WIDTH;
    public static int HEIGHT;

    public Window() { initSet(); }

    void initSet() {
        final String title = Global.appTitle;
        WIDTH = Global.windowSize.width;
        HEIGHT = Global.windowSize.height;

        Route.push(new HomePage());

        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void build() {
        getContentPane().removeAll();
        getContentPane().add(Route.getThisPage());
        revalidate();
        repaint();
    }
}
