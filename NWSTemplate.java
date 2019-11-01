/**
 @author Chelsea Dorich (Email: <a href="mailto:"robotqi@gmail.com>robotqi@gmail.com</a>)
 @version 1.1 05/21/2014
 @assignment.number A190-15
 @prgm.usage Called from the operating system
 @see "Gaddis, 2013, Starting out with Java, From Control Structures, 5th Edition"
 @see "<a href='http://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/index.html'>JavaDoc Documentation</a>
 */

public interface NWSTemplate
{
    /**
     * update string updates main string from main method
     * @param strVar strVar is updates and set to equal strWeather
     */
    public void updateString(String strVar);
    /** getPos() method. Accepts a string variable called strAlt which represents the altitude and returns an integer
     * representing the character position
     * @param strAlt - strAlt will...
     * @return Returns number representing the starting point for seven digit number group extraction relating to chosen
     * altitude
     */
    public int getPos(String strAlt);
    /**
     * getAltitudeWeather method. uses the user input strAlt to resemble altitude to find correct block of numbers and return it
     * @param strAlt input the abreviated number to the program and use it to find the information  pertinant to the altitude
     * @return strAltWea return the block of information relevant to the chosen altitude
     */
    public String getAltitudeWeather(String strAlt);

    /**
     * getWindDirection() method uses altitude apropriate seven digit string to discern wind direction.
     * @param strAlt uses altitude input to find string and extract correct direction/exception conditions.
     * @return returns wind direction or exception statement; clam or NA.
     */
    public String getWindDir(String strAlt);
    /**
     * getWindSpeed() method uses altitude apropriate seven digit string to discern wind speed.
     * @param strAlt uses altitude input to find string and extract correct speed/exception conditions.
     * @return returns wind speed or exception statement; clam or NA.
     */
    public String getWindSpeed(String strAlt);
    /**
     * getWindTemp() method uses altitude apropriate seven digit string to discern wind temperature.
     * @param strAlt  uses altitude input to find string and extract correct temperature/exception conditions.
     * @return returns wind temperature or exception statement; clam or NA.
     */
    public String getWindTemp(String strAlt);
    /**
     * getStationID gets weather string and extracts first three charachters for station ID
     * @return first three charachters of wether string, returns string return
     */
    public String getStationId();
}
