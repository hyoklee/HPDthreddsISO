#!/bin/sh
#
ulimit -n 2048
#
CATALINA_HOME="/home/tomcat8887/apache-tomcat-7.0.93"
export CATALINA_HOME
CATALINA_BASE="/home/tomcat8887/apache-tomcat-7.0.93"
export CATALINA_BASE
JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.191.b12-1.el7_6.x86_64/jre"
export JAVA_HOME
CONTENT_ROOT="-Dtds.content.root.path=/home/tomcat8887/apache-tomcat-7.0.93/content"
NORMAL="-d64 -Xmx4090m -Xms512m -server"
MAX_PERM_GEN="-XX:MaxPermSize=256m"
HEADLESS="-Djava.awt.headless=true"
JAVA_PREFS_SYSTEM_ROOT="-Djava.util.prefs.systemRoot=$CATALINA_HOME/content/thredds/javaUtilPrefs -Djava.util.prefs.userRoot=$CATALINA_HOME/content/thredds/javaUtilPrefs"
#             
JAVA_OPTS="$CONTENT_ROOT $NORMAL $MAX_PERM_GEN $HEADLESS $JAVA_PREFS_SYSTEM_ROOT"
export JAVA_OPTS
