package es.udc.rdopazo.tfg.app.model.persistence.jpa.evento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT")
@SequenceGenerator(name = "event_seq", sequenceName = "EVENT_SEQ", allocationSize = 1)
public class JpaEvento {

	@Id
    @Column(name = "X_EVENT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CITY")
	private String city;
	
	
	
	
	
	
}
