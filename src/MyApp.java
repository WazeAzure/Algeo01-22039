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
		System.out.println("------------------------------");
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
		MetodeEliminasi ME = new MetodeEliminasi();

		int choose = menu();

		while (state == true) {
			if (choose == 1) {
				// SPL
				int input_SPL = jenis_SPL();
				if (input_SPL == 1) {
					// Gauss
					int input_type = jenis_input();
					if (input_type == 1) { // keyboard
						Matrix m = new Matrix();
						m = ODM.readSPL();
						ME.toEselon(m);
						int n = ME.Gauss(m);
						if (n == 1) {
							Matrix result = new Matrix();
							result = ME.SolveSPLUnik(m);
							int i;
							for (i = 0; i < result.get_COL_EFF(); i++) {
								System.out.println("x" + (i + 1) + " = " + result.get_ELMT(i, 0));
							}
						} else if (n == 2) {
							ME.SolvesSPLParametrik(m);
						}
						choose = menu();
					} else if (input_type == 2) { // file

					} else {
						System.out.println("Masukan input salah");
						choose = menu();
					}
				} else if (input_SPL == 2) {
					// Gauss Jordan
					int input_type = jenis_input();
					if (input_type == 1) { // keyboard
						Matrix m = new Matrix();
						m = ODM.readSPL();
						ME.toEselonRed(m);
						int n = ME.Gauss(m);
						if (n == 1) {
							Matrix result = new Matrix();
							result = ME.SolveSPLUnik(m);
							int i;
							for (i = 0; i < result.get_COL_EFF(); i++) {
								System.out.println("x" + (i + 1) + " = " + result.get_ELMT(i, 0));
							}
						} else if (n == 2) {
							ME.SolvesSPLParametrik(m);
						}
						choose = menu();
					} else if (input_type == 2) { // file

					} else {
						System.out.println("Masukan input salah");
						choose = menu();
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
					Determinan det = new Determinan();
					int input_type = jenis_input();
					if (input_type == 1) { // keyboard
						Matrix m = new Matrix();
						m = ODM.readSPLCramer();
						if (m.get_ROW_EFF() + 1 != m.get_COL_EFF()) {
							System.out.println("Matrix tersebut tidak dapat diselesaikan menggunakan Kaidah Cramer.");
							System.out.println("Banyaknya persamaan harus sama dengan banyaknya variabel.");
						} else {
							System.out.println("------------------------------");
							det.KaidahCramer(m);
							choose = menu();
						}
					} else if (input_type == 2) { // file
						Matrix m = new Matrix();
						System.out.println("Masukkan nama file: ");
						String filename = sc.nextLine();
						System.out.println("------------------------------");
						ODM.readMatrixFile(filename, m);
						ODM.displayMatrix(m);
						if (m.get_ROW_EFF() + 1 != m.get_COL_EFF()) {
							System.out.println("Matrix tersebut tidak dapat diselesaikan menggunakan Kaidah Cramer.");
							System.out.println("Banyaknya persamaan harus sama dengan banyaknya variabel.");
						} else {
							System.out.println("------------------------------");
							det.KaidahCramer(m);
							choose = menu();
						}
					} else {
						System.out.println("Masukan input salah");
						choose = menu();
					}
				} else {
					System.out.println("Masukan input salah");
					choose = menu();
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
					System.out.println("Masukkan nama file: ");
					String filename = sc.nextLine();
					System.out.println("------------------------------");
					ODM.readMatrixFile(filename, m);
					ODM.displayMatrix(m);
				}
				Determinan det = new Determinan();
				double n = -999.999;
				if (ODM.isSquare(m)) {
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
				} else {
					System.out.println("------------------------------");
					System.out.println("Determinan tidak dapat ditentukan karena bukan matriks persegi");
					System.out.println("------------------------------");
				}

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
				state = false;
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