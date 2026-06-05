package org.aasvogel.sudokusolver;

import org.aasvogel.sudokusolver.model.Cell;
import org.aasvogel.sudokusolver.model.Digits;
import org.aasvogel.sudokusolver.model.Page;
import org.aasvogel.sudokusolver.model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Solver {

    private JTextArea hintsPane;
    private final Set<Symbol> symbols;
    private Page page;

    private List<List<JTextField>> cells;

    public static void main(String[] args) {

        Solver instance = new Solver(Digits.getSet());
        instance.startFrame();
    }

    private Solver(Set<Symbol> symbols) {
        this.symbols = symbols;
        page = new Page(3);
    }

    private void startFrame() {
        JFrame frame = new JFrame("Sudoku solver"); // TODO I18N
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(1, 2));

        fillMainPanel(mainPanel);

        frame.setVisible(true);
    }

    private void fillMainPanel(JPanel panel) {

        Component page = constructPage();
        panel.add(page);

        Component hints = constructHints();
        panel.add(hints);
    }

    private Component constructPage() {

        int size = page.getSize();
        int gapsize = 8;

        LayoutManager gridLayout = new GridLayout(size, size, gapsize, gapsize);
        JPanel page = new JPanel(gridLayout);
        cells = new ArrayList<>(size);

        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            List<JTextField> row = new ArrayList(size);

            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                JTextField cell = new JTextField();

                row.add(cell);
                page.add(cell);
            }
            cells.add(row);
        }

        return page;
    }

    private JTextField getCellAt(int rowIndex, int columnIndex) {
        return cells.get(rowIndex).get(columnIndex);
    }

    private Component constructHints() {
        JPanel rightPanel = new JPanel(new BorderLayout());

        hintsPane = new JTextArea(getExampleText());
        rightPanel.add(hintsPane);
        rightPanel.add(getButtonsPanel(), BorderLayout.SOUTH);
        return rightPanel;
    }

    private Component getButtonsPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));


        JButton checkButton = new JButton("Check");
//        checkButton.addActionListener(); FIXME
        buttonPanel.add(checkButton);
        JButton hintsButton = new JButton("Get hint");
//        hintsButton.addActionListener();
        buttonPanel.add(hintsButton);

        return buttonPanel;
    }

    private void readCellAt(int rowIndex, int columnIndex) {
        JTextField viewCell = getCellAt(rowIndex, columnIndex);
        String text = viewCell.getText();
        Symbol symbol = Symbol.fromText(symbols, text);

        Cell modelCell = page.getCellAt(rowIndex, columnIndex);

    }


    private String getExampleText() {
        return """
                TODO
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                when an unknown printer took a galley of type and scrambled it to make a type 
                specimen book. It has survived not only five centuries, but also the leap into 
                electronic typesetting, remaining essentially unchanged. It was popularised 
                in the 1960s with the release of Letraset sheets containing Lorem Ipsum 
                passages, and more recently with desktop publishing software like Aldus 
                PageMaker including versions of Lorem Ipsum.
                """;
    }

}