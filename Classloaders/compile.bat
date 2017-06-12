@ECHO OFF
javac -sourcepath ./src -d bin src/com/epam/classloaders/main/Main.java
javac -sourcepath ./src -d bin src/com/epam/example1/main/Main.java
java -cp ./bin com.epam.classloaders.main.Main com.epam.example1.main.Main
