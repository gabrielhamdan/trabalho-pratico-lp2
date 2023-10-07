package curso;

import java.util.List;

import aluno.Matricula;
import disciplina.Disciplina;

public class Curso {
	private String nome;
	private List<Disciplina> disciplinas;
	private List<Matricula> matriculas;
	
	public Curso(String nome) {
		this.nome = nome;
	}
	
	public void incluiDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
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
}
