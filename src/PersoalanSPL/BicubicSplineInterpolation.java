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

    OperasiDasarMatrix ODM = new OperasiDasarMatrix();
    MetodeEliminasi ME = new MetodeEliminasi();

    // public Matrix f(int a, int b){
    //     Matrix temp = new Matrix();
    //     ODM.createMatrix(temp, 1, 16);
        
    //     for(int col=0; col < temp.get_COL_EFF(); col++){
    //         double ab = Math.pow(a, 0) * Math.pow(b, col);
    //         m.set_ELMT(0, col, ab);
    //     }
    //     return temp;
    // }
  
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

        // int row = 0;
        // for(int i=0; i<=1; i++){
        //     for(int j=0; j<=1;j++){
        //         Matrix x = new Matrix();
        //         x = f(i, j);
        //         MBesar.mem[row] = x.mem[0];
        //         row++;
        //     }
        // }

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
        ODM.createMatrix(MBesar, 16, 16);
        createInitialMatrix(MBesar);
        // Matrix m = new Matrix();
        // ODM.readMatrixFile("test.txt", m);
        // ODM.displayMatrix(m);
        System.out.println("---------------------------");
        ME.toEselon(MBesar);
        ODM.displayMatrix(MBesar);
        System.out.println("---------------------------");
        
        Gauss.eliminasiGauss(MBesar, true);
        ODM.displayMatrix(MBesar);
    }
}