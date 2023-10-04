package Library;

public class MetodeEliminasi {
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();

    public static boolean isEselon(Matrix m) {
        return true;
    }

    public void swapRows(Matrix m, int a, int b) {
        for (int col = 0; col < m.get_COL_EFF(); col++) {
            double temp = m.get_ELMT(a, col);
            m.set_ELMT(a, col, m.get_ELMT(b, col));
            m.set_ELMT(b, col, temp);
        }
    }

    public void divideRow(Matrix m, int idx, double k) {
        for (int j = 0; j < m.get_COL_EFF(); j++) {
            if (m.get_ELMT(idx, j) != 0) {
                m.set_ELMT(idx, j, m.get_ELMT(idx, j) / k);
            }
        }
    }

    public void substractRows(Matrix m, int a, int pivot, double div) {
        // System.out.printf("%f\n", div);
        for (int j = 0; j < m.get_COL_EFF(); j++) {
            double temp = m.get_ELMT(a, j) - (div * m.get_ELMT(pivot, j));
            // System.out.printf("%f - %f = %f\n", m.get_ELMT(a, j),div * m.get_ELMT(pivot,
            // j), temp);
            m.set_ELMT(a, j, temp);
        }
        // System.out.println();
    }

    public void toEselon(Matrix m) {
        int i = 0, j = 0;
        while (i < m.get_ROW_EFF() && j < m.get_COL_EFF()) {
            // check first col
            int pivotIndex = -1;
            for (int row = i; row < m.get_ROW_EFF(); row++) {
                if (m.get_ELMT(row, j) != 0) {
                    pivotIndex = row;
                    break;
                }
            }

            if (pivotIndex == -1) {
                j++;

                // System.out.printf("dalam --------- %d %d\n", i, j);
                // ODM.displayMatrix(m);
                // System.out.printf("\n");
                continue;
            }

            // swap with first row;
            swapRows(m, pivotIndex, i);

            // make all rows in column 0;

            divideRow(m, i, m.get_ELMT(i, j));
            // ODM.displayMatrix(m);
            double k = m.get_ELMT(i, j);
            // System.out.printf("Nilai k -- %f\n", k);

            for (int row = i + 1; row < m.get_ROW_EFF(); row++) {
                double div = m.get_ELMT(row, j) / k;
                // System.out.println(div);
                if (m.get_ELMT(row, j) == 0) {
                    continue;
                }

                substractRows(m, row, i, div);
            }

            // System.out.printf("--------- %d %d\n", i, j);
            // ODM.displayMatrix(m);
            // System.out.printf("\n");

            i++;
            j++;
        }
    }

    public void toEselonRed(Matrix m) {
        // System.out.println("Hello world");
        toEselon(m);

        for (int i = m.get_ROW_EFF() - 1; i >= 0; i--) {
            // find satu utama
            int foundFirst = -1;
            for (int j = 0; j < m.get_COL_EFF(); j++) {
                if (m.get_ELMT(i, j) == 1) {
                    foundFirst = j;
                    break;
                }
            }
            // System.out.printf("foundFirst -- %d\n", foundFirst);
            if (foundFirst == -1) {
                continue;
            }

            // clear to up
            double k = m.get_ELMT(i, foundFirst);
            for (int row = i - 1; row >= 0; row--) {
                // System.out.printf("row foundFirst --- %d %d\n", row, foundFirst);
                double div = m.get_ELMT(row, foundFirst) / k;
                // System.out.println("div -- " + div);
                if (m.get_ELMT(row, foundFirst) != 0) {

                    // System.out.printf("i row foundFirst---%d %d %d\n", i, row, foundFirst);
                    substractRows(m, row, i, div);
                    // ODM.displayMatrix(m);
                }
            }
            ODM.displayMatrix(m);
        }
    }

