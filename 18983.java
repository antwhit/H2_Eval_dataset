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
package ###PACKAGE_NAME###.entity;

import javax.persistence.MappedSuperclass;

import org.jcompany.domain.PlcBaseMapEntity;
/**
 * @since jCompany 3.2 Ancestral para todas as classes da aplica��o, 
 * com pre-mapeamentos para OID, versao e auditoria minima herdados.
 */
@MappedSuperclass
public abstract class AppBaseEntity extends PlcBaseMapEntity {

}
