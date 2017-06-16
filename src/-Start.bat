@echo off
cd %~dp0
del *.class
@echo on
javac *.java
pause
java Main
pause