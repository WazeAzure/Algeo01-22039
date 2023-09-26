package Library;
import java.util.Scanner;

public class OperasiDasarMatrix {
    // constructor
    public static void createMatrix(Matrix m, int nRows, int nCols){
        // inisiasi array bernilai 0;
        System.out.println("called");
        m.set_COL_EFF(nCols);
        m.set_ROW_EFF(nRows);
    }

    // selector

    
    // Read & Write
    public static void readMatrix(Matrix m, int nRow, int nCol){

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

    public float determinant(Matrix m){
        if(m.get_ROW_EFF() == 2){
            return (m.get_ELMT(0, 0) * m.get_ELMT(1, 1)) - (m.get_ELMT(0, 1) * m.get_ELMT(1, 0));
        }
        
        float temp = 0;
        int x;
        for(x=0; x< m.get_COL_EFF(); x++){
            Matrix m2 = new Matrix();
            createMatrix(m2, m.get_ROW_EFF()-1, m.get_COL_EFF()-1);

            int r=0, c=0;
            int i, j;
            for(i=0;i<m.get_ROW_EFF();i++){
                for(j=0;j<m.get_COL_EFF();j++){
                    if(i == 0 || j == x){
                        continue;
                    } else {
                        m2.set_ELMT(r, c, m.get_ELMT(i, j));
                        c++;
                        if(c == m2.get_COL_EFF()){
                            c = 0;
                            r++;
                        }
                    }
                }
            }
            float det = (m.get_ELMT(0, x) * determinant(m2));
            if(x%2 == 1) det *= -1;
            temp += det;
        }
        return temp;
    }
}
