# hpOM Listener - HP Operations Manager Listener for AppDynamics

This project provides a RESTful web service based on the Dropwizard framework that receives 
events from an AppDynamics Controller.  The events are consumed by the web service, 
reformatted to create a message and then sent to the HP Operations Manager.

## Things to Do

*  Add opcmsg API to send message to HP Operations Manager.
*  Add basic authentication.
*  Add ability to store encrypted password.
*  Add HTTPS support.
*  Add logging.
*  Add health check URL to determine health of hpOM Listener
