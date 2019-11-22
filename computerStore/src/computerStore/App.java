package computerStore;

import computerStore.Menu.MenuView;

import javax.swing.*;

public class App {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception ignored){}
		new MenuView().setVisible(true);

	}
	
}
