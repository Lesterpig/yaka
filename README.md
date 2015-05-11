Yaka
====

[![Build Status](https://travis-ci.org/Lesterpig/yaka.svg)](https://travis-ci.org/Lesterpig/yaka)

Yaka compiler - INSA Rennes Project.

**WARNING** : this is a project for EDUCATIONAL PURPOSES ONLY.
The code is really ugly, and should not be used in production.

Presentation
------------

Yaka is a simple langage for education purposes.

Usage
-----

```
make                    # generates the parser
make test               # execute unit tests
make run FILE=test.txt  # executes the parser for file test.txt
make download-tasm      # download tasm tools
make test-asm           # launch asm compilation
```

Dosemu tests
------------

```
dosemu -dumb "out.exe" 2> /dev/null
```

Note
----

Use SSH and not HTTPS to clone project under windows
