package PersoalanSPL;

import Library.Matrix;
import Library.MetodeEliminasi;
import Library.OperasiDasarMatrix;
import java.lang.Math;
import java.util.Scanner;

public class InterpolasiPolinomial {
    OperasiDasarMatrix Operate = new OperasiDasarMatrix();
    Gauss ME = new Gauss();

    // Jika input dari keyboard:
    public Matrix InputtoMatrix (int n){
        // n adalah banyaknya titik yang diketahui
        Matrix m = new Matrix();
        Operate.createMatrix(m, n, 2);

        System.out.println("Masukkan titik");
        Operate.readMatrix(m, n, 2);
        return m;
    }

    public Matrix MatrixtoMatrixInt (Matrix m) {
        Matrix inter = new Matrix();
        Operate.createMatrix(inter, m.get_ROW_EFF(), m.get_ROW_EFF() + 1);

        Operate.createMatrix(inter, m.get_ROW_EFF(), m.get_ROW_EFF()+1);
        int i, j;
        for (i = 0; i < inter.get_ROW_EFF(); i++){
            for (j = 0; j < inter.get_COL_EFF()-1; j++){
                inter.set_ELMT(i, j, Math.pow(m.get_ELMT(i, 0), j));
            }
        }
        for (i = 0; i < inter.get_ROW_EFF(); i++){
            inter.set_ELMT(i, inter.get_COL_EFF()-1 , m.get_ELMT(i, 1));
        }
        return inter;
    }

    public double Interpolasi (Matrix m, double x){
        // memperkirakan nilai pada titik x
        Matrix SPL = new Matrix();
        SPL = ME.eliminasiGauss(m, false);
        int i; 
        double y = 0;
        for(i = 0; i < SPL.get_ROW_EFF(); i++){
            y += Math.pow(x, i) * SPL.get_ELMT(i, 0);
        }
        return y;
    }

    public void InterPolinomial (int n){
        // sebelum ke prosedur ini harus menerima input berapa banyak baris matriksnya
        // matrix m memiliki baris sebanyak n dan dicari nilai interpolasi x
        Matrix m = new Matrix();

        m = InputtoMatrix(n);
        m = MatrixtoMatrixInt(m);
        Operate.displayMatrix(m);

        Scanner sc = new Scanner(System.in);
        System.out.println("masukkan x:");
        double x = sc.nextDouble();
        double y = Interpolasi(m, x);
        System.out.println("nilai interpolasi x = " + x + " menghasilkan nilai y = " + y);
    }
}
