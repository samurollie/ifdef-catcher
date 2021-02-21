/*
 * Project: VIM
 * File: memfile.c
 *
 * Syntax Error ID = 1
 *
 * Problem: !AMIGA && !UNIX && !MSDOS
 *
 * Solution: Version 5.0 solved this problem.
 *
 * How was it introduced? Adding new code in version 3.0
 *
 */


int main(){

mfp->mf_fd = open((char *)fname, new ? (O_CREAT | O_RDWR | O_TRUNC) : (O_RDONLY)

#ifdef AMIGA				/* Amiga has no mode argument */
					);
#endif
#ifdef UNIX					/* open in rw------- mode */
# ifdef SCO
					, (mode_t)0600);
# else
					, 0600);
# endif
#endif
#ifdef MSDOS				/* open read/write */
					, S_IREAD | S_IWRITE);
#endif

}
