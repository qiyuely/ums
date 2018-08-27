@echo off

title ums manager

cd lib/ums
java -jar -Xms32m -Xmx64m -XX:CompressedClassSpaceSize=64m ums.jar

@pause
