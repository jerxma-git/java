package expression.generic;

import java.math.BigInteger;
import java.util.Map;

import expression.exceptions.*;

public class GenericTabulator implements Tabulator {
    private Object[][][] res;
    private static final Map<String, Evaluative<?>> TYPES = Map.of(
        "i", new IntCover(0),
        "d", new DoubleCover(0),
        "bi", new BigIntCover(new BigInteger("0"))
    );
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        Evaluative<?> type = TYPES.get(mode);
       
        tab(type, expression, x1, x2, y1, y2, z1, z2);
        return res;
    }

    private <T> void tab(Evaluative<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {






        
        Parser<T> parser = new expression.generic.ExpressionParser<>(type);
        CommonExpression<T> exp = parser.parse(expression);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        res[i][j][k] = exp.evaluate(type.parse((x1 + i) + ""), type.parse((y1 + j) + ""), type.parse((z1 + k) + ""));
                    } catch(OverflowException e) {
                        res[i][j][k] = null;
                    }
                }
            }   
        }
    }




}

