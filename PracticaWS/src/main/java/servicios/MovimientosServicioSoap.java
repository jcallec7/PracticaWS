package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import modelo.Movimiento;
import negocio.GestionMovimientoLocal;
import utils.Transferencia;

@WebService
public class MovimientosServicioSoap {

	@Inject
	private GestionMovimientoLocal gml;
	
	//private Transferencia t;
	
	@WebMethod
	public Respuesta crearMovimiento(Transferencia t) {
		
		Respuesta respuesta = new Respuesta();
		try {
			gml.guardarMovimiento(t);
			respuesta.setCodigo(1);
			respuesta.setMensaje("AL PELOO");
		
			//gml.guardarTransferencia(t, t.getMonto());
			
		} catch(Exception e) {
			respuesta.setCodigo(99);
			respuesta.setMensaje("ERROR ALV");
		}
		
		return respuesta;	
	}
	
	@WebMethod
	public Respuesta crearTransferencia(Transferencia t) {
		
		Respuesta respuesta = new Respuesta();
		try {
			gml.guardarTransferencia(t);
			respuesta.setCodigo(1);
			respuesta.setMensaje("AL PELOO");
		
			//gml.guardarTransferencia(t, t.getMonto());
			
		} catch(Exception e) {
			respuesta.setCodigo(99);
			respuesta.setMensaje("ERROR ALV");
		}
		
		return respuesta;	
	}
	
	@WebMethod
	public List<Movimiento> listMovimientosPorId(int id) {
		List<Movimiento> l = new ArrayList<Movimiento>();
		l = gml.getMovimientosPorId(id);		
		return l;
	}
	
}
