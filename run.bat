call mvn clean
call mvn package
call mvn jacoco:report
call simian.bat
pause