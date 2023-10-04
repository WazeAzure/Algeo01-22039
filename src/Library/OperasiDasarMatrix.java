package Library;

import java.util.Scanner;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors

public class OperasiDasarMatrix {
    static final int ROW_CAP = 100;
    static final int COL_CAP = 100;

    // constructor
    public void createMatrix(Matrix m, int nRows, int nCols) {
        // inisiasi array bernilai 0;
        // System.out.println("called");
        m.set_COL_EFF(nCols);
        m.set_ROW_EFF(nRows);
    }

    // selector

    // Read & Write
    public void readMatrix(Matrix m, int nRow, int nCol) {

        // define for scanner. Di java untuk input harus pake scanner.
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                m.set_ELMT(i, j, sc.nextFloat());
            }
        }
    }

    public void readMatrixFile(String filename, Matrix m) {
        System.out.println(filename);
        try {

            filename = "test/" + filename;
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            int nCol = 0;
            int i = 0;
            int j = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ");

                nCol = parts.length;

                for (j = 0; j < parts.length; j++) {
                    m.set_ELMT(i, j, Double.parseDouble(parts[j]));
                }

                i++;
            }
            myReader.close();

            m.set_COL_EFF(nCol);
            m.set_ROW_EFF(i);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readMatrixFileInterpolate(String filename, Matrix m, double x) {
        System.out.println(filename);
        try {

            filename = "test/" + filename;
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            int nCol = 0;
            int i = 0;
            int j = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ");

                nCol = parts.length;

                for (j = 0; j < parts.length; j++) {
                    m.set_ELMT(i, j, Double.parseDouble(parts[j]));
                }

                i++;
            }
            myReader.close();
            x = m.get_ELMT(i, 0);
            m.set_ELMT(i, 0, 0);
            m.set_COL_EFF(nCol);
            m.set_ROW_EFF(i-1);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Matrix readSPLCramer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan ukuran matriks A (baris): ");
        int m = sc.nextInt();
        System.out.println("Masukkan ukuran matriks A (kolom): ");
        int n = sc.nextInt();
        if (m != n){
            Matrix SPL = new Matrix();
            createMatrix(SPL, 0, 0);
            return SPL;
        } else {
            Matrix a = new Matrix();
            createMatrix(a, m, n);
            System.out.println("Masukkan matriks A: ");
            readMatrix(a, m, n);
            System.out.println("Masukkan matriks b: ");
            Matrix b = new Matrix();
            createMatrix(b, m, 1);
            readMatrix(b, m, 1);
            // Gabungkan matrix a dan b untuk mendapatkan matriks augmented dalam matrix SPL
            Matrix SPL = new Matrix();
            createMatrix(SPL, m, n + 1);
            int i = 0, j = 0;
            for (i = 0; i < a.get_ROW_EFF(); i++) {
                for (j = 0; j < a.get_COL_EFF(); j++) {
                    SPL.set_ELMT(i, j, a.get_ELMT(i, j));
                }
            }
            for (i = 0; i < a.get_ROW_EFF(); i++) {
                SPL.set_ELMT(i, SPL.get_COL_EFF() - 1, b.get_ELMT(i, 0));
            }
            return SPL;
        }
        
    }

    public void displayMatrix(Matrix m) {
        for (int i = 0; i < m.get_ROW_EFF(); i++) {
            for (int j = 0; j < m.get_COL_EFF() - 1; j++) {
                System.out.printf("%.2f ", m.get_ELMT(i, j));
            }
            System.out.printf("%.2f\n", m.get_ELMT(i, m.get_COL_EFF() - 1));
        }
    }

    public void displayMatrixtoFile(Matrix m) {

    }

    // boolean stuff
    public boolean isMatrixIdxValid(int i, int j) {
        return ((i >= 0 && i < ROW_CAP) && (j >= 0 && j < COL_CAP));
    }

    public int getLastIdxRow(Matrix m) {
        return m.get_ROW_EFF() - 1;
    }

    public int getLastIdxCol(Matrix m) {
        return m.get_COL_EFF() - 1;
    }

    public boolean isIdxEff(Matrix m, int i, int j) {
        return (i >= 0 && i <= getLastIdxRow(m)) && (j >= 0 && j <= getLastIdxCol(m));
    }

    public double getElmtDiagonal(Matrix m, int i) {
        return m.get_ELMT(i, i);
    }

    public Matrix copyMatrix(Matrix mIn) {
        Matrix mOut = new Matrix();
        createMatrix(mOut, mIn.get_ROW_EFF(), mIn.get_COL_EFF());

        int i = 0, j = 0;
        for (i = 0; i < mIn.get_ROW_EFF(); i++) {
            for (j = 0; j < mIn.get_COL_EFF(); j++) {
                mOut.set_ELMT(i, j, mIn.get_ELMT(i, j));
            }
        }

        return mOut;
    }

    public Matrix addMatrix(Matrix m1, Matrix m2) {
        Matrix m3 = new Matrix();
        createMatrix(m3, m1.get_ROW_EFF(), m1.get_COL_EFF());

        int i, j;
        for (i = 0; i < m1.get_ROW_EFF(); i++) {
            for (j = 0; j < m1.get_COL_EFF(); j++) {
                m3.set_ELMT(i, j, m1.get_ELMT(i, j) + m2.get_ELMT(i, j));
            }
        }
        return m3;
    }

    // section-10
    public Matrix subtractMatrix(Matrix m1, Matrix m2) {
        Matrix m3 = new Matrix();
        createMatrix(m3, m1.get_ROW_EFF(), m1.get_COL_EFF());

        int i, j;
        for (i = 0; i < m1.get_ROW_EFF(); i++) {
            for (j = 0; j < m1.get_COL_EFF(); j++) {
                m3.set_ELMT(i, j, m1.get_ELMT(i, j) - m2.get_ELMT(i, j));
            }
        }
        return m3;
    }
    // section 11

    public Matrix multiplyMatrix(Matrix m1, Matrix m2) {
        Matrix m3 = new Matrix();
        createMatrix(m3, m1.get_ROW_EFF(), m2.get_COL_EFF());

        int i;
        for (i = 0; i < m1.get_ROW_EFF(); i++) {
            int j;
            for (j = 0; j < m1.get_COL_EFF(); j++) {
                int k;
                double temp = 0;
                for (k = 0; k < m2.get_ROW_EFF(); k++) {
                    temp += (m1.get_ELMT(i, k) * m2.get_ELMT(k, j));
                }
                m3.set_ELMT(i, j, temp);
            }
        }
        return m3;
    }
    // section-12

    public Matrix multiplyMatrixWithMod(Matrix m1, Matrix m2, int mod) {
        Matrix m3 = new Matrix();
        createMatrix(m3, m1.get_ROW_EFF(), m2.get_COL_EFF());

        int i;
        for (i = 0; i < m1.get_ROW_EFF(); i++) {
            int j;
            for (j = 0; j < m1.get_COL_EFF(); j++) {
                int k;
                int temp = 0;
                for (k = 0; k < m2.get_ROW_EFF(); k++) {
                    temp += (m1.get_ELMT(i, k) * m2.get_ELMT(k, j));
                }
                m3.set_ELMT(i, j, temp % mod);
            }
        }
        return m3;
    }
    // section-13

    public Matrix multiplyByConst(Matrix m, double x) {
        Matrix m3 = new Matrix();
        createMatrix(m3, m.get_ROW_EFF(), m.get_COL_EFF());

        int i, j;
        for (i = 0; i < m.get_ROW_EFF(); i++) {
            for (j = 0; j < m.get_COL_EFF(); j++) {
                m3.set_ELMT(i, j, m.get_ELMT(i, j) * x);
            }
        }
        return m3;
    }

    public void pMultiplyByConst(Matrix m, double k) {
        int i, j;
        for (i = 0; i < m.get_ROW_EFF(); i++) {
            for (j = 0; j < m.get_COL_EFF(); j++) {
                m.set_ELMT(i, j, m.get_ELMT(i, j) * k);
            }
        }
    }

    public boolean isMatrixEqual(Matrix m1, Matrix m2) {
        if (m1.get_COL_EFF() == m2.get_COL_EFF() && m1.get_ROW_EFF() == m2.get_ROW_EFF()
                && getLastIdxCol(m1) == getLastIdxCol(m2) && countElmt(m1) == countElmt(m2)) {
            int i, j;
            for (i = 0; i < m1.get_ROW_EFF(); i++) {
                for (j = 0; j < m1.get_COL_EFF(); j++) {
                    if (m1.get_ELMT(i, j) != m2.get_ELMT(i, j)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    // section-16

    public boolean isMatrixNotEqual(Matrix m1, Matrix m2) {
        return !isMatrixEqual(m1, m2);
    }
    // section-17

    public boolean isMatrixSizeEqual(Matrix m1, Matrix m2) {
        return (m1.get_ROW_EFF() == m2.get_ROW_EFF()) && (m1.get_COL_EFF() == m2.get_COL_EFF());
    }

    public int countElmt(Matrix m) {
        return m.get_ROW_EFF() * m.get_COL_EFF();
    }

    public boolean isSquare(Matrix m) {
        return m.get_COL_EFF() == m.get_ROW_EFF();
    }

    public boolean isSymmetric(Matrix m) {
        if (isSquare(m)) {
            int i, j;
            for (i = 0; i < m.get_ROW_EFF(); i++) {
                for (j = 0; j < m.get_COL_EFF(); j++) {
                    if (m.get_ELMT(i, j) != m.get_ELMT(j, i)) {
                        // printf("%d %d\n", i, j);
                        // printf("%d %d\n", ELMT(m, i, j), ELMT(m, j, i));
                        // printf("called1\n");
                        return false;
                    }
                }
            }
            return true;
        }
        // printf("called\n");
        return false;
    }
    // section-20

    public boolean isIdentity(Matrix m) {
        if (isSquare(m)) {
            // cek diagonal
            int i, j;
            for (i = 0; i < m.get_ROW_EFF(); i++) {
                if (m.get_ELMT(i, i) != 1)
                    return false;
            }

            // cek kanan atas
            for (i = 0; i < m.get_ROW_EFF() - 1; i++) {
                for (j = i + 1; j < m.get_COL_EFF(); j++) {
                    if (m.get_ELMT(i, j) != 0)
                        return false;
                }
            }

            // cek kiri bawah
            for (i = 1; i < m.get_ROW_EFF(); i++) {
                for (j = 0; j < i; j++) {
                    if (m.get_ELMT(i, j) != 0)
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isSparse(Matrix m) {
        int i, j;
        double num = 0, zero = 0;

        for (i = 0; i < m.get_ROW_EFF(); i++) {
            for (j = 0; j < m.get_COL_EFF(); j++) {
                if (m.get_ELMT(i, j) != 0)
                    num++;
                else
                    zero++;
            }
        }

        double result = num * 100 / (num + zero);
        if (result <= 5)
            return true;
        return false;
    }

    public Matrix negation(Matrix m) {
        return multiplyByConst(m, -1);
    }

    public void Negation(Matrix m) {
        pMultiplyByConst(m, -1);
    }

    public double determinant(Matrix m) {
        if (m.get_ROW_EFF() == 2) {
            return (m.get_ELMT(0, 0) * m.get_ELMT(1, 1)) - (m.get_ELMT(0, 1) * m.get_ELMT(1, 0));
        }

        double temp = 0;
        int x;
        for (x = 0; x < m.get_COL_EFF(); x++) {
            Matrix m2 = new Matrix();
            createMatrix(m2, m.get_ROW_EFF() - 1, m.get_COL_EFF() - 1);

            int r = 0, c = 0;
            int i, j;
            for (i = 0; i < m.get_ROW_EFF(); i++) {
                for (j = 0; j < m.get_COL_EFF(); j++) {
                    if (i == 0 || j == x) {
                        continue;
                    } else {
                        m2.set_ELMT(r, c, m.get_ELMT(i, j));
                        c++;
                        if (c == m2.get_COL_EFF()) {
                            c = 0;
                            r++;
                        }
                    }
                }
            }
            double det = (m.get_ELMT(0, x) * determinant(m2));
            if (x % 2 == 1)
                det *= -1;
            temp += det;
        }
        return temp;
    }

    public Matrix mergeMatrix(Matrix m1, Matrix m2) {
        // merge m2 to m1;
        Matrix m3 = new Matrix();
        // createMatrix(m3, m1.get_ROW_EFF(), m1.get_COL_EFF() + m2.get_COL_EFF())

        m3 = copyMatrix(m1);
        m3.set_COL_EFF(m1.get_COL_EFF() + m2.get_COL_EFF());

        for (int i = 0; i < m3.get_ROW_EFF(); i++) {
            for (int j = m1.get_COL_EFF(); j < m1.get_COL_EFF() + m2.get_COL_EFF(); j++) {
                m3.set_ELMT(i, j, m2.get_ELMT(i, j - m1.get_COL_EFF()));
            }
        }
        return m3;
    }
}
