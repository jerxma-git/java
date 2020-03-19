package expression.generic;

import java.math.BigInteger;
import java.util.Map;

import expression.exceptions.*;

public class GenericTabulator implements Tabulator {
    private Object[][][] res;
    private static final Map<String, Evaluative<? extends Evaluative<?>>> TYPES = Map.of(
        "i", new MyInteger(0),
        "d", new MyDouble(0),
        "bi", new MyBigInteger(new BigInteger("0"))
    );
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        Evaluative<?> fabric = TYPES.get(mode);

    }
    private <T> void tab(Evaluative<?> fabric, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Parser<T> parser = new ExpressionParser<T>()
    }


    // private <T> void tab(Evaluative<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {




    //     Parser<T> parser = new expression.generic.ExpressionParser<T>(type);
    //     CommonExpression<Evaluative<T>> exp = parser.parse(expression);
    //     for (int i = 0; i <= x2 - x1; i++) {
    //         for (int j = 0; j <= y2 - y1; j++) {
    //             for (int k = 0; k <= z2 - z1; k++) {
    //                 try {
    //                     res[i][j][k] = exp.evaluate(type.parse((x1 + i) + ""), type.parse((y1 + j) + ""), type.parse((z1 + k) + ""));
    //                 } catch(OverflowException e) {
    //                     res[i][j][k] = null;
    //                 }
    //             }
    //         }   
    //     }
    // }






}

