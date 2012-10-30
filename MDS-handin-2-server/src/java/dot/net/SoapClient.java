package dot.net;

public class SoapClient implements IClient {

    private org.tempuri.ITaskManagerService port;

    /**
     * Use this to test out the SOAP Client.
     *
     * @param args Should either be "POST", "GET" or "DELETE".
     */
    public static void main(String[] args) {
        IClient client = new SoapClient();

        args = new String[1];
        //args[0] = "POST";
        args[0] = "GET";
        //args[0] = "DELETE";

        if (args.length < 1) {
            System.out.println("Please use either 'POST', 'GET' or 'DELETE' as argument");
            return;
        }

        if (args[0].equals("POST")) {
            String taskXml = "\n<task id =\"delete-me\" name=\"delete this task\" date=\"26-09-2012\" status=\"not-executed\">"
                    + "\n\t<description>This task should be deleted.</description>"
                    + "\n\t<attendants>the-hitmen</attendants>"
                    + "\n</task>\n\n";
            System.out.println(client.createTask(taskXml));
        } else if (args[0].equals("GET")) {
            String attendantId = "the-hitmen";
            System.out.println(client.getAttendantTasks(attendantId));
        } else if (args[0].equals("DELETE")) {
            String taskId = "delete-me";
            System.out.println(client.deleteTask(taskId));
        } else {
            System.out.println("Please use either 'POST', 'GET' or 'DELETE' as argument");
        }
    }

    public SoapClient() {
        org.tempuri.TaskManagerService service = new org.tempuri.TaskManagerService();
        port = service.getBasicHttpBindingITaskManagerService();
    }

    @Override
    public String createTask(java.lang.String taskXml) {
        return port.createTask(taskXml);
    }

    @Override
    public String deleteTask(java.lang.String taskId) {
        return port.deleteTask(taskId);
    }

    @Override
    public String getAttendantTasks(java.lang.String attendantId) {
        return port.getAttendantTasks(attendantId);
    }
}
