package Library;

public class MatriksBalikan {

    /* *** CREATE OBJECTS *** */
    Determinan DET = new Determinan();
    MetodeEliminasi ME = new MetodeEliminasi();
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();

    /* *** HELPER FUNCTIONS *** */

    public boolean isMatrixHaveInv(Matrix m){
        /* Mengecek apakah sebuah matrix memiliki inverse atau tidak */
        return (ODM.determinant(m) != 0);
    }

    public Matrix createIdentityMatrix(int n){
        /* Membuat matrix identitas ukuran n x n dan mengeluarkannya*/
        Matrix m = new Matrix();
        ODM.createMatrix(m, n, n);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i == j){
                    m.set_ELMT(i, j, 1);
                }
                else{
                    m.set_ELMT(i, j, 0);
                }
            }
        }
        return m;
    }

    public Matrix mergeSidetoSideMatrix(Matrix m1, Matrix m2){
        /* Mengembalikan gabungan 2 matrix ukuran sama yang diletakkan bersampingan */
        Matrix m = new Matrix();
        ODM.createMatrix(m, m1.get_ROW_EFF(), 2 * m1.get_COL_EFF());
        for (int i = 0; i < m1.get_ROW_EFF(); i++){
            for (int j = 0; j < m1.get_COL_EFF(); j++){
                m.set_ELMT(i, j, m1.get_ELMT(i, j));
                m.set_ELMT(i, j + m1.get_COL_EFF(), m2.get_ELMT(i, j));
            }
        }
        return m;
    }

    public Matrix cropMatrix(Matrix m){
        /* Memotong matrix ukuran n x 2n menjadi n x n */
        Matrix m1 = new Matrix();
        ODM.createMatrix(m1, m.get_ROW_EFF(), m.get_COL_EFF() / 2);
        for (int i = 0; i < m1.get_ROW_EFF(); i++){
            for (int j = 0; j < m1.get_COL_EFF(); j++){
                m1.set_ELMT(i, j, m.get_ELMT(i, j + m1.get_COL_EFF()));
            }
        }
        return m1;
    }

    public Matrix cropLastColOfMatrix(Matrix m){
        /* Menghapus kolom terakhir dari sebuah matrix */
        Matrix m1 = new Matrix();
        ODM.createMatrix(m1, m.get_ROW_EFF(), m.get_COL_EFF() - 1);
        for (int i = 0; i < m1.get_ROW_EFF(); i++){
            for (int j = 0; j < m1.get_COL_EFF(); j++){
                m1.set_ELMT(i, j, m.get_ELMT(i, j));
            }
        }
        return m1;
    }

    /* *** PRIMARY FUNCTIONS *** */

    public Matrix inverse2x2(Matrix m){
        /* Mengeluarkan inverse dari sebuah matrix ukuran 2 x 2 */
        // [a b]
        // [c d]
        double determinan = DET.determinan2x2(m);
        // Matrix dijadikan
        // [d -b]
        // [-c a]
        double temp = m.get_ELMT(0, 0);
        m.set_ELMT(0, 0, m.get_ELMT(1, 1));
        m.set_ELMT(1, 1, temp);
        m.set_ELMT(0, 1, - m.get_ELMT(0, 1));
        m.set_ELMT(1, 0, - m.get_ELMT(1, 0));
        
        return (ODM.multiplyByConst(m, (1 / determinan)));
    }


    public Matrix inverseWithAdjoin(Matrix m){
        /* Mancari inverse sebuah matrix n x n menggunakan adjoin dan mengeluarkannya */
        double determinan = ODM.determinant(m);
        return (ODM.multiplyByConst(DET.adjoinMatrix(m), (1 / determinan)));
    }    

    public Matrix inverseWithGaussJordan(Matrix m){
        /* Mancari inverse sebuah matrix n x n menggunakan eliminasi Gauss-Jordan dan mengeluarkannya */
        Matrix mIdentity, mergeMatrix, inverse = new Matrix();
        mIdentity = createIdentityMatrix(m.get_ROW_EFF());

        // Buat matrix [m|I]
        mergeMatrix = mergeSidetoSideMatrix(m, mIdentity);

        // Jadikan [I|m^-1]
        ME.toEselonRed(mergeMatrix);

        // Ambil inverse
        inverse = cropMatrix(mergeMatrix);

        for (int j = 0; j < inverse.get_COL_EFF(); j++){
            if (mergeMatrix.get_ELMT(mergeMatrix.get_ROW_EFF() - 1, j) != 0){
                return inverse;
            }
        }
        // Jika ada baris bernilai 0, matrix m tidak punya balikan
        return null;
    }

    public Matrix solveSPLwithInverse(Matrix m){
        /* Mencari solusi SPL Ax = B dengan menggunakan metode matrix balikan */
        Matrix inverse = new Matrix();
        Matrix solution = new Matrix();
        Matrix A = new Matrix();
        Matrix b  = new Matrix();
        
        // Pisah matrix menjadi A dan b
        ODM.createMatrix(A, m.get_ROW_EFF(), m.get_COL_EFF() - 1);
        ODM.createMatrix(b, m.get_ROW_EFF(), 1);
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            for (int j = 0; j < m.get_COL_EFF(); j++){
                if (j < m.get_COL_EFF() - 1){
                    A.set_ELMT(i, j, m.get_ELMT(i, j));
                }
                else{
                    b.set_ELMT(i, 0, m.get_ELMT(i, j));
                }
            }
        }

        // Bebas metode apa
        // inverse = inverseWithAdjoin(m);
        inverse = inverseWithGaussJordan(A);

        // Solusinya adalah x = (A^-1)b
        solution = ODM.multiplyMatrix(inverse, b);
        return solution;
    }
}
