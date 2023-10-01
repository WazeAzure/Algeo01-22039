import java.util.Scanner;

import Library.Matrix;
import Library.MetodeEliminasi;
import Library.OperasiDasarMatrix;

/* *** FILE UNTUK MENGUJI TIAP FUNGSI *** */

public class driver {
    public static void main(String[] args){
        System.out.println("Hello World");

		Scanner sc = new Scanner(System.in);
        OperasiDasarMatrix ODM = new OperasiDasarMatrix();
        MetodeEliminasi ME = new MetodeEliminasi();

        Matrix m = new Matrix();

        ODM.createMatrix(m, 3, 4);
        ODM.readMatrix(m, 3, 4);
        ODM.displayMatrix(m);

        ME.toEselon(m);
        ODM.displayMatrix(m);

        ME.toEselonRed(m);
        ODM.displayMatrix(m);

    }
}
