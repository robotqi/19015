/**
 * Created by Chelsea on 4/21/14.
 */
//XMLTemplate


/**
 @author Chelsea Dorich (Email: <a href="mailto:"robotqi@gmail.com>robotqi@gmail.com</a>)
 @version 1.1 05/21/2014
 @assignment.number A190-15
 @prgm.usage Called from the operating system
 @see "Gaddis, 2013, Starting out with Java, From Control Structures, 5th Edition"
 @see "<a href='http://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/index.html'>JavaDoc Documentation</a>
 */

public interface XMLTemplate
{
    int intRecordPos = -1;
    /**
     This function accepts a URL to an XML page and stores it in a
     Document object.  The class variable intRecNum is also set to -1.
     @param strURL - URL to the XML page (can be local or over the Internet)
     */
    public void loadPage(String strURL) throws Exception;
    /**
     This function looks for a table name in the XML document
     and returns the first child node.
     @param strTable - the name of the root table
     @return string - returns the child node as a string
     */
    public String setTable(String strTable) throws Exception;
    /**
     This function will search for a value represented by strKey in
     a field indicated by strKeyField.

     @param strKeyName - the field name to search
     @param strKeyContents - the value of the field name to be searched
     @return integer - the record number(position) or a minus one if it did not find the key
     */
    public int find(String strKeyName, String strKeyContents) throws Exception;
    /**
     This method extracts the contents of the field at the
     the CURRENT position as determined by intRecNum.

     @param strFieldName - the field name of the data you want to retrieve
     @return integer - the contents of the field
     */
    public String getField(String strFieldName) throws Exception;
    /**
     This method sets the current record to intRec and then
     extracts the contents of that field at that record.
     @param strField - the field name of the data you want to retrieve
     @param intRec - the record number to use
     @return string - the contents of the field
     */
    public String getField(String strField, int intRec) throws Exception;
    /**
     This method returns the number of records in the XML
     file after a getElementsByTagName() search
     @param strFieldName - this can be any field that is in the record
     @return integer - the number of records in that table
     */
    public int getRecordCount(String strFieldName);
    /**
     This method is just used to display data during testing.
     @param strVar - any string (it is displayed in the jGrasp messages window)
     */
    public void status(String strVar);
    /**
     Returns a string about this object.
     */
    public String toString();

}