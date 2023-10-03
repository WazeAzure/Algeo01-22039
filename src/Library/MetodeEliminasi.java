package Library;

public class MetodeEliminasi {
    OperasiDasarMatrix ODM = new OperasiDasarMatrix();

    public static boolean isEselon(Matrix m){
        return true;
    }

    public void swapRows(Matrix m, int a, int b){
        for(int col=0; col < m.get_COL_EFF(); col++){
            double temp = m.get_ELMT(a, col);
            m.set_ELMT(a, col, m.get_ELMT(b, col));
            m.set_ELMT(b, col, temp);
        }
    }

    public void divideRow(Matrix m, int idx, double k){
        for(int j=0; j < m.get_COL_EFF(); j++){
            if(m.get_ELMT(idx, j) != 0){
                m.set_ELMT(idx, j, m.get_ELMT(idx, j) / k);
            }
        }
    }

    public void substractRows(Matrix m, int a, int pivot, double div){
        // System.out.printf("%f\n", div);
        for(int j=0; j < m.get_COL_EFF(); j++){
            double temp = m.get_ELMT(a, j) - (div * m.get_ELMT(pivot, j));
            // System.out.printf("%f - %f = %f\n", m.get_ELMT(a, j),div * m.get_ELMT(pivot, j), temp);
            m.set_ELMT(a, j, temp);
        }
        // System.out.println();
    }

    public void toEselon(Matrix m){
        int i=0, j = 0;
        while(i < m.get_ROW_EFF() && j < m.get_COL_EFF()){
            // check first col
            int pivotIndex = -1;
            for(int row=i; row < m.get_ROW_EFF(); row++){
                if(m.get_ELMT(row, j) != 0){
                    pivotIndex = row;
                    break;
                }
            }

            if(pivotIndex == -1){
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

            for(int row=i+1; row < m.get_ROW_EFF(); row++){
                double div = m.get_ELMT(row, j) / k;
                // System.out.println(div);
                if(m.get_ELMT(row, j) == 0){
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

    public void toEselonRed(Matrix m){
        // System.out.println("Hello world");
        toEselon(m);
        
        for(int i=m.get_ROW_EFF()-1; i>=0; i--){
            // find satu utama
            int foundFirst = -1;
            for(int j=0; j<m.get_COL_EFF(); j++){
                if(m.get_ELMT(i, j) == 1){
                    foundFirst = j;
                    break;
                }
            }
            // System.out.printf("foundFirst -- %d\n", foundFirst);
            if(foundFirst == -1){
                continue;
            }

            // clear to up
            double k = m.get_ELMT(i, foundFirst);
            for(int row=i-1; row>=0; row--){
                // System.out.printf("row foundFirst --- %d %d\n", row, foundFirst);
                double div = m.get_ELMT(row, foundFirst) / k;
                // System.out.println("div -- " + div);
                if(m.get_ELMT(row, foundFirst) != 0){
                    
                    // System.out.printf("i row foundFirst---%d %d %d\n", i, row, foundFirst);
                    substractRows(m, row, i, div);
                    // ODM.displayMatrix(m);
                }
            }
            // ODM.displayMatrix(m);
        }
    }

    public boolean isSegitiga(Matrix m){
        for(int i=0; i<m.get_ROW_EFF(); i++){
                if(m.get_ELMT(i, i) != 1){
                    return false;
                }
            }
        return true;
    }

    public String Gauss(Matrix m){
        // prasyarat matriks harus eselon / eselon terduksi
        // 0 --> not solvable
        // 1 --> unik
        // 2 --> parametrik

        // check if solveable
        if(m.get_ELMT(m.get_ROW_EFF()-1, m.get_COL_EFF()-2) == 0 && m.get_ELMT(m.get_ROW_EFF()-1, m.get_COL_EFF()-1)  != 0){
            return 0;
        } else {
            if(isSegitiga(m)){
                // solusi unik
                m = SolvesSPL(Matrix m);
            } else {
                // solusi parametrik
            }
        }
    }

    public Matrix SolveSPL(Matrix m){

    }
}