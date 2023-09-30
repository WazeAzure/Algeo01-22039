package PersoalanSPL;

import Library.Matrix;
import Library.OperasiDasarMatrix;
import Library.OperasiDasarGambar;

public class BicubicSplineInterpolation {
    public BicubicSplineInterpolation(){

        OperasiDasarMatrix ODM = new OperasiDasarMatrix();
        OperasiDasarGambar ODG = new OperasiDasarGambar();

        System.out.println("Halo aku bagian ed");
        
        Matrix m = new Matrix();
        m = ODG.readImage("lena.png");
        // ODG.writeImage("result_lena.png", m);
    }
}