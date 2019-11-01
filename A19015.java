import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
/**
 @author Chelsea Dorich (Email: <a href="mailto:"robotqi@gmail.com>robotqi@gmail.com</a>)
 @version 1.1 05/21/2014
 @assignment.number A190-15
 @prgm.usage Called from the operating system
 @see "Gaddis, 2013, Starting out with Java, From Control Structures, 5th Edition"
 @see "<a href='http://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/index.html'>JavaDoc Documentation</a>
 */

public class A19015 extends JDialog
{
    private JPanel contentPane;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    /**
     * public A19015 sts up action listeners for every buttons click event
     */
    public A19015()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
/**
 * eight button is a simple one, it closes the program
 */
        eightButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });
        /**
         * oneButton goes to a web page and sifts through the information given about a table that is specified,
         * and breaks up that information to reconstruct it into a SQL statement using string builder, that is then
         * executed and the table created.
         */

oneButton.addActionListener(new ActionListener()
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        DBMS db = new DBMS();
        db.openConnection("WEATHERDATA");
        INET net = new INET();
        StringBuilder str = new StringBuilder("CREATE TABLE LOCATIONS (");
        try
        {
            String strSQlRAW = net.getURLRaw("http://faculty.sdmiramar.edu/jcouture/2014sp/cisc190/webct/manual/schema-locations.txt");
        net.saveToFile("Data/SQLdata.txt",strSQlRAW);
            BufferedReader inputFile;
            String strRecord = "1";
            inputFile = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Chelsea\\A19015\\Data\\SQLdata.txt")));
            for(int i = 0; i<=9; i++)
            {
                String strB =  inputFile.readLine();
            }

            strRecord = inputFile.readLine();


            while (inputFile.ready()&& !strRecord.equals(null))
            {
               String[] ary = strRecord.split("\\s+");
                String strName = ary[0];
                String strChar = "VARCHAR(100)";
                String strNull = "NOT NULL ";
                String strDef = "DEFAULT ''";
                str.append(strName + " " + strChar + " " + strNull + strDef + ",");
                strRecord = inputFile.readLine();
            }
            String strSQL =str.toString();

        db.execute(strSQL.substring(0,strSQL.length()-1)+")");
        } catch (Exception e1)
        {
            e1.printStackTrace();
        }db.close();
    }
});
        /**
         * threeButton goes again to the intenet to retrieve another FBIN file that contains the location name,
         * id, and state. after the information is saved to file it is then read and the information split, and
         * then added to the database in the fields of station name and state, using the inet function of propercase for the name field.
         */
        threeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            INET net = new INET();
                DBMS db = new DBMS();
                db.openConnection("WEATHERDATA");
                try
                {
                    String strFancyPants = net.getURLRaw("http://faculty.sdmiramar.edu/jcouture/2014sp/cisc190/webct/manual/data-stations.txt");
                    net.saveToFile("C:\\Users\\Chelsea\\A19015\\Data\\FBIN2.txt", strFancyPants);
                    BufferedReader inputFile = new BufferedReader(new FileReader("C:\\Users\\Chelsea\\A19015\\Data\\FBIN2.txt"));
                   String strRecord = "1";
                    while(inputFile.ready()&& !strRecord.equals(null))
                    {
                       strRecord = inputFile.readLine();
                       String[] ary = strRecord.split(",");
                       db.setField("LOCATIONS","stationid", ary[0], "stationname", net.properCase(ary[1]));
                       db.setField("LOCATIONS", "stationid", ary[0],"state", ary[2]);


                    }
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                }db.close();

            }
        });
        /**
         * gets winds aloft information from internet, saves it to file, andreads saved information, saving select
         * portions to the data base fields of stationid (creting a ne record) and windsaloft
         */
        twoButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                {String strFb = "";
                    DBMS db = new DBMS();
                    NWSFB fb = new NWSFB(strFb);
                    INET net = new INET();

                    boolean blnFileExists = net.fileExists("FBIN.txt");
                    fb.getFile("C:\\Users\\Chelsea\\A19015\\Data\\FBIN.txt","http://www.aviationweather.gov/products/nws/all",true);
                    BufferedReader inputFile;
                    db.openConnection("WEATHERDATA");
                    try
                    {
                        inputFile = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Chelsea\\A19015\\Data\\FBIN.txt")));



                    while (inputFile.ready())
                    {
                        String strRecord = inputFile.readLine();


                        String strStaID = "K"+strRecord.substring(0,4).trim();
                        String strWAloft = strRecord.substring(3);
                        db.addRecord("LOCATIONS","stationid", strStaID);
                        db.setField("LOCATIONS","stationid",strStaID,"windsaloft",strWAloft);

                    }
                              } catch (FileNotFoundException e1)
                    {
                        e1.printStackTrace();
                    } catch (IOException e1)
                    {
                        e1.printStackTrace();
                    } db.close();
                }

            }
        });
        /**
         * fourButton uses buttonFour methodto sort the data from worlds text to the new file USA.txt thatwill only contain
         * USA related data
         */
