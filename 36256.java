/*
 * Created on 16-Oct-07
 *
 * COPYRIGHT BY HER MAJESTY THE QUEEN AS REPRESENTED BY THE MINISTER OF 
 * NATIONAL DEFENCE, 2007.
 *
 */
package /*!BASE_PACKAGE!*/;

import hla.rti1516.AttributeHandle;
import hla.rti1516.AttributeHandleSet;
import hla.rti1516.ObjectClassHandle;

import java.util.HashMap;

import /*!BASE_PACKAGE.!*/exceptions.InvalidConstructorParameterException;

/**
 * This class provides a container to hold all the information
 * one might need about a FOM object class.  This is NOT
 * meant to be extended to handle object instances.
 * 
 * @author Allan Gillis allan.gillis@drdc-rddc.gc.ca
 *
 */
public class FOMObjectClass extends FOMClass {
	
	/**
	 * The class handle for this type of object; from the RTI.
	 */
	protected ObjectClassHandle handle;
	
	/**
	 * This map holds all the attribute handles for this class, indexed
	 * by the name as a key.
	 */
	protected HashMap<String, AttributeHandle> attributeHandles;
	
	/**
	 * A full AttributeHandleSet ready to use.
	 */
	protected AttributeHandleSet attributeHandleSet;
	
	/**
	 * The constructor.
	 * 
	 * @param className the full dotted name of the FOM object class.
	 * @param classHandle the class handle provided by the RTI.
	 * @throws InvalidConstructorParameterException if either paramter is null.
	 */
	public FOMObjectClass(String className, ObjectClassHandle classHandle) throws InvalidConstructorParameterException {
		
		if (className == null || classHandle == null) {
			throw new InvalidConstructorParameterException("Either the class name or class handle was null!");
		}
		//Set the name and the class handle
		this.name = className;
		this.handle = classHandle;
		
		//Make up the attribute map.
		attributeHandles = new HashMap<String, AttributeHandle>();
		
		//And set the attributes handle set to null
		this.attributeHandleSet = null;
		
		
	}//end constructor

	
	/**
	 * Lets you get the handle for the FOM class. 
	 * 
	 * @return the class handle for this FOM class.
	 */
	public ObjectClassHandle getClassHandle() {
		return this.handle;
	}//end getClassHandle
	
	/**
	 * Lets you add an attribute to the class.
	 * 
	 * @param attributeName the name of the attribute.
	 * @param attributeHandle the handle for the attribute as provided by the RTI.
	 */
	public void addAttribute(String attributeName, AttributeHandle attributeHandle) {
		if (attributeName == null || attributeHandle == null) {
			System.err.println("Cannot add the attribute to class " + this.name +
					" because either the attribute name or handle was null!");
		} else {
			if (!this.attributeHandles.containsKey(attributeName)) {
				this.attributeHandles.put(attributeName, attributeHandle);
			}//end if we don't already have it
		}//end if the params are ok
	}//end addAttribute
	
	/**
	 * Lets you get the handle for a particular attribute of this class.
	 * 
	 * @param attributeName the name of the attribute you want the handle for.
	 * @return the AttributeHandle object for the attribute, or null if it wasn't found.
	 */
	public AttributeHandle getAttributeHandle(String attributeName) {
		return this.attributeHandles.get(attributeName);
	}//end getAttributeHandle
	
	/**
	 * Allows you to add the attribute handle set to the FOMObjectClass.
	 * 
	 * @param theSet
	 */
	public void setAttributeHandleSet(AttributeHandleSet theSet) {
		this.attributeHandleSet = theSet;
	}//end setAttributeHandleSet
	
	/**
	 * Lets you get the attribute handle set for this particular class's subscribed attributes.
	 * 
	 * @return the attribute handle set, or null, if it hasn't been set yet.
	 */
	public AttributeHandleSet getAttributeHandleSet() {
		return this.attributeHandleSet;
	}//end getAttributeHandleSet
	
}//end FOMObjectClass
