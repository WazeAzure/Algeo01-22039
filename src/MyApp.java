import java.util.*;

import PersoalanSPL.*;
import Library.*;

public class MyApp {

	/* *** CREATE OBJECTS *** */
	Determinan DET = new Determinan();
	Scanner sc = new Scanner(System.in);
	MatriksBalikan MB = new MatriksBalikan();
	MetodeEliminasi ME = new MetodeEliminasi();
	OperasiDasarMatrix ODM = new OperasiDasarMatrix();

	/* *** HELPER FUNCTIONS *** */

	public static int menu() {
		/* Menampilkan menu utama */
		System.out.println("------------------------------");
		System.out.println("MENU");
		System.out.println("1. Sistem Persamaan Linier");
		System.out.println("2. Determinan");
		System.out.println("3. Matriks Balikan");
		System.out.println("4. Interpolasi Polinom");
		System.out.println("5. Interpolasi Bicubic Spline");
		System.out.println("6. Regresi Linier Berganda");
		System.out.println("7. Keluar");
		System.out.print("\nMasukkan pilihan: ");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		System.out.println("------------------------------");
		state = true;
		return choose;
	}

	// Jika memilih 1 (SPL) di MENU
	public static int jenis_SPL() {
		/* Menampilkan submenu metode SPL */
		System.out.println("Pilihan metode SPL:");
		System.out.println("1. Metode eliminasi Gauss");
		System.out.println("2. Metode eliminasi Gauss-Jordan");
		System.out.println("3. Metode matriks balikan");
		System.out.println("4. Kaidah Cramer");
		System.out.print("\nMasukkan pilihan: ");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println("\n");
		return x;
	}

	// Jika memilih 3 (Matriks Balikan) di MENU
	public static int jenis_inverse() {
		/* Menampilkan submenu metode inverse */
		System.out.println("Pilihan metode inverse:");
		System.out.println("1. Gauss-Jordan");
		System.out.println("2. Adjoin Matriks");
		System.out.print("\nMasukkan pilihan: ");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println("------------------------------");
		return x;
	}

	public static int jenis_input() {
		/* Menampilkan pilihan untuk input */
		System.out.println("Pilihan input:");
		System.out.println("1. Input dari keyboard");
		System.out.println("2. Input dari file");
		System.out.print("\nMasukkan pilihan: ");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println("------------------------------");
		return x;
	}

	public static int askToSaveOutput() {
		/* Menampilkan pilihan ingin menyimpan output ke file atau tidak */
		System.out.println("Simpan output ke dalam file?");
		System.out.println("1. Simpan");
		System.out.println("2. Tidak");
		System.out.print("\nMasukkan pilihan: ");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println("------------------------------");
		return x;
	}

	public static boolean state = false;

