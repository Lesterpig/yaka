defaut:
	@mkdir -p class
	@javacc -OUTPUT_DIRECTORY=src src/Yaka.jj
	@javac -d class src/*.java

run:
	@java -classpath class Yaka $(FILE)