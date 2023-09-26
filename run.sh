#!/bin/bash

clear

javac -d bin/ $(find ./src/* | grep .java) && jar cvmf MANIFEST.MF FinalApp.jar  bin/*.class && java -jar FinalApp.jar
