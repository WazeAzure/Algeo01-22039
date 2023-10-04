// package PersoalanSPL;

// import java.lang.Math;
// import Library.Matrix;

// public class Gauss {
//     public static double rounding(double n){
//         double x = Math.pow(10, -8), y = Math.abs(n)-Math.abs(n%1);
//         if (y==0 && (Math.abs(n % 1) < x || Math.abs(1-(n%1)) < x)){
//             n = 0;
//         } else if (Math.abs(n % 1) < x){
//             if (n<0){
//                 n += (n%1);
//             } else{
//                 n -= (n%1);
//             }
//         } else if (Math.abs(1-(n%1)) < x){
//             if (n<0){
//                 n -= (1-(n%1));
//             } else{
//                 n += (1-(n%1));
//             }
//         }
//         return n;
//     }

//     public static Matrix add(Matrix n, int f, int s, double x){
//         int i, col = n.get_COL_EFF();
//         for (i=0;i<col;i++){
//             n.mem[f][i] = rounding(n.mem[f][i] += n.mem[s][i]*x);
//         }
//         return n;
//     }

//     public static Matrix swap(Matrix n, int r_1, int r_2){
//         double[] f = n.mem[r_1], s = n.mem[r_2];
//         n.mem[r_2] = f;
//         n.mem[r_1] = s;
//         return n;
//     }

//     public static Matrix multi(Matrix n, int row, double x){
//         int i;
//         for(i = 0; i < n.get_COL_EFF();i++){
//             n.mem[row][i] = rounding(n.mem[row][i] *= x);
//         }
//         return n;
//     }

//     public static void first(Matrix n, int r_start, int col){
//         int i;
//         boolean end = false;
//         while (end == false){
//             for(i=r_start;i< n.get_ROW_EFF();i++){
//                 if (n.get_ELMT(i, col) != 0){
//                     swap(n, r_start, i);
//                     end = true;
//                 }
//             }
//             end = true;
//         }
//     }

//     public static Matrix eliminasiGauss(Matrix n, boolean x){
//         int row = 0, col = 0, r_now, i, max_r = n.get_ROW_EFF(), max_c=n.get_COL_EFF();
//         double s;
//         if (x == true){
//             max_c -= 1;
//         }
//         while(row < max_r && col < max_c){
//             r_now = row;
//             first(n, row, col);
//             s = n.get_ELMT(r_now, col);
//             if (s != 0){
//                 multi(n, r_now, 1/s);
//             }
//             for (i = r_now + 1; i < n.get_ROW_EFF(); i++){
//                 add(n, i, r_now, -n.mem[i][col]);
//             }
//             row++;
//             col++;
//         }
//         return n;
//     }

//     // public static void main(String[] args){
//     //     Matrix m1 = new Matrix(0, 0);
//     //     m1.inputRowCol();
//     //     m1.readMatrix();
//     //     eliminasiGauss(m1, true);
//     //     m1.displayMatrix();
//     // }
// }

// public static void bikubik(){
//         boolean file = true;
//         int i, x=0, y=0;
//         double a, b, hasil = 0;
//         Matrix fromFile = new Matrix(0, 0);
//         Matrix f = new Matrix(16, 16);
//         System.out.print("Masukan nama path file: ");
//         String namaFile = scan.next();
//         try {
//             fromFile = Matrix.fileToMatrix(namaFile);
//         } catch (FileNotFoundException e) {
//             System.out.println("Tidak ditemukan file.");
//             file = false;
//         }


//         if (file){
//         a = fromFile.m[4][0];
//         b = fromFile.m[4][1];

//         for (i=0; i<16; i++){
//             if (i < 4){
//                 f.m[i] = fungsi1(x, y);
//             } else if (4 <= i && i < 8){
//                 f.m[i] = fungsiX(x, y);
//             } else if (8 <= i && i < 12){
//                 f.m[i] = fungsiY(x, y);
//             } else {
//                 f.m[i] = fungsiXY(x, y);
//             }
//             x++;
//             if (x>1){
//                 x = 0;
//                 y++;
//             }
//             if (y>1){
//                 y = 0;
//             }
//         }

//         Matrix tambahan = new Matrix(16,1);
//         int j,p = 0;
//         for(i=0;i<4;i++){
//             for(j=0;j<4;j++){
//                 tambahan.m[p][0] = fromFile.m[i][j];
//                 p++;
//             }
//         }

//         Matrix M_final = new Matrix(0, 0);
//         double[] l_constA = new double[16], l_final = new double[16];

//         M_final = Matrix.mergeMatrix(f, tambahan);
//         l_constA = splGaussBicubic(M_final);
//         l_final = fungsi1(a, b);

//         for(i=0;i<16;i++){
//             hasil += l_constA[i]*l_final[i];
//         }
//         System.out.println(hasil);

//         System.out.print("\n");
//         String s1 = "f(";
//         s1 = s1 + (String.format("%2f", a)) + "," + (String.format("%2f", b)) + ") = " + (String.format("%4f", hasil));
//         Matrix.saveString(s1);
//         }
//     }