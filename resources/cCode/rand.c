#include<stdlib.h>
#include<math.h>
#include<stdio.h>
// #include <string.h>

#define ARRAY_SIZE 2100

#define FORMULA (a * (int)result[i-1] + c) % m

void random_generator(long int, long int, long int, long int, long double*);
void print_array(long double*);
void writeArray(char*, long double*);

int main(int argc, char * argv[]){
    long double result[ARRAY_SIZE];
    long int seed = 10;
    char* file;

    if (argc >= 2) {
        // file = malloc(sizeof(char) * strlen(argv[1]));
        seed = atol(argv[2]);
    }
    
    //x(n) = 16807 x(n-1) mod(231-1)
    printf("\n\n\n x(n) = 16807 x(n-1) mod(231-1): \n");
    random_generator(16807, 0, (long int)powl(2, 31)-1, seed, result);
    print_array(result);
    writeArray(argv[1], result);
    
}

void random_generator(long int a, long int c, long int m, long int seed, long double* result){
    result[0] = seed;

    for(long int i = 1; i < ARRAY_SIZE; i++){
        result[i] = ((long double)(FORMULA));
    }

    for(long int i = 0; i < ARRAY_SIZE; i++){
        result[i] /= m;
    }

    
}

void print_array(long double* array){
    printf("----------------------------------------------------------\n");
    for(long int i = 0; i < ARRAY_SIZE; i++){
        printf("%Lf\n", array[i]);
    }
    printf("----------------------------------------------------------\n");
}

void writeArray(char* fname, long double* out){
    FILE* fp = fopen(fname,"w");
    for (int i = 0; i < ARRAY_SIZE; i++){
        fprintf(fp,"%Lf \n",out[i]);
    }
    fclose(fp);
}