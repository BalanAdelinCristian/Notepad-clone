package GUI.MenuBar.MenuBarElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import GUI.TextArea.TextArea;
import Main.Window;

public class FindWordWindow {

	private final JFrame frame;
	private static JTextField textField;
	private static JCheckBox matchCaseCheckBox;
	private static boolean isFindWindowClosed = false;

	public FindWordWindow() {
		matchCaseCheckBox = new JCheckBox("Match Case CheckBox");
		this.frame = new JFrame();
		textField = new JTextField();
		showWindow();
	}

	private JFrame showWindow() {
		frame.setBounds(100, 100, 450, 150);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);;
		frame.setVisible(true);
		frame.setFont(new Font("Segoe", Font.PLAIN, 12));
		frame.setLocationRelativeTo(Window.textArea);

		// Text field where the user can input the desired word to find
		textField.setBounds(68, 24, 245, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setFont(new Font("Segoe", Font.PLAIN, 12));

		// Button which activates the search function in the main text frame
		JButton findButton = new JButton("Find next");
		findButton.setFont(new Font("Segoe", Font.PLAIN, 12));
		findButton.addActionListener(findNextActionListener());
		findButton.setBounds(325, 23, 89, 23);
		findButton.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(findButton);

		// Cancel button used to close the current window
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Segoe", Font.PLAIN, 12));
		cancelButton.setBounds(325, 57, 89, 23);
		cancelButton.addActionListener(cancelButtonActionListener());
		cancelButton.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(cancelButton);

		// Match case of the desired word
		matchCaseCheckBox.setBounds(65, 60, 97, 23);
		matchCaseCheckBox.setBackground(Color.WHITE);
		frame.getContentPane().add(matchCaseCheckBox);

		// Label shown to the left side of the search field
		JLabel textFieldLabel = new JLabel("Find what");
		textFieldLabel.setFont(new Font("Segoe", Font.PLAIN, 12));
		textFieldLabel.setBounds(10, 27, 60, 14);
		frame.getContentPane().add(textFieldLabel);

		return frame;
	}

	private ActionListener cancelButtonActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isFindWindowClosed = true;
				frame.dispose();
			}
		};
	}

	public ActionListener findNextActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Checking if we have a word in the text field
				if (getTextField().getText() != "") {
					// Creation of a new thread to search in the document
					new Thread() {

						@Override
						public void run() {
							
							// Word to find and the documents's text
							String wordToFind = FindWordWindow.getTextField().getText();
							String text = TextArea.getTextArea().getText();

							// Highlighter class used to highlight the found word
							Highlighter highlighter = TextArea.getTextArea().getHighlighter();
							HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
									new Color(79, 215, 215));

							// Test if the "Match Case" option is checked 
							if (FindWordWindow.getMatchCaseCheckBoxState() == false) {
								wordToFind.toLowerCase();
								text.toLowerCase();
							}
							
							// Checking for the desired word in the document
							if (text.contains(wordToFind)) {
								
								// Position of the word in the document
								int pos = TextArea.getTextArea().getText().indexOf(wordToFind);
								
								// Highlightion of the text
								for (int i = 0; i < wordToFind.length(); i++) {
									try {
										highlighter.addHighlight(pos + i, pos + i + 1, painter);
									} catch (BadLocationException e) {
									}
								}
							}
							// Waiting for the window to be closed
							while(isWindowClosed() == false) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
								}
							}

							// Removal of the highlightion
							if (isWindowClosed() == true) {
								highlighter.removeAllHighlights();
							}
						}
					}.start();
				}
			}
		};

	}

	public static JTextField getTextField() {
		return textField;
	}

	public static boolean getMatchCaseCheckBoxState() {
		return (matchCaseCheckBox.isSelected() ? true : false);
	}

	public static boolean isWindowClosed() {
		return (isFindWindowClosed ? true : false);
	}
}
