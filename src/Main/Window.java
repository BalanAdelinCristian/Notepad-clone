package Main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import GUI.MenuBar.MenuBar;
import GUI.TextArea.TextArea;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Window{

	public JFrame frame;
	public static JTextArea textArea;
	public static String documentPath = null;
	public static JScrollPane scrollPane;
	public static UndoManager undoManager;
	public static volatile boolean runnable = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */		  
	private void initialize() {
		// JFrame instance and settings
		frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.setFont(new Font("Segoe", Font.PLAIN, 10));
		frame.getContentPane().setBackground(new Color(200, 200, 200));
		frame.setTitle("Notepad");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setMinimumSize(new Dimension(screenSize.width * 2 / 3, screenSize.height * 2 / 3));
		frame.setLocationRelativeTo(null);
		
		// MenuBar addition
		frame.setJMenuBar(new MenuBar().addElements());
		
		// TextArea instance and properties set
		textArea = new TextArea().setProperties();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		// ScrollPane instance and set
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBackground(new Color(200, 200, 200));
		scrollPane.setBackground(Color.WHITE);
		frame.getContentPane().add(scrollPane);
		
		// UndoManager instance
		undoManager = new UndoManager();
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				undoManager.addEdit(e.getEdit());	
			}
		});
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
}
