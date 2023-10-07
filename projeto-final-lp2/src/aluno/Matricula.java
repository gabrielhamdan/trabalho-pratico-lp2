package aluno;

import java.util.ArrayList;
import java.util.List;
import curso.Curso;
import semestre.AnoSemestre;

public class Matricula {
	private String matricula;
	private int ano;
	private int semestre;
	private Aluno aluno;
	private Curso curso;
	
	public Matricula(String matricula, int ano, int semestre) {
		this.matricula = matricula;
		this.ano = ano;
		this.semestre = semestre;
	}
	
	public List<AnoSemestre> listaAnoSemestre() {
		return new ArrayList<AnoSemestre>();
	}
	
	public void addAnoSemestre(int ano, int semestre) {
		
	}

	public String getMatricula() {
		return matricula;
	}

	public int getAno() {
		return ano;
	}

	public int getSemestre() {
		return semestre;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	protected void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	protected void setCurso(Curso curso) {
		this.curso = curso;
	}
}
