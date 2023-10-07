package aluno;

import java.util.List;

public class Aluno {
	private String cpf;
	private String nome;
	private List<Matricula> matriculas;
	
	public Aluno(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}
	
	protected void adicionaMatricula(Matricula matricula) {
		matriculas.add(matricula);
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
}