    public boolean isSegitiga(Matrix m) {
        for (int i = 0; i < m.get_ROW_EFF(); i++) {
            if (m.get_ELMT(i, i) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isUnik(Matrix m) {
        if (m.get_COL_EFF() - 1 == m.get_ROW_EFF()) {
            return true;
        }
        return false;
    }

    public boolean isSolveable(Matrix m) {
        // boolean isSolve = true;
        for (int i = 0; i < m.get_ROW_EFF(); i++) {
            boolean isRow = false;
            for (int j = 0; j < m.get_COL_EFF() - 1; j++) {
                if (m.get_ELMT(i, j) != 0) {
                    isRow = true;
                    break;
                }
            }

            if (!isRow && m.get_ELMT(i, m.get_COL_EFF()) != 0) {
                return false;
            }

        }
        return true;
    }

    public int Gauss(Matrix m) {
        // prasyarat matriks harus eselon / eselon terduksi
        // 0 --> not solvable
        // 1 --> unik
        // 2 --> parametrik

        // check if solveable
        if (isSolveable(m)) {
            if (isSegitiga(m) && m.get_COL_EFF() - 1 == m.get_ROW_EFF()) {
                System.out.println("Solusi Unik");
                // solusi uniks
                return 1;
            } else {
                System.out.println("Solusi Parametrik");
                // solusi parametrik
                SolvesSPLParametrik(m);
                return 2;
            }
        } else {
            System.out.println("Tidak Memiliki Solusi");
            return 0;
        }
    }

    public void SolvesSPLParametrik(Matrix m){
        // input berupa matrix augmented hasil gauss / gauss jordan.

        Matrix koefisien = new Matrix();
        ODM.createMatrix(koefisien, m.get_COL_EFF() - 1, m.get_COL_EFF() - 1);

        double hasil[] = new double[m.get_COL_EFF()-1]; // simpan koefisien x1 x2 x3 ...
        String solusi[] = new String[m.get_COL_EFF()-1];

        for (int i = 0 ; i < m.get_COL_EFF()-1 ; i++) {
            solusi[i] = "" ;
        }
        // Double solusi2[] = new Double[m.get_COL_EFF() - 1];
        
        // for loop dari bawah
        for(int i=m.get_ROW_EFF()-1; i>= 0; i--){
            // find pivot
            int pivotIdx = -1;
            for(int j=0; j<m.get_COL_EFF()-2; j++){
                if(m.get_ELMT(i, j) != 0){
                    pivotIdx = j;
                    break;
                }
            }

            if(pivotIdx == -1){
                continue;
            }

            // cek kanannya dari 1.
            for(int j=m.get_COL_EFF()-2; j>pivotIdx; j--){
                boolean all0 = true;
                for(int row = i; row <= m.get_ROW_EFF()-1; row++){
                    if(row != i){
                        if(m.get_ELMT(row, j) != 0){
                            all0 = false;
                            // break;
                        }
                    }
                    
                }

                if(all0){
                    // check if it is exist in solution
                    solusi[j] = "X" + Integer.toString(j+1);
                    koefisien.set_ELMT(j, j, 1);
                }
            }

            // urus 1 utama
            hasil[pivotIdx] = m.get_ELMT(i, m.get_COL_EFF()-1) / m.get_ELMT(i, pivotIdx);
            for(int col=pivotIdx+1; col < m.get_COL_EFF()-1; col++){
                hasil[pivotIdx] -= m.get_ELMT(i, col) * hasil[col];
                for(int j=0; j < m.get_COL_EFF()-1; j++){
                    if(koefisien.get_ELMT(col, j) != 0){
                        koefisien.set_ELMT(pivotIdx, j, koefisien.get_ELMT(pivotIdx, j) + -koefisien.get_ELMT(col, j) * m.get_ELMT(i, col));
                    }
                }
            }

            // masukin solusi dari pivot
            if(hasil[pivotIdx] != 0){
                solusi[pivotIdx] += Double.toString(hasil[pivotIdx]);
            } 
            for(int col=0; col < m.get_COL_EFF()-1; col++){
                if(koefisien.get_ELMT(pivotIdx, col) != 0){
                    if(koefisien.get_ELMT(pivotIdx, col) > 0){
                        if(solusi[pivotIdx] == ""){
                            if(koefisien.get_ELMT(pivotIdx, col) == 1){
                                solusi[pivotIdx] += "X" + Integer.toString(col + 1);
                            } else {
                                solusi[pivotIdx] += Double.toString(koefisien.get_ELMT(pivotIdx, col)) + " X" + Integer.toString(col + 1);
                            }
                        } else {
                            if(koefisien.get_ELMT(pivotIdx, col) == 1){
                                solusi[pivotIdx] += " + " + "X" + Integer.toString(col + 1);
                            } else {
                                solusi[pivotIdx] += " + " + Double.toString(koefisien.get_ELMT(pivotIdx, col)) + " X" + Integer.toString(col + 1);
                            }
                        }
                    } else {
                        if(solusi[pivotIdx] == ""){
                            if(koefisien.get_ELMT(pivotIdx, col) == 1){
                                solusi[pivotIdx] += "X" + Integer.toString(col+1);
                            } else {
                                solusi[pivotIdx] += Double.toString(Math.abs(koefisien.get_ELMT(pivotIdx, col))) + " X" + Integer.toString(col + 1);
                            }
                        } else {
                            if(koefisien.get_ELMT(pivotIdx, col) == 1){
                                solusi[pivotIdx] += " - " + "X" + Integer.toString(col + 1);
                            } else {
                                solusi[pivotIdx] += " - " + Double.toString(Math.abs(koefisien.get_ELMT(pivotIdx, col))) + " X" + Integer.toString(col + 1);
                            }
                        }
                    }
                }
            }
        }
        for (int j = 0 ; j < m.get_COL_EFF()-1 ; j++) {
            if (solusi[j] == "") {
                solusi[j] = "0.0" ;
            }
        }
        for (int j = 0 ; j < m.get_COL_EFF()-1 ; j++) {
            System.out.printf("X%d = " , j+1) ;
            System.out.printf("%s" , solusi[j]) ;
            System.out.println() ;
        }
    }

    public Matrix SolveSPLUnik(Matrix m) {
        Matrix result = new Matrix();
        ODM.createMatrix(result, m.get_ROW_EFF(), 1);

        for (int i = m.get_ROW_EFF() - 1; i >= 0; i--) {
            result.mem[i][0] = m.get_ELMT(i, m.get_COL_EFF() - 1);
            // System.out.println("result mem - " + result.mem[i][0]);
            for (int j = m.get_COL_EFF() - 2; j > i; j--) {
                result.mem[i][0] -= (m.get_ELMT(i, j) * result.get_ELMT(j, 0));
            }
            result.mem[i][0] = result.mem[i][0] / m.get_ELMT(i, i);
            // System.out.println("====================");
            // ODM.displayMatrix(m);
            // ODM.displayMatrix(result);
        }
        return result;
    }

    public Matrix SolvesSPLUnikRed(Matrix m) {
        Matrix result = new Matrix();
        ODM.createMatrix(result, m.get_ROW_EFF(), 1);

        // copy right most element
        for (int i = 0; i < m.get_ROW_EFF(); i++) {
            result.set_ELMT(i, 0, m.get_ELMT(i, m.get_COL_EFF() - 1));
        }
        return result;
    }

    public double toSegitiga(Matrix m){
        int i = 0, j = 0;
        double mul = 1;
        while (i < m.get_ROW_EFF() && j < m.get_COL_EFF()) {
            // check first col
            int pivotIndex = -1;
            for (int row = i; row < m.get_ROW_EFF(); row++) {
                if (m.get_ELMT(row, j) != 0) {
                    pivotIndex = row;
                    break;
                }
            }

            if (pivotIndex == -1) {
                j++;

                // System.out.printf("dalam --------- %d %d\n", i, j);
                // ODM.displayMatrix(m);
                // System.out.printf("\n");
                continue;
            }

            // swap with first row;
            if(pivotIndex != i){
                mul *= -1;
            }
            swapRows(m, pivotIndex, i);

            // make all rows in column 0;
            mul *= m.get_ELMT(i, j);
            divideRow(m, i, m.get_ELMT(i, j));
            // ODM.displayMatrix(m);
            double k = m.get_ELMT(i, j);
            
            // System.out.printf("Nilai k -- %f\n", k);

            for (int row = i + 1; row < m.get_ROW_EFF(); row++) {
                double div = m.get_ELMT(row, j) / k;
                // System.out.println(div);
                if (m.get_ELMT(row, j) == 0) {
                    continue;
                }

                substractRows(m, row, i, div);
            }

            // System.out.printf("--------- %d %d\n", i, j);
            // ODM.displayMatrix(m);
            // System.out.printf("\n");

            i++;
            j++;
        }
        return mul;
    }
}