package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import modelo.Cuenta;
import modelo.Movimiento;

@Local
public interface GestionMovimientoLocal {
	
	public void guardarMovimiento(int id, Date fecha, Cuenta origen, Cuenta destino, double monto);
	
	public List<Movimiento> getMovimientos();
	
	public List<Movimiento> getMovimientosPorNombre(int filtro);
	
}
	
