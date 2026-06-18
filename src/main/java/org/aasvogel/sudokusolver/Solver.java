package org.aasvogel.sudokusolver;

import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.logic.hints.Hint;
import org.aasvogel.sudokusolver.logic.hints.RuleCoordinator;
import org.aasvogel.sudokusolver.logic.solvability.SolvabilityChecker;
import org.aasvogel.sudokusolver.logic.solvability.SolvabilityResult;
import org.aasvogel.sudokusolver.logic.solver.BruteForceSolver;
import org.aasvogel.sudokusolver.logic.validity.ValidityChecker;
import org.aasvogel.sudokusolver.logic.validity.ValidityResult;
import org.aasvogel.sudokusolver.model.Page;
import org.aasvogel.sudokusolver.view.Board;
import org.aasvogel.sudokusolver.view.Cell;
import org.aasvogel.sudokusolver.view.CellListener;
import org.aasvogel.sudokusolver.view.Highlights;
import org.aasvogel.sudokusolver.view.resultFormater.CorrectnessResultFormater;
import org.aasvogel.sudokusolver.view.resultFormater.HintFormatter;
import org.aasvogel.sudokusolver.view.resultFormater.SolvabilityResultFormater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class Solver {

    public static final Dimension PREFERED_BUTTONSIZE = new Dimension(60, 20);
    private JTextArea hintsPane;
    private Page model;
    private Board board;

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

        hintsPane = new JTextArea("waiting for input");
        rightPanel.add(hintsPane);
        rightPanel.add(getButtonsPanel(), BorderLayout.SOUTH);
        return rightPanel;
    }

    private Component getButtonsPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        addButton("Check if valid", this::checkIfValid, buttonPanel);
        addButton("Check if solvable", this::checkIfSolvable, buttonPanel);
        addButton("Solve", this::solve, buttonPanel);
        addButton("Get hint", this::getHint, buttonPanel);

        return buttonPanel;
    }

    private void getHint(ActionEvent actionEvent) {
        RuleCoordinator rules = new RuleCoordinator();
        Optional<Hint> hintOpt = rules.check(model);

        String text = HintFormatter.format(hintOpt);
        hintsPane.setText( text);
        if (hintOpt.isPresent()) {
            Hint hint = hintOpt.get();
            Cell cell = board.getCellAt(hint.getTargetCell());
            board.highlight(Highlights.fromHint(hint));
            board.setFocusedCell( cell);
        }
    }

    private void checkIfSolvable(ActionEvent actionEvent) {
        SolvabilityChecker checker = new SolvabilityChecker(new BruteForceSolver());

        SolvabilityResult result = checker.isSolvable(model);
        String formatedResult = SolvabilityResultFormater.format(result);
        hintsPane.setText( formatedResult);
    }

    private void addButton(String text, ActionListener action, JPanel buttonPanel) {
        JButton solveButton = new JButton(text);
        solveButton.setPreferredSize(PREFERED_BUTTONSIZE);
        solveButton.addActionListener( action);
        buttonPanel.add(solveButton);
    }

    private void solve(ActionEvent actionEvent) {
//        BruteForce bfSolver = new BruteForce();
//
//        Set<Page> solutions = bfSolver.getSollutions(model);
//        hintsPane.setText( "Number of possible solutions: " + solutions.size() + ".");
//        if (!solutions.isEmpty())


    }

    private void checkIfValid(ActionEvent actionEvent) {
        ValidityChecker checker = new ValidityChecker();
        ValidityResult result = checker.check( model);

        hintsPane.setText( CorrectnessResultFormater.format( result));
        board.highlight(Highlights.multipleCells(result.getCellsInvolved()));
    }

    private Cell createCell(int rowIndex, int columnIndex) {
        CellCoordinates coordinates = new CellCoordinates(rowIndex, columnIndex);
        Cell cell = new Cell(coordinates, model.getCellAt( coordinates));
        CellListener cellListener = new CellListener(board, cell);
        cell.addMouseListener( cellListener );
        return cell;
    }
}