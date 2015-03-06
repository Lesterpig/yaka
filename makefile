# YAKA COMPILER MAKEFILE PROJECT
# Works on linux distributions

# Vars

TESTS = compiler.OperateurTest compiler.ExpressionTest

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
