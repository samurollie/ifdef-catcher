int main(){
mfp->mf_fd = open((char *)fname, new ? (O_CREAT | O_RDWR | O_TRUNC) : (O_RDONLY)
#if defined(AMIGA)
);
#endif
#if defined(UNIX)
#if defined(SCO)
, (mode_t)0600);
#else
, 0600);
#endif
#endif
#if defined(MSDOS)
, S_IREAD | S_IWRITE);
#endif
}
