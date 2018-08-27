@echo off

cd jar/ums
java -jar -Xms32m -Xmx64m -XX:CompressedClassSpaceSize=64m ums.jar

pause