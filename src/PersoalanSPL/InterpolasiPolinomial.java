package PersoalanSPL;

import Library.Matrix;
import Library.MetodeEliminasi;
import Library.OperasiDasarMatrix;
import java.lang.Math;
import java.util.Scanner;

public class InterpolasiPolinomial {
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();
    MetodeEliminasi ME = new MetodeEliminasi();
    Scanner sc = new Scanner(System.in);

    // Jika input dari keyboard:
    public Matrix InputtoMatrix(int n, int input) {
        // n adalah banyaknya titik yang diketahui
        Matrix m = new Matrix();
        ODM.createMatrix(m, n, 2);
        if (input == 1) {
            System.out.println("Masukkan titik: ");
            ODM.readMatrix(m, n, 2);
        } else {
            // System.out.println("Masukkan nama file: ");
            // sc.nextLine();
            // String filename = sc.nextLine();
            // ODM.readMatrixFileInterpolate(filename, m, x);
        }

        return m;
    }

    public Matrix MatrixtoMatrixInt(Matrix m) {
        Matrix inter = new Matrix();
        ODM.createMatrix(inter, m.get_ROW_EFF(), m.get_ROW_EFF() + 1);

        ODM.createMatrix(inter, m.get_ROW_EFF(), m.get_ROW_EFF() + 1);
        int i, j;
        for (i = 0; i < inter.get_ROW_EFF(); i++) {
            for (j = 0; j < inter.get_COL_EFF() - 1; j++) {
                inter.set_ELMT(i, j, Math.pow(m.get_ELMT(i, 0), j));
            }
        }
        for (i = 0; i < inter.get_ROW_EFF(); i++) {
            inter.set_ELMT(i, inter.get_COL_EFF() - 1, m.get_ELMT(i, 1));
        }
        return inter;
    }

    public Matrix constOfInterpolation(Matrix m){
        Matrix SPL = new Matrix();
        ME.toEselon(m);
        SPL = ME.SolveSPLUnik(m);
        return SPL;
    }

    public double Interpolasi(Matrix m, double x) {
        // memperkirakan nilai pada titik x
        Matrix SPL = new Matrix();
        SPL = constOfInterpolation(m);
        double y;
        int n = ME.Gauss(m);
        if (n == 1) {
            int i;
            y = 0;
            for (i = 0; i < SPL.get_ROW_EFF(); i++) {
                y += Math.pow(x, i) * SPL.get_ELMT(i, 0);
                y = Math.round(y * 10000.0) / 10000.0;
            }
        } else {
            y = -9999.9999;
        }
        return y;
    }

    // public void InterPolinomial(int n) {
    // // sebelum ke prosedur ini harus menerima input berapa banyak baris
    // matriksnya
    // // matrix m memiliki baris sebanyak n dan dicari nilai interpolasi x
    // Matrix m = new Matrix();

    // m = InputtoMatrix(n);
    // m = MatrixtoMatrixInt(m);
    // Operate.displayMatrix(m);

    // Scanner sc = new Scanner(System.in);
    // System.out.println("masukkan x:");
    // double x = sc.nextDouble();
    // double y = Interpolasi(m, x);
    // System.out.println("nilai interpolasi x = " + x + " menghasilkan nilai y = "
    // + y);
    // }
}