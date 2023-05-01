package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.Main;

public class MyInfo extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public MyInfo() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		
		add(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
}
