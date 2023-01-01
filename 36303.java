import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lpsolve.*;

public class Demo {

    public Demo() {
    }

    public int execute() throws LpSolveException {
        LpSolve lp;
        int Ncol, j, ret = 0;
        Ncol = 2;
        int[] colno = new int[Ncol];
        double[] row = new double[Ncol];
        lp = LpSolve.makeLp(0, Ncol);
        if (lp.getLp() == 0) ret = 1;
        if (ret == 0) {
            lp.setColName(1, "x");
            lp.setColName(2, "y");
            lp.setAddRowmode(true);
            j = 0;
            colno[j] = 1;
            row[j++] = 120;
            colno[j] = 2;
            row[j++] = 210;
            lp.addConstraintex(j, row, colno, LpSolve.LE, 15000);
        }
        if (ret == 0) {
            j = 0;
            colno[j] = 1;
            row[j++] = 110;
            colno[j] = 2;
            row[j++] = 30;
            lp.addConstraintex(j, row, colno, LpSolve.LE, 4000);
        }
        if (ret == 0) {
            j = 0;
            colno[j] = 1;
            row[j++] = 1;
            colno[j] = 2;
            row[j++] = 1;
            lp.addConstraintex(j, row, colno, LpSolve.LE, 75);
        }
        if (ret == 0) {
            lp.setAddRowmode(false);
            j = 0;
            colno[j] = 1;
            row[j++] = 143;
            colno[j] = 2;
            row[j++] = 60;
            lp.setObjFnex(j, row, colno);
        }
        if (ret == 0) {
            lp.setMaxim();
            lp.writeLp("model.lp");
            lp.setVerbose(LpSolve.IMPORTANT);
            ret = lp.solve();
            if (ret == LpSolve.OPTIMAL) ret = 0; else ret = 5;
        }
        if (ret == 0) {
            System.out.println("Objective value: " + lp.getObjective());
            lp.getVariables(row);
            for (j = 0; j < Ncol; j++) System.out.println(lp.getColName(j + 1) + ": " + row[j]);
        }
        if (lp.getLp() != 0) lp.deleteLp();
        return (ret);
    }

    public static void main(String[] args) {
        try {
            new Demo().execute();
        } catch (LpSolveException e) {
            e.printStackTrace();
        }
    }
}
