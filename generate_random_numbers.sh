#!/bin/bash


# Este script shell compila o código C 'rand.c' e o executa em loop utilizando
# os números do arquivo keys.data como entrada para o argumento.
# Esse argumento é o que define a semente da série de números randômicos.


filename="./resources/data/keys.data"

gcc ./resources/cCode/rand.c -o ./../data/rand

declare -i i=1

while IFS= read -r key || [ -n "$key" ]; do
    frand="./resources/data/rand$i.data"
    ./resources/data/rand $frand $key
    i+=1
done < "$filename"