package dominio;

import java.util.List;

public class Curso {
	private String nome;
	private List<Disciplina> disciplinas;
	private List<Matricula> matriculas;
	
	public Curso(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	
	public void incluiDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}
}
