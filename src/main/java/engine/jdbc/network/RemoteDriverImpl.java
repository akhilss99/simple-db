package engine.jdbc.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import engine.server.SimpleDB;

/**
 * The RMI server-side implementation of RemoteDriver.
 * @author Edward Sciore
 */
@SuppressWarnings("serial")
public class RemoteDriverImpl extends UnicastRemoteObject implements RemoteDriver {
   private SimpleDB db;
   
   public RemoteDriverImpl(SimpleDB db) throws RemoteException {
      this.db = db;
   }
   
   /**
    * Creates a new RemoteConnectionImpl object and 
    * returns it.
    * @see RemoteDriver#connect()
    */
   public RemoteConnection connect() throws RemoteException {
      return new RemoteConnectionImpl(db);
   }
}

