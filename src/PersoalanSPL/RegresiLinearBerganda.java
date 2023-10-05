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

    /* *** PRIMARY FUNCTION *** */

    public Matrix PersamaanHasilRegresiLinearBerganda(Matrix m){
        /* Mendapatkan semua konstanta dari persamaan hasil regresi linear berganda */
        int n = m.get_ROW_EFF();
        int k = m.get_COL_EFF() - 1;

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

        return allConst;
    }
}