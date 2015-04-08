# YAKA COMPILER MAKEFILE PROJECT
# Works on linux distributions

# Vars

TESTS = compiler.OperateurTest compiler.ExpressionTest compiler.YvmTest compiler.YvmAsmTest compiler.TabIdentTest

# Targets

defaut: javacc javac

javacc:
	@mkdir -p class
	@javacc -OUTPUT_DIRECTORY=src/generated src/Yaka.jj

javac:
	@echo -n "Compiling... "
	@find . -name "*.java" -print | xargs javac -d class -classpath .:/usr/share/java/junit4.jar
	@echo DONE


test: javac
	@java -classpath class:/usr/share/java/junit4.jar org.junit.runner.JUnitCore $(TESTS)

clean:
	-@rm -rf class
	-@rm -rf src/generated
	-@rm -rf *.asm *.exe *.lst *.obj *.map

package: defaut
	-@rm -rf Yaka.jar
	@cd class; jar cmvf ../META-INF/MANIFEST.MF Yaka.jar generated/*.class compiler/*.class
	@mv class/Yaka.jar Yaka.jar

download-tasm:
	@wget https://www.dropbox.com/s/lv5j7wxk402934e/tasm.zip?raw=1 -O tasm.zip
	@unzip -o tasm.zip > /dev/null
	@sudo sysctl -w vm.mmap_min_addr=0
	@dosemu -dumb "./tasm/BIN/TASM.EXE"

generate-asm:
	@java -jar Yaka.jar $(FILE) > out.asm

compile-asm:
	@dosemu -dumb "./asm-dosemu.bat" 2> /dev/null
	@test -e biblio.obj
	@test -e out.obj
	@test -e out.exe

run-asm:
	@sudo sysctl -w vm.mmap_min_addr=0
	@make generate-asm FILE=$(FILE)
	@make compile-asm
	@dosemu -dumb out.exe
