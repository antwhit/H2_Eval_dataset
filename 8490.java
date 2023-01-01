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
package ###PACKAGE_NAME###.control;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jcompany.commons.PlcBaseUserProfileEntity;
import org.jcompany.commons.facade.IPlcFacade;
import org.jcompany.control.service.PlcBaseUserProfileService;

/**
 * ###PROJECT_NAME### . Implementar aqui l�gicas de perfil do usu�rio
 * da aplica��o (user profiling)
 */
public class AppUserProfileService extends PlcBaseUserProfileService {
	/**
	 * jCompany 5.0: L�gicas de Inicializa��o de Perfil de Usuario
	 * Recebe a requisi��o, o perfil do usu�rio preenchido no ancestral e 
	 * a interface para chamada da persist�ncia.
	 *
	 * Preencher o objeto de Perfil com informa��es espec�ficas, especializando-o
	 * se necess�rio.
	 */
	@Override
	public PlcBaseUserProfileEntity registerSpecificProfile(HttpServletRequest request,
		    HttpServletResponse response, PlcBaseUserProfileEntity plcProfileEntity, IPlcFacade plcFacade) throws Exception{
	
		// Exemplos de implementa��o de filtros para Roles fict�cias Gerente Geral Plc e Administrador Geral Plc
		
		log.debug("Entrou em registraPerfilUsuario");

		Map<String,String> m = new HashMap<String,String>();
		
		if (request.isUserInRole("Gerente Geral Plc")) {
//			m.put("idioma","N");
			log.debug("Registrou restricoes de filtro vertical para usuario com role Gerente Geral Plc");
		}
		
		plcProfileEntity.getVerticalSecurityPlc().putAll(m);
		
		//se usu�rio for Administrador Geral, adicionar essa role no perfil
		if(request.isUserInRole("Administrador Geral Plc")) {
		    List grupos = new ArrayList(1);
		    grupos.add("Administrador Geral");
		    plcProfileEntity.setGroups(grupos);
		}

		// Retorna objeto modificado
		return plcProfileEntity;
		
	}
}

