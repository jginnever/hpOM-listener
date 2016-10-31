# hpOM Listener - HP Operations Manager Listener for AppDynamics

This project provides a RESTful web service based on the Dropwizard framework that receives 
events from an AppDynamics Controller.  The events are consumed by the web service, 
reformatted to create a message and then sent to the HP Operations Manager.

## How It Works
This web service executes as a standalone jar file.  It includes all of the libraries that are 
needed to provide a callable REST web service.  All that is needed is a Java JRE.  It has 
been tested with Java 1.7 and Java 1.8.

It is completely parameter driven.  The config.yml file contains these parameters: 

*  A user name and password that is used for basic authentication - userName and password.
*  The path (including the executable shell script) to the opcmsg command - pathToExecutable.
*  The name of the message group to be used by the HP Operations Manager - msgGroup.
*  The http and https ports - specified in the applicationConnectors section.
*  The key store path of the keystore containing the certificate - keyStorePath.
*  The password for the keystore - keyStorePassword.
*  log4J parameters for how logging is configured - loggers and appenders section.

This web service assumes that a JSON payload is sent with these name/value pairs:

*  Application - the name of the Application in the AppDynamics system that produced the event
*  MessageGroup - the name of the message group to be used in the HP Operations Manager
*  Object - the AppDynamics health rule that was violated to produce the event
*  Severity - the severity of the event
*  MsgText - the event detailed message
*  Node - the host of the violating process

This web service is meant to be called from the AppDynamics HTTP Request Action - 
https://docs.appdynamics.com/display/PRO42/HTTP+Request+Actions+and+Templates 

A jar file is included if there is a need to generate an encrypted password for the basic authentication.
To generate an encrypted password run the following command:

java -cp "appd-exts-commons-1.1.2.jar" com.appdynamics.extensions.crypto.Encryptor myKey myPassword
