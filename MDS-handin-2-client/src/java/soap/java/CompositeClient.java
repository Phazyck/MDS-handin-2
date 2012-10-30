package soap.java;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import soap.xml.Task;
import soap.xml.TaskSerializer;

public class CompositeClient {

    /**
     * Use this to test out the MDS-handin-2 project.
     *
     * @param args Should either be 'POST', 'GET' or 'DELETE' followed by '1',
     * '2' or '3'.
     */
    public static void main(String[] args) {
        CompositeClient client = new CompositeClient();

        args = new String[2];
        //args[0] = "POST";
        args[0] = "GET";
        //args[0] = "DELETE";

        //args[1] = "1";
        //args[1] = "2";
        args[1] = "3";

        if (args.length < 2) {
            System.out.println("Please use exactly two arguments.");
            System.out.println(args.length + " arguments are not valid.");
            return;
        }

        try {
            if (args[0].equals("POST")) {
                Task t;
                t = new Task();
                t.id = "delete-me";
                t.name = "delete this task";
                t.date = "01-10-2012";
                t.status = "not-executed";
                t.description = "This task should be deleted";
                t.attendants = "the-hitmen";
                
                String taskXml = "";
                try {
                    taskXml = TaskSerializer.serialize(t);
                } catch (JAXBException ex) {
                    Logger.getLogger(CompositeClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CompositeClient.class.getName()).log(Level.SEVERE, null, ex);
                }

                client.createTask(taskXml, Integer.parseInt(args[1]));
            } else if (args[0].equals("GET")) {
                String attendantId = "the-hitmen";
                System.out.println(client.getAttendantTasks(attendantId, Integer.parseInt(args[1])));
            } else if (args[0].equals("DELETE")) {
                String taskId = "delete-me";
                client.deleteTask(taskId, Integer.parseInt(args[1]));
            } else {
                System.out.println("Please use either 'POST', 'GET' or 'DELETE' as the first argument.");
                System.out.println(args[0] + " is not a valid argument.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please use either '1', '2' or '3' as the second argument.");
            System.out.println(args[1] + " is not a valid argument.");
        }
    }
    soap.java.CompositeServer port;

    public CompositeClient() {
        soap.java.CompositeServer_Service service = new soap.java.CompositeServer_Service();
        port = service.getCompositeServerPort();
    }

    public void createTask(java.lang.String taskXml, int serviceOption) {
        port.createTask(taskXml, serviceOption);
    }

    public void deleteTask(java.lang.String taskId, int serviceOption) {
        port.deleteTask(taskId, serviceOption);
    }

    public String getAttendantTasks(java.lang.String attendantId, int serviceOption) {
        return port.getAttendantTasks(attendantId, serviceOption);
    }
}
