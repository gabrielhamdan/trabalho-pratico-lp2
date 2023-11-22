package dominio;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
	private String cpf;
	private String nome;
	private List<Matricula> matriculas = new ArrayList<Matricula>();;
	
	public Aluno(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	
	protected void adicionaMatricula(Matricula matricula) {
		matriculas.add(matricula);
	}
}
