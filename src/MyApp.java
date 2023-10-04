import java.util.*;

import PersoalanSPL.*;
import Library.*;

public class MyApp {
	public static boolean state = false;

	public static int jenis_input() {

		System.out.println("Jenis pilihan input:");
		System.out.println("1. Input dari keyboard");
		System.out.println("2. Input dari file");
		Scanner sc = new Scanner(System.in);

		System.out.println("\n");
		System.out.print("Masukkan pilihan: ");
		int x = sc.nextInt();
		System.out.println("------------------------------");
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

	public static int menu() {
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
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		System.out.println("------------------------------");
		state = true;
		return choose;
	}

	public static void main(String[] args) {
		System.out.println("------------------------------");
		System.out.println("Selamat datang aslab tercintah");
		System.out.println("------------------------------");

		Scanner sc = new Scanner(System.in);
		OperasiDasarMatrix ODM = new OperasiDasarMatrix();

		int choose = menu();

		while (state == true) {
			if (choose == 1) {
				// SPL
				int input_SPL = jenis_SPL();
				if (input_SPL == 1) {
					// Gauss
					int input_type = jenis_input();
					if (input_type == 1) { // keyboard

					} else if (input_type == 2) { // file

					} else {
						// System.out.println("Masukan input salah");
					}
				} else if (input_SPL == 2) {
					// Gauss Jordan
					int input_type = jenis_input();
					if (input_type == 1) { // keyaboard

					} else if (input_type == 2) { // file

					} else {
						// System.out.println("Masukan input salah");
					}
				} else if (input_SPL == 3) {
					// Matriks balikan
					int input_type = jenis_input();
					if (input_type == 1) { // keyboard

					} else if (input_type == 2) { // file

					} else {
						// System.out.println("Masukan input salah");
					}
				} else if (input_SPL == 4) {
					// Kaidah Cramer
					int input_type = jenis_input();
					if (input_type == 1) { // keyboard
						Determinan det = new Determinan();
						Matrix m = new Matrix();
						m = ODM.readSPLCramer();
						if (m.get_ROW_EFF() == 0) {
							System.out.println("Matrix tersebut tidak dapat diselesaikan menggunakan Kaidah Cramer.");
							System.out.println("Banyaknya persamaan harus sama dengan banyaknya variabel.");
						} else {
							det.KaidahCramer(m);
						}
					} else if (input_type == 2) { // file

					} else {
						// System.out.println("Masukan input salah");
					}

				} else {
					// System.out.println("Masukan input salah");
				}
			} else if (choose == 2) { // DETERMINAN
				Matrix m = new Matrix();
				int input_type = jenis_input();
				if (input_type == 1) {
					System.out.print("Masukkan ukuran matriks: ");
					int n = sc.nextInt();
					System.out.println("------------------------------");
					ODM.createMatrix(m, n, n);
					System.out.println("Masukkan matriks: ");
					ODM.readMatrix(m, n, n);
					System.out.println("------------------------------");
					ODM.displayMatrix(m);
				} else {
					sc.nextLine();
					System.out.println("Masukkan nama file: ");
					String filename = sc.nextLine();
					ODM.readMatrixFile(filename, m);
					ODM.displayMatrix(m);
				}
				Determinan det = new Determinan();
				double n;
				System.out.println("------------------------------");
				System.out.println("Pilihan Metode: ");
				System.out.println("1. Reduksi Baris");
				System.out.println("2. Ekspansi Kofaktor");
				System.out.println("\n");
				System.out.print("Masukkan pilihan: ");
				int metode = sc.nextInt();
				System.out.println("------------------------------");
				if (metode == 1) {
					n = det.DetReduksiBaris(m);
				} else {
					n = det.DetEkspansiKofaktor(m);
				}
				System.out.println("Hasil determinan dari matriks tersebut adalah " + n);
				System.out.println("------------------------------");
				choose = menu();
			} else if (choose == 3) {
				// Matriks balikan
			} else if (choose == 4) {
				//
				InterpolasiPolinomial ip = new InterpolasiPolinomial();
				Matrix m = new Matrix();
				int input_type = jenis_input();
				System.out.println("Masukkan ukuran matriks: ");
				int n = sc.nextInt();
				if (input_type == 1) {
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
			} else if (choose == 7) {
				// Keluar
				state = false;
			}
		}
	}
}