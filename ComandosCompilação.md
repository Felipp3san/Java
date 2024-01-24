# Comandos de compilação

javac -sourcepath ./src/ -d ./out/ ./src/presentation/StartConsole.java 
javac -cp lib/mysql-connector-java-8.0.27.jar -sourcepath ./src/ -d ./out/ ./src/presentation/StartConsole.java


# Comandos de execução

java -classpath ./out presentation.StartConsole
java -cp lib/mvsal-connector-iava-8.0.27.iar:./out/ presentation.StartConsole
