set projectLocation=C:\Users\urvashi\git\LocalRepositoryUAT\bluAndroid
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java -cp %classpath% org.testng.TestNG %projectLocation%\jenkinsTestNG.xml
pause
