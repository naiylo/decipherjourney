#!/bin/bash

# Installiere Java (JDK 21)
apt-get update
apt-get install -y openjdk-21-jdk

# Installiere Maven
apt-get install -y maven

# Baue das Projekt
mvn clean install