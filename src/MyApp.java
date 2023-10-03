import java.util.*;

import PersoalanSPL.*;
import Library.*;

public class MyApp {

	public static int jenis_input() {
		System.out.println("Jenis pilihan input:");
		System.out.println("1. Input dari keyboard");
		System.out.println("2. Input dari file");
		Scanner sc = new Scanner(System.in);
		System.out.println("\n");
		System.out.print("Masukkan pilihan: ");
		int x = sc.nextInt();
		System.out.println("\n");
		return x;
	}

	public static int jenis_SPL() {
		System.out.println("Jenis pilihan input:");
		System.out.println("1. Metode eliminasi Gauss");
		System.out.println("2. Metode eliminasi Gauss-Jordan");
		System.out.println("3. Metode matriks balikan");
		System.out.println("4. Kaidah Cramer");
		Scanner sc = new Scanner(System.in);
		System.out.println("\n");
		System.out.print("Masukkan pilihan: ");
		int x = sc.nextInt();
		System.out.println("\n");
		return x;
	}


	public static void main(String[] args) {
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

		System.out.println("\n");
		System.out.print("Masukkan pilihan: ");
		int choose = sc.nextInt();
		System.out.println("\n");

		if (choose == 1) {
			// SPL
			int input_SPL = jenis_SPL();
			if (input_SPL == 1){
				// Gauss
				int input_type = jenis_input();
				if (input_type == 1){ // keyboard

				} else if (input_type == 2){ // file

				} else {
					// System.out.println("Masukan input salah");
				}
			} else if (input_SPL == 2){
				// Gauss Jordan
				int input_type = jenis_input();
				if (input_type == 1){ // keyaboard

				} else if (input_type == 2){ // file

				} else {
					// System.out.println("Masukan input salah");
				}
			} else if (input_SPL == 3){
				// Matriks balikan
				int input_type = jenis_input();
				if (input_type == 1){ // keyboard

				} else if (input_type == 2){ // file

				} else {
					// System.out.println("Masukan input salah");
				}
			} else if (input_SPL == 4){
				// Kaidah Cramer
				int input_type = jenis_input();
				if (input_type == 1){ // keyboard

				} else if (input_type == 2){ // file

				} else {
					// System.out.println("Masukan input salah");
				}

			} else {
				// System.out.println("Masukan input salah");
			}
		} else if (choose == 2) {
			Matrix m = new Matrix();
			int input_type = jenis_input();
			if (input_type == 1) {
				System.out.print("Masukkan ukuran matriks: ");
				int n = sc.nextInt();
				System.out.println("\n");
				ODM.createMatrix(m, n, n);
				System.out.println("Masukkan matriks: ");
				ODM.readMatrix(m, n, n);
				ODM.displayMatrix(m);
			} else {
				sc.nextLine();
				System.out.println("Masukkan nama file: ");
				String filename = sc.nextLine();
				System.out.println("Masukkan matriks: ");
				ODM.readMatrixFile(filename, m);
				ODM.displayMatrix(m);
			}
			Determinan det = new Determinan();
			double n;
			System.out.println("\n");
			System.out.println("Pilihan Metode: ");
			System.out.println("1. Reduksi Baris");
			System.out.println("2. Ekspansi Kofaktor");
			System.out.println("\n");
			System.out.print("Masukkan pilihan: ");
			int metode = sc.nextInt();
			System.out.println("\n");
			if (metode == 1) {
				n = det.DetReduksiBaris(m);
			} else {
				n = det.DetEkspansiKofaktor(m);
			}
			System.out.println("Hasil determinan dari matriks tersebut adalah " + n);
		} else if (choose == 3) {
			// Matriks balikan
		} else if (choose == 4) {
			//
			InterpolasiPolinomial ip = new InterpolasiPolinomial();
			int n = sc.nextInt();
			Matrix m = new Matrix();

			int input_type = jenis_input();
			if (input_type == 1){
				m = ip.InputtoMatrix(n, 1);
			} else {
				m = ip.InputtoMatrix(n, 2);
			}

			m = ip.MatrixtoMatrixInt(m);
			ODM.displayMatrix(m);

			System.out.print("Masukkan nilai x ang ingin diinterpolasi: ");
			double x = sc.nextDouble();
			System.out.println("\n");
			double y = ip.Interpolasi(m, x);
			System.out.println("Nilai interpolasi x = " + x + " menghasilkan nilai y = " + y);

		} else if (choose == 5) {
			//
			BicubicSplineInterpolation bsi = new BicubicSplineInterpolation();
		} else if (choose == 6) {
			//
			RegresiLinearBerganda rlb = new RegresiLinearBerganda();
		}
	}
}