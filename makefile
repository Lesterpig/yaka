defaut:
	@mkdir -p class
	@javacc -OUTPUT_DIRECTORY=src/generated src/Yaka.jj
	@find . -name "*.java" -print | xargs javac -d class

run:
	@java -classpath class generated.Yaka $(FILE)

clean:
	-@rm -rf class