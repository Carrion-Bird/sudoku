package org.aasvogel.sudokusolver;

import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.logic.CheckResult;
import org.aasvogel.sudokusolver.logic.ValidityChecker;
import org.aasvogel.sudokusolver.model.Digits;
import org.aasvogel.sudokusolver.model.Page;
import org.aasvogel.sudokusolver.model.Symbol;
import org.aasvogel.sudokusolver.view.Board;
import org.aasvogel.sudokusolver.view.Cell;
import org.aasvogel.sudokusolver.view.CellListener;
import org.aasvogel.sudokusolver.view.CheckResultFormater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class Solver {

    private JTextArea hintsPane;
    private Page model;
    private Board board;
    private ValidityChecker checker = new ValidityChecker();

    public static void main(String[] args) {

        Solver instance = new Solver();
        instance.startFrame();
    }

    private Solver() {
        model = new Page();
    }

    private void startFrame() {
        JFrame frame = new JFrame("Sudoku solver"); // TODO I18N
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);

        mainPanel.setLayout(new GridLayout(1, 2));

        fillMainPanel(mainPanel);

        Set<Symbol> set = new HashSet<>(Configuration.symbols);
        board.getCellAt(1, 2).setpenciled( set);
        board.getCellAt(2, 1).setValue( Digits.FIVE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        board.requestFocusInWindow();
    }

    private void fillMainPanel(JPanel panel) {

        constructPage();
        panel.add( board);

        Component hints = constructHints();
        panel.add(hints);
    }

    private void constructPage() {
        board = new Board();

        int size = Configuration.getSymbolsQuantity();

        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {

                Cell cell = createCell(rowIndex, columnIndex);
                board.add(cell);
            }
        }
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
        Dimension buttonSize = new Dimension(60, 20);

        JButton checkButton = new JButton("Check");
        checkButton.setPreferredSize(buttonSize);
        checkButton.addActionListener( this::checkIfValid);
        buttonPanel.add(checkButton);
        JButton hintsButton = new JButton("Get hint");
        hintsButton.setPreferredSize(buttonSize);
//        hintsButton.addActionListener();
        buttonPanel.add(hintsButton);

        return buttonPanel;
    }

    private void checkIfValid(ActionEvent actionEvent) {

        CheckResult result = checker.check( model);

        hintsPane.setText( CheckResultFormater.format( result));
        // FIXME! Better Highlighting!
    }

    private Cell createCell(int rowIndex, int columnIndex) {
        CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
        Cell cell = new Cell(coordinates, model.getCellAt( rowIndex, columnIndex));
        CellListener cellListener = new CellListener(board, cell);
        cell.addMouseListener( cellListener );
        return cell;
    }

//    private void readCellAt(int rowIndex, int columnIndex) {
//        SudokuCellComponent viewCell = getCellAt(rowIndex, columnIndex);
//        String text = viewCell.getText();
//        Symbol symbol = Symbol.fromText(text);
//
//        Cell modelCell = page.getCellAt(rowIndex, columnIndex);
//
//    }


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