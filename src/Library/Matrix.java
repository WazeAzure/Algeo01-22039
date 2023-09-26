package Library;

public class Matrix {

    static final int ROW_CAP = 100;
    static final int COL_CAP = 100;

    // main data
    private int rowEff=0;
    private int colEff=0;
    private float[][] mem = new float[ROW_CAP][COL_CAP];

    // constructor
    public Matrix(){
        for(int i=0; i<ROW_CAP; i++){
            for(int j=0; j<COL_CAP; j++){
                this.mem[i][j] = 0;
            }
        }
    }

    // selector
    int get_ROW_EFF(){
        return this.rowEff;
    }

    int get_COL_EFF(){
        return this.colEff;
    }

    float get_ELMT(int i, int j){
        return this.mem[i][j];
    }

    void set_ROW_EFF(int nRow){
        this.rowEff = nRow;
    }

    void set_COL_EFF(int nCol){
        this.colEff = nCol;
    }

    void set_ELMT(int i, int j, float x){
        this.mem[i][j] = x;
    }

    //

}