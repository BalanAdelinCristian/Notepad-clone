package GUI.MenuBar.MenuBarElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Main.Window;
import Utils.Document;

@SuppressWarnings("serial")
public class File extends JMenu {

	private JMenu file;

	public File() {
		file = new JMenu("File");
		file.setFont(new Font("Segoe", Font.PLAIN, 12));
	}

	/*
	 * Elements of the File tab of the menu bar are added here.
	 */
	public JMenu addElements() {
		file.setBackground(Color.WHITE);
		file.setHorizontalAlignment(SwingConstants.LEFT);

		// New project
		JMenuItem New = new JMenuItem("New");
		New.addActionListener(newProjectActionListener());
		New.setFont(new Font("Segoe", Font.PLAIN, 12));
		New.setBackground(Color.WHITE);
		file.add(New);

		// New window
		JMenuItem New_Window = new JMenuItem("New Window");
		New_Window.addActionListener(newWindowActionListener());
		New_Window.setFont(new Font("Segoe", Font.PLAIN, 12));
		New_Window.setBackground(Color.WHITE);
		file.add(New_Window);

		// Open existing file
		JMenuItem Open = new JMenuItem("Open...");
		Open.addActionListener(openProjectActionListener());
		Open.setFont(new Font("Segoe", Font.PLAIN, 12));
		Open.setBackground(Color.WHITE);
		file.add(Open);

		/*
		 * Class used to save a file which is already created or to save it with a
		 * prefferd name and extension using a file explorer window.
		 */
		JMenuItem Save = new JMenuItem("Save");
		Save.addActionListener(saveProjectActionListener());
		Save.setFont(new Font("Segoe", Font.PLAIN, 12));
		Save.setBackground(Color.WHITE);
		file.add(Save);

		/*
		 * Using this class the user has the opportunity to choose the file extension
		 * and the abosulte path of the document. This class uses the JFileChooser class
		 * which allows us to navigate in a file explorer.
		 */
		JMenuItem SaveAs = new JMenuItem("Save As...");
		SaveAs.addActionListener(saveProjectAsActionListener());
		SaveAs.setFont(new Font("Segoe", Font.PLAIN, 12));
		SaveAs.setBackground(Color.WHITE);
		file.add(SaveAs);

		/*
		 * Exit button class The button can be found in the menu bar at File->Exit
		 */
		JMenuItem Exit = new JMenuItem("Exit");
		Exit.addActionListener(exitActionListener());
		Exit.setFont(new Font("Segoe", Font.PLAIN, 12));
		Exit.setBackground(Color.WHITE);
		file.add(Exit);

		return file;
	}

	public static ActionListener newProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// If the document isn't empty we invoke a confirmation dialog then we let the
				// user to chose a destination where the current document will be saved,
				// otherwise we erase the
				// hole window
				if (Document.isEmpty() == false) {
					int action = JOptionPane.showConfirmDialog(null, "Do you want to save the current document?");
					if (action == JOptionPane.YES_OPTION) {
						Document.SaveAs();
					} else if (action == JOptionPane.NO_OPTION) {
						Document.clear();
					}
				}
			}
		};
	}
	
	public ActionListener newWindowActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// New window creation
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception event) {
					event.printStackTrace();
				}
			}
		};
	}
	
	public ActionListener openProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Open a file
				Document.Open();
			}
		};
	}
	
	public ActionListener saveProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Save a file
				Document.Save(Document.getPath());
			}
		};
	}
	
	public ActionListener saveProjectAsActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Save document as
				Document.SaveAs();
			}
		};
	}
	
	public ActionListener exitActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 *  Exit action listener
				 *  Checks if the document is modified
				 */
				if (Document.isEmpty() == false) {
					int action = JOptionPane.showConfirmDialog(null, "Do you want to save the current document?");
					if (action == JOptionPane.YES_OPTION) {
						Document.SaveAs();
					}
				}
				System.exit(0);
			}
		};
	}

}
