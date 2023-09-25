package Library;
// import ...

public class MetodeEliminasi {
    /* *** Helper Functions *** */
    public static boolean isRowAllZero(Matrix m, int idxRow){
        /* Mengecek apakah sebuah baris dari suatu matrix seluruh elemennya nol */
        boolean all0 = true;
        for (int i = 0; i < m.get_COL_EFF(); i++){
            if (m.get_ELMT(idxRow, i) != 0){
                all0 = false;
            }
        }
        return all0;
    }

    public static boolean isRowHaveOne(Matrix m, int idxRow){
        /* Mengecek apakah sebuah baris dari suatu matrix memiliki elemen 1 atau tidak */
        boolean have1 = false;
        for (int i = 0; i < m.get_COL_EFF(); i++){
            if (m.get_ELMT(idxRow, i) == 1){
                have1 = true;
            }
        }
        return have1;
    }

    public static int idxSatuUtama(Matrix m, int idxRow){
        /* Mengeluarkan index kolom dari 1 utama suatu baris di matrix */
        int i = 0;
        while (i < m.get_COL_EFF()){
            if (m.get_ELMT(idxRow, i) == 1){
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean isOneMoreRight(Matrix m, int idxRow){
        /* Mengecek apakah 1 utama di suatu baris lebih kanan dibanding 1 utama di baris atasnya */
        return (idxSatuUtama(m, idxRow) > idxSatuUtama(m, idxRow - 1));
    }

    public static boolean isColHaveZero(Matrix m, int idxCol){
        /* Mengecek apakah suatu kolom memiliki elemen 0 */
        boolean have0 = false;
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            if (m.get_ELMT(i, idxCol) == 0){
                have0 = true;
            }
        }
        return have0;
    }

    public static boolean isEselon(Matrix m){
        /* Mengecek apakah sebuah matrix merupakan matrix eselon atau bukan */
        boolean isEselon = true;

        // Cek apakah jika sebuah baris tidak terdiri dari seluruhnya nol, maka terdapat elemen 1
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            if (!isRowAllZero(m, i) && !isRowHaveOne(m, i)){
                isEselon = false;
            }
        }

        if (isEselon){
            int countAll0Row = 0;
            for (int i = 0; i < m.get_ROW_EFF(); i++){
                if (isRowAllZero(m, i)){
                    countAll0Row += 0;
                }
            }

            // Cek apakah semua baris yang seluruh elemennya nol berkumpul di bagian bawah matrix
            for (int i = m.get_ROW_EFF() - 1; i >= m.get_ROW_EFF() - countAll0Row; i++){
                if (!isRowAllZero(m, i)){
                    isEselon = false;
                }
            }

            if (isEselon){
                // Cek apakah semua dua baris berurutan yang tidak seluruhnya nol memenuhi
                // 1 utama pada baris lebih rendah terletak lebih kanan daripada 1 utama baris lebih tinggi
                for (int i = m.get_ROW_EFF() - countAll0Row - 1; i >= 1; i--){
                    if(!isOneMoreRight(m, i)){
                        isEselon = false;
                    }
                } 
            }
        }
        
        return isEselon;
    }

    public static boolean isEselonRed(Matrix m){
        /* Mengecek apakah sebuah matrix merupakan matrix eselon tereduksi atau bukan */
        boolean isEselonRed = true;
        if (isEselon(m)){
            // Cek apakah setiap kolom yang memiliki 1 utama memiliki nol di tempat lain
            for (int i = 0; i < m.get_ROW_EFF(); i++){
                for (int j = 0; j < m.get_COL_EFF(); j++){
                    if (idxSatuUtama(m, i) == j){
                        if (!isColHaveZero(m, j)){
                            isEselonRed = false;
                        }
                    }
                }
            }    
        }
        else{ // Bukan matriks eselon
            isEselonRed = false;
        }
        return isEselonRed;
    }

    public static void rowTimesConst(Matrix m, int idxRow, int k){
        /* Mengalikan sebuah baris dengan konstanta tidak nol */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            m[idxRow][i] *= k;
        }
    }

    public static void swapRows(Matrix m, int idxRow1, int idxRow2){
        /* Menukar dua buah baris di suatu matrix */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            int temp = m[idxRow1][i];
            m[idxRow1][i] = m[idxRow2][i];
            m[idxRow2][i] = temp;
        }
    }

    public static void addMultiplyOfOtherRow(Matrix m, int idxRow1, int idxRow2, int k){
        /* Menambahkan sebuah baris dengan kelipatan baris lainnnya */
        // Row1 + k(Row2)
        rowTimesConst(m, idxRow2, k);
        for (int i = 0; i < m.get_COL_EFF(); i++){
            m[idxRow1][i] += m[idxRow2][i];
        }
    }

    public static void OBE(Matrix m){
        /* Melakukan Operasi Baris Elementer terhadap suatu matriks augmented */
    }

    
}