fourButton.addActionListener(new ActionListener()
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            buttonFour("C:\\Users\\Chelsea\\A19015\\Data\\Worlds.txt");
        } catch (IOException e1)
        {
            System.out.println("Button 4444444 Faiiiiilll");
            e1.printStackTrace();
        }
    }
});
        /**
         * fiveButton uses the fiveButton method to query database to see if record from line of usa text exists in database. If it
         * exists, latituse, logitude and elevation are added in the fields of the retrieved record
         */
fiveButton.addActionListener(new ActionListener()
{
    @Override
    public void actionPerformed(ActionEvent e)
    {

        fiveButton();
    }
});
        /**
         * sixButton querys the database to select all of the california records into the data set, and then updates
         * them using the xmlRead class within the updateStationInfo method, retrieveing their data
         * from the internet for most of the remaining fields.
         */
sixButton.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {DBMS db = new DBMS();
            db.openConnection("WEATHERDATA");
            db.query("SELECT * FROM LOCATIONS WHERE state = 'CA'");

            while( db.moreRecords())
            {db.status(db.getField("stationid")+"++++++++++++++9");


                UpdateStationInfo(db.getField("stationid"), true);}

            db.close();
            //UpdtDatabase();
        }
    });
// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });
/**
 * sevenButton prints out all of the data from the database to a text file to serve as a summary output
  */
sevenButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {DBMS db = new DBMS();
                NWSFB fb = new NWSFB("");

                try
                {db.openConnection("WEATHERDATA");
                    db.query("SELECT * FROM LOCATIONS");
                    PrintWriter outputFile = new PrintWriter("C:\\Users\\Chelsea\\A19015\\Data\\OUTPUT.txt");
                    outputFile.println("ICAO,Station Name, State, Latitude, Longitude, Altitude, Winds Aloft," +
                            "   Surface TempC, Surface Humidity, WindDir, WindSp, WindTemp");
                    while (db.moreRecords())
                    {
                        fb.updateString(db.getField("windsaloft"));
                        outputFile.println(db.getField("stationid")+ ", "+ db.getField("stationname") +", "+ db.getField("state")+
                        ", "+ db.getField("latitude")+", "+db.getField("longitude")+", "+db.getField("elevation")+", "+db.getField("windsaloft")+
                        ", "+ db.getField("temperature")+", " +db.getField("humidity")+", "+ db.getField("humidity")+", "
                       + fb.getWindSpeed("30000")+ ", "+fb.getWindSpeed("30000")+", "+fb.getWindTemp("30000")+"\r\n");

                    }

                    outputFile.close();
                    db.close();

                } catch (FileNotFoundException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * closes form
     */

    private void onCancel()
    {
// add your code here if necessary
        dispose();
    }

    /**
     * buttonFour method to sort the data from worlds text to the new file USA.txt thatwill only contain
     * USA related data
     * @param strFileName accepts the file name of the starting doccument
     * @throws IOException
     */

    private static void buttonFour(String strFileName) throws IOException
    {
        DBMS db = new DBMS();
        INETTemplate net = new INET();
        BufferedReader inputFile;
        inputFile = new BufferedReader(new InputStreamReader(new FileInputStream(strFileName)));
        PrintWriter outputFile = new PrintWriter("C:\\Users\\Chelsea\\A19015\\Data\\USA.txt");
        while (inputFile.ready())
        {
            String strRecord = inputFile.readLine();

            if (strRecord.length()>9)
            {
                if(strRecord.substring(7,8).equals("K"))
                {

                    outputFile.println(strRecord);

                                  }}

        } outputFile.close();db.close();}
    /**updatestationinfo updates database fields using the xmlRead class within the updateStationInfo method,
    *  retrieveing their data from the internet for most of the remaining fields.
    */
     private void UpdateStationInfo(String strStationID, boolean blnUpdate)
    {
        DBMS db = new DBMS();
        db.openConnection("WEATHERDATA");
        XMLRead xm = new XMLRead();
        String UrlId = strStationID.trim();
        try
        { // update the fields for the given station
            xm.loadPage("http://w1.weather.gov/xml/current_obs/"+ UrlId +".xml");
            String strLat = (xm.getField("latitude"));
            String strLon = (xm.getField("longitude"));
            String strDew = (xm.getField("dewpoint_f"));
            String strHum = (xm.getField("relative_humidity"));
            String strTemp =(xm.getField("temp_f"));
            String strDir = (xm.getField("wind_dir"));
            String strSpeed = (xm.getField("wind_mph"));
            String strPress = (xm.getField("pressure_string"));
            db.setField("LOCATIONS","stationid",strStationID,"latitude", strLat);
            db.setField("LOCATIONS","stationid",strStationID,"longitude", strLon);
            db.setField("LOCATIONS","stationid",strStationID,"temperature", strTemp);
            db.setField("LOCATIONS","stationid",strStationID,"humidity", strHum);
            db.setField("LOCATIONS","stationid",strStationID,"windspeed", strSpeed);
            db.setField("LOCATIONS","stationid",strStationID,"winddirection", strDir);
            db.setField("LOCATIONS","stationid",strStationID,"pressure", strPress);
            db.setField("LOCATIONS","stationid",strStationID,"dewpoint", strDew);

        } catch (Exception e)
        {
            db.status("fail 88888");
        }db.close();}

    /**
     * fiveButton method querys database to see if record from line of usa text exists in database. If it
     * exists, latituse, logitude and elevation are added in the fields of the retrieved record
     */
public void fiveButton()
{
    try
    {DBMS db = new DBMS();
        db.openConnection("WEATHERDATA");
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(new FileInputStream(("C:\\Users\\Chelsea\\A19015\\Data\\USA.txt"))));
        while(inputFile.ready())
        {
            String strRecord = inputFile.readLine();
            String[] ary = strRecord.split(";");
            db.query("SELECT * FROM LOCATIONS WHERE stationid = '" +  ary[2]+"'");

            db.setField("LOCATIONS","stationid",ary[2],"latitude", ary[8]);
            db.setField("LOCATIONS","stationid",ary[2],"longitude", ary[7]);
            db.setField("LOCATIONS","stationid",ary[2],"elevation", ary[11]);
            db.status(ary[8]+ary[7]+ary[11]);
        }
        db.close();

    } catch (FileNotFoundException e1)
    {
        e1.printStackTrace();
    } catch (IOException e1)
    {
        e1.printStackTrace();
    }
}

    /**
     * main method sets up form, creates database, and drops tables.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        A19015 dialog = new A19015();
        DBMS db = new DBMS();
        String strWmdb = "jdbc:derby:WEATHERDATA;create=true";
        db.dbConn = DriverManager.getConnection(strWmdb);
       db.openConnection("WEATHERDATA");
        db.execute("DROP TABLE LOCATIONS");

        Statement dbCmdText =  db.dbConn.createStatement();
        db.close();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
