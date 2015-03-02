defaut:
	@mkdir -p class
	@javacc -OUTPUT_DIRECTORY=src src/Yaka.jj
	@javac -d class src/*.java

run:
	@java -classpath class Yaka $(FILE)

clean:
	-@rm -rf class
	-@rm src/ParseException.java
	-@rm src/SimpleCharStream.java
	-@rm src/Token.java
	-@rm src/TokenMgrError.java
	-@rm src/Yaka.java
	-@rm src/YakaConstants.java
	-@rm src/YakaTokenManager.java