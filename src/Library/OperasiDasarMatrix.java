package Library;
import java.util.Scanner;

public class OperasiDasarMatrix {
    // constructor
    public void createMatrix(Matrix m, int nRows, int nCols){
        // inisiasi array bernilai 0;
        System.out.println("called");
        m.set_COL_EFF(nCols);
        m.set_ROW_EFF(nRows);
    }

    // selector

    
    // Read & Write
    public void readMatrix(Matrix m, int nRow, int nCol){

        // define for scanner. Di java untuk input harus pake scanner.
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<nRow; i++){
            for(int j=0;j<nCol;j++){
                m.set_ELMT(i, j, sc.nextInt());
            }
        }
    }

    public void displayMatrix(Matrix m){
        for(int i=0; i<m.get_ROW_EFF(); i++){
            for(int j=0; j<m.get_COL_EFF()-1; j++){
                System.out.printf("%d ", m.get_ELMT(i, j));
            }
            System.out.printf("%d\n", m.get_ELMT(i, m.get_COL_EFF()-1));
        }
    }

    
}
