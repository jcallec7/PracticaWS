package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import modelo.Cuenta;
import modelo.Movimiento;
import utils.Transferencia;

@Local
public interface GestionMovimientoLocal {
	
	public void guardarMovimiento(Transferencia t);
	
	public void guardarTransferencia(Transferencia t);
	
	public List<Movimiento> getMovimientos();
	
	public List<Movimiento> getMovimientosPorId(int filtro);
	
	
}
	
