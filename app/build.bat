@echo off

title ums build

cd ../src/ums/
call mvn clean install

@pause