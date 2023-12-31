package PersoalanSPL;

import Library.Matrix;
import Library.OperasiDasarMatrix;
import Library.OperasiDasarGambar;
import Library.MetodeEliminasi;
import Library.MatriksBalikan;
// import PersoalanSPL.Gauss;
import java.lang.Math;

import java.util.Scanner;



public class BicubicSplineInterpolation {
    Matrix MBesar = new Matrix();
    Matrix MSoal = new Matrix();

    OperasiDasarMatrix ODM = new OperasiDasarMatrix();
    MetodeEliminasi ME = new MetodeEliminasi();
    MatriksBalikan MB = new MatriksBalikan();

    Scanner sc = new Scanner(System.in);
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
                // System.out.printf("%d %d\n", x, y);
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
                // System.out.printf("%d %d\n", x, y);
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
                // System.out.printf("%d %d\n", x, y);
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
                // System.out.printf("%d %d\n", x, y);
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

    public void StartBSI(int pilihan){
        System.out.println("Selamat Datang Di Bicubic Spline Interpolation");
        System.out.println();

        ODM.createMatrix(MBesar, 16, 16);
        createInitialMatrix(MBesar);

        // METODE 2 TANPA INVERS
        ODM.createMatrix(MSoal, 16, 1);

        double pointX;
        double pointY;
        if(pilihan == 1){
            System.out.println("Silahkan masukkan Matrix 4 x 4");
            ODM.readMatrix(MSoal, 16, 1);
            System.out.print("Masukkan nilai untuk di interpolasi x: ");
            pointX = sc.nextDouble();
            System.out.print("Masukkan nilai untuk di interpolasi y: ");
            pointY = sc.nextDouble();
        } else {
            System.out.print("Masukkan Nama File: ");
            String filename = sc.nextLine();
            System.out.println();
            Matrix temp = new Matrix();
            ODM.readMatrixFile(filename, temp);
            // ODM.displayMatrix(temp);

            ODM.createMatrix(MSoal, 16, 1);
            

            int pos = 0;
            for(int i=0; i<4;i++){
                for(int j=0;j<4;j++){
                    MSoal.set_ELMT(pos, 0, temp.get_ELMT(i, j));
                    pos++;
                }
            }
            // ODM.displayMatrix(MSoal);
            pointX = temp.get_ELMT(4, 0);
            pointY = temp.get_ELMT(4, 1);
        }
        

        // System.out.println("---------------------------");
        Matrix merged = ODM.mergeMatrix(MBesar, MSoal);
        // ODM.displayMatrix(merged);
        ME.toEselon(merged);
        // System.out.println("---------------------------");
        // ODM.displayMatrix(merged);
        merged = ME.SolveSPLUnik(merged);
        // System.out.println("---------------------------");
        // ODM.displayMatrix(merged);

        Matrix ans = new Matrix();
        ODM.createMatrix(ans, 16, 1);

        

        int row = 0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                double temp = Math.pow(pointX, i) * Math.pow(pointY, j);
                ans.set_ELMT(row, 0, (double)temp);
                row++;
            }
        }

        double finalAns = 0;
        for(int i=0; i<16; i++){
            finalAns += (ans.get_ELMT(i, 0) * merged.get_ELMT(i,0));
        }
        System.out.printf("f(%f, %f) = %f ---\n", pointX, pointY, finalAns);
        
    }
}