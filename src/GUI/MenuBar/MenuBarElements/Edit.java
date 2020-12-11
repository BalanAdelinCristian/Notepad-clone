package GUI.MenuBar.MenuBarElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.text.DefaultEditorKit;

import GUI.TextArea.TextArea;
import Main.Window;

@SuppressWarnings("serial")
public class Edit extends JMenu {

	JMenu edit;

	public Edit() {
		edit = new JMenu("Edit");
		edit.setFont(new Font("Segoe", Font.PLAIN, 12));
	}

	public JMenu addElements() {
		edit.setBackground(Color.WHITE);
		// Undo TextArea text changes
		JMenuItem Undo = new JMenuItem("Undo");
		Undo.setBackground(Color.WHITE);
		Undo.addActionListener(undoActionListener());
		Undo.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Undo);

		// Cut TextArea text
		JMenuItem Cut = new JMenuItem("Cut");
		Cut.addActionListener(new DefaultEditorKit.CutAction());
		Cut.setBackground(Color.WHITE);
		Cut.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Cut);

		// Copy TextArea text
		JMenuItem Copy = new JMenuItem("Copy");
		Copy.addActionListener(new DefaultEditorKit.CopyAction());
		Copy.setBackground(Color.WHITE);
		Copy.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Copy);

		// Paste TextArea text
		JMenuItem Paste = new JMenuItem("Paste");
		Paste.setBackground(Color.WHITE);
		Paste.addActionListener(new DefaultEditorKit.PasteAction());
		Paste.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Paste);

		// Delete TextArea text
		JMenuItem Delete = new JMenuItem("Delete");
		Delete.setBackground(Color.WHITE);
		Delete.addActionListener(deleteActionListener());
		Delete.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Delete);
		
		// Find word in a document
		JMenuItem Find = new JMenuItem("Find...");
		Find.setBackground(Color.WHITE);
		Find.addActionListener(findActionListener());
		Find.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Find);

		// Replace a word in a document
		JMenuItem Replace = new JMenuItem("Replace");
		Replace.setBackground(Color.WHITE);
		Replace.addActionListener(replaceActionListener());
		Replace.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Replace);
		// Select all the text in a document
		JMenuItem SelectAll = new JMenuItem("Select All");
		SelectAll.setBackground(Color.WHITE);
		SelectAll.addActionListener(selectAllActionListener());
		SelectAll.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(SelectAll);

		// Insert the current date and time
		JMenuItem Time_Date = new JMenuItem("Time/Date");
		Time_Date.setBackground(Color.WHITE);
		Time_Date.addActionListener(timeDateActionListener());
		Time_Date.setFont(new Font("Segoe", Font.PLAIN, 12));
		edit.add(Time_Date);

		return this.edit;
	}

	private ActionListener undoActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Window.undoManager.undo();
			}
		};
	}

	public ActionListener deleteActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Replacement of the selected text with empty space
				TextArea.getTextArea().replaceSelection("");
			}
		};
	}

	public ActionListener selectAllActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Select all the text in the document
				TextArea.getTextArea().selectAll();
			}
		};
	}

	public ActionListener timeDateActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// The insertion is done by using the SimpleDateFormat
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				TextArea.getTextArea().insert(formatter.format(date), Window.textArea.getCaretPosition());
			}
		};
	}

	public ActionListener findActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// New instance of the word finder window
				new FindWordWindow();
			}
		};
	}

	public ActionListener replaceActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// New instance of the word replacement window
				new ReplaceWordWindow();
			}
		};
	}

}
