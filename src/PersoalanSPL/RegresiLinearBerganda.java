package PersoalanSPL;

import java.util.Scanner;

import Library.Determinan;
import Library.Matrix;
import Library.MetodeEliminasi;
import Library.OperasiDasarMatrix;

public class RegresiLinearBerganda{

    /* *** CREATE OBJECTS *** */
    Determinan DET = new Determinan();
    MetodeEliminasi ME = new MetodeEliminasi();
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();
    Scanner sc = new Scanner(System.in);

    /* *** HELPER FUNCTIONS *** */

    public double sumOfAllElmtInCol(Matrix m, int idxCol){
        /* Menghitung jumlah semua elemen pada suatu kolom */
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
        /* Menghitung jumlah dari hasil kali setiap 2 elemen sebaris dalam 2 kolom */
        double sum = 0;
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            sum += (m.get_ELMT(i, idxCol1) * m.get_ELMT(i, idxCol2));
        }
        return sum;
    }

    /* *** CONSTRUCTOR *** */

    public RegresiLinearBerganda(){
        System.out.println("\nHalo aku bagian novel");
        
        // Input dari keyboard
        int k, n;
        System.out.println("Masukkan jumlah peubah x:");
        k = sc.nextInt();
        System.out.println("Masukkan jumlah sampel:");
        n = sc.nextInt();
        System.out.println("Masukkan data (dalam bentuk matriks):");
        Matrix m = new Matrix();
        ODM.createMatrix(m, n, k + 1);
        ODM.readMatrix(m, n, k + 1);

        // Bentuk Normal Estimation Equation dalam matrix
        Matrix NormalEqMatrix = new Matrix();
        ODM.createMatrix(NormalEqMatrix, k + 1, k + 2);
        // Set baris 1
        NormalEqMatrix.set_ELMT(0, 0, n);
        for (int j = 1; j < NormalEqMatrix.get_COL_EFF(); j++){
            NormalEqMatrix.set_ELMT(0, j, sumOfAllElmtInCol(m, j-1));
        }
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

        // Dapatkan nilai konstanta persamaan regresi linier berganda
        ME.toEselonRed(NormalEqMatrix);
        Matrix allConst = new Matrix();
        if (ME.Gauss(NormalEqMatrix) == 1){
            allConst = ME.SolveSPLUnik(NormalEqMatrix);
        }

        // Print persamaan hasil regresi linier berganda
        System.out.println("Persamaan hasil regresi linier berganda:");
        String[][] persamaan;
        persamaan = new String[2][1];
        persamaan[0][0] = "f(x) = " + (Math.round(allConst.get_ELMT(0, 0) * 100000.0) / 100000.0);
        System.out.printf("f(x) = %.5f", allConst.get_ELMT(0, 0));
        for (int i = 1; i < allConst.get_ROW_EFF(); i++){
            persamaan[0][0] += " + " + (Math.round(allConst.get_ELMT(i, 0) * 100000.0) / 100000.0) + " x" + i;
            System.out.printf(" + %.5f x%d", allConst.get_ELMT(i, 0), i);
        }
        
        // Mencari taksiran nilai fungsi pada x yang diberikan
        System.out.println("\nTaksiran nilai fungsi:");
        System.out.printf("Masukkan %d nilai x yang ingin ditaksir: ", k);
        Matrix taksir = new Matrix();
        ODM.createMatrix(taksir, k, 1);
        for (int i = 0; i < taksir.get_ROW_EFF(); i++){
            taksir.set_ELMT(i, 0, sc.nextDouble());
        }
        double hasil = allConst.get_ELMT(0, 0);
        for (int i = 0; i < allConst.get_ROW_EFF(); i++){
            hasil += allConst.get_ELMT(i + 1, 0) * taksir.get_ELMT(i, 0);
        }
        System.out.printf("Hasil taksirannya adalah %.5f", hasil);
    }
}