import java.util.*;

import PersoalanSPL.*;

public class MyApp{
	public static void main(String[] args){
		System.out.println("Hello World");

		Scanner sc = new Scanner(System.in);

		System.out.println("Halo Silahkan pilih ke bagian mana aja");
		System.out.println("1. Interpolasi Polinom (Mbakdi)\n2. Regresi Linear Berganda (novel)\n3. Bicubic (ed)");

		int choose = sc.nextInt();

		if(choose == 1){
			// 
			InterpolasiPolinomial ip = new InterpolasiPolinomial();
		} else if(choose == 2){
			//
			RegresiLinearBerganda rlb = new RegresiLinearBerganda();
		} else if(choose == 3){
			// 
			BicubicSplineInterpolation bsi = new BicubicSplineInterpolation();

		}
	}
}
