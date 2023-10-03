import java.util.*;

import PersoalanSPL.*;
import Library.*;

public class MyApp{
	public static int jenis_input(){
		System.out.println("Jenis pilihan input:");
		System.out.println("1. Input dari keyboard");
		System.out.println("2. Input dari file");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		return x;
	}

	public static void main(String[] args){
		System.out.println("Hello World");

		Scanner sc = new Scanner(System.in);
		OperasiDasarMatrix ODM = new OperasiDasarMatrix();

		System.out.println("MENU");
		System.out.println("1. Sistem Persamaan Linier");
		System.out.println("2. Determinan");
		System.out.println("3. Matriks Balikan");
		System.out.println("4. Interpolasi Polinom");
		System.out.println("5. Interpolasi Bicubic Spline");
		System.out.println("6. Regresi Linier Berganda");
		System.out.println("7. Keluar");

		int choose = sc.nextInt();

		if(choose == 1){
			// SPL
		} else if(choose == 2){
			Matrix m = new Matrix();
			int input_type = jenis_input();
			if (input_type == 1){
				ODM.createMatrix(m, 3, 3);
				ODM.readMatrix(m, 3, 3);
				ODM.displayMatrix(m);
			} else {
				sc.nextLine();
				String filename = sc.nextLine();
				ODM.readMatrixFile(filename, m);
				ODM.displayMatrix(m);
			}
			Determinan det = new Determinan();
			double n;
			n = det.DetEkspansiKofaktor(m);
			System.out.println(n);
		} else if(choose == 3){
			// Matriks balikan
		} else if(choose == 4){
			// 
			InterpolasiPolinomial ip = new InterpolasiPolinomial();
			int n = sc.nextInt();
			ip.InterPolinomial(n);
		} else if(choose == 5){
			// 
			BicubicSplineInterpolation bsi = new BicubicSplineInterpolation();
		} else if(choose == 6) {
			//
			RegresiLinearBerganda rlb = new RegresiLinearBerganda();
		}
	}
}
