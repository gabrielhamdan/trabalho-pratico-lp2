package dominio;

import java.util.ArrayList;
import java.util.List;

public class Matricula {
	private String matricula;
	private int ano;
	private int semestre;
	private Aluno aluno;
	private Curso curso;
	private List<AnoSemestre> anoSemestreLista;
	
	public Matricula(String matricula, int ano, int semestre, Aluno aluno, Curso curso) throws Exception {
		if(aluno == null || curso == null)
			throw new Exception("Aluno e/ou Curso não pode(m) ter valor(es) nulo(s)!");
		
		this.matricula = matricula;
		this.ano = ano;
		this.semestre = semestre;
		this.aluno = aluno;
		this.curso = curso;
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
	
	public Curso getCurso() {
		return curso;
	}
	
	public List<AnoSemestre> listaAnoSemestre() {
		return anoSemestreLista;
	}
	
	public void registraAnoSemestre(int ano, int semestre) throws Exception {
		if(anoSemestreLista == null)
			anoSemestreLista = new ArrayList<AnoSemestre>();
		
		AnoSemestre anoSemestre = new AnoSemestre(ano, semestre, this);
		anoSemestreLista.add(anoSemestre);
	}
	
	protected void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	protected void setCurso(Curso curso) {
		this.curso = curso;
	}
}
