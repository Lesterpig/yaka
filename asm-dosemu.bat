@echo off
REM From : https://github.com/nhurman/yaka-compiler/blob/master/bin/asm.bat

tasm\BIN\TASM.EXE /l /zi lib\biblio.asm

tasm\BIN\TASM.exe /l /zi out.asm

tasm\BIN\TLINK.exe /v out out biblio.obj
