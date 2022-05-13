# jdk 11 on ubuntu
# see https://developer.salesforce.com/docs/atlas.en-us.salesforce_developer_environment_tipsheet.meta/salesforce_developer_environment_tipsheet/salesforce_developer_environment_java_stubs.htm

java -classpath force-wsc-54.0.0.jar:js-1.7R2.jar:ST4-4.3.3.jar:antlr-runtime-3.5.2.jar com.sforce.ws.tools.wsdlc wsdl.xml enterprise_stub.jar
