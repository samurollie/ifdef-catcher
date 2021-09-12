Project list:

- [x] apache
- [x] cherokee
- [x] dia
- [ ] glibc
- [ ] gnumeric
- [ ] gnuplot
- [ ] lighttpd
- [ ] openldap
- [ ] tcl
- [ ] xterm


# apache

https://github.com/apache/httpd

### 69b41bc90b8977a446ff127a6263f647d0a1d00e modules/proxy/mod_proxy_hcheck.c

Url: https://github.com/apache/httpd/commit/69b41bc90b8977a446ff127a6263f647d0a1d00e <br>
Commiter: name = Jim Jagielski; email = jim@apache.org <br>
File: modules/proxy/mod_proxy_hcheck.c <br>
Mar 29, 2016

![image](https://user-images.githubusercontent.com/5865045/131277497-2ca7d14c-8996-4a15-905c-d4a9ea49803d.png)

### 9d0cc0b345485f93863012137836bf324d43c8d4 modules/ssl/ssl_engine_kernel.c

Url: https://github.com/apache/httpd/commit/9d0cc0b345485f93863012137836bf324d43c8d4 <br>
Commiter: name = Rainer Jung; email = rjung@apache.org <br>
File: modules/ssl/ssl_engine_kernel.c <br>
Feb 12, 2016

![image](https://user-images.githubusercontent.com/5865045/131277682-c42025ad-0f25-48aa-8102-981a08d81285.png)

### 1e57700c314751b07bc8d9c22a88d8ad6175dc3f modules/slotmem/mod_slotmem_shm.c

Url: https://github.com/apache/httpd/commit/1e57700c314751b07bc8d9c22a88d8ad6175dc3f <br>
Commiter: name = Yann Ylavic; email = ylavic@apache.org <br>
File: modules/slotmem/mod_slotmem_shm.c <br>
Sep 15, 2015

![image](https://user-images.githubusercontent.com/5865045/131277756-e55b9f64-4df2-4466-a0a1-4fe2ab29578d.png)

### a97bec21dc7ff2eb945946e02db876da9f25b083 modules/ssl/ssl_engine_init.c

Url: https://github.com/apache/httpd/commit/a97bec21dc7ff2eb945946e02db876da9f25b083 <br>
Commiter: name = Stefan Fritsch; email = sf@apache.org <br>
File: modules/ssl/ssl_engine_init.c <br>
Dec 29, 2011

![image](https://user-images.githubusercontent.com/5865045/131277863-8b90f444-714f-44b4-b4b4-e0e66d6ae83d.png)

### e155f87c68be8a64250acf28bf00ff42a02f2c97 modules/session/mod_session_crypto.c

Url: https://github.com/apache/httpd/commit/e155f87c68be8a64250acf28bf00ff42a02f2c97 <br>
Commiter: name = Graham Leggett; email = minfrin@apache.org <br>
File: modules/session/mod_session_crypto.c <br>
May 15, 2011

![image](https://user-images.githubusercontent.com/5865045/131277943-f53b8bef-775b-407b-a9e1-9a268cd82e23.png)

### 41d7f374af6e9fbc9e8760125b5faef36c14762b modules/ssl/ssl_engine_kernel.c

Url: https://github.com/apache/httpd/commit/41d7f374af6e9fbc9e8760125b5faef36c14762b <br>
Commiter: name = Joe Orton; email = jorton@apache.org <br>
File: modules/ssl/ssl_engine_kernel.c <br>
Feb 4, 2010

![image](https://user-images.githubusercontent.com/5865045/131278003-350ff590-7f5a-4910-b730-98d8dadfc830.png)

# cherokee

https://github.com/cherokee/webserver

no results

# dia

https://github.com/GNOME/dia

### 576e5ff1f4e756ec95e0a6b6694dea4632563065 lib/diagdkrenderer.c

Url: https://github.com/GNOME/dia/commit/576e5ff1f4e756ec95e0a6b6694dea4632563065 <br>
Commiter: name = Hans Breuer; email = hans@breuer.org <br>
File: lib/diagdkrenderer.c <br>
May 26, 2008

![image](https://user-images.githubusercontent.com/5865045/131278200-0a428a2e-c4a0-4870-a780-998e0871953c.png)


# glibc

Project url: https://github.com/bminor/glibc

### 62f63c47ee9d6dcac1f47870e8038f1b1889e0d4 sysdeps/unix/opendir.c

Url: https://github.com/bminor/glibc/commit/62f63c47ee9d6dcac1f47870e8038f1b1889e0d4#diff-44cfa2e1fa37d3e8f6d11a9325b10e80cd99325052d21299e46ce46623ed03d2L178<br>
Committer: name=Ulrich Drepper; email=drepper@redhat.com<br>
File: sysdeps/unix/opendir.c<br>
Feb 5, 2009

<table>
<tr>

<td>

Before - Non disciplined
```C
#ifdef _STATBUF_ST_BLKSIZE
  if (__builtin_expect ((size_t) statp->st_blksize >= sizeof (struct dirent64),
			1))
    allocation = statp->st_blksize;
  else
#endif
    allocation = default_allocation;
```

</td>

<td>

After - Disciplined
```C
  size_t allocation = default_allocation;
#ifdef _STATBUF_ST_BLKSIZE
  if (statp != NULL && default_allocation < statp->st_blksize)
    allocation = statp->st_blksize;
#endif
```

</td>

</tr>
</table>

# gnumeric

Project url: https://github.com/GNOME/gnumeric

### 74b852b96964e7a51f14fb562d4235f5a5c781bc src/wbc-gtk.c

Url: https://github.com/GNOME/gnumeric/commit/74b852b96964e7a51f14fb562d4235f5a5c781bc#diff-006e796e15d931049e16264538d2c9709bb7020414e9998a49add13239ce88d6R2557 <br>
Commiter: name=Morten Welinder; email=terra@gnome.org<br>
File: src/wbc-gtk.c<br>
Sep 18, 2009

<table>
<tr>

<td>

Before - Disciplined
```C
#ifdef GNM_USE_HILDON
	go_action_combo_text_set_width (wbcg->zoom,  "100000000%");
#else
	go_action_combo_text_set_width (wbcg->zoom,  "10000%");
#endif
```

</td>

<td>

After - Non Disciplined
```C
	go_action_combo_text_set_width (wbcg->zoom_haction,
#ifdef GNM_USE_HILDON
					"100000000%"
#else
					"10000%"
#endif
					);
```

</td>

</tr>
</table>

# gnuplot

Project url: https://github.com/gnuplot/gnuplot

### 97083d929b9b7f41ad7ebf8358b4f7235c840ab0 src/readline.c

Url: https://github.com/gnuplot/gnuplot/commit/97083d929b9b7f41ad7ebf8358b4f7235c840ab0#diff-65c509c956bffbcf49dae610fc109982d93c173a2dcd6a75ba56cfafefd5d37dL1167 <br>
Commiter: name=Bastian Maerkisch; email=bmaerkisch@web.de <br>
File: src/readline.c <br>
Feb 12, 2014

<table>
<tr>

<td>

Before - Disciplined
```C
#ifdef DJGPP
	c = ch & 0xff;
#else /* not DJGPP */
# ifdef OS2
	c = getc(stdin);
# else				/* not OS2 */
	c = getch();		/* Get the extended code. */
# endif				/* OS2 */
#endif /* not DJGPP */
```

</td>

<td>

After - Non Disciplined
```C
#ifdef DJGPP
	c = ch & 0xff;
#elif defined(OS2)
	c = getc(stdin);
#else /* not OS2, not DJGPP */
# if defined (USE_MOUSE)
    if (term && term->waitforinput && interactive)
	c = term->waitforinput(0);
    else
# endif /* not USE_MOUSE */
	c = getch();		/* Get the extended code. */
#endif /* not DJGPP, not OS2 */
```

</td>

</tr>
</table>

### 80ef508ee9da9df6ce964a97434ca600e3406f09 src/win/wgraph.c

Url: https://github.com/gnuplot/gnuplot/commit/80ef508ee9da9df6ce964a97434ca600e3406f09#diff-b4c52e02d2a17ca8dc8b8d350854c047bd93bf0d89b78869f7e173b7c3e29c34L1353 <br>
Committer: name=Bastian Maerkisch; email=bmaerkisch@web.de<br>
File: src/win/wgraph.c<br>
May 14, 2011

<table>
<tr>

<td>

Before - Disciplined
```C
#ifdef HAVE_GDIPLUS
				if (!lpgw->antialiasing)
					Polyline(hdc, ppt, polyi);
				else
					gdiplusPolyline(hdc, ppt, polyi, &cur_penstruct);
#else
				Polyline(hdc, ppt, polyi);
#endif
```

</td>

<td>

After - Non Disciplined
```C
#ifdef HAVE_GDIPLUS
				if (lpgw->antialiasing)
					gdiplusPolyline(hdc, ppt, polyi, &cur_penstruct);
				else
#endif
					Polyline(hdc, ppt, polyi);
```

</td>

</tr>
</table>

# lighttpd

Project url: https://github.com/lighttpd/lighttpd1.4

### 31250a9af8a19e89ffcc58c18e94bace8b58de7a src/http_auth.c

Url: https://github.com/lighttpd/lighttpd1.4/commit/31250a9af8a19e89ffcc58c18e94bace8b58de7a#diff-7982a0e0a2237b01732b5e646eed80d7af77a69956890c90ba523b97c3fd6897L551 <br>
Committer: name=Glenn Strauss; email=gstrauss@gluelogic.com<br>
File: src/http_auth.c
Aug 14, 2016

<table>
<tr>
<td>

Before - Non Disciplined
```C
			char *crypted;
#if defined(HAVE_CRYPT_R)
			struct crypt_data crypt_tmp_data;
			crypt_tmp_data.initialized = 0;
#endif

			/* a simple DES password is 2 + 11 characters. everything else should be longer. */
			if (buffer_string_length(password) < 13) {
				return -1;
			}

#if defined(HAVE_CRYPT_R)
			if (0 == (crypted = crypt_r(pw, password->ptr, &crypt_tmp_data))) {
#else
			if (0 == (crypted = crypt(pw, password->ptr))) {
#endif
				/* crypt failed. */
				return -1;
			}
```

</td>

<td>

After - Disciplined
```C
            char *crypted;
#if defined(HAVE_CRYPT_R)
			struct crypt_data crypt_tmp_data;
			crypt_tmp_data.initialized = 0;
			crypted = crypt_r(pw, password->ptr, &crypt_tmp_data);
#else
			crypted = crypt(pw, password->ptr);
#endif
			if (NULL != crypted) {
				rc = strcmp(password->ptr, crypted);
	
	    	}
```

</td>

</tr>
</table>

### a6218765c28cbbaf8bae0cc46f9095a41479e488 src/configfile.c

Url: https://github.com/lighttpd/lighttpd1.4/commit/a6218765c28cbbaf8bae0cc46f9095a41479e488#diff-afb560d2f7859e4dc7bd0b22ad6dbda82529fc07f7ebb69b69b1cf98904ab1bdL1009<br>
Committer: name=Stefan B?hler; email=stbuehler@web.de<br>
File: src/configfile.c
Mar 7, 2009

<table>
<tr>
<td>

Before - Non Disciplined
```C
	pos = strrchr(fn,
#ifdef __WIN32
			'\\'
#else
			'/'
#endif
			);
```

</td>

<td>

After - Disciplined
```C
#ifdef __WIN32
	pos = strrchr(fn, '\\');
#else
	pos = strrchr(fn, '/');
#endif
```

</td>

</tr>
</table>

# openldap

Project url: https://github.com/openldap/openldap

### d886593193a69d63a4a4c484089b570e6af58df7 libraries/liblmdb/mdb.c

Url: https://github.com/openldap/openldap/commit/d886593193a69d63a4a4c484089b570e6af58df7#diff-df708c5d1a7a691b1840b3409e5f2365e077e77eaf6ed8e78a85089d67fa7dd2L4631<br>
Committer: name=Hallvard Furuseth; email=hallvard@openldap.org<br>
File: libraries/liblmdb/mdb.c<br>
Jun 15, 2016

<table>
<tr>
<td>

Before - Non Disciplined
```C
		if ((rc = pthread_mutexattr_init(&mattr))
			|| (rc = pthread_mutexattr_setpshared(&mattr, PTHREAD_PROCESS_SHARED))
#ifdef MDB_ROBUST_SUPPORTED
			|| (rc = pthread_mutexattr_setrobust(&mattr, PTHREAD_MUTEX_ROBUST))
#endif
			|| (rc = pthread_mutex_init(env->me_txns->mti_rmutex, &mattr))
			|| (rc = pthread_mutex_init(env->me_txns->mti_wmutex, &mattr)))
			goto fail;
```

</td>

<td>

After - Disciplined
```C
		rc = pthread_mutexattr_setpshared(&mattr, PTHREAD_PROCESS_SHARED);
#ifdef MDB_ROBUST_SUPPORTED
		if (!rc) rc = pthread_mutexattr_setrobust(&mattr, PTHREAD_MUTEX_ROBUST);
#endif
		if (!rc) rc = pthread_mutex_init(env->me_txns->mti_rmutex, &mattr);
		if (!rc) rc = pthread_mutex_init(env->me_txns->mti_wmutex, &mattr);
		pthread_mutexattr_destroy(&mattr);
		if (rc)
			goto fail;
```

</td>

</tr>
</table>

### de5b6893081a5dd64a8bcbbaa8adb8934062fe29 libraries/liblmdb/mdb.c

Url: https://github.com/openldap/openldap/commit/de5b6893081a5dd64a8bcbbaa8adb8934062fe29#diff-df708c5d1a7a691b1840b3409e5f2365e077e77eaf6ed8e78a85089d67fa7dd2L3697<br>
Committer: name=Hallvard Furuseth; email=hallvard@openldap.org<br>
File: libraries/liblmdb/mdb.c<br>
Oct 25, 2015

<table>
<tr>
<td>

Before - Non Disciplined
```C
		    ptr = env->me_map;
			if (toggle) {
#ifndef _WIN32	/* POSIX msync() requires ptr = start of OS page */
				if (meta_size < env->me_os_psize)
					meta_size += meta_size;
				else
#endif
					ptr += meta_size;
			}
```

</td>

<td>

After - Disciplined
```C
			ptr = (char *)mp - PAGEHDRSZ;
#ifndef _WIN32	/* POSIX msync() requires ptr = start of OS page */
			r2 = (ptr - env->me_map) & (env->me_os_psize - 1);
			ptr -= r2;
			meta_size += r2;
#endif
```

</td>

</tr>
</table>

### d6d2638acc245116b8f091ac425b6700d06c4713 libraries/liblmdb/mdb.c

Url: https://github.com/openldap/openldap/commit/d6d2638acc245116b8f091ac425b6700d06c4713#diff-df708c5d1a7a691b1840b3409e5f2365e077e77eaf6ed8e78a85089d67fa7dd2L2484<br>
Committer: name=Hallvard Furuseth; email=hallvard@openldap.org<br>
File: libraries/liblmdb/mdb.c<br>
Jun 22, 2013

<table>
<tr>
<td>

Before - Non Disciplined
```C
#ifdef _WIN32
		if (!ReadFile(env->me_fd, &pbuf, MDB_PAGESIZE, (DWORD *)&rc, NULL) || rc == 0)
#else
		if ((rc = read(env->me_fd, &pbuf, MDB_PAGESIZE)) == 0)
#endif
		{
			return ENOENT;
		}
		else if (rc != MDB_PAGESIZE) {
			err = ErrCode();
			if (rc > 0)
				err = MDB_INVALID;
			DPRINTF("read: %s", strerror(err));
			return err;
		}
```

</td>

<td>

After - Disciplined
```C
#ifdef _WIN32
		DWORD len;
		OVERLAPPED ov;
		memset(&ov, 0, sizeof(ov));
		ov.Offset = off;
		rc = ReadFile(env->me_fd,&pbuf,MDB_PAGESIZE,&len,&ov) ? (int)len : -1;
#else
		rc = pread(env->me_fd, &pbuf, MDB_PAGESIZE, off);
#endif
		if (rc != MDB_PAGESIZE) {
			if (rc == 0 && off == 0)
				return ENOENT;
			rc = rc < 0 ? (int) ErrCode() : MDB_INVALID;
			DPRINTF("read: %s", mdb_strerror(rc));
			return rc;
		}
```

</td>

</tr>
</table>

### 93da727d86c72b8f21e24e90dd010dafcb35a9d7 libraries/liblutil/detach.c

Url: https://github.com/openldap/openldap/commit/93da727d86c72b8f21e24e90dd010dafcb35a9d7#diff-c4d570878ed9d50b977d1c87f901251fa62c30d8c07ec5e6b54cc61446e03e76L73<br>
Committer: name=Howard Chu; email=hyc@openldap.org<br>
File: libraries/liblutil/detach.c<br>
Feb 28, 2011

<table>
<tr>
<td>

Before - Non Disciplined
```C
#ifdef HAVE_THR
			switch ( fork1() )
#else
			switch ( fork() )
#endif
			{
			case -1:
				sleep( 5 );
				continue;

			case 0:
				break;

			default:
				_exit( EXIT_SUCCESS );
			}
```

</td>

<td>

After - Disciplined
```C
#ifdef HAVE_THR
			pid = fork1();
#else
			pid = fork();
#endif
			switch ( pid )
			{
			case -1:
				sleep( 5 );
				continue;

			case 0:
				break;

			default:
				return pid;
			}
```

</td>

</tr>
</table>





# tcl

Project url: https://github.com/tcltk/tcl

no results

# xterm

Project url: https://github.com/ThomasDickey/xterm-snapshots

### 13b6768b6cd71310010c0a0ae2a83b779ade0c74 misc.c

Url: https://github.com/ThomasDickey/xterm-snapshots/commit/13b6768b6cd71310010c0a0ae2a83b779ade0c74#diff-ff8e8bc39c66c66077c9b645473ac3ddebbf6bd878bed0e64c4ad0d6513828a3L586<br>
Committer: name=Thomas E. Dickey; email=dickey@invisible-island.net<br>
File: misc.c<br>
Jan 12, 2020
<table border="0">
<tr>
<td>

Before - Non Disciplined
```C
	} else if ((screen->send_mouse_pos == ANY_EVENT_MOUSE
#if OPT_DEC_LOCATOR
		    || screen->send_mouse_pos == DEC_LOCATOR
#endif /* OPT_DEC_LOCATOR */
		   )
		   && event.xany.type == MotionNotify
		   && event.xcrossing.window == XtWindow(xw)) {
	    SendMousePosition(xw, &event);
	    xtermShowPointer(xw, True);
```

</td>
<td>

After - Disciplined
```C
	} else if (event.xany.type == MotionNotify
		   && event.xcrossing.window == XtWindow(xw)) {
	    switch (screen->send_mouse_pos) {
	    case BTN_EVENT_MOUSE:
	    case ANY_EVENT_MOUSE:
#if OPT_DEC_LOCATOR
	    case DEC_LOCATOR:
#endif /* OPT_DEC_LOCATOR */
		SendMousePosition(xw, &event);
		xtermShowPointer(xw, True);
```

</td>
</tr>
</table>

### 45f2b5178a8cdda39dbab929833b0d08d053887a util.c

Url: https://github.com/ThomasDickey/xterm-snapshots/commit/45f2b5178a8cdda39dbab929833b0d08d053887a#diff-e21ac3db1fb1f4c2521d0911da5ca3414412766a9aecad6c4c0cfad5d67b5165L2311<br>
Committer: name=Thomas E. Dickey; email=dickey@invisible-island.net<br>
File: util.c<br>
Jun 21, 2019

<table>
<tr>
<td>

Before - Disciplined
```C
#if OPT_DOUBLE_BUFFER
		XFillRectangle(screen->display, draw,
			       FillerGC(xw, screen),
			       x, y, (unsigned) w2, (unsigned) h2);
#else
		XClearArea(screen->display, VWindow(screen),
			   x, y, (unsigned) w2, (unsigned) h2, False);
#endif
```

</td>

<td>

After - Non Disciplined
```C
#if OPT_DOUBLE_BUFFER
		if (resource.buffered) {
		    XFillRectangle(screen->display, draw,
				   FillerGC(xw, screen),
				   x, y, (unsigned) w2, (unsigned) h2);
		} else
#endif
		    XClearArea(screen->display, VWindow(screen),
			       x, y, (unsigned) w2, (unsigned) h2, False);
```

</td>

</tr>
</table>

