package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import Main.Window;

public abstract class Document {

	public static void setPath(String path) {
		Window.documentPath = path;
	}

	public static String getPath() {
		return Window.documentPath;
	}

	public static void SaveAs() {
		// Destination path
		String path = null;
		// Create an object of JFileChooser class
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		// Invoke the showsSaveDialog function to show the save dialog
		int openDialog = fileChooser.showSaveDialog(null);
		// If the user gives a path
		if (openDialog == JFileChooser.APPROVE_OPTION) {
			// Set the label to the path of the selected file
			path = fileChooser.getSelectedFile().getAbsolutePath();
		}

		Save(path);
		setPath(path);
	}

	public static void Save(String path) {
		if (path != null) {
			// Set the label to the path of the selected directory
			File file = new File(path);

			try {
				// Create a file writer
				FileWriter fileWriter = new FileWriter(file, false);

				// Create buffered writer to write
				BufferedWriter writer = new BufferedWriter(fileWriter);

				// Write
				writer.write(Window.textArea.getText());

				writer.flush();
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public static void Open() {
		String path = null;
		// Create an object of JFileChooser class
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		// Invoke the showsOpenDialog function to show the open dialog
		int openDialog = fileChooser.showOpenDialog(null);
		// If the user selects a file
		if (openDialog == JFileChooser.APPROVE_OPTION) {
			// Set the label to the path of the selected file
			path = fileChooser.getSelectedFile().getAbsolutePath();
		}

		// If the user selects a file
		if (openDialog == JFileChooser.APPROVE_OPTION) {
			// Set the label to the path of the selected directory
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

			try {
				// Source and destination string variables
				String source = "", destination = "";

				// File reader
				FileReader fileReader = new FileReader(file);
				// Buffered reader
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				// Initilize of the destination document content
				destination = bufferedReader.readLine();
				// Take the input from the source file
				while ((source = bufferedReader.readLine()) != null) {
					destination = destination + "\n" + source;
				}
				// Set the text
				Window.textArea.setText(destination);

				bufferedReader.close();
				fileReader.close();
			} catch (Exception e) {
			}
		}

		setPath(path);
	}

	public static void clear() {
		Window.textArea.setText(null);
	}

	public static Boolean isEmpty() {
		String check = Window.textArea.getText();
		if (check.equals("")) {
			return true;
		}
		return false;
	}

}
