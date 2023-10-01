package Library;
// import Library.Matrix;

public class MetodeEliminasi {
    /* *** HELPER FUNCTIONS *** */
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

    public void rowTimesConst(Matrix m, int idxRow, double k){
        /* Mengalikan sebuah baris dengan konstanta tidak nol */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            m.set_ELMT(idxRow, i, m.get_ELMT(idxRow, i) * k);
        }
    }

    public void swapRows(Matrix m, int idxRow1, int idxRow2){
        /* Menukar dua buah baris di suatu matrix */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            double temp = m.get_ELMT(idxRow1, i);
            m.set_ELMT(idxRow1, i, m.get_ELMT(idxRow2, i));
            m.set_ELMT(idxRow2, i, temp);
        }
    }

    public void addMultiplyOfOtherRow(Matrix m, int idxRow1, int idxRow2, double k){
        /* Menambahkan sebuah baris dengan kelipatan baris lainnnya */
        // Row1 + k(Row2)
        rowTimesConst(m, idxRow2, k);
        for (int i = 0; i < m.get_COL_EFF(); i++){
            m.set_ELMT(idxRow1, i, m.get_ELMT(idxRow1, i) + m.get_ELMT(idxRow2, i));
        }
    }

    public void elmtTo1(Matrix m, int idxRow){
        /* Melakukan OBE untuk mencapai m[idxRow][idxRow] = 1 */
        rowTimesConst(m, idxRow, 1 / m.get_ELMT(idxRow, idxRow));
    }

    public boolean isMatrixHaveRow0(Matrix m){
        /* Mengecek apakah matrix memiliki baris yang seluruh elemennya nol */
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            if (isRowAllZero(m, i)){
                return true;
            }
        }
        return false;
    }

    public int lastIdxNotRow0(Matrix m){
        /* Mengeluarkan index baris terakhir yang bukan baris nol */
        for (int i = m.get_ROW_EFF() - 1; i >= 0; i--){
            if (isRowAllZero(m, i)){
                return i;
            }
        }
        return -1;
    }

    public int lastIdxDiagonalNot0(Matrix m){
        /* Mengeluarkan index baris terakhir yang elemen m[i][i] nya bukan 0 */
        for (int i = m.get_ROW_EFF() - 1; i >= 0; i--){
            if (m.get_ELMT(i, i) != 0){
                return i;
            }
        }
        return -1;
    }

    public void moveRow0toBottom(Matrix m, int idxRow){
        /* Memindahkan sebuah baris nol ke bagian bawah matrix */
        if (idxRow < lastIdxNotRow0(m)){
            swapRows(m, idxRow, lastIdxNotRow0(m));
        }
    }

    public void moveRowDiagonal0toBottom(Matrix m, int idxRow){
        /* Memindahkan sebuah baris yang elemen m[i][i] = 0 ke bagian bawah matriks */
        int idx = idxRow;
        for (int i = idxRow + 1; i <= lastIdxNotRow0(m); i++){
            if (m.get_ELMT(i, idxRow) != 0){
                idx = i;   
            }
        }
        swapRows(m, idxRow, idx);
    }

    public boolean isAllBeforeIdxis0(Matrix m, int idxRow, int i){
        /* Mengecek apakah semua elemen sebuah baris sebelum m[idxRow][i] adalah 0 */
        for (int j = 0; j < i; j++){
            if (m.get_ELMT(idxRow, j) != 0){
                return false;
            }
        }
        return true;
    }

    /* *** PRIMARY FUNCTIONS *** */

    public void toEselon(Matrix m){
        /* Melakukan OBE terhadap suatu matrix augmented hingga terbentuk matrix eselon baris */
        
        // Steps:
        // 1. Jika ada baris nol, pindahkan ke bagian bawah matrix
        // 2. Jika di suatu baris M[i][i] = 0, tukar baris tersebut dengan baris lebih bawah yang M[i][i] nya bukan 0
        // 3. Di tiap baris, cek M[i][i] != 0, lalu jadikan M[i][i] = 1, dan M[i+1][i] sampai M[lastIdxNotRow0(m)][i] = 0
        // 4. Ulangi step 3 untuk setiap kolom sampai idx kolom = lastIdxNotRow0(m)
        // 5. Udah sih kayaknya

        // Cek apakah matrix punya baris 0
        if (isMatrixHaveRow0(m)){
            // Pindahkan semua baris nol ke bagian bawah
            for (int i = 0; i < m.get_ROW_EFF(); i++){
                if (isRowAllZero(m, i)){
                    moveRow0toBottom(m, i);
                }
            }
        }

        // Pindahkan baris yang elemen m[i][i] = 0 ke bawah
        for (int i = 0; i <= lastIdxNotRow0(m); i++){
            if (m.get_ELMT(i, i) == 0){
                moveRowDiagonal0toBottom(m, i);
            }
        }

        for (int i = 0; i <= lastIdxNotRow0(m); i++){
            // Menjadikan m[i][i] = 1
            if (i <= lastIdxDiagonalNot0(m)){
                rowTimesConst(m, i, 1 / m.get_ELMT(i, i));
            }
            // Menjadikan m[i+1][i] sampai M[lastIdxNotRow0(m)][i] = 0
            for (int j = i + 1; j <= lastIdxNotRow0(m); j++){
                if (m.get_ELMT(j, i) != 0){
                    // Mencari baris untuk melakukan operasi addMultiplyOfOtherRow(m, j, idxRow, k)
                    // Cari yang elmt(row, 0) sampai elmt(row, i-1) = 0 dan elmt(row, i) != 0
                    int idxRow = 0;
                    double k = 0;
                    if (i == 0){
                        if (m.get_ELMT(idxRow, i) != 0){
                            k = - m.get_ELMT(j, i) / m.get_ELMT(idxRow, i);
                        }
                        else{
                            while (idxRow < m.get_ROW_EFF() && m.get_ELMT(idxRow, i) == 0){
                                idxRow++;
                                if (m.get_ELMT(idxRow, i) != 0){
                                    k = - m.get_ELMT(j, i) / m.get_ELMT(idxRow, i);
                                }
                            }
                        }
                    }
                    else{
                        if (isAllBeforeIdxis0(m, idxRow, i) && m.get_ELMT(idxRow, i) != 0){
                            k = - m.get_ELMT(j, i) / m.get_ELMT(idxRow, i);
                        } 
                        else{
                            while (idxRow < m.get_ROW_EFF() && !(isAllBeforeIdxis0(m, idxRow, i) && m.get_ELMT(idxRow, i) != 0)){
                                idxRow++;
                                if (isAllBeforeIdxis0(m, idxRow, i) && m.get_ELMT(idxRow, i) != 0){
                                    k = - m.get_ELMT(j, i) / m.get_ELMT(idxRow, i);
                                } 
                            }
                        }
                    }
                    addMultiplyOfOtherRow(m, j, idxRow, k);
                }
            }
        }
    }

    public void toEselonRed(Matrix m){
        /* Melakukan OBE terhadap suatu matrix augmented hingga terbentuk matrix eselon baris tereduksi */

        // Steps:
        // 1. Jadikan ke bentuk matrix eselon baris
        // 2. Lakukan operasi addMultiplyOfOtherRow hingga nilai m[j-1][j] sampai m[0][j] = 0
        
        toEselon(m);
        for (int j = lastIdxDiagonalNot0(m); j >= 1; j--){
            for (int i = j - 1; i >= 0; i--){
                if (m.get_ELMT(i, j) != 0){
                    double k = - m.get_ELMT(i, j) / m.get_ELMT(j, j);
                    addMultiplyOfOtherRow(m, i, j, k);
                }
            }
        }
    }

    public Matrix Gauss(Matrix m){
        /* Menghasilkan solusi dari SPL dengan Metode Eliminasi Gauss */
        return m;
    }

    public Matrix GaussJordan(Matrix m){
        /* Menghasilkan solusi dari SPL dengan Metode Eliminasi Gauss-Jordan */
        return m;
    }
}