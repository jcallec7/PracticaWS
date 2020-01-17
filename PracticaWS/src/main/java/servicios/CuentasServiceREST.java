package servicios;

import java.util.List;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import modelo.Cuenta;
import negocio.GestionCuentaLocal;

@Path("/cuentas")
public class CuentasServiceREST {

	@Inject
	private GestionCuentaLocal gcl;
	
	@GET
	@Produces("application/json")
	public List<Cuenta> getCuentas() {
		List<Cuenta> listado = gcl.getCuentas();
		return listado;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Respuesta crearCuenta(Cuenta c) {
		Respuesta r = new Respuesta();
		
		try {
			gcl.insertCuenta(c.getId(), c.getNombre(), c.getApellido(), c.getSaldo());
			r.setCodigo(1);
			r.setMensaje("OK");
		} catch(Exception e) {
			r.setCodigo(99);
			r.setMensaje("Error "+e.getMessage());
		}
		return r;
	}
	
}
