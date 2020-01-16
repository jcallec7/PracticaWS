package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cuenta {
	
	@Id
	@Column(name = "ws_cu_id")
	private int id;
	
	@Column(name = "ws_cu_nombre")
	private String nombre;
	
	@Column(name = "ws_cu_apellido")
	private String apellido;
	
	
	

}
