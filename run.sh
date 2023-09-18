#!/bin/bash

clear

javac -d bin/ src/*.java

jar cvmf MANIFEST.MF FinalApp.jar  bin/*.class

java -jar FinalApp.jar
