package Lesson2.HW.Variant15;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 3/30/14
 * Time: 7:47 PM
 */

/**
 * Путем перестановки элементов квадратной вещественной матрицы добиться того,
 * чтобы ее максимальный элемент находился в левом верхнем углу,
 * следующий по величине – в позиции (2,2), следующий по величине – в позиции (3,3) и т.д.
 * заполнив таким образом всю главную диагональ.
 * */

/**
 * 1. Створення
 * 2. Пошук максимальних значень їх позначення в допоміжному масиві (isUsed) і запам’ятовування індексів (indexes)
 * 3. Почерговий обмін місцями з перевірками
 * 4. Виведення
 * */

 public class Main {
	public static void main(String[] args) {
		DiagonalSort matrix = new DiagonalSort(3);
		System.out.println("Before");
		matrix.show();
		matrix.buildDiagonal();
		System.out.println("\nAfter");
		matrix.show();
	}
}

class DiagonalSort {
	private int[][] matrix;
	private boolean[][] isUsed;
	Index[] indexes;

	DiagonalSort(int dimension) {
		matrix = new int[dimension][dimension];
		isUsed = new boolean[dimension][dimension];
		indexes = new Index[dimension];
		for (int i = 0; i < dimension; i++){
			for (int j = 0; j < dimension; j++){
				matrix[i][j] = (int)(Math.random() * 10);
				isUsed[i][j] = false;
			}
			indexes[i] = new Index(0, 0);
		}
	}

	public void show(){
		for (int[] subMatrix : matrix) {
			for (int element : subMatrix) {
				System.out.printf("%3d", element);
			}
			System.out.println();
		}
	}

	private void showUsed(int value){
		for (int i = 0; i < matrix.length; i++){
			System.out.print(" ");
			for (int j = 0; j < matrix[i].length; j++){
				if (isUsed[i][j])
					System.out.print("*" + matrix[i][j] + " ");
				else
					System.out.print(" " + matrix[i][j] + " ");
			}
			if (i == (matrix[i].length / 2))
				System.out.print("\t" + value);
			System.out.println();
		}
	}

	public void buildDiagonal(){

		System.out.println("\nSearching");

		Index anyNotUsed;
		for (int pass = 0; pass < indexes.length; pass++){
			anyNotUsed = findAnyNotUsed();
			if (anyNotUsed != null)
				indexes[pass] = anyNotUsed;

			for (int i = 0; i < matrix.length; i++){
				for (int j = 0; j < matrix[i].length; j++){

					if (!isUsed[i][j] && (matrix[indexes[pass].getI()][indexes[pass].getJ()] < matrix[i][j])){
						indexes[pass].setIJ(i, j);
					}

				}
			}
			isUsed[indexes[pass].getI()][indexes[pass].getJ()] = true;

			System.out.println("--------------");
			showUsed(matrix[indexes[pass].getI()][indexes[pass].getJ()]);

		}

		System.out.println("\nManipulating");

		for (int pass = 0; pass < indexes.length; pass++){
			swap(indexes[pass].getI(), indexes[pass].getJ(), pass, pass);

			System.out.println("--------------");
			showUsed(matrix[indexes[pass].getI()][indexes[pass].getJ()]);

		}


	}

	public Index findAnyNotUsed(){
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix[i].length; j++){
				if (!isUsed[i][j]){
					return new Index(i, j);
				}
			}
		}
		return null;
	}

	private void swap(int i1, int j1, int i2, int j2){
		if (i1 == i2 && j1 == j2)
			return;

		matrix[i1][j1] += matrix[i2][j2];
		matrix[i2][j2] = matrix[i1][j1] - matrix[i2][j2];
		matrix[i1][j1] -= matrix[i2][j2];

		for (Index each : indexes){
			if (i2 == each.getI() && j2 == each.getJ()){
				each.setIJ(i1, j1);
			}
		}

		boolean temp = isUsed[i1][j1];
		isUsed[i1][j1] = isUsed[i2][j2];
		isUsed[i2][j2] = temp;
	}

	private class Index{
		private int i;
		private int j;

		Index(int i, int j) {
			this.i = i;
			this.j = j;
		}

		int getI() {
			return this.i;
		}
		int getJ() {
			return this.j;
		}

		void setIJ(int i, int j){
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "{" + " i = " + i + ", j = " + j + " }";
		}
	}

}