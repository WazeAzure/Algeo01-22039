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

        ODM.createMatrix(m, 4, 7);
        ODM.readMatrix(m, 4, 7);

        // int arr_temp[][] = {
        //         {3, 1, 1, 1, 1, 1},
        //         {4, 2, 1, 1, 1, 1},
        //         {2, 1, 4, 1, 1, 1},
        //         {0, 3, 5, 0, 1, 1},
        //         {0, 0, 0, 0, 0, 0}
        //     };

        // for(int i=0; i<m.get_ROW_EFF(); i++){
        //     for(int j=0;j<m.get_COL_EFF(); j++){
        //         m.mem[i][j] = arr_temp[i][j];
        //     }
        // }
        
        System.out.println("-------------------");
        ME.toEselon(m);
        ODM.displayMatrix(m);

    }
}
