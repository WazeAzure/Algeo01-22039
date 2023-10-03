package Library;

public class MetodeEliminasi {

    OperasiDasarMatrix ODM = new OperasiDasarMatrix();

    /* *** HELPER FUNCTIONS *** */
    public static boolean isRowAllZero(Matrix m, int idxRow){
        /* Mengecek apakah sebuah baris dari suatu matrix seluruh elemennya nol */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            if (m.get_ELMT(idxRow, i) != 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isRowHaveOne(Matrix m, int idxRow){
        /* Mengecek apakah sebuah baris dari suatu matrix memiliki elemen 1 atau tidak */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            if (m.get_ELMT(idxRow, i) == 1){
                return true;
            }
        }
        return false;
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
        for (int i = 0; i < m.get_ROW_EFF(); i++){
            if (m.get_ELMT(i, idxCol) == 0){
                return true;
            }
        }
        return false;
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
        Matrix temp = new Matrix();
        temp = ODM.copyMatrix(m);
        rowTimesConst(temp, idxRow2, k);
        for (int i = 0; i < m.get_COL_EFF(); i++){
            m.set_ELMT(idxRow1, i, temp.get_ELMT(idxRow1, i) + temp.get_ELMT(idxRow2, i));
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
            if (!isRowAllZero(m, i)){
                return i;
            }
        }
        return -1;
    }

    public int lastIdxDiagonalNot0(Matrix m){
        /* Mengeluarkan index baris terakhir yang elemen m[i][i] nya bukan 0 */
        for (int i = -1; i < m.get_ROW_EFF() - 1; i++){
            if (m.get_ELMT(i + 1, i + 1) == 0){
                return i;
            }
        }
        return (m.get_ROW_EFF() - 1);
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

        System.out.println(idx + " | " + idxRow);
        while (m.get_ELMT(idx, idxRow) == 0 && idx < m.get_ROW_EFF() - 1){
            idx++;
            if (idx == m.get_ROW_EFF() - 1 && m.get_ELMT(idx, idxRow) == 0){
                idx = idxRow;
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

    public int idxFirstNot0inRow(Matrix m, int idxRow){
        /* Mengeluarkan index elemen bukan 0 pertama di sebuah baris */
        for (int i = 0; i < m.get_COL_EFF(); i++){
            if (m.get_ELMT(idxRow, i) != 0){
                return i;
            }
        }
        return -1;
    }

    public void atur1Utama(Matrix m){
        /* Mengatur agar 1 utama pada baris yang lebih rendah berada lebih kanan dari 1 utama baris atasnya */
        for (int i = lastIdxDiagonalNot0(m) + 1; i <= lastIdxNotRow0(m) - 1; i++){
            for (int j = i + 1; j <= lastIdxNotRow0(m); j++){
                if (!isRowAllZero(m, i)){
                    rowTimesConst(m, i, 1 / m.get_ELMT(i, idxFirstNot0inRow(m, i)));
                }
            }
        }
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
            if (i < m.get_COL_EFF()){
                if (m.get_ELMT(i, i) == 0){
                    moveRowDiagonal0toBottom(m, i);
                }
            }
        } 

        for (int i = 0; i <= lastIdxDiagonalNot0(m); i++){
            // Menjadikan m[i][i] = 1
            // ODM.displayMatrix(m);
            elmtTo1(m, i);
            
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
                            while (idxRow <= lastIdxNotRow0(m) && m.get_ELMT(idxRow, i) == 0){
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
                            while (idxRow <= lastIdxNotRow0(m) && !(isAllBeforeIdxis0(m, idxRow, i) && m.get_ELMT(idxRow, i) != 0)){
                                idxRow++;
                                if (isAllBeforeIdxis0(m, idxRow, i) && m.get_ELMT(idxRow, i) != 0){
                                    k = - m.get_ELMT(j, i) / m.get_ELMT(idxRow, i);
                                } 
                            }
                        }
                    }
                    // System.out.println("-------------------before-------------");
                    // ODM.displayMatrix(m);
                    addMultiplyOfOtherRow(m, j, idxRow, k);
                    // System.out.println("-------------------After-------------");
                    // ODM.displayMatrix(m);
                }
            }
            // System.out.println(i);
            // ODM.displayMatrix(m);
            // System.out.println("ini nilai idx diagonal -- " + lastIdxDiagonalNot0(m));
        }
        // ODM.displayMatrix(m);

        // Membuat tiap baris selain baris 0 mempunyai 1 utama
        if (lastIdxDiagonalNot0(m) != lastIdxNotRow0(m)){
            for (int i = lastIdxDiagonalNot0(m) + 1; i <= lastIdxNotRow0(m); i++){
                rowTimesConst(m, i, 1 / m.get_ELMT(i, idxFirstNot0inRow(m, i)));
            }
        }

        // Atur 1 utama di baris-baris setelah index baris lastIdxDiagonalNot0(m)

    }

    public void toEselonRed(Matrix m){
        /* Melakukan OBE terhadap suatu matrix augmented hingga terbentuk matrix eselon baris tereduksi */

        // Steps:
        // 1. Jadikan ke bentuk matrix eselon baris
        // 2. Lakukan operasi addMultiplyOfOtherRow hingga nilai m[j-1][j] sampai m[0][j] = 0
        
        toEselon(m);
        if (lastIdxDiagonalNot0(m) > 0){
            for (int j = lastIdxDiagonalNot0(m); j >= 1; j--){
                for (int i = j - 1; i >= 0; i--){
                    if (m.get_ELMT(i, j) != 0){
                        double k = - m.get_ELMT(i, j) / m.get_ELMT(j, j);
                        addMultiplyOfOtherRow(m, i, j, k);
                    }
                }
            }
        }

        System.out.println("-------------------Eselon Red -------------");
        ODM.displayMatrix(m);

        // Setiap elemen di kolom yang memuat 1 utama dijadikan 0
        if (lastIdxDiagonalNot0(m) != lastIdxNotRow0(m)){
            int idxCol = lastIdxNotRow0(m);
            for (int i = lastIdxDiagonalNot0(m) + 1; i <= idxCol; i++){
                if (!isRowAllZero(m, i)){
                    rowTimesConst(m, i, 1 / m.get_ELMT(i, idxFirstNot0inRow(m, i)));
                    for (int j = 0; j <= idxCol; j++){
                        if (j != i){
                            addMultiplyOfOtherRow(m, j, i, - m.get_ELMT(j, idxSatuUtama(m, i)));
                        }
                    }
                    // for (int k = lastIdxDiagonalNot0(m) + 1; k <= lastIdxNotRow0(m); i++){
                    //     rowTimesConst(m, k, 1 / m.get_ELMT(k, idxFirstNot0inRow(m, k)));
                    // }
                }
            }
        }
        
        // Memindahkan lagi semua baris nol yang mungkin terbentuk selama operasi ke bawah
        if (isMatrixHaveRow0(m)){
            // Pindahkan semua baris nol ke bagian bawah
            for (int i = lastIdxDiagonalNot0(m) + 1; i < idxCol; i++){
                if (isRowAllZero(m, i)){
                    moveRow0toBottom(m, i);
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

    /*
    NOTES:
    - Kalau baris terakhir adalah baris 0, solusi banyak (dalam berntuk parameter)
    - Kalau baris terakhir 0 semua selain elemen terakhir, solusi tidak ada
    - Kalau toEselon berbentuk segitiga sampai baris terakhir, solusi unik
    - isGaussUnik (bentuk segitiga), isGaussJordanUnik (identity)
    */
}