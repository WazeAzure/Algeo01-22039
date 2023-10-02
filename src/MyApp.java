import java.util.*;

import PersoalanSPL.*;
import Library.*;

public class MyApp{
	public static void main(String[] args){
		System.out.println("Hello World");

		Scanner sc = new Scanner(System.in);
		OperasiDasarMatrix ODM = new OperasiDasarMatrix();

		System.out.println("Halo Silahkan pilih ke bagian mana aja");
		System.out.println("1. Interpolasi Polinom (Mbakdi)\n2. Regresi Linear Berganda (novel)\n3. Bicubic (ed)");

		int choose = sc.nextInt();

		if(choose == 1){
			// 
			InterpolasiPolinomial ip = new InterpolasiPolinomial();
			int n = sc.nextInt();
			ip.InterPolinomial(n);

		} else if(choose == 2){
			//
			RegresiLinearBerganda rlb = new RegresiLinearBerganda();
		} else if(choose == 3){
			// 
			BicubicSplineInterpolation bsi = new BicubicSplineInterpolation();

		} else if(choose == 4){
			Matrix m = new Matrix();
			ODM.createMatrix(m, 3, 3);
			Determinan det = new Determinan();
			ODM.readMatrix(m, 3, 3);
			ODM.displayMatrix(m);
			double n;
			n = det.DetEkspansiKofaktor(m);
			System.out.println(n);
		}
	}
}
