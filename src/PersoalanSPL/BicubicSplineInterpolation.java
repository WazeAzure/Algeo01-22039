package PersoalanSPL;

import Library.Matrix;
import Library.OperasiDasarMatrix;
import Library.OperasiDasarGambar;
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
                        m.set_ELMT(row, col, (float)temp);
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
                            m.set_ELMT(row, col, (float)temp);
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
                            m.set_ELMT(row, col, (float)temp);
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
                            m.set_ELMT(row, col, (float)temp);
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
    //                     m.set_ELMT(row, col, (float)temp);
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
        ODM.createMatrix(MBesar, 16, 16);
        ODM.createMatrix(MSoal, 16, 1);
        createInitialMatrix(MBesar);
        ODM.displayMatrix(MBesar);

        ODM.readMatrix(MSoal, 16, 1);

        Scanner sc = new Scanner(System.in);
        float a = sc.nextFloat();
        float b = sc.nextFloat();

        Matrix MHasil = ODM.mergeMatrix(MBesar, MSoal);

        ODM.displayMatrix(MHasil);
        // createSoalMatrix(MSoal);

        System.out.println("Halo aku bagian ed");


        
        // Matrix m = new Matrix();
        // m = ODG.readImage("lena.png");
        // ODG.writeImage("result_lena.png", m);
    }
}