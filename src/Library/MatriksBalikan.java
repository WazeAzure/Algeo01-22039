package Library;
import Library.Matrix;
import Library.Determinan;
import Library.MetodeEliminasi;



public class MatriksBalikan {

    /* *** CREATE OBJECTS *** */
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();
    Determinan DET = new Determinan();
    MetodeEliminasi ME = new MetodeEliminasi();

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
                m1.set_ELMT(i, j, m.get_ELMT(i, j + m.get_COL_EFF()));
            }
        }
        return m1;
    }

    /* *** PRIMARY FUNCTIONS *** */

    public Matrix inverse2x2(Matrix m){
        /* Mengeluarkan inverse dari sebuah matrix ukuran 2 x 2 */
        double determinan = ODM.determinant(m);
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

        for (int i = 0; i < m.get_COL_EFF(); i++){
            if (m.get_ELMT(m.get_ROW_EFF() - 1, i) != 0){
                // Jika tidak ada baris bernilai 0, matrix m punya balikan
                inverse = cropMatrix(mergeMatrix);
                return inverse;
            }
        }
        // Jika ada baris bernilai 0, matrix m tidak punya balikan
        return null;
    }

    public Matrix solveSPLwithInverse(Matrix A, Matrix b){
        /* Mencari solusi SPL Ax = B dengan menggunakan metode matrix balikan */
        Matrix inverse, solution = new Matrix();
        // Bebas metode apa
        // inverse = inverseWithAdjoin(m);
        inverse = inverseWithGaussJordan(A);

        // Solusinya adalah x = (A^-1)b
        solution = ODM.multiplyMatrix(inverse, b);
        return solution;
    }
}
