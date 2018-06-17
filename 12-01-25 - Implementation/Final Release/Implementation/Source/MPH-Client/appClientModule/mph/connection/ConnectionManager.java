package mph.connection;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This singleton class is used to set up a RMI connection with the Server module.
 */
public class ConnectionManager {
	
	private static ConnectionManager uniqueInstance = null;
	
	private static Context jndiContext = null;
	
	/**
	 * @return the Context of the remote session between the Client and the Server
	 */
	public Context getJndiContext() {
		return jndiContext;
	}
	
	/**
	 * @return the unique instance of the singleton
	 */
	public static ConnectionManager getInstance() {
	      if(uniqueInstance == null) {
	         uniqueInstance = new ConnectionManager();
	      }
	      return uniqueInstance;
	   }
	
	/**
	 * Set up the connection with the string representing the location of the server
	 * @throws NamingException if a connection error occurs
	 */
	public void setConnection() throws NamingException {
		/* This will setup the initial context for the Client */
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		
		jndiContext = new InitialContext(props); /* Obtained the context! */
		
	}

}
