## SCSB - GATEWAY

Shared Collection Service Bus

The SCSB Middleware codebase and components are all licensed under the Apache 2.0 license, with the exception of a set of API design components (JSF, JQuery, and Angular JS), which are licensed under MIT X11.

The SCSB application acts as a microservice which exposes the core functionality APIs used in the application. Each API call made in the SCSB application reaches this microservice application as a starting point and the microservice decides which microservice(scsb-circ,scsb-doc,scsb-etl,scsb-batch-scheduler) to be redirected to make the received request complete successfully.

## Software Required

  - Java 11
  - Docker 19.03.13   
  
## Prerequisite

1. **Cloud Config Server**

Dspring.cloud.config.uri=http://scsb-config-server:8888

## Build

Download the Project , navigate inside project folder and build the project using below command

**./gradlew clean build -x test**

## Docker Image Creation

**sudo docker build -t scsb-gateway .**

## Docker Run

User the below command to Run the Docker

**sudo docker run --name scsb-gateway -v /data:/recap-vol --label collect_logs_with_filebeat="true" --label decode_log_event_to_json_object="true"  -p 9093:9093 -e "ENV=-Dorg.apache.activemq.SERIALIZABLE_PACKAGES="*"  -Dspring.cloud.config.uri=http://scsb-config-server:8888 "  --network=scsb  -d scsb-gateway**
