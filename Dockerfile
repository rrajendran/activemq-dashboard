FROM tomcat:alpine
MAINTAINER ramesh.rajendran

ADD target/sqs-dashboard.war $CATALINA_HOME/webapps/
COPY sqs-dashboard.properties $CATALINA_HOME/lib

CMD ["catalina.sh", "run"]