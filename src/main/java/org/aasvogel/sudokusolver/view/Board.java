package org.aasvogel.sudokusolver.view;

import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;

import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_DELETE;

public class Board extends JPanel {

    private static final Logger LOG = Logger.getLogger(Board.class.getName());
    private Cell focusedCell;

    public Board() {
        super(new GridLayout(Configuration.getSymbolsQuantity(), Configuration.getSymbolsQuantity()));

        addKeyListener(new BoardKeyListener());
    }

    private void handleInput(Symbol symbol) {
        if (focusedCell == null) {
            LOG.fine("input ignored because no focused cell");
            return;
        }

        focusedCell.setValue(symbol);
    }

    public void highlight(Highlights highlights) {
        for (Component component : getComponents()) {
            if (component instanceof Cell cell) {
                cell.setHighlighted(highlights);
            }
        }
    }

    public Cell getCellAt(int rowIndex, int columnIndex) {

        for (Component component : getComponents()) {
            if (component instanceof Cell cell) {
                if (cell.getCoordinates().getRow() == rowIndex && cell.getCoordinates().getCol() == columnIndex)
                    return cell;
            }
        }

        throw new IllegalArgumentException("Cell not found: " + rowIndex + ", " + columnIndex + ".");
    }

    public void setFocusedCell(Cell focusedCell) {
        this.focusedCell = focusedCell;

        highlight(Highlights.singleCell(focusedCell.getCoordinates()));
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        Graphics2D g2 = (Graphics2D) g.create();

        try {
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(9f));

            int cellWidth = getWidth() / Configuration.getSymbolsQuantity();
            int cellHeight = getHeight() / Configuration.getSymbolsQuantity();

            // Vertical block lines
            g2.drawLine(cellWidth * Configuration.sizeBase, 0,
                    cellWidth * Configuration.sizeBase, getHeight());

            g2.drawLine(cellWidth * 6, 0,
                    cellWidth * 6, getHeight());

            // Horizontal block lines
            g2.drawLine(0, cellHeight * Configuration.sizeBase,
                    getWidth(), cellHeight * Configuration.sizeBase);

            g2.drawLine(0, cellHeight * 6,
                    getWidth(), cellHeight * 6);

        } finally {
            g2.dispose();
        }
    }

    private void clearValue() {
        focusedCell.setValue(null);
    }

    private class BoardKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO: multi-char-Symbols?
            char keyChar = e.getKeyChar();
            LOG.fine("Input: " + keyChar);

            var symbol = Symbol.fromText(Character.toString(keyChar));
            symbol.ifPresent(Board.this::handleInput);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == VK_BACK_SPACE || keyCode == VK_DELETE)
                clearValue();

            // TODO implement nevigation via arrow-keys
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_LEFT:
//                    moveLeft();
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    moveRight();
//                    break;
//                case KeyEvent.VK_UP:
//                    moveUp();
//                    break;
//                case KeyEvent.VK_DOWN:
//                    moveDown();
//                    break;
//            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // do nothing
        }
    }
}
