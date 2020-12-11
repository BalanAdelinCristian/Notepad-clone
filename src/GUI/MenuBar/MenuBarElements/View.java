package GUI.MenuBar.MenuBarElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GUI.TextArea.TextArea;

@SuppressWarnings("serial")
public class View extends JMenu {

	JMenu view;

	public View() {
		view = new JMenu("View");
	}

	public JMenu addElements() {
		view.setBackground(Color.WHITE);
		view.setFont(new Font("Segoe", Font.PLAIN, 12));
		
		JMenu Zoom = new JMenu("Zoom");
		Zoom.setFont(new Font("Segoe", Font.PLAIN, 12));
		Zoom.getComponent().setBackground(Color.WHITE);
		view.add(Zoom);

		JMenuItem ZoomIn = new JMenuItem("Zoom In");
		ZoomIn.setFont(new Font("Segoe", Font.PLAIN, 12));
		ZoomIn.addActionListener(zoomInActionListener());
		ZoomIn.setBackground(Color.WHITE);
		Zoom.add(ZoomIn);

		JMenuItem ZoomOut = new JMenuItem("Zoom Out");
		ZoomOut.addActionListener(zoomOutActionListener());
		ZoomOut.setFont(new Font("Segoe", Font.PLAIN, 12));
		ZoomOut.setBackground(Color.WHITE);
		Zoom.add(ZoomOut);
		
		JMenuItem RestoreDefaultZoom = new JMenuItem("Restore Default Zoom");
		RestoreDefaultZoom.addActionListener(restoreDefaultZoomActionListener());
		RestoreDefaultZoom.setFont(new Font("Segoe", Font.PLAIN, 12));
		RestoreDefaultZoom.setBackground(Color.WHITE);
		Zoom.add(RestoreDefaultZoom);

		return this.view;
	}

	public ActionListener zoomInActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextArea.getTextArea().setFont(new Font(TextArea.getTextArea().getFont().getFontName(),
						TextArea.getTextArea().getFont().getStyle(), TextArea.getTextArea().getFont().getSize() + 2));
			}
		};
	}

	public ActionListener zoomOutActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextArea.getTextArea().setFont(new Font(TextArea.getTextArea().getFont().getFontName(),
						TextArea.getTextArea().getFont().getStyle(), TextArea.getTextArea().getFont().getSize() - 2));
			}
		};
	}

	public ActionListener restoreDefaultZoomActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TextArea.getTextArea().setFont(new Font(TextArea.getDefaultFontFamily(), TextArea.getDefaultFontStyle(),
						TextArea.getDefaultZoom()));
			}
		};
	}

}
