import java.util.Scanner;

public class MatrixMultiply {
    static int[][] A = new int[5][5];
    static int[][] B = new int[5][5];
    static int[][] C = new int[5][5];
    static int r1, c1, r2, c2;

    static class WorkerThread extends Thread {
        int row;
        WorkerThread(int row) {
            this.row = row;
        }
        public void run() {
            for (int i = 0; i < c2; i++) {
                C[row][i] = 0;
                for (int j = 0; j < c1; j++) {
                    C[row][i] += A[row][j] * B[j][i];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no of rows and columns for Matrix A (max 5x5): : ");
        r1 = sc.nextInt();
        c1 = sc.nextInt();

        System.out.print("Enter no of rows and columns for Matrix B (max 5x5): : ");
        r2 = sc.nextInt();
        c2 = sc.nextInt();

        if (c1 != r2) {
            System.out.println("Wrong inputs!Must be A columns=B rows");
            return;
        }

        System.out.println("Enter Matrix A elements:");
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c1; j++)
                A[i][j] = sc.nextInt();

        System.out.println("Enter Matrix B elements:");
        for (int i = 0; i < r2; i++)
            for (int j = 0; j < c2; j++)
                B[i][j] = sc.nextInt();

        WorkerThread[] threads = new WorkerThread[r1];
        for (int i = 0; i < r1; i++) {
            threads[i] = new WorkerThread(i);
            threads[i].start();
        }

        for (int i = 0; i < r1; i++) {
            threads[i].join();
        }

        System.out.println("Matrix C:");
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++)
                System.out.print(C[i][j] + " ");
            System.out.println();
        }
    }
}
