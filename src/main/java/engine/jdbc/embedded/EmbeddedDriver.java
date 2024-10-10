package engine.jdbc.embedded;

import java.util.Properties;
import java.sql.SQLException;
import engine.server.SimpleDB;
import engine.jdbc.DriverAdapter;

/**
 * The RMI server-side implementation of RemoteDriver.
 * @author Edward Sciore
 */

public class EmbeddedDriver extends DriverAdapter {   
   /**
    * Creates a new RemoteConnectionImpl object and 
    * returns it.
    * @see engine.jdbc.network.RemoteDriver#connect()
    */
   public EmbeddedConnection connect(String url, Properties p) throws SQLException {
      String dbname = url.replace("jdbc:simpledb:", "");
      SimpleDB db = new SimpleDB(dbname);
      return new EmbeddedConnection(db);
   }
}

