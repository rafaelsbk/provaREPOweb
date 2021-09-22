package tads.eaj.ufrn.aularestinicial.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Pessoa {
	@Id
	@Column(name="pessoaID")
	Long id;
	String nome;
	String sobrenome;
	String telefoneID;
	Date deleted = null;

	@OneToMany (mappedBy = "pessoa")
	@JoinColumn (name = "telefoneID")
	private List<Telefone> telefones;
}
