package negocio;

import java.util.List;

import javax.ejb.Local;

import modelo.Cuenta;

@Local
public interface GestionCuentaLocal {
	
	public void insertCuenta(int id, String nombre, String apellido, Double saldo);
	
	public List<Cuenta> getCuentas();
}

