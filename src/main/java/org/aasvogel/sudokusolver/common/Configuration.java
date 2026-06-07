package org.aasvogel.sudokusolver.common;

import org.aasvogel.sudokusolver.model.Digits;
import org.aasvogel.sudokusolver.model.Symbol;

import java.awt.*;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

    public static int sizeBase = 3;

    public static List<Symbol> symbols = Digits.getList();
    public static Color highlightBlock = new Color(245, 245, 235);
    public static Color highlightStraight = new Color(245, 235, 245);
    public static Color highlightTwo = new Color(235, 215, 235);
    public static Color highlightAll = new Color(225, 205, 215);

    static {
        setDebugLogging();
    }

    public static int getSymbolsQuantity() {
        return sizeBase * sizeBase;
    }

    public static void setDebugLogging(){
        Level debugLevel = Level.INFO;

        Logger rootLogger = Logger.getLogger("");

        rootLogger.setLevel( debugLevel);
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel( debugLevel);
        }
    }

    private Configuration() {
    }
}
