package org.aasvogel.sudokusolver.view;

import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.Set;

public class Cell extends JComponent {

    private final CellCoordinates coordinates;

    private boolean rowHighlighted = false;
    private boolean columnHighlighted = false;
    private boolean blockHighlighted = false;

    private final org.aasvogel.sudokusolver.model.Cell modelCell;

    public Cell(CellCoordinates coordinates, org.aasvogel.sudokusolver.model.Cell modelCell) {
        setPreferredSize(new Dimension(60, 60));

        this.modelCell = modelCell;
        this.coordinates = coordinates;
    }

    public void setHighlighted(CellCoordinates highlightCoordinates) {
        rowHighlighted = coordinates.getRow() == highlightCoordinates.getRow();
        columnHighlighted = coordinates.getCol() == highlightCoordinates.getCol();
        blockHighlighted = (coordinates.getRow() / 3) == (highlightCoordinates.getRow() / 3) &&
                (coordinates.getCol() / 3) == (highlightCoordinates.getCol() / 3);
        repaint();
    }

    public void setValue(Symbol value) {
        modelCell.setValue(value);

        repaint();
    }

    public void setpenciled(Set<Symbol> values) {
        modelCell.setPenciled(values);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        try {
            paintBackground(g2);

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            // Border
            g2.setColor(Color.BLACK);
            g2.drawRect(0, 0, width - 1, height - 1);

            Optional<Symbol> value = modelCell.getValue();
            value.ifPresentOrElse( //
                    v -> paintFixedValue(g2, width, height, v),
                    () -> paintPenciled(g2, width, height, modelCell.getPenciled()));
        } finally {
            g2.dispose();
        }
    }

    private void paintBackground(Graphics2D g2) {
        Color bg; // FIXME: highlight row or column or block or several coordinates
        if (rowHighlighted && columnHighlighted && blockHighlighted)
            bg = Configuration.highlightAll;
        else if (rowHighlighted && columnHighlighted)
            bg = Configuration.highlightTwo;
        else if (rowHighlighted || columnHighlighted)
            bg = Configuration.highlightStraight;
        else if (blockHighlighted)
            bg = Configuration.highlightBlock;
        else
            bg = Color.WHITE;

        g2.setColor(bg);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintFixedValue(Graphics2D g2, int w, int h, Symbol value) {
        Font font = getFont().deriveFont(Font.BOLD, h * 0.7f);
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        String text = value.getRepresentation();
        FontMetrics fm = g2.getFontMetrics();
        int x = (w - fm.stringWidth(text)) / 2;
        int y = (h - fm.getHeight()) / 2 + fm.getAscent();

        g2.drawString(text, x, y);
    }

    private void paintPenciled(Graphics2D g2, int w, int h, Set<Symbol> penciled) {

        Font font = getFont().deriveFont(h * 0.18f);
        g2.setFont(font);
        g2.setColor(Color.GRAY);

        FontMetrics fm = g2.getFontMetrics();

        int cellW = w / Configuration.sizeBase;
        int cellH = h / Configuration.sizeBase;

        int position = -1;

        for (Symbol symbol : Configuration.symbols) {
            position++;

            if (!penciled.contains(symbol)) {
                continue;
            }

            int col = position % Configuration.sizeBase;
            int row = position / Configuration.sizeBase;

            int centerX = col * cellW + cellW / 2;
            int centerY = row * cellH + cellH / 2;

            String text = symbol.getRepresentation();

            int x = centerX - fm.stringWidth(text) / 2;
            int y = centerY + fm.getAscent() / 2 - 2;

            g2.drawString(text, x, y);
        }
    }

    public CellCoordinates getCoordinates() {
        return coordinates;
    }
}
