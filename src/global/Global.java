package global;

import model.user.Customer;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Global {
	public static final String appTitle = "Korean Air"; 
	public static final Dimension windowSize = new Dimension(1024, 600);
	
	public static Customer.Sex[] sexs = { Customer.Sex.MALE, Customer.Sex.FEMALE };
	
	public static ImageIcon logo = new ImageIcon("./asset/logo.png");
	private static Image backImage = new ImageIcon("./asset/background.png")
		.getImage().getScaledInstance(windowSize.width, windowSize.height, Image.SCALE_DEFAULT);
	public static ImageIcon background = new ImageIcon(backImage);
}
