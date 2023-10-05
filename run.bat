cls

javac -d bin\ .\src\MyApp.java .\src\Library\* .\src\PersoalanSPL\* && jar cvmf MANIFEST.MF FinalApp.jar bin\MyApp.class bin\Library\* bin\PersoalanSPL\*  && java -jar FinalApp.jar
:: untuk compile semua file di source (src) menjadi binary (bin)


:: output berupa FinalApp.jar, butuh file manifest dan class dari bin


:: running file FinalApp.jar