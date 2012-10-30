package dot.net;

import java.io.*;
import java.net.*;

public class RestClient implements IClient {

    private InputStreamReader in;
    private PrintWriter out;

    /**
     * Use this to test out the REST Client.
     *
     * @param args Should either be "POST", "GET" or "DELETE".
     */
    public static void main(String[] args) {
        RestClient client = new RestClient();

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

    /**
     * Creates as REST Client and gets it ready to make requests.
     */
    public RestClient() {
        try {
            Socket s = new Socket("trustcare.itu.dk", 80);
            out = new PrintWriter(s.getOutputStream());
            in = new InputStreamReader(s.getInputStream());
        } catch (UnknownHostException e) {
            System.out.println("UnknownHost: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    @Override
    public String getAttendantTasks(String attendantId) {
        String request =
                "GET /task-manager-rest/tasks/attendant/" + attendantId + " HTTP/1.1"
                + "\nHost: trustcare.itu.dk"
                + "\nAccept: application/xml"
                + "\n\n";

        return getReply(request);
    }

    @Override
    public String createTask(String taskXml) {
        String request =
                "POST /task-manager-rest/tasks/createtask HTTP/1.1"
                + "\nHost: trustcare.itu.dk"
                + "\nContent-Type: application/xml"
                + "\nContent-Length: " + taskXml.length()
                + "\n\n" + taskXml;

        return getReply(request);
    }

    @Override
    public String deleteTask(String taskId) {
        String request =
                "DELETE /task-manager-rest/tasks/DeleteTask?taskId=" + taskId + " HTTP/1.1"
                + "\nHost: trustcare.itu.dk"
                + "\nAccept: application/xml"
                + "\n\n";

        return getReply(request);
    }

    private String getReply(String request) {
        try {
            out.print(request); // send http-request
            out.flush();
            StringBuilder sb = new StringBuilder();

            while (!in.ready()) {
                // wait for reply from server
            }

            while (in.ready()) { // read http-response
                char c = (char) in.read();
                sb.append(c);
            }
            return sb.toString();
        } catch (IOException e) {
            return "IOException: " + e.getMessage();
        }
    }
}
