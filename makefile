# YAKA COMPILER MAKEFILE PROJECT
# Works on linux distributions

# Vars

TESTS = compiler.OperateurTest compiler.ExpressionTest compiler.YvmTest compiler.YvmAsmTest compiler.DeclarationTest

# Targets

defaut: javacc javac

usage help:
	@echo "Usage: make [command] [option=value]"
	@echo
	@echo "    make         : builds the compiler"
	@echo "    make help    : displays this page"
	@echo "    make javacc  : only builds the Yaka.jj file"
	@echo "    make javac   : only builds java to class files"
	@echo "    make run     : runs the compiler"
	@echo "         option ->  make run FILE=pathToFile"
	@echo "    make test    : runs the test (needs JUnit4)"
	@echo "    make package : packages the compiler in a .jar file"
	@echo "    make clean   : removes generated files"
	@echo

javacc:
	@mkdir -p class
	@javacc -OUTPUT_DIRECTORY=src/generated src/Yaka.jj

javac:
	@echo -n "Compiling... "
	@find . -name "*.java" -print | xargs javac -d class -classpath .:/usr/share/java/junit4.jar
	@echo DONE

run:
	@java -classpath class generated.Yaka $(FILE)

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
	@wget http://dufour.tk/~quentin/tasm.zip
	@unzip tasm.zip
	@sudo sysctl -w vm.mmap_min_addr=0
	@dosemu -dumb "./tasm/BIN/TASM.EXE"

generate-asm:
	@java -jar Yaka.jar $(FILE) > out.asm

compile-asm: 
	@dosemu -dumb "./asm-dosemu.bat" 2> /dev/null
	@test -e biblio.obj
	@test -e out.obj
	@test -e out.exe

test-asm: package
	@make generate-asm FILE=example/exp.yaka
	@make compile-asm

