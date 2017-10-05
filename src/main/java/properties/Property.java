package properties;

import java.io.*;
import java.util.Properties;

public class Property {

    public static String getProperty( String arg ) {

        Properties prop = new Properties();
        String property=null;

        try (InputStream input = new FileInputStream("src/main/resources/config")){

            prop.load(input);

            property=prop.getProperty(arg);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return property;

    }

}
