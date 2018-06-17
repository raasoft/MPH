package mph.beans.dto;

import java.io.Serializable;

/**
 * This interface let an object to represent a Data Transfer Object. A DTO encapsulates the business data of a particular entity. <br/>
 * A single method call is used to send and retrieve the Data Transfer Object. 
 * When the client requests the enterprise bean for the business data, the enterprise bean can construct the Data Transfer Object, populate it with 
 * its attribute values, and pass it by value to the client. <br/>
 * <br/> 
 * The client receives the Data Transfer Object and can then invoke accessor (or getter) methods on the Data Transfer Object to get the individual attribute 
 * values from the Transfer Object. Because the Data Transfer Object is passed by value to the client, all calls to the Data Transfer Object instance are local 
 * calls instead of remote method invocations.

 * 
 * 
 * It extends {@link Serializable}.
 */
public interface DataTransferObject extends Serializable {

}
