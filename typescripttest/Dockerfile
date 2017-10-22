FROM node:latest
MAINTAINER Jacopo Mangiavacchi
LABEL Description="Docker image for running Jacopo Node.JS test microservice."

USER root

# Expose default port for Kitura
EXPOSE 8090

# Bundle application source & binaries
RUN mkdir /root/nodetest
COPY . /root/nodetest

# Command to start Node.JS application
CMD [ "sh", "-c", "cd /root/nodetest && node dist/server.js" ]
