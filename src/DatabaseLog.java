/*
 * Name: Jay Wu
 * SID: 200331529
 */

/**
 *
 * 
 * @author Jay Wu
 * @since TODO 10/11/2026
 */

public class DatabaseLog {

    /* TODO */
    private MyStack log;

    /**
     * Tracks database log activity using MyStack.
     * @param capacity the max number of log entries
     */
    public DatabaseLog(int capacity) {
        log = new MyStack(capacity);
    }

    /**
     * Creates a log with given capacity.
     */
    public DatabaseLog() {
        log = new MyStack();
    }

    /**
     * Adds log entry to top of log
     *
     * @param logEntry the entry to add
     */
    public void addLog(String logEntry) {
        log.push(logEntry);
    }

    /**
     * Returns the most recent log entry without removing it.
     *
     * @return the most recent entry
     */
    public String viewLog() {
        return log.peek();
    }

    /**
     * Removes and returns most recent log entry
     *
     * @return the removed entry
     */
    public String removeLog() {
        return log.pop();
    }

    /**
     * Returns LogID field of a log entry.
     *
     * @param logEntry the given entry
     * @return the LogID
     */
    public String getLogID(String logEntry) {
        String[] parts = logEntry.split(" - ");
        return parts[0];
    }

    /**
     * Returns action field of log entry.
     *
     * @param logEntry the entry that is being parsed.
     * @return the action
     */
    public String getAction(String logEntry) {
        String[] parts = logEntry.split(" - ");
        return parts[1];
    }

    /**
     * Returns name of log entry
     *
     * @param logEntry entry that is being parsed
     * @return the name
     */
    public String getName(String logEntry) {
        String[] parts = logEntry.split(" - ");
        return parts[2];
    }

    /**
     * Returns month, date, year, hour and minute of log entry
     *
     * @param logEntry the entry that is being parsed.
     * @return the timestamp
     */
    public String[] getTimestamp(String logEntry) {
        String timestamp = logEntry.split(" - ")[3];
        return timestamp.split("[-_:]");
    }

    /**
     * Returns summary of log entry.
     * @param logEntry the entry to summarize
     * @return the summary string
     */
    public String summarizeLog(String logEntry) {
        return "ID: " + getLogID(logEntry) + "\n"
                + "ACTION: " + getAction(logEntry) + "\n"
                + "NAME: " + getName(logEntry) + "\n"
                + "TIMESTAMP: " + logEntry.split(" - ")[3];
    }

    /**
     * Removes and returns up to count of the most recent entries
     * @param count the number of entries to return
     * @return the entries, most recent first
     */
    public String[] getRecentLogs(int count) {
        return log.multiPop(count);
    }

    /**
     * Returns all log entries for name
     * @param name the name to search
     * @return matching entries
     */
    public String[] getLogsForStudent(String name) {
         if (log.isEmpty()) {
             return new String[0];
         }
         String[] all = log.multiPop(log.size());
         int matches = 0;
         for (String entry: all) {
             if (getName(entry).equals(name)) {
                 matches ++;
             }
         }
         String[] result = new String[matches];
         int index = 0;
         for (String entry : all) {
             if (getName(entry).equals(name)) {
                 result[index] = entry;
                 index ++;
             }
         }
        return result;
    }
}
