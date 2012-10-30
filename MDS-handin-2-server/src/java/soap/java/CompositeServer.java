package soap.java;

import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "CompositeServer")
@Stateless()
public class CompositeServer {

    private dot.net.IClient restClient;
    private dot.net.IClient soapClient;
    
    public CompositeServer() {
        restClient = new dot.net.RestClient();
        soapClient = new dot.net.SoapClient();
    }
    
    @WebMethod(operationName = "GetAttendantTasks")
    public String GetAttendantTasks(@WebParam(name = "attendantId") String attendantId, @WebParam(name = "serviceOption") int serviceOption) {
        switch(serviceOption) {
            // Use SOAP.
            case 1:
                return soapClient.getAttendantTasks(attendantId);
            // Use REST.
            case 2:
                return restClient.getAttendantTasks(attendantId);
            // Use both.
            case 3:
                return soapClient.getAttendantTasks(attendantId) + "\n###\n" + restClient.getAttendantTasks(attendantId);
            // Use none.
            default:
                return "No such service option: " + serviceOption;
        } 
    }

    @WebMethod(operationName = "CreateTask")
    @Oneway
    public void CreateTask(@WebParam(name = "taskXml") String taskXml, @WebParam(name = "serviceOption") int serviceOption) {
        switch (serviceOption) {
            // Use SOAP.
            case 1:
                soapClient.createTask(taskXml);
                break;
            // Use REST.
            case 2:
                restClient.createTask(taskXml);
                break;
            // Use both.
            case 3:
                soapClient.createTask(taskXml);
                restClient.createTask(taskXml);
                break;
            // Use none.
            default:
                break;
        }
    }

    @WebMethod(operationName = "DeleteTask")
    @Oneway
    public void DeleteTask(@WebParam(name = "taskId") String taskId, @WebParam(name = "serviceOption") int serviceOption) {
        switch(serviceOption) {
            // Use SOAP.
            case 1:
                soapClient.deleteTask(taskId);
                break;
            // Use REST.
            case 2:
                restClient.deleteTask(taskId);
                break;
            // Use both.
            case 3:
                soapClient.deleteTask(taskId);
                restClient.deleteTask(taskId);
                break;
            // Use none.
            default:
                break;
        }
    }
}
