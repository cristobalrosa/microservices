Crosa labs example application
=================================

This is just a simple and small application to demostrate how you can use kafka, elasticsearch and Spring boot to create
an microservice ecosystem. 

The application will only have 2 microservices:
 - windows-agent: Simulates an application running in windows hosts and creating events that will be sent to kafka
 - event-analyzer: Simulates the event processor. It will read the events from kafka and then it will store those events into an ES cluster.
 

## Running the application in docker

If you want to know how to build the docker environment to run the app you will find detailed information
in the README.md file under the docker folder.