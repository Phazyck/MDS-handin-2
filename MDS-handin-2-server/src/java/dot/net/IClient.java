package dot.net;

/**
 * The common interface for the clients of the .NET servers.
 *
 * @author The Hitmen
 */
public interface IClient {

    /**
     * Returns a String of all tasks including the attendant in XML format.
     *
     * @param attendantId Id oIf the attendant
     * @return All tasks including a given attendant
     */
    String getAttendantTasks(String attendantId);

    /**
     * Creates a new task
     *
     * @param taskXml The task, written as a String of XML.
     * @return Response message from the underlying server.
     */
    String createTask(String taskXml);

    /**
     * Deletes a task.
     *
     * @param taskId The ID of the task to be deleted.
     * @return Response message from the underlying server.
     */
    String deleteTask(String taskId);
}
