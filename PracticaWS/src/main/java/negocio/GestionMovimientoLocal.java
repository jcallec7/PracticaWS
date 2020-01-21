package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import modelo.Cuenta;
import modelo.Movimiento;
import servicios.Respuesta;
import utils.Transferencia;

@Local
public interface GestionMovimientoLocal {
	
	public Respuesta guardarMovimiento(Transferencia t);
	
	public Respuesta guardarTransferencia(Transferencia t);
	
	public List<Movimiento> getMovimientos();
	
	public List<Movimiento> getMovimientosPorId(int filtro);
	
	
}
	
