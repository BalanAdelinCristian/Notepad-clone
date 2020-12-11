package GUI.MenuBar.MenuBarElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JMenu;

import GUI.TextArea.TextArea;

@SuppressWarnings("serial")
public class Format extends JMenu {

	private JMenu format;

	public Format() {	
		format = new JMenu("Format");
		
	}

	public JMenu addElements() {
		format.setBackground(Color.WHITE);
		format.setFont(new Font("Segoe", Font.PLAIN, 12));
		// Word wrap option
		JCheckBox WordWrap = new JCheckBox("Word Wrap");
		WordWrap.addActionListener(wordWrapActionListener());
		WordWrap.setFont(new Font("Segoe", Font.PLAIN, 12));
		WordWrap.setBackground(Color.WHITE);
		format.add(WordWrap);

		return this.format;
	}

	public ActionListener wordWrapActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextArea.getTextArea().setWrapStyleWord(true);
				TextArea.getTextArea().setLineWrap(true);
			}
		};
	}

}
