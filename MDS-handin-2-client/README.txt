This project contains what the exercise describe as the "Client Application".

It contains three java files.

A CompositeClient class, which acts as a Client to what is described as the "Composite web Service in Java".
A Task class, which is used to have java objects representing the XML task data.
A TaskSerializer, which is used to convert back and forth between Task java objects and Task XML.

The TaskSerializer contains a main method to test the functionality of the serializer itself.
In order to test client project as a whole. The MDS-handin-2 project should be deployed in Netbeans first. Then, the main method of the CompositeClient should be run.


NOTE: All main methods have been hardcoded to overwrite the args array.
This has been done since we still haven't been able to figure out how to supply arguments to main methods in Netbeans. In order to test the full capabilites of these main methods, the hardcoded part should either be removed or edited.

If you know how to fix this problem, please tell us. :-)