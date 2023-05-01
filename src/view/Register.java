package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.Main;

public class Register extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public Register() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

		// TODO: 여기 채우세요
		
		add(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
}