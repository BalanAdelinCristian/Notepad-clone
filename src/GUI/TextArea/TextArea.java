package GUI.TextArea;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class TextArea extends JTextArea{
	
	private static JTextArea textArea;
	private static final int defaultZoom = 12;
	private static final String defaultFontFamily = "Times New Roman";
	private static final int defaultFontStyle = Font.PLAIN;
	
	public TextArea() {
		textArea = new JTextArea();
	}
	
	public JTextArea setProperties() {
		textArea.setBackground(Color.WHITE);
		textArea.setFont(new Font(defaultFontFamily, defaultFontStyle, defaultZoom));
		return textArea;
	}
	
	public static JTextArea getTextArea() {
		return textArea;
	}
	
	public static int getDefaultZoom() {
		return defaultZoom;
	};
	
	public static String getDefaultFontFamily() {
		return defaultFontFamily;
	};
	
	public static int getDefaultFontStyle() {
		return defaultFontStyle;
	};
}
