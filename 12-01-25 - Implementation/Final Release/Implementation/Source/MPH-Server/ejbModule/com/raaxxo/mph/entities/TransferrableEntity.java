package com.raaxxo.mph.entities;

import java.io.Serializable;

import mph.beans.dto.DataTransferObject;

/**
 * 
 * 
 * This interfaces forces the object to build a Data Transfer Object to be transferred. <br/>
 * When an enterprise bean uses a Data Transfer Object, the client makes a single remote method invocation to the enterprise bean to request the Data
 * Transfer Object instead of numerous remote method calls to get individual attribute values. The enterprise bean then constructs a new Data Transfer 
 * Object instance, copies values into the object and returns it to the client. 
 * The client receives the Data Transfer Object and can then invoke accessor (or getter) methods on the Data Transfer Object to get the individual attribute 
 * values from the Transfer Object. Because the Data Transfer Object is passed by value to the client, all calls to the Data Transfer Object instance are local 
 * calls instead of remote method invocations.<br/><br/>
 * 
 * The entity bean is forced to provide a getDTO() method to get the Data Transfer Object containing the attribute values. This may eliminate having multiple get 
 * methods implemented in the bean and defined in the bean's remote interface. Instead of multiple client calls over the network to the BusinessObject to get attribute 
 * values, this solution provides a single method call. At the same time, this one method call returns a greater amount of data to the client than the 
 * individual accessor methods each returned. A Data Transfer Object transfers the values from the entity bean to the client in one remote method call. <br/><br/>
 * 
 * The Data Transfer Object acts as a data carrier and reduces the number of remote network method calls required to obtain the attribute values from the entity beans. 
 * The reduced chattiness of the application results in better network performance.
 * 
 */
public interface TransferrableEntity extends Serializable {

	public DataTransferObject getDTO(); 
}
