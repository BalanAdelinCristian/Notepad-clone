package GUI.MenuBar.MenuBarElements;

import java.awt.Color;
import java.awt.Label;
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

public class ReplaceWordWindow {

	private final JFrame frame;
	private static JTextField findWhatTextField;
	private static JTextField replaceWithTextField;
	private static JCheckBox matchCaseCheckBox;
	private static boolean isFindWindowClosed = false;

	public ReplaceWordWindow() {
		matchCaseCheckBox = new JCheckBox("Match Case CheckBox");
		this.frame = new JFrame();
		findWhatTextField = new JTextField();
		replaceWithTextField = new JTextField();
		showWindow();
	}

	private JFrame showWindow() {
		frame.setBounds(100, 100, 504, 222);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(Window.textArea);

		// Text field where the user can input the desired word to find
		findWhatTextField.setBounds(105, 24, 253, 20);
		frame.getContentPane().add(findWhatTextField);
		findWhatTextField.setColumns(10);

		// Replace field
		replaceWithTextField.setBounds(105, 55, 253, 20);
		frame.getContentPane().add(replaceWithTextField);
		replaceWithTextField.setColumns(10);

		// Button which activates the search function in the main text frame
		JButton findButton = new JButton(" Find");
		findButton.setBounds(369, 23, 100, 23);
		findButton.addActionListener(findNextActionListener());
		frame.getContentPane().add(findButton);

		// Cancel button used to close the current window
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(369, 125, 100, 23);
		cancelButton.addActionListener(cancelButtonActionListener());
		frame.getContentPane().add(cancelButton);

		// Match case of the desired word
		JCheckBox matchCaseCheckBox = new JCheckBox("Match case");
		matchCaseCheckBox.setBounds(10, 95, 97, 23);
		frame.getContentPane().add(matchCaseCheckBox);

		// Label shown to the left side of the search field
		JLabel findWhatLabel = new JLabel("Find what");
		findWhatLabel.setBounds(10, 27, 62, 14);
		frame.getContentPane().add(findWhatLabel);

		// Button which activates the replace function for the first
		// occurance of the desired word
		JButton replaceButton = new JButton("Replace");
		replaceButton.setBounds(369, 57, 100, 23);
		replaceButton.addActionListener(replaceActionListener());
		frame.getContentPane().add(replaceButton);

		// Button which activates the replace function for all the occurances
		// of the desired word
		JButton replaceAllButton = new JButton("Replace All");
		replaceAllButton.setBounds(369, 91, 100, 23);
		replaceAllButton.addActionListener(replaceAllActionListener());
		frame.getContentPane().add(replaceAllButton);

		// Label located to the left side of the replace field
		Label replaceWithLabel = new Label("Replace with");
		replaceWithLabel.setBounds(10, 58, 77, 22);
		frame.getContentPane().add(replaceWithLabel);

		return this.frame;
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
				if (getFindWhatTextField().getText() != "") {
					new Thread() {

						@Override
						public void run() {

							String wordToFind = ReplaceWordWindow.getFindWhatTextField().getText();
							String text = TextArea.getTextArea().getText();

							Highlighter highlighter = TextArea.getTextArea().getHighlighter();
							HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
									new Color(79, 215, 215));

							if (ReplaceWordWindow.getMatchCaseCheckBoxState() == false) {
								wordToFind.toLowerCase();
								text.toLowerCase();
							}

							if (text.contains(wordToFind)) {

								int pos = TextArea.getTextArea().getText().indexOf(wordToFind);

								for (int i = 0; i < wordToFind.length(); i++) {
									try {
										highlighter.addHighlight(pos + i, pos + i + 1, painter);
									} catch (BadLocationException e) {
									}
								}
							}

							while (isWindowClosed() == false) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
								}
							}

							if (isWindowClosed() == true) {
								highlighter.removeAllHighlights();
							}
						}
					}.start();
				}
			}
		};

	}

	public ActionListener replaceActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getFindWhatTextField().getText() != "" && getReplaceWithTextField().getText() != "") {
					new Thread() {

						@Override
						public void run() {

							String wordToFind = ReplaceWordWindow.getFindWhatTextField().getText();
							String replacementString = ReplaceWordWindow.getReplaceWithTextField().getText();
							String text = TextArea.getTextArea().getText();

							if (ReplaceWordWindow.getMatchCaseCheckBoxState() == false) {
								wordToFind.toLowerCase();
								replacementString.toLowerCase();
								text.toLowerCase();
							}

							if (text.contains(wordToFind)) {

								int pos = TextArea.getTextArea().getText().indexOf(wordToFind);

								try {
									TextArea.getTextArea().replaceRange(replacementString, pos, pos);
								} catch (Exception e) {
								}
							}
						}
					}.start();
				}
			}
		};
	}

	public ActionListener replaceAllActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getFindWhatTextField().getText() != "" && getReplaceWithTextField().getText() != "") {
					new Thread() {

						@Override
						public void run() {

							String wordToFind = ReplaceWordWindow.getFindWhatTextField().getText();
							String replacementString = ReplaceWordWindow.getReplaceWithTextField().getText();
							String text = TextArea.getTextArea().getText();

							if (ReplaceWordWindow.getMatchCaseCheckBoxState() == false) {
								wordToFind.toLowerCase();
								replacementString.toLowerCase();
								text.toLowerCase();
							}
							TextArea.getTextArea().insert("   ", TextArea.getTextArea().getText().length());

							while (text.contains(wordToFind)) {

								int pos = TextArea.getTextArea().getText().indexOf(wordToFind);

								try {
									TextArea.getTextArea().replaceRange(replacementString, pos,
											pos + replacementString.length() + 1);
								} catch (Exception e) {
								}
							}
						}
					}.start();
				}
			}
		};
	}

	public static JTextField getFindWhatTextField() {
		return findWhatTextField;
	}

	public static JTextField getReplaceWithTextField() {
		return replaceWithTextField;
	}

	public static boolean getMatchCaseCheckBoxState() {
		return (matchCaseCheckBox.isSelected() ? true : false);
	}

	public static boolean isWindowClosed() {
		return (isFindWindowClosed ? true : false);
	}

}
