package org.aasvogel.sudokusolver.view;

import org.aasvogel.sudokusolver.logic.CheckResult;
import org.aasvogel.sudokusolver.model.Region;

import java.util.StringJoiner;

public class CheckResultFormater {

    public static String format(CheckResult result) {
        if (CheckResult.ResultType.VALID.equals(result.getType())) {
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

    private CheckResultFormater() {
    }
}
