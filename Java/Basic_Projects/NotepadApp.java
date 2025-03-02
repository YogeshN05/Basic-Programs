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
        setTitle("Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        fileChooser = new JFileChooser();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // File Menu Items
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        JMenuItem closeItem = new JMenuItem("Close");
        JMenuItem quitItem = new JMenuItem("Quit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(closeItem);
        fileMenu.addSeparator();
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
        undoItem.addActionListener(e -> {
            if (undoManager.canUndo()) undoManager.undo();
        });
        redoItem.addActionListener(e -> {
            if (undoManager.canRedo()) undoManager.redo();
        });
        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());
        findItem.addActionListener(e -> findText());
        replaceItem.addActionListener(e -> replaceText());
    }

    private void openFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                e.printStackTrace();
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
            textArea.setText(textArea.getText().replace(find, replace));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotepadApp().setVisible(true));
    }
}
