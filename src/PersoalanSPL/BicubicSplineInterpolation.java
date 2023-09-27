package PersoalanSPL;

import Library.Matrix;
import Library.OperasiDasarMatrix;

public class BicubicSplineInterpolation {
    public BicubicSplineInterpolation(){

        System.out.println("Halo aku bagian ed");
        Matrix m1 = new Matrix();

        OperasiDasarMatrix ODM = new OperasiDasarMatrix();

        ODM.createMatrix(m1, 2, 2);
        ODM.readMatrix(m1, 2, 2);
        ODM.displayMatrix(m1);
        System.out.println(ODM.determinant(m1));

        Matrix m2 = new Matrix();
        ODM.readMatrixFile("1a.txt");
    }
}