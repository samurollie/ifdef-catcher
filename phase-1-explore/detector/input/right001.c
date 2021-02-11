#include <stdio.h>
#include <conio.h>

#define NUMBER 1

void main() {
    #ifdef expression_1
    if (condition_1 && condition_2) {
        // lines of code
    }
    #else
    if (condition_1) {
        // lines of code
    }
    #endif
}