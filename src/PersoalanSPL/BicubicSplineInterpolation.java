package PersoalanSPL;

import Library.Matrix;
import Library.OperasiDasarMatrix;
import Library.OperasiDasarGambar;
import Library.MetodeEliminasi;
import PersoalanSPL.Gauss;
import java.lang.Math;

import java.util.Scanner;



public class BicubicSplineInterpolation {
    Matrix MBesar = new Matrix();
    Matrix MSoal = new Matrix();
  
    public void createInitialMatrix(Matrix m){
        // biasa
        int row = 0;
        for(int y=0;y<=1;y++){
            for(int x=0;x<=1;x++){
                System.out.printf("%d %d\n", x, y);
                int col = 0;
                for(int j=0;j<4;j++){
                    for(int i=0;i<4;i++){
                        double temp = Math.pow(x, i) * Math.pow(y, j);
                        m.set_ELMT(row, col, (double)temp);
                        col++;
                    }
                }
                row++;
            }
        }

        // Turunan terhadap x;
        row = 4;
        for(int y=0;y<=1;y++){
            for(int x=0;x<=1;x++){
                System.out.printf("%d %d\n", x, y);
                int col = 0;
                for(int j=0;j<4;j++){
                    for(int i=0;i<4;i++){
                        if(i==0){
                             m.set_ELMT(row, col, 0);
                        } else {
                            double temp = i * Math.pow(x, i-1) * Math.pow(y, j);
                            m.set_ELMT(row, col, (double)temp);
                        }
                        col++;
                    }
                }
                row++;
            }
        }

        // turunan terhadap y
        row = 8;
        for(int y=0;y<=1;y++){
            for(int x=0;x<=1;x++){
                System.out.printf("%d %d\n", x, y);
                int col = 0;
                for(int j=0;j<4;j++){
                    for(int i=0;i<4;i++){
                        if(j==0){
                             m.set_ELMT(row, col, 0);
                        } else {
                            double temp = j * Math.pow(x, i) * Math.pow(y, j-1);
                            m.set_ELMT(row, col, (double)temp);
                        }
                        col++;
                    }
                }
                row++;
            }
        }

        // turunan terhadap xy
        row = 12;
        for(int y=0;y<=1;y++){
            for(int x=0;x<=1;x++){
                System.out.printf("%d %d\n", x, y);
                int col = 0;
                for(int j=0;j<4;j++){
                    for(int i=0;i<4;i++){
                        if(i == 0 || j == 0){
                            m.set_ELMT(row, col, 0);
                        } else {
                            double temp = i * j * Math.pow(x, i-1) * Math.pow(y, j-1);
                            m.set_ELMT(row, col, (double)temp);
                        }
                        col++;
                    }
                }
                row++;
            }
        }
    }

    // public void createSoalMatrix(Matrix m){
    //     // biasa
    //     int row = 0;
    //     for(int y=0;y<=1;y++){
    //         for(int x=0;x<=1;x++){
    //             System.out.printf("%d %d\n", x, y);
    //             int col = 0;
    //             for(int j=0;j<4;j++){
    //                 for(int i=0;i<4;i++){
    //                     double temp = Math.pow(x, i) * Math.pow(y, j);
    //                     m.set_ELMT(row, col, (double)temp);
    //                     col++;
    //                 }
    //             }
    //             row++;
    //         }
    //     }
    // }

    public BicubicSplineInterpolation(){
        OperasiDasarMatrix ODM = new OperasiDasarMatrix();
        OperasiDasarGambar ODG = new OperasiDasarGambar();
        MetodeEliminasi ME = new MetodeEliminasi();
        // ODM.createMatrix(MBesar, 16, 16);
        // ODM.createMatrix(MSoal, 16, 1);
        // createInitialMatrix(MBesar);
        // ODM.displayMatrix(MBesar);

        // ODM.readMatrix(MSoal, 16, 1);

        // Scanner sc = new Scanner(System.in);
        // double a = sc.nextFloat();
        // double b = sc.nextFloat();

        // Matrix MHasil = ODM.mergeMatrix(MBesar, MSoal);
        // Matrix MHasil2 = ODM.copyMatrix(MHasil);
        // System.out.println(MHasil.get_ROW_EFF());
        // ODM.displayMatrix(MHasil);
        
        // createSoalMatrix(MSoal);
        System.out.println("------------------fungsi gonza------------");
        // Gauss.eliminasiGauss(MHasil, true);
        // ODM.displayMatrix(MHasil);
        System.out.println("------------------fungsi novel------------");
        // ME.toEselon(MHasil2);
        // ODM.displayMatrix(MHasil2);
        System.out.println("Halo aku bagian ed");

        // testcase new OBE
        Matrix tc = new Matrix();
        ODM.createMatrix(tc, 4, 5);
        ODM.readMatrix(tc, 4, 5);

        ME.toEselon(tc);
        ODM.displayMatrix(tc);


        
        // Matrix m = new Matrix();
        // m = ODG.readImage("lena.png");
        // ODG.writeImage("result_lena.png", m);
    }
}