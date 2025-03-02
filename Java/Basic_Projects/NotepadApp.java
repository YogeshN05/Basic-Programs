//Create a simple Notepad application using Java Swing. The application should have the following features:
//1. A text area to write text. 
//2. A menu bar with the following options:
//   a. File: Open, Save, Save As, Close, Quit
//   b. Edit: Undo, Redo, Cut, Copy, Paste, Find, Replace
//3. Implement the functionality of the menu items using appropriate event listeners.
//4. Implement the Find and Replace functionality using JOptionPane.


package Basic_Projects; 
import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotepadApp extends JFrame { 
    private JTextArea textArea; 
    private JFileChooser fileChooser; 
    private File currentFile; 
    private UndoManager undoManager;

    public NotepadApp() {
        setTitle("Notepad"); // Set the title of the window
        setSize(800, 600);  // Set the size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Set the default close operation
        setLocationRelativeTo(null); // Center the window on the screen

        textArea = new JTextArea();    // Create a new text area
        undoManager = new UndoManager(); // Create a new undo manager
        textArea.getDocument().addUndoableEditListener(undoManager); // Add the undo manager to the text area
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Add the text area to the center of the window

        fileChooser = new JFileChooser(); // Create a new file chooser

        JMenuBar menuBar = new JMenuBar(); // Create a new menu bar
        setJMenuBar(menuBar);  // Set the menu bar for the window

        JMenu fileMenu = new JMenu("File"); // Create a new file menu
        JMenu editMenu = new JMenu("Edit"); // Create a new edit menu
        menuBar.add(fileMenu);  // Add the file menu to the menu bar
        menuBar.add(editMenu);  // Add the edit menu to the menu bar

        // File Menu Items
        JMenuItem openItem = new JMenuItem("Open");  // Create a new menu item
        JMenuItem saveItem = new JMenuItem("Save"); // Create a new menu item
        JMenuItem saveAsItem = new JMenuItem("Save As");    // Create a new menu item
        JMenuItem closeItem = new JMenuItem("Close");   // Create a new menu item
        JMenuItem quitItem = new JMenuItem("Quit"); 

        fileMenu.add(openItem); // Add the menu item to the file menu
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();    // Add a separator between menu items
        fileMenu.add(quitItem);

        // Edit Menu Items
        JMenuItem undoItem = new JMenuItem("Undo");     
        JMenuItem redoItem = new JMenuItem("Redo");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem findItem = new JMenuItem("Find");
        JMenuItem replaceItem = new JMenuItem("Replace");

        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(findItem);
        editMenu.add(replaceItem);

        // File Menu Actions
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        saveAsItem.addActionListener(e -> saveFileAs());
        closeItem.addActionListener(e -> textArea.setText(""));
        quitItem.addActionListener(e -> System.exit(0));

        // Edit Menu Actions
        undoItem.addActionListener(e -> {   // Add an action listener to the menu item
            if (undoManager.canUndo()) undoManager.undo();  // Check if undo is possible and undo the last edit
        });
        redoItem.addActionListener(e -> {
            if (undoManager.canRedo()) undoManager.redo();  // Check if redo is possible and redo the last edit
        });
        cutItem.addActionListener(e -> textArea.cut());     // Add an action listener to the menu item
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());
        findItem.addActionListener(e -> findText());    
        replaceItem.addActionListener(e -> replaceText());
    }

    private void openFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {  // Show the open file dialog
            currentFile = fileChooser.getSelectedFile();    // Get the selected file
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) { // Create a buffered reader
                textArea.read(reader, null);    // Read the file into the text area
            } catch (IOException e) {   
                e.printStackTrace();    // Print the stack trace of the exception
            }
        }
    }

    private void saveFile() {
        if (currentFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                textArea.write(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveFileAs();
        }
    }

    private void saveFileAs() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            saveFile();
        }
    }

    private void findText() {
        String find = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (find != null) {
            String text = textArea.getText();
            int index = text.indexOf(find);
            if (index >= 0) {
                textArea.setSelectionStart(index);
                textArea.setSelectionEnd(index + find.length());
            } else {
                JOptionPane.showMessageDialog(this, "Text not found");
            }
        }
    }

    private void replaceText() {
        JPanel panel = new JPanel(new java.awt.GridLayout(2, 2));
        JTextField findField = new JTextField();
        JTextField replaceField = new JTextField();
        panel.add(new JLabel("Find:"));
        panel.add(findField);
        panel.add(new JLabel("Replace with:"));
        panel.add(replaceField);

        int option = JOptionPane.showConfirmDialog(this, panel, "Find & Replace", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String find = findField.getText();
            String replace = replaceField.getText();
            textArea.setText(textArea.getText().replace(find, replace));    // Replace all occurrences of the text
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotepadApp().setVisible(true)); // Create a new instance of the NotepadApp class and make it visible
    }
}   
