package Library;

import java.lang.Math;

public class Determinan {
    // Buat punya Diana (determinan, kaidah cramer)

    /* *** PRIMARY FUNCTIONS *** */
    double det;
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();
    MetodeEliminasi ME = new MetodeEliminasi();

    public double DetReduksiBaris(Matrix m) {
        if (ODM.isSquare(m)) {
            ME.toEselon(m);
            if (MetodeEliminasi.isEselon(m)) {
                int n = m.get_COL_EFF();
                for (int i = 0; i < n; i++) {
                    det = 1;
                    det *= m.get_ELMT(i, i);
                }
            }
        }
        return det;
    }

    public double determinan2x2(Matrix m) {
        return (m.get_ELMT(0, 0) * m.get_ELMT(1, 1)) - (m.get_ELMT(0, 1) * m.get_ELMT(1, 0));
    }

    public double Kofaktor(Matrix m, int i, int j) {
        double Cij = 1;
        Matrix m2 = new Matrix();
        ODM.createMatrix(m2, m.get_ROW_EFF() - 1, m.get_COL_EFF() - 1);
        int row, col;
        int x = 0, y = 0;
        // System.out.println("kofaktor func called");
        for (row = 0; row < m.get_ROW_EFF(); row++) {
            // System.out.println(row);
            for (col = 0; col < m.get_COL_EFF(); col++) {
                if (row == i || col == j) {
                    continue;
                } else {
                    m2.set_ELMT(x, y, m.get_ELMT(row, col));
                    y++;
                    if (y == m2.get_COL_EFF()) {
                        y = 0;
                        x++;
                    }
                }
            }
        }
        // ODM.displayMatrix(m2);
        // kondisi m2 adalah submatrix yang tidak memiliki elemen baris i dan kolom j
        if ((m2.get_ROW_EFF() == 2) && (m2.get_COL_EFF() == 2)) {
            Cij = determinan2x2(m2) * Math.pow((-1), (i + j));
        } else {
            DetEkspansiKofaktor(m2); // rekursif
        }
        // System.out.println(Cij);
        return Cij;
    }

    public double DetEkspansiKofaktor(Matrix m) {
        if (ODM.isSquare(m) && m.get_COL_EFF() > 2) {
            int n = m.get_COL_EFF();
            int i, j;
            det = 0;
            i = 0;
            for (j = 0; j < n; j++) {
                det += m.get_ELMT(i, j) * Kofaktor(m, i, j); // menambahkan setiap perkalian kofaktor dari baris pertama
                                                             // matriks dengan element terkait untuk mendapatkan
                                                             // determinan.
            }
        } else if (m.get_COL_EFF() == 2) {
            det = determinan2x2(m);
        }
        return det;
    }

    public Matrix adjoinMatrix(Matrix m) {
        // ubah ke matriks kofaktor
        Matrix mCij = new Matrix();
        ODM.createMatrix(mCij, m.get_ROW_EFF(), m.get_COL_EFF());
        int row, col;
        for (row = 0; row < m.get_ROW_EFF(); row++) {
            for (col = 0; col < m.get_COL_EFF(); col++) {
                double x = Kofaktor(m, row, col);
                mCij.set_ELMT(row, col, x);
            }
        }
        // transpose matriks kofaktor
        Matrix mAdj = new Matrix();
        ODM.createMatrix(mAdj, m.get_ROW_EFF(), m.get_COL_EFF());
        for (row = 0; row < m.get_ROW_EFF(); row++) {
            for (col = 0; col < m.get_COL_EFF(); col++) {
                mAdj.set_ELMT(row, col, mCij.get_ELMT(col, row));
            }
        }
        return mAdj;
    }

    // KAIDAH CRAMER

    public void splitMatrixSPL(Matrix m, Matrix a, Matrix b) {
        int row, col;
        for (row = 0; row < m.get_ROW_EFF(); row++) {
            for (col = 0; col < m.get_COL_EFF() - 1; col++) {
                a.set_ELMT(row, col, m.get_ELMT(row, col));
            }
        }
        int rowb;
        for (rowb = 0; rowb < m.get_ROW_EFF(); rowb++) {
            b.set_ELMT(rowb, 0, m.get_ELMT(rowb, m.get_COL_EFF() - 1));
        }
    }

    public Matrix MatrixAj(Matrix a, int j, Matrix b) {
        Matrix Aj = new Matrix();
        Aj = ODM.copyMatrix(Aj);
        // mengganti kolom j dengan matrix b
        int rowAj;
        for (rowAj = 0; rowAj < a.get_ROW_EFF(); rowAj++) {
            Aj.set_ELMT(rowAj, j, b.get_ELMT(0, j));
        }
        return Aj;
    }

    public void KaidahCramer(Matrix m, int input) {
        Matrix a = new Matrix();
        Matrix b = new Matrix();
        ODM.createMatrix(a, m.get_ROW_EFF(), m.get_COL_EFF() - 1);
        ODM.createMatrix(b, m.get_ROW_EFF(), 1);
        splitMatrixSPL(m, a, b);

        // asumsi banyaknya persamaan memenuhi persayaratan agar dapat diselesaikan
        double detA = DetReduksiBaris(a);
        if (input == 1) {
            if (detA == 0) {
                System.out.println("Determinan matriks = 0");
            } else {
                int i = 0;
                double[] listX = new double[a.get_ROW_EFF()];
                for (i = 0; i < a.get_ROW_EFF(); i++) {
                    Matrix Aj = MatrixAj(a, i, b);
                    listX[i] = (DetReduksiBaris(Aj)) / detA;
                    System.out.println("x" + (i + 1) + " = " + listX[i]);
                }
            }
        } else if (input == 2) {
            // belum ada fungsi write matrix to file
        }
    }
}