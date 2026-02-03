#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../out/production/IP" ]
then
    mkdir -p ../out/production/IP
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# delete tasks.txt to start with clean state
if [ -e "./tasks.txt" ]
then
    rm tasks.txt
fi

if [ -e "../out/production/IP/tasks.txt" ]
then
    rm ../out/production/IP/tasks.txt
fi

# compile the code into the out folder
javac -cp ../src/main/java -Xlint:none -d ../out/production/IP ../src/main/java/roshan/*.java
if [ $? -ne 0 ]
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../out/production/IP roshan.Roshan < input.txt > ACTUAL.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
