package org.aasvogel.sudokusolver.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellListener extends MouseAdapter {

    private final Board board;
    private final Cell cell;

    public CellListener(Board board, Cell cell) {
        this.board = board;
        this.cell = cell;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        board.setFocusedCell( cell);
        board.requestFocusInWindow();
    }
}
