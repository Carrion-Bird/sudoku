package org.aasvogel.sudokusolver.view.resultFormater;

import org.aasvogel.sudokusolver.logic.validity.ValidityResult;
import org.aasvogel.sudokusolver.model.Region;
import org.aasvogel.sudokusolver.view.Highlights;

import java.util.StringJoiner;

public class CorrectnessResultFormater {

    public static String format(ValidityResult result) {
        if (ValidityResult.ResultType.VALID.equals(result.getType())) {
            return "Valid";
        } else {
            StringJoiner sb = new StringJoiner("\n");
            sb.add("Invalid!");

            sb.add("Symbol: " + result.getSymbolInvolved().getRepresentation());

            Region regionInvolved = result.getRegionInvolved();
            sb.add(regionInvolved.getRegionTypeName() + ": " + regionInvolved.getRegionPosition() );
            sb.add("involved cells:");
            result.getCellsInvolved().forEach(coordinates -> sb.add(coordinates.format()));
            return sb.toString();
        }
    }

    private static Highlights getHighlights( ValidityResult result){
        return Highlights.multipleCells( result.getCellsInvolved());
    }

    private CorrectnessResultFormater() {
    }
}
