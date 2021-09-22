package tads.eaj.ufrn.aularestinicial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Telefone {
	@Id
	@Column (name = "telefoneID")
	Long id;
	String numeroTelefone;
	String pessoaID;
	Date deleted= null;

	@ManyToOne
	@JoinColumn (name = "pessoaID")
	private Pessoa pessoa;
}
