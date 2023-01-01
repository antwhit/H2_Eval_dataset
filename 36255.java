/*  																						
	    jCompany Full-Stack Framework - Community Version									
	    Copyright (C) 2008  Powerlogic														
																							
	    This program is free software: you can redistribute it and/or modify				
	    it under the terms of the GNU General Public License as published by				
	    the Free Software Foundation, version 3 of the License.								
	    																					
	    This program is distributed in the hope that it will be useful,						
	    but WITHOUT ANY WARRANTY; without even the implied warranty of						
	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the						
	    GNU General Public License for more details.										
	    																					
	    You should have received a copy of the GNU General Public License					
	    along with this program.  If not, see <http://www.gnu.org/licenses/>.				
																							
	    Contact: plc@powerlogic.com.br - www.powerlogic.com.br 								
																							
 */ 
package ###PACKAGE_NAME###.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.jboss.annotation.ejb.LocalBinding;
import org.jboss.annotation.ejb.RemoteBinding;

import org.jcompany.commons.PlcConstantsCommons;
import org.jcompany.facade.PlcFacadeImpl;

/*
 * Anotações exclusivas para o Jboss Application Server
 */
@LocalBinding (jndiBinding="ejb/AppFacadeImpl")
@RemoteBinding (jndiBinding="ejb/AppFacadeImplRemote")
/*
 * Anotações Java EE
 */
@Stateless(name="AppFacadeImpl", mappedName="ejb/AppFacadeImpl" + PlcConstantsCommons.PLC_DEFAULT_SUFFIX_JNDI_NAME_REMOTE)
@TransactionManagement(TransactionManagementType.CONTAINER)

public class AppFacadeImpl extends PlcFacadeImpl implements IAppFacade, IAppFacadeRemote {
	
}
