package GUI.MenuBar;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenuBar;

import GUI.MenuBar.MenuBarElements.Edit;
import GUI.MenuBar.MenuBarElements.File;
import GUI.MenuBar.MenuBarElements.Format;
import GUI.MenuBar.MenuBarElements.Help;
import GUI.MenuBar.MenuBarElements.View;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	private JMenuBar menuBar;

	public MenuBar() {
		menuBar = new JMenuBar();
	}
	
	public JMenuBar addElements() {
		// Adding the elements of the menuBar entity
		this.menuBar.add(new File().addElements());
		this.menuBar.add(new Edit().addElements());
		this.menuBar.add(new Format().addElements());
		this.menuBar.add(new View().addElements());
		this.menuBar.add(new Help().addElements());
		this.menuBar.setBackground(Color.WHITE);
		this.menuBar.setFont(new Font("Segoe", Font.PLAIN, 10));
		
		return this.menuBar;
	}
}
