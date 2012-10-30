package soap.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class TaskSerializer {

    public static void main(String args[]) throws IOException {
        try {
            Task t;
            t = new Task();
            t.id = "delete-me";
            t.name = "delete this task";
            t.date = "01-10-2012";
            t.status = "not-executed";
            t.description = "This task should be deleted";
            t.attendants = "the-hitmen";

            // Serialize Cal object into XML.
            serialize(t);

        } catch (JAXBException ex) {
            Logger.getLogger(TaskSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String serialize(Task task) throws JAXBException, IOException {
        // create an instance context class, to serialize.
        JAXBContext jaxbContext = JAXBContext.newInstance(Task.class);

        // Serialize Cal object into XML.
        StringWriter writer = new StringWriter();

        jaxbContext.createMarshaller().marshal(task, writer);

        System.out.println("Printing serialized Task XML!");

        // Print the serialized Xml to Console.
        System.out.println(writer.toString());

        // Finally return the XML file.
        return writer.toString();
    }
}
