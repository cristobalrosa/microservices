Crosa labs docker compose file.
==================================================

I have developed this example in windows 10 home edition. As you probably know, 
you cannot run docker directly in this OS but you can use docker machine to do it. 

Docker machine will create a virtual image, using virtualbox for instance, and it will run docker
into that image. 

A detailed explanation on how Docker Machine works can be found here:
- https://www.sitepoint.com/docker-windows-10-home/

To run this demo app you will need:

- Install Maven, see https://maven.apache.org/download.cgi
- Install Virtual Box from https://www.virtualbox.org/wiki/Downloads (if you are using windows)
- Install Docker Compose, see
https://docs.docker.com/compose/#installation-and-set-up
- Install Docker, see https://docs.docker.com/installation/
- Install Docker Machine, see https://docs.docker.com/machine/#installation

## Instructions.
- Go to directory `crosalabs` and run `mvn package` there
- If you want to use Docker Machine:
  - Execute `docker-machine create  --virtualbox-memory "4096" --driver virtualbox crosa-labs` . This will create a new environment called `crosa-labs`with Docker
    Machine. It will be virtual machine in Virtual Box with 4GB RAM. You will need to do this only once 
    to create the virtual image that docker machine will use to run docker.
  -  I'm using powershell so to setup the environment i'm running this command: `& "docker-machine.exe" env crosa-labs | Invoke-Expression`
- Change to the directory `docker` and run `docker-compose
   build` followed by `docker-compose up`. 
- Once you have created the docker machine you don't need to create it anymore. You can start it by running
`docker-machine start crosa-labs`

### Some useful commands

- If you want to know the ip of your virtual machine you can run `docker-machine ip crosa-labs`
- Use `docker-machine rm crosa-labs` to destroy the virtual machine.
- Use `docker-compose down --remove-orphans` to stop the docker containers and remove the orphans.
- To ssh into the docker-machine: `docker-machine ssh crosa-labs` 

## Apps running in the docker compose environment. 

This docker compose setups:
 - kafka
 - kafdrop (Monitoring tool for kafka) -> http://ipadresss:9000/
 - ElasticSearch
 - Kibana -> http://ipadresss:5601/
 - Windows Agent UI: http://ipaddress:8081/

## Issues
https://github.com/elastic/elasticsearch-docker/issues/92

## References
- https://medium.com/@TimvanBaarsen/how-to-run-an-elasticsearch-7-x-single-node-cluster-for-local-development-using-docker-compose-2b7ab73d8b82
- https://dzone.com/articles/introduction-to-event-streaming-with-kafka-and-kaf-1