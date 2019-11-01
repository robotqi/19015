/**
 @author Chelsea Dorich (Email: <a href="mailto:"robotqi@gmail.com>robotqi@gmail.com</a>)
 @version 1.1 05/21/2014
 @assignment.number A190-15
 @prgm.usage Called from the operating system
 @see "Gaddis, 2013, Starting out with Java, From Control Structures, 5th Edition"
 @see "<a href='http://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/index.html'>JavaDoc Documentation</a>
 */

public interface DBMSTemplate
{  /**
 * Adds record to derby database
 * @param strTable string representing the tble name
 * @param strKeyName Name of the key field the record is to be added to
 * @param strKeyContents contents to be added to key
 * @return return a bln indicating the success of the operation
 */
    public boolean addRecord(String strTable,
                           String strKeyName,
                           String strKeyContents);
    /**
     *  delets all records from given table
     * @param strTable table name to be deleted
     * @return  return a bln indicating the success of the operation
     */
    public boolean deleteAll(String strTable);
    /**
     * closes database
     */
    public void close();
    /**
     * gets contents from specified field and returns it
     * @param strFieldName field to be retrieved
     * @return contense of field
     */
    public  String getField(String strFieldName);
    /**
     * moves database reader down one record
     * @return return a bln indicating the success of the operation
     */
    public Boolean moreRecords();
    /**
     * opens connection to given dtabase
     * @param strDataSourceName name of database to be opened
     */
    public void openConnection(String strDataSourceName);
    /**
     * executes a query to the database
     * @param strSQL written query to be performed
     */
    public void query(String strSQL);
    /**
     * sets specefied field with given contents
     * @param strTable name of table to be modified
     * @param strKeyName name of key
     * @param strKeyContents key contents to be found
     * @param strFieldName field to be retrieved
     * @param strFieldContents contents to be added to indicated field
     * @return
     */
    public Boolean setField(String strTable,
                            String strKeyName,
                            String strKeyContents,
                            String strFieldName,
                            String strFieldContents);
    /**
     * prints out a status message
     * @param strVar message to be displayed
     */
    public void status(String strVar);

}
