package Library;

public class Determinan {
    // Buat punya Diana (determinan, kaidah cramer)

    /* *** PRIMARY FUNCTIONS *** */
    double det;

    public double ReduksiBaris(Matrix m) {
        OperasiDasarMatrix Operate = new OperasiDasarMatrix();
        if (Operate.isSquare(m)) {
            MetodeEliminasi mEselon = new MetodeEliminasi();
            mEselon.toEselon(m);
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
        OperasiDasarMatrix Operate = new OperasiDasarMatrix();
        Operate.createMatrix(m2, m.get_ROW_EFF() - 1, m.get_COL_EFF() - 1);
        int row, col;
        int x = 0, y = 0;
        for (row = 0; row < m2.get_ROW_EFF(); row++) {
            if (row == i) {
                x++;
                continue;
            }
            for (col = 0; col < m2.get_COL_EFF(); row++) {
                if (col == j) {
                    y++;
                    continue;
                } else {
                    m2.set_ELMT(row, col, m.get_ELMT(x, y));
                    y++;
                }
            }
            x++;
        }
        // kondisi m2 adalah submatrix yang tidak memiliki elemen baris i dan kolom j
        if ((m2.get_ROW_EFF() == 2) && (m2.get_COL_EFF() == 2)) {
            Cij = determinan2x2(m2) * ((-1) ^ (i + j));
        } else {
            EkspansiKofaktor(m2); // rekursif
        }
        return Cij;
    }

    public double EkspansiKofaktor(Matrix m) {
        OperasiDasarMatrix Operate = new OperasiDasarMatrix();
        if (Operate.isSquare(m)) {
            int n = m.get_COL_EFF();
            int i, j;
            det = 1;
            i = 0;
            for (j = 0; j < n; j++) {
                det += m.get_ELMT(i, j) * Kofaktor(m, i, j); // menambahkan setiap perkalian kofaktor dari baris pertama
                                                             // matriks dengan element terkait untuk mendapatkan
                                                             // determinan.
            }
        }
        return det;
    }

    public Matrix adjoinMatrix(Matrix m) {
        // ubah ke matriks kofaktor
        Matrix mCij = new Matrix();
        OperasiDasarMatrix Operate = new OperasiDasarMatrix();
        Operate.createMatrix(mCij, m.get_ROW_EFF(), m.get_COL_EFF());
        int row, col;
        for (row = 0; row < m.get_ROW_EFF(); row++) {
            for (col = 0; col < m.get_ROW_EFF(); col++) {
                double x = Kofaktor(m, row, col);
                mCij.set_ELMT(row, col, x);
            }
        }
        // transpose matriks kofaktor
        Matrix mAdj = new Matrix();
        Operate.createMatrix(mAdj, m.get_ROW_EFF(), m.get_COL_EFF());
        for (row = 0; row < m.get_ROW_EFF(); row++) {
            for (col = 0; col < m.get_ROW_EFF(); col++) {
                mAdj.set_ELMT(row, col, mCij.get_ELMT(col, row));
            }
        }
        return mAdj;
    }
}
