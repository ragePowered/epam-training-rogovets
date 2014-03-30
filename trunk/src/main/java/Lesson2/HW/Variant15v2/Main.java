package Lesson2.HW.Variant15v2;

import java.util.Arrays;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 3/31/14
 * Time: 12:27 AM
 */

/**
 * Путем перестановки элементов квадратной вещественной матрицы добиться того,
 * чтобы ее максимальный элемент находился в левом верхнем углу,
 * следующий по величине – в позиции (2,2), следующий по величине – в позиции (3,3) и т.д.
 * заполнив таким образом всю главную диагональ.
 * */

public class Main {
	public static void main(String[] args) {
		DiagonalSort matrix = new DiagonalSort(10);
		System.out.println("Before");
		matrix.show();
		matrix.biDiToMonoDi();
		System.out.println("After");
		matrix.transform();
		matrix.show();
		System.out.print("Andrew Rogovets\nWed Mar 26 21:46:00 EEST 2014\n" + new Date());
	}
}

class DiagonalSort{
	private int[][] biDi;
	private int[] monoDi;

	DiagonalSort(int dimension) {
		biDi = new int[dimension][dimension];
		monoDi = new int[dimension * dimension];
		for (int i = 0; i < dimension; i++){
			for (int j = 0; j < dimension; j++){
				biDi[i][j] = (int)(Math.random() * 10);
			}
		}
	}

	public void show(){
		for (int[] subMatrix : biDi) {
			for (int element : subMatrix) {
				System.out.printf("%3d", element);
			}
			System.out.println();
		}
	}

	public void biDiToMonoDi(){
		int i = 0;
		for (int[] subMatrix : biDi){
			for (int element : subMatrix){
				monoDi[i++] = element;
			}
		}

	}

	public void transform(){
		Arrays.sort(monoDi);
		for (int i = 0; i < monoDi.length / 2; i++) {
			swap(i, monoDi.length - 1 - i);
		}
		for (int i = 1; i < biDi.length; i ++){
			swap(i, i * biDi.length + i);
		}
		for (int i = 0; i < biDi.length; i++){
			System.arraycopy(monoDi, (i * biDi.length), biDi[i], 0, biDi[i].length);
		}
	}

	private void swap(int a, int b){
		monoDi[a] += monoDi[b];
		monoDi[b] = monoDi[a] - monoDi[b];
		monoDi[a] -= monoDi[b];
	}

}