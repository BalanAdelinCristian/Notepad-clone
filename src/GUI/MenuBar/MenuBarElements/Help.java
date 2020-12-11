package GUI.MenuBar.MenuBarElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Help extends JMenu {
	JMenu help;

	public Help() {
		help = new JMenu("Help");
	}

	public JMenu addElements() {
		help.setBackground(Color.WHITE);
		help.setFont(new Font("Segoe", Font.PLAIN, 12));

		JMenuItem ViewHelp = new JMenuItem("View Help");
		ViewHelp.setFont(new Font("Segoe", Font.PLAIN, 12));
		ViewHelp.setBackground(Color.WHITE);
		help.add(ViewHelp);

		JMenuItem SendFeedback = new JMenuItem("Send Feedback");
		SendFeedback.setFont(new Font("Segoe", Font.PLAIN, 12));
		SendFeedback.setBackground(Color.WHITE);
		help.add(SendFeedback);

		JMenuItem AboutNotepad = new JMenuItem("About Notepad");
		AboutNotepad.setFont(new Font("Segoe", Font.PLAIN, 12));
		AboutNotepad.addActionListener(aboutNotepadActionListener());
		AboutNotepad.setBackground(Color.WHITE);
		help.add(AboutNotepad);

		return this.help;
	}

	public ActionListener aboutNotepadActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame aboutWindowFrame = new JFrame();
				aboutWindowFrame.getContentPane().setBackground(Color.WHITE);
				aboutWindowFrame.setVisible(true);
				aboutWindowFrame.setBounds(new Rectangle(500, 300));
				aboutWindowFrame.setLocationRelativeTo(null);
				JLabel textJLabel = new JLabel("Welcome to notepad!", CENTER);
				textJLabel.getFont().deriveFont(16);
				aboutWindowFrame.add(textJLabel);
			}
		};
	}
}
