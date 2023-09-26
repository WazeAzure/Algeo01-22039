cls

javac -d bin\ .\src\MyApp.java .\src\Library\* .\src\PersoalanSPL\*
:: untuk compile semua file di source (src) menjadi binary (bin)

jar cvmf MANIFEST.MF FinalApp.jar bin\MyApp.class bin\Library\* bin\PersoalanSPL\* 
:: output berupa FinalApp.jar, butuh file manifest dan class dari bin

java -jar FinalApp.jar
:: running file FinalApp.jar