This project contains what the exercise describe as the "Composite web Service in Java".

It contains four java files.

A RestClient and a SoapClient class, which implements the IClient interface.
The RestClient, acts as a client to what is described as the "Task Manager REST Service (.Net technology)".
The SoapClient, acts as a client to what is described as the "Task Manager SOAP Service (.Net technology)".

The CompositeServer is a SOAP-service, which uses the SoapClient and RestClient to orchestrate the .Net services. Using the serviceOption integer in a switch, the client can choose which .Net services this java services should act as a proxy for.


Both the SoapClient and RestClient, have main methods to test the clients themselves.
In order to test the service as a whole. The project should be deployed in Netbeans. Then, the webservice can either be tested in the browser, by running the main method of the CompositeClient in the MDS-handin-2-client project.


NOTE: All main methods have been hardcoded to overwrite the args array.
This has been done since we still haven't been able to figure out how to supply arguments to main methods in Netbeans. In order to test the full capabilites of these main methods, the hardcoded part should either be removed or edited.

If you know how to fix this problem, please tell us. :-)