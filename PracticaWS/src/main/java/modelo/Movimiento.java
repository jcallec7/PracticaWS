package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 8494633931799962941L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ws_mov_id")
	private int id;
	
}
