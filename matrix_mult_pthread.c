#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX 5
int A[MAX][MAX], B[MAX][MAX], C[MAX][MAX];
int r1, c1, r2, c2;

void* multiplyRow(void* arg) {
int row = *(int*)arg;
for (int i = 0; i < c2; i++) {
C[row][i] = 0;
for (int j = 0; j < c1; j++) {
C[row][i] += A[row][j] * B[j][i];
}
}
pthread_exit(0);
}

int main() {
printf("Enter no of rows and columns for Matrix A (max 5x5): ");
scanf("%d %d", &r1, &c1);

printf("Enter no of rows and columns for Matrix B (max 5x5): ");
scanf("%d %d", &r2, &c2);

if (c1 != r2) {
printf("Wrong inputs!Must be A columns=B rows\n");
return -1;
}

printf("Enter Matrix A elements(%dx%d):\n", r1, c1);
for (int i = 0; i < r1; i++)
for (int j = 0; j < c1; j++)
scanf("%d", &A[i][j]);

printf("Enter Matrix B elements(%dx%d):\n", r2, c2);
for (int i = 0; i < r2; i++)
for (int j = 0; j < c2; j++)
scanf("%d", &B[i][j]);

pthread_t threads[MAX];
int rowIndex[MAX];

for (int i = 0; i < r1; i++) {
rowIndex[i] = i;
pthread_create(&threads[i], NULL, multiplyRow, &rowIndex[i]);
}

for (int i = 0; i < r1; i++) {
pthread_join(threads[i], NULL);
}

printf("Matrix C (%dx%d):\n", r1, c2);
for (int i = 0; i < r1; i++) {
for (int j = 0; j < c2; j++)
printf("%d ", C[i][j]);
printf("\n");
}
return 0;
}
