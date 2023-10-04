package PersoalanSPL;

import Library.Determinan;
import Library.Matrix;
import Library.MetodeEliminasi;
import Library.OperasiDasarMatrix;

public class RegresiLinearBerganda{

    /* *** CREATE OBJECTS *** */
    Determinan DET = new Determinan();
    MetodeEliminasi ME = new MetodeEliminasi();
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();
    // MyApp MENU = new MyApp();

    public double sumOfAllElmtInCol(Matrix m, int idxCol){
        /* M */
        double sum = 0;
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            sum += m.get_ELMT(i, idxCol);
        }
        return sum;
    }

    public void rowTimesConst(Matrix m, int idxRow, double k){
        /* Mengalikan sebuah baris dengan konstanta tidak nol */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            m.set_ELMT(idxRow, i, m.get_ELMT(idxRow, i) * k);
        }
    }

    public double sumOfProductTwoCols(Matrix m, int idxCol1, int idxCol2){
        /* M */
        double sum = 0;
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            sum += (m.get_ELMT(i, idxCol1) * m.get_ELMT(i, idxCol2));
        }
        return sum;
    }

    public RegresiLinearBerganda(){
    // public static void main(String[] args) {
        System.out.println("Halo aku bagian novel");
        
        // Matrix m = new Matrix();
        
        // MENU.jenis_input();
        // int input_type = jenis_input();
        // if (input_type == 1){
        //     // input dari keyboard
        //     ODM.createMatrix(m, );
        // }
        // else{
        //     // input dari file
        // }
        
        // input matrix awal:
        // x0,0 ... x(k-1),0 y0
        // ...
        // x(n-1),0 ... x(n-1),(k-1) y(n-1)
        


        // n banyak baris input, k banyak variabel x
        int n = 20, k = 3; // input matrix n x (k+1) (ex: 20 x 4)
        // n = m.getROW, k = m.getCol - 1

        // CONTOH: (n dan k fix yang dari spek)
        Matrix m = new Matrix();
        ODM.createMatrix(m, n, k + 1);
        ODM.readMatrix(m, n, k + 1);

        Matrix NormalEqMatrix = new Matrix();
        ODM.createMatrix(NormalEqMatrix, k + 1, k + 2);
        
        // Set baris 1
        NormalEqMatrix.set_ELMT(0, 0, n);
        for (int j = 1; j < NormalEqMatrix.get_COL_EFF(); j++){
            NormalEqMatrix.set_ELMT(0, j, sumOfAllElmtInCol(m, j-1));
        }

        System.out.println("--------CEK Baris 1 -----------");
        ODM.displayMatrix(NormalEqMatrix);

        // // Set baris pengali
        // Matrix BarisPengali = new Matrix();
        // ODM.createMatrix(BarisPengali, 1, NormalEqMatrix.get_COL_EFF());
        // BarisPengali.set_ELMT(0, 0, 1);
        // for (int j = 1; j < NormalEqMatrix.get_COL_EFF(); j++){
        //     BarisPengali.set_ELMT(0, j, NormalEqMatrix.get_ELMT(0, j));
        // }

        // System.out.println("--------CEK Baris Pengali -----------");
        // ODM.displayMatrix(BarisPengali);

        // Set kolom 1
        for (int i = 1; i < m.get_ROW_EFF(); i++){
            NormalEqMatrix.set_ELMT(i, 0, sumOfAllElmtInCol(m, i - 1));
        }

        // Set baris lainnya
        for (int i = 1; i < NormalEqMatrix.get_ROW_EFF(); i++){
            for (int j = 1; j < NormalEqMatrix.get_COL_EFF(); j++){
                NormalEqMatrix.set_ELMT(i, j, sumOfProductTwoCols(m, i - 1, j - 1));
            }
        }
        System.out.println("--------CEK NormalEqMatrix -----------");
        ODM.displayMatrix(NormalEqMatrix);

        ME.toEselonRed(NormalEqMatrix);
        System.out.println("--------CEK NormalEqMatrix to RED -----------");
        ODM.displayMatrix(NormalEqMatrix);
        if (ME.Gauss(NormalEqMatrix) == 1){
            Matrix allConst = new Matrix();
            allConst = ME.SolveSPLUnik(NormalEqMatrix);

            System.out.println("--------CEK Solution -----------");
            ODM.displayMatrix(allConst);
        }
    }
    // input y, x1, ..., xn
    // k = 20 n = 3
    // matrix 20 x 4 jadi (k+1)x(k + 2) 21 x 22

}