	/* *** MAIN FUNCTION *** */
	public static void main(String[] args) {

		/* *** CREATE OBJECTS *** */
		// Determinan DET = new Determinan();
		Scanner sc = new Scanner(System.in);
		MatriksBalikan MB = new MatriksBalikan();
		MetodeEliminasi ME = new MetodeEliminasi();
		OperasiDasarMatrix ODM = new OperasiDasarMatrix();

		System.out.println("------------------------------");
		System.out.println("Selamat Datang Aslab Tercintah <3");

		int choose = menu();

		while (state == true) {
			if (choose == 1) {
				// ---------------------------------------------------------------------------------------------------------------------------------
				// ---------------------------------------- SPL
				// ------------------------------------------------------------------------------------
				// ---------------------------------------------------------------------------------------------------------------------------------
				int input_SPL = jenis_SPL();
				// ---------------------------------------- METODE GAUSS
				// ---------------------------------------------------------------------------
				if (input_SPL == 1) {
					Matrix m = new Matrix();
					int input_type = jenis_input();
					// Cek input dari file atau keyboard
					if (input_type == 1) { // keyboard
						m = ODM.readSPL();
					} else if (input_type == 2) { // file
						System.out.println("Masukkan nama file: ");
						String filename = sc.nextLine();
						System.out.println("------------------------------");
						ODM.readMatrixFile(filename, m);
						ODM.displayMatrix(m);
					} else {
						System.out.println("Masukan input salah");
						System.exit(0);
					}

					// Lakukan Metode Gauss
					ME.toEselon(m);
					int n = ME.Gauss(m);
					Matrix result = new Matrix();
					// Cek jenis solusi gauss
					if (n == 0) { // No solution
						System.out.println("SPL tidak memiliki solusi");
					} else if (n == 1) { // Solusi unik
						result = ME.SolveSPLUnik(m);
						int i;
						for (i = 0; i < result.get_COL_EFF(); i++) {
							System.out.println("x" + (i + 1) + " = " + result.get_ELMT(i, 0));
						}
						// Simpan solusi SPL atau tidak
						int save = askToSaveOutput();
						if (save == 1) { // Simpan
							System.out.println("Masukkan nama file: ");
							String filename = sc.nextLine();
							ODM.displayMatrixtoFile(result, "sol" + filename);
							System.out.println("Hasil tersimpan pada file sol" + filename);
						} else { // Tidak simpan
							System.out.println("Hasil tidak disimpan ke dalam file");
						}
					} else if (n == 2) { // Solusi banyak
						ME.SolvesSPLParametrik(m);
					}

					choose = menu();
				}
				// ---------------------------------------- METODE GAUSS JORDAN
				// ----------------------------------------------------------------------
				else if (input_SPL == 2) {
					Matrix m = new Matrix();
					int input_type = jenis_input();
					// Cek input dari file atau keyboard
					if (input_type == 1) { // keyboard
						m = ODM.readSPL();
					} else if (input_type == 2) { // file
						System.out.println("Masukkan nama file: ");
						String filename = sc.nextLine();
						System.out.println("------------------------------");
						ODM.readMatrixFile(filename, m);
						ODM.displayMatrix(m);
					} else {
						System.out.println("Masukan input salah");
						System.exit(0);
					}

					// Lakukan Metode Gauss Jordan
					ME.toEselonRed(m);
					int n = ME.Gauss(m);
					Matrix result = new Matrix();
					// Cek jenis solusi gauss
					if (n == 0) { // No solution
						System.out.println("SPL tidak memiliki solusi");
					} else if (n == 1) {
						result = ME.SolveSPLUnik(m);
						int i;
						for (i = 0; i < result.get_COL_EFF(); i++) {
							System.out.println("x" + (i + 1) + " = " + result.get_ELMT(i, 0));
						}
						// Simpan solusi SPL atau tidak
						int save = askToSaveOutput();
						if (save == 1) { // Simpan
							System.out.println("Masukkan nama file: ");
							String filename = sc.nextLine();
							ODM.displayMatrixtoFile(result, "sol" + filename);
							System.out.println("Hasil tersimpan pada file sol" + filename);
						} else { // Tidak simpan
							System.out.println("Hasil tidak disimpan ke dalam file");
						}
					} else if (n == 2) {
						ME.SolvesSPLParametrik(m);
					}

					choose = menu();
				}
				// ---------------------------------------- METODE MATRIKS BALIKAN
				// ---------------------------------------------------------------------------
				else if (input_SPL == 3) {
					Matrix m = new Matrix();
					Matrix A = new Matrix();
					Matrix result = new Matrix();
					int input_type = jenis_input();
					// Cek input dari file atau keyboard
					if (input_type == 1) { // keyboard
						m = ODM.readSPL();
					} else if (input_type == 2) { // file
						System.out.println("Masukkan nama file: ");
						String filename = sc.nextLine();
						System.out.println("------------------------------");
						ODM.readMatrixFile(filename, m);
						ODM.displayMatrix(m);
					} else {
						System.out.println("Masukan input salah");
						System.exit(0);
					}

					// Lakukan Metode Matriks Balikan
					A = MB.cropLastColOfMatrix(m); // m tanpa kolom b
					if (!ODM.isSquare(A) || ODM.determinant(A) == 0) {
						// Matrix tidak punya inverse
						ODM.displayMatrix(A);
						System.out.println("Matrix tersebut tidak punya inverse");
						System.out.println("Matrix tidak dapat diselesaikan menggunakan metode matriks balikan.");
						System.out.println("Gunakan metode lain");
					} else {
						System.out.println("Matrix Augmented:");
						ODM.displayMatrix(m);
						System.out.println("Solusi dari SPL adalah ");
						result = MB.solveSPLwithInverse(m);
						for (int i = 0; i < m.get_ROW_EFF(); i++) {
							System.out.println("x" + (i + 1) + " = " + m.get_ELMT(i, 0));
						}
						// Simpan solusi SPL atau tidak
						int save = askToSaveOutput();
						if (save == 1) { // Simpan
							System.out.println("Masukkan nama file: ");
							String filename = sc.nextLine();
							ODM.displayMatrixtoFile(result, "sol" + filename);
							System.out.println("Hasil tersimpan pada file sol" + filename);
						} else { // Tidak simpan
							System.out.println("Hasil tidak disimpan ke dalam file");
						}
					}

					choose = menu();
				}
				// ---------------------------------------- METODE KAIDAH CRAMER
				// ---------------------------------------------------------------------------
				else if (input_SPL == 4) {
					Matrix m = new Matrix();
					Matrix result = new Matrix();
					Determinan det = new Determinan();
					int input_type = jenis_input();
					// Cek input dari file atau keyboard
					if (input_type == 1) { // keyboard
						m = ODM.readSPLCramer();
					} else if (input_type == 2) { // file
						System.out.println("Masukkan nama file: ");
						String filename = sc.nextLine();
						System.out.println("------------------------------");
						ODM.readMatrixFile(filename, m);
						ODM.displayMatrix(m);
					} else {
						System.out.println("Masukan input salah");
						System.exit(0);
					}

					// Lakukan Kaidah Cramer
					if (m.get_ROW_EFF() + 1 != m.get_COL_EFF()) {
						System.out.println("Matrix tersebut tidak dapat diselesaikan menggunakan Kaidah Cramer.");
						System.out.println("Banyaknya persamaan harus sama dengan banyaknya variabel.");
					} else {
						System.out.println("------------------------------");
						result = det.KaidahCramer(m);
						if (result.get_COL_EFF() == 0) {
							System.out.println("Matrix tersebut tidak dapat diselesaikan menggunakan Kaidah Cramer.");
						} else {
							for (int i = 0; i < result.get_COL_EFF(); i++) {
								System.out.println("x" + (i + 1) + " = " + result.get_ELMT(i, 0));
							}
							// Simpan solusi SPL atau tidak
							int save = askToSaveOutput();
							if (save == 1) { // Simpan
								System.out.println("Masukkan nama file: ");
								String filename = sc.nextLine();
								ODM.displayMatrixtoFile(result, "sol" + filename);
								System.out.println("Hasil tersimpan pada file sol" + filename);
							} else { // Tidak simpan
								System.out.println("Hasil tidak disimpan ke dalam file");
							}
						}
					}

					choose = menu();
				} else {
					System.out.println("Masukan input salah");
					System.exit(0);
				}
			} else if (choose == 2) {
				// ---------------------------------------------------------------------------------------------------------------------------------
				// ---------------------------------------- DETERMINAN
				// -----------------------------------------------------------------------------
				// ---------------------------------------------------------------------------------------------------------------------------------
				Matrix m = new Matrix();
				int input_type = jenis_input();
				// Cek input dari file atau keyboard
				if (input_type == 1) {
					System.out.print("Masukkan ukuran matriks: ");
					int n = sc.nextInt();
					System.out.println("------------------------------");
					ODM.createMatrix(m, n, n);
					System.out.println("Masukkan matriks: ");
					ODM.readMatrix(m, n, n);
				} else if (input_type == 2) {
					System.out.println("Masukkan nama file: ");
					String filename = sc.nextLine();
					System.out.println("------------------------------");
					ODM.readMatrixFile(filename, m);
					ODM.displayMatrix(m);
				} else {
					System.out.println("Masukan input salah");
					System.exit(0);
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
				// ---------------------------------------------------------------------------------------------------------------------------------
				// ---------------------------------------- MATRIKS BALIKAN
				// ------------------------------------------------------------------------
				// ---------------------------------------------------------------------------------------------------------------------------------
				Matrix m = new Matrix();
				Matrix result = new Matrix();
				int input_type = jenis_input();
				if (input_type == 1) { // keyboard
					System.out.print("Masukkan n (ukuran matriks n x n): ");
					int n = sc.nextInt();
					System.out.println("------------------------------");
					ODM.createMatrix(m, n, n);
					System.out.println("Masukkan matriks: ");
					ODM.readMatrix(m, n, n);
				} else if (input_type == 2) { // file
					System.out.println("Masukkan nama file: ");
					String filename = sc.nextLine();
					System.out.println("------------------------------");
					ODM.readMatrixFile(filename, m);
					ODM.displayMatrix(m);
				} else {
					System.out.println("Masukan input salah");
					System.exit(0);
				}

				// Cek determinan matrix
				if (ODM.determinant(m) != 0) {
					// Matrix punya balikan
					int jenis_inverse = jenis_inverse();
					if (jenis_inverse == 1) {
						result = MB.inverseWithGaussJordan(m);
					} else if (jenis_inverse == 2) {
						result = MB.inverseWithAdjoin(m);
					}
					System.out.println("Matriks balikannya adalah ");
					ODM.displayMatrix(result);
					System.out.println("------------------------------");
					// Simpan solusi SPL atau tidak
					int save = askToSaveOutput();
					if (save == 1) { // Simpan
						System.out.println("Masukkan nama file: ");
						String filename = sc.nextLine();
						ODM.displayMatrixtoFile(result, "sol" + filename);
						System.out.println("Matriks balikannya tersimpan pada file sol" + filename);
					} else { // Tidak simpan
						System.out.println("Hasil tidak disimpan ke dalam file");
					}
				} else {
					System.out.println("Matriks tidak punya balikan");
				}

				choose = menu();

			} else if (choose == 4) {
				// ---------------------------------------------------------------------------------------------------------------------------------
				// ---------------------------------------- INTERPOLASI POLINOMIAL
				// -----------------------------------------------------------------
				// ---------------------------------------------------------------------------------------------------------------------------------
				InterpolasiPolinomial ip = new InterpolasiPolinomial();
				Matrix m = new Matrix();
				double x = 0;
				int input_type = jenis_input();
				// Cek input dari file atau keyboard
				if (input_type == 1) {
					System.out.println("Masukkan ukuran matriks: ");
					int n = sc.nextInt();
					m = ip.InputtoMatrix(n, 1);
					System.out.print("Masukkan nilai x yang ingin diinterpolasi: ");
					x = sc.nextDouble();
					System.out.println("\n");
				} else {
					System.out.println("Masukkan nama file: ");
					String filename = sc.nextLine();
					System.out.println("------------------------------");
					ODM.readMatrixFileInterpolate(filename, m, x);
					ODM.displayMatrix(m);
					System.out.println("------------------------------");
				}

				m = ip.MatrixtoMatrixInt(m);

				double y = ip.Interpolasi(m, x);
				if (y == -9999.9999) {
					System.out.println("Matriks tidak menghasilkan SPL unik, nilai interpolasi tidak didapatkan.");
				} else {
					System.out.println("Nilai interpolasi x = " + x + " menghasilkan nilai y = " + y);
				}
				choose = menu();
			} else if (choose == 5) {
				// ---------------------------------------------------------------------------------------------------------------------------------
				// ---------------------------------------- BICUBIC SPLINE
				// -------------------------------------------------------------------------
				// ---------------------------------------------------------------------------------------------------------------------------------

				int pilihan = jenis_input();
				while (pilihan != 1 && pilihan != 2) {
					pilihan = jenis_input();
				}
				BicubicSplineInterpolation bsi = new BicubicSplineInterpolation();
				bsi.StartBSI(pilihan);

				// System.out.println("After infinite loop");
				choose = menu();
			} else if (choose == 6) {
				// ---------------------------------------------------------------------------------------------------------------------------------
				// ---------------------------------------- REGRESI LINIER
				// ------------------------------------------------------------------------------------
				// ---------------------------------------------------------------------------------------------------------------------------------
				RegresiLinearBerganda RLB = new RegresiLinearBerganda();
				Matrix m = new Matrix();
				int input_type = jenis_input();
				int k, n;
				if (input_type == 1) { // keyboard
					System.out.println("Masukkan jumlah peubah x:");
					k = sc.nextInt();
					System.out.println("Masukkan jumlah sampel:");
					n = sc.nextInt();
					System.out.println("Masukkan data (dalam bentuk matriks):");
					ODM.createMatrix(m, n, k + 1);
					ODM.readMatrix(m, n, k + 1);
				} else if (input_type == 2) { // file
					System.out.println("Masukkan nama file: ");
					String filename = sc.nextLine();
					System.out.println("------------------------------");
					ODM.readMatrixFile(filename, m);
					ODM.displayMatrix(m);
				} else {
					System.out.println("Masukan input salah");
					choose = menu();
				}

				n = m.get_ROW_EFF();
				k = m.get_COL_EFF() - 1;
				Matrix allConst = RLB.PersamaanHasilRegresiLinearBerganda(m);
				// Print persamaan hasil regresi linier berganda
				System.out.println("Persamaan hasil regresi linier berganda:");
				String[][] persamaan;
				persamaan = new String[2][1];
				persamaan[0][0] = "f(x) = " + (Math.round(allConst.get_ELMT(0, 0) * 10000.0) / 10000.0);
				System.out.printf("f(x) = %.4f", allConst.get_ELMT(0, 0));
				for (int i = 1; i < allConst.get_ROW_EFF(); i++) {
					persamaan[0][0] += " + " + (Math.round(allConst.get_ELMT(i, 0) * 10000.0) / 10000.0) + " x" + i;
					System.out.printf(" + %.4f x%d", allConst.get_ELMT(i, 0), i);
				}

				// Mencari taksiran nilai fungsi pada x yang diberikan
				System.out.println("\nTaksiran nilai fungsi:");
				System.out.printf("Masukkan %d nilai x yang ingin ditaksir: ", k);
				Matrix taksir = new Matrix();
				ODM.createMatrix(taksir, k, 1);
				for (int i = 0; i < taksir.get_ROW_EFF(); i++) {
					taksir.set_ELMT(i, 0, sc.nextDouble());
				}
				double hasil = allConst.get_ELMT(0, 0);
				for (int i = 0; i < allConst.get_ROW_EFF(); i++) {
					hasil += allConst.get_ELMT(i + 1, 0) * taksir.get_ELMT(i, 0);
				}
				System.out.printf("Hasil taksirannya adalah %.4f\n", hasil);
				choose = menu();
			} else if (choose == 7) {
				// Keluar
				state = false;
			}
		}
	}
}