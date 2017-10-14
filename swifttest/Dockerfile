FROM swift:4.0
MAINTAINER Jacopo Mangiavacchi
LABEL Description="Docker image for running Jacopo Swift test microservice."

USER root

# Expose default port for Kitura
EXPOSE 8080

# Bundle application source & binaries
RUN mkdir /root/swifttest
COPY . /root/swifttest

# Command to start Swift application
CMD [ "sh", "-c", "cd /root/swifttest && swift build && .build/debug/swifttest" ]
