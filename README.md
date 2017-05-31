# datapath

***************************PROBLEM STATEMENT*****************************************************
Write a job-processor in java with REST interface
* a job processor should take job submits from a REST interface and work on them
* there are three type of jobs: CalculatePi, WaitFor20Seconds and FailImmediately
* the job-processor should schedule and work on these tasks using N workers
* the REST interface should provide a call to change the number of N while the job-processor is running
* the job-processor should accept for jobs which are defined as plugins, i.e. create a second mvn-module and write job CountWords without touching the job-processor itself
* use java8 where appropriate, use reflection were appropriate
**************************************************************************************************

****************************API REFERENCE*********************************************************
Get(GET method): /calculatePI/   Ex: curl -i -X GET http://localhost:8080/datapath/data/calculatePI
Get(GET method): /wait/          Ex: curl -i -X GET  http://localhost:8080/datapath/data/wait
Get(GET method): /fail/          Ex: curl -i -X GET  http://localhost:8080/datapath/data/fail
Get(PUT method): /update/{value} Ex: curl -i -X PUT  http://localhost:8080/datapath/data/update/{value}

****************************************************************************************************


