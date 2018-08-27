@echo off

title ums manager

cd jar/ums
java -jar -Xms32m -Xmx64m -XX:CompressedClassSpaceSize=64m ums.jar
