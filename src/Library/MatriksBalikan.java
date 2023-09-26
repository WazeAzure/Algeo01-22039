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
        /* Mengembalikan gabungan 2 matrix yang diletakkan bersampingan */
        Matrix m = new Matrix();
        ODM.createMatrix(m, (m1.get_ROW_EFF() + m2.get_ROW_EFF()), (m1.get_COL_EFF() + m2.get_COL_EFF()));
        for (int i = 0; i < m1.get_ROW_EFF(); i++){
            for (int j = 0; j < m1.get_COL_EFF(); j++){
                m.set_ELMT(i, j, m1.get_ELMT(i, j));
                m.set_ELMT(i + m1.get_ROW_EFF(), j, m2.get_ELMT(i, j));
            }
        }
        return m;
    }

    public Matrix toMatrixIdentity(Matrix m, Matrix m1){
        /* Gatau masih bingung fungsi ini perlu dibuat 
        atau langsung di inverseWithGaussJordan aja */
        return m; // nanti hapus
    }

    /* *** PRIMARY FUNCTIONS *** */

    public Matrix inverse2x2(Matrix m){
        /* Mengeluarkan inverse dari sebuah matrix ukuran 2 x 2 */
        float determinan = ODM.determinant(m);
        return (ODM.multiplyByConst(m, (1 / determinan)));
    }

    public Matrix inverseWithAdjoin(Matrix m){
        /* Mancari inverse sebuah matrix n x n menggunakan adjoin dan mengeluarkannya */
        float determinan = ODM.determinant(m);
        return (ODM.multiplyByConst(DET.adjoinMatrix(m), (1 / determinan)));
    }    

    public Matrix inverseWithGaussJordan(Matrix m){
        /* Mancari inverse sebuah matrix n x n menggunakan eliminasi Gauss-Jordan dan mengeluarkannya */
        Matrix mIdentity, mergeMatrix = new Matrix();
        mIdentity = createIdentityMatrix(m.get_ROW_EFF());
        mergeMatrix = mergeSidetoSideMatrix(m, mIdentity);
        // Belum selesai
        // if ada baris bernilai 0, matrix tidak punya balikan
        return m; // nanti hapus
    }

    // to-do
    // if A punya balikan, SPL punya solusi unik
    // SPL homogen (perlu?)
    // menghitung inverse dengan eliminasi gauss-jordan
    // penyelesaian SPL dengan inverse

}
