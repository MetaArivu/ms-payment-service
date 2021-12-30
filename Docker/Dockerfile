#################################################################
#
# Microservice Template
# (C) MetaMagic Global Inc, NJ, USA, 2021
#
# Container Name: mock-order
#
# Microservices Containers - SpringBoot 2.5.3
#
# Version 0.1
#################################################################
# Base Version
FROM metamagic/java:8

LABEL author="Ketan Gote <ketan.gote@metamagic.in>"

RUN mkdir /Softwares/service
RUN mkdir /Softwares/service/libs
RUN mkdir /home/arivu/libs

ARG SPRINGBOOT_JAR=payment-service-1.0.jar

# SpringBoot Fat Jar
COPY --chown=arivu:arivu ${SPRINGBOOT_JAR} /Softwares/service/


# Change the Mode
RUN chmod -R 750 /Softwares/service/*

# Expose Microservice Port
EXPOSE 8080

# Start Microservice
ENTRYPOINT ["java","-jar","/Softwares/service/payment-service-1.0.jar"]
