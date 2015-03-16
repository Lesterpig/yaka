@echo off
REM From : https://github.com/nhurman/yaka-compiler/blob/master/bin/asm.bat

X:\tasm /l /zi lib\biblio.asm

X:\tasm /l /zi %1.asm

X:\tlink /v %1 %1 biblio.obj