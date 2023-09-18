cls

javac -d bin\ src\*.java 
:: untuk compile semua file di source (src) menjadi binary (bin)

jar cvmf MANIFEST.MF FinalApp.jar bin/*.class
:: output berupa FinalApp.jar, butuh file manifest dan class dari bin

java -jar FinalApp.jar
:: running file FinalApp.jar