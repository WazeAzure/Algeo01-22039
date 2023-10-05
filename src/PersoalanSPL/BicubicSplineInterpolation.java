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

    public BicubicSplineInterpolation(){
        ODM.createMatrix(MBesar, 16, 16);
        createInitialMatrix(MBesar);

        
        // METODE 1 DENGAN INVERS
        // System.out.println("---------------------------");
        // ODM.displayMatrix(MBesar);

        // Matrix MBesarInverse = new Matrix();
        // MBesarInverse = MB.inverseWithGaussJordan(MBesar);

        // ODM.displayMatrixtoFile(MBesarInverse, "Matriks_Invers.txt");
        // ODM.displayMatrix(MBesarInverse);

        // ODM.createMatrix(MSoal, 16, 1);
        // ODM.readMatrix(MSoal, 16, 1);

        // System.out.println("---------------------------");
        // Matrix result = new Matrix();
        // result  = ODM.multiplyMatrix(MBesarInverse, MSoal);
        // ODM.displayMatrix(result);
        
        // System.out.println("---------------------------");
        // double pointX = sc.nextDouble();
        // double pointY = sc.nextDouble();
        
        // Matrix interpolate = new Matrix();
        // ODM.createMatrix(interpolate, 16, 1);
        // int row = 0;
        // for(int j=0;j<4;j++){
        //     for(int i=0;i<4;i++){
        //         double temp = Math.pow(pointX, i) * Math.pow(pointY, j);
        //         interpolate.set_ELMT(row, 0, (double)temp);
        //         row++;
        //     }
        // }

        // ODM.displayMatrix(interpolate);
        // System.out.println("---------------------------");
        // double finalAns = 0;
        // for(int i=0; i<16; i++){
        //     finalAns += interpolate.get_ELMT(i, 0) * result.get_ELMT(i, 0);
        // }
        // System.out.println("final anss --- " + finalAns);

        // METODE 2 TANPA INVERS
        // ODM.createMatrix(MSoal, 16, 1);
        // ODM.readMatrix(MSoal, 16, 1);

        // System.out.println("---------------------------");
        // Matrix merged = ODM.mergeMatrix(MBesar, MSoal);
        // ODM.displayMatrix(merged);
        // ME.toEselon(merged);
        // System.out.println("---------------------------");
        // ODM.displayMatrix(merged);
        // merged = ME.SolveSPLUnik(merged);
        // System.out.println("---------------------------");
        // ODM.displayMatrix(merged);

        // Matrix ans = new Matrix();
        // ODM.createMatrix(ans, 16, 1);

        // double pointX = sc.nextDouble();
        // double pointY = sc.nextDouble();

        // int row = 0;
        // for(int j=0;j<4;j++){
        //     for(int i=0;i<4;i++){
        //         double temp = Math.pow(pointX, i) * Math.pow(pointY, j);
        //         ans.set_ELMT(row, 0, (double)temp);
        //         row++;
        //     }
        // }

        // double finalAns = 0;
        // for(int i=0; i<16; i++){
        //     finalAns += (ans.get_ELMT(i, 0) * merged.get_ELMT(i,0));
        // }
        // System.out.println("FinalAns ---- " + finalAns);
        
    }
}