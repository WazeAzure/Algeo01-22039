#!/bin/bash

clear

javac -d ./bin ./src/*.java

jar cmvf ./MANIFEST.MF FinalApp.jar  ./bin/*.class
java -jar FinalApp.jar
