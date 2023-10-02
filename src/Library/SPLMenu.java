// package Library;

// import java.util.Scanner;
// import PersoalanSPL.Gauss;
// import Library.Matrix;
// import Library.OperasiDasarMatrix;

// public class SPLMenu {
//     OperasiDasarMatrix ODM = new OperasiDasarMatrix();
//     private static Scanner scan = new Scanner(System.in);

//     public static boolean checkMatrix(Matrix n){
//         int i = n.get_ROW_EFF()-1, j=0, count = 0;
//         boolean found = false;
//         while (found == false){
//             if (Gauss.rounding(n.mem[i][j]) == 0){
//                 count++;
//             }
//             j++;
//             if (j > n.get_COL_EFF() - 1){
//                 j = 0;
//                 i -= 1;
//                 if(count == n.get_COL_EFF()-1){
//                     found = true;
//                 }
//                 count = 0;
//             }
//         }
//         return found;
//     }

//     public static int zeroRow(Matrix n){
//         int i = n.get_ROW_EFF()-1,j = 0, count = 0;
//         boolean found = false;
//         while (found == false && i > 0){
//             if (n.mem[i][j] != 0){
//                 found = true;
//             }
//             j += 1;
//             if (j > n.get_COL_EFF() - 1){
//                 j = 0;
//                 count += 1;
//                 i -= 1;
//             }
//         }
//         return count;
//     }

//     public static int[] makelist(Matrix n, int row){
//         int i, count = getOther(n.mem[row]), j=0;
//         int[] l = new int[count];
//         for (i = 0; i < n.get_COL_EFF()-1; i++){
//             if (n.mem[row][i] != 0){
//                 l[j] = i;
//                 j += 1;
//             }
//         }
//         return l;
//     }

//     public static int getOther(double[] l){
//         int i, count = 0;
//         for (i = 0; i < l.length - 1; i++){
//             if (l[i] != 0){
//                 count += 1;
//             }
//         }
//         return count - 1;
//     }

//     public static void splGauss(Matrix m1){
//         int i,j, r_zero = 0, count = 1;
//         boolean ada = false;
//         Gauss.eliminasiGauss(m1, true);
//         if (checkMatrix(m1) == true){
//             System.out.println("Persamaan tidak memiliki penyelesaian.");
//         } else {
//             m1.displayMatrix();
//             r_zero = zeroRow(m1);
//             for (i = 0; i < m1.get_ROW_EFF() - r_zero; i++){
//                 for (j = i+1; j < m1.get_ROW_EFF() - r_zero; j++){
//                     Gauss.add(m1, i, j, -m1.mem[i][Gauss_jordan.getFirstOne(m1,j)]);
//                 }
//             }
//             for (i = 0; i < m1.get_ROW_EFF() - r_zero; i++){
//                 if (r_zero == 0){
//                     System.out.printf("x%d = %f", i + 1, m1.mem[i][m1.get_COL_EFF()-1]);
//                     System.out.println();
//                 } else {
//                     int x;
//                     x = Gauss_jordan.getFirstOne(m1, i);
//                     if (m1.mem[i][m1.get_COL_EFF()-1] != 0){
//                         System.out.printf("x%d = %f", x + 1, m1.mem[i][m1.get_COL_EFF()-1]);
//                     } else {
//                         System.out.printf("x%d = ", x + 1);
//                         ada = true;
//                     }
//                     if (getOther(m1.mem[i]) > 0){
//                         count = Gauss_jordan.getFirstOne(m1,i) + 1;
//                         while (count < m1.get_COL_EFF() - 1){
//                             if (m1.mem[i][count] != 0){
//                                 if (ada == false){
//                                     if (m1.mem[i][count] < 0){
//                                         System.out.printf(" + ");
//                                     } else {
//                                         System.out.printf(" - ");
//                                     }
//                                 }
//                                 System.out.printf("%fx%d", m1.mem[i][count],count + 1);
//                             }
//                             count += 1;
//                         }
//                     }
//                     System.out.println();
//                 }
//             }
//         }
//     }
// }