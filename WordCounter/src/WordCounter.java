import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounter extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WordCounter();
        });
    }

    private JTextArea inputArea;
    private JLabel totalWordsLabel, totalCharsLabel;

    public WordCounter() {
        setupUI();
    }

    private void setupUI() {
        setTitle("Text Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 320);

        inputArea = new JTextArea(12, 45);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(inputArea);

        JButton calculateButton = new JButton("Calculate Counts");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateStatistics();
            }
        });

        totalWordsLabel = new JLabel("Total Words: 0");
        totalCharsLabel = new JLabel("Total Characters: 0");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        contentPanel.add(scrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        contentPanel.add(calculateButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.add(totalWordsLabel);
        resultPanel.add(totalCharsLabel);

        contentPanel.add(resultPanel);

        add(contentPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculateStatistics() {
        String textContent = inputArea.getText();

        int charCount = textContent.length();
        totalCharsLabel.setText("Total Characters: " + charCount);

        String trimmedText = textContent.trim();
        int wordCount = 0;
        if (!trimmedText.isEmpty()) {
            wordCount = trimmedText.split("\\s+").length;
        }
        totalWordsLabel.setText("Total Words: " + wordCount);
    }

}