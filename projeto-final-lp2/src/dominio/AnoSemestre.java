package dominio;

import java.util.ArrayList;
import java.util.List;

public class AnoSemestre {
	private int ano;
	private int semestre;
	private Matricula matricula;
	private List<NotaDisciplina> listaNotaDisciplina;
	
	protected AnoSemestre(int ano, int semestre, Matricula matricula) throws Exception {
		if(matricula == null)
			throw new Exception("Matrícula não pode ter valor nulo!");
		
		this.ano = ano;
		this.semestre = semestre;
		this.matricula = matricula;
	}
	
	public int getAno() {
		return ano;
	}

	public int getSemestre() {
		return semestre;
	}

	public Matricula getMatricula() {
		return matricula;
	}
	
	public NotaDisciplina registraNotaDisciplina(float nota, Disciplina disciplina, Situacao situacao) throws Exception {
		if(listaNotaDisciplina == null)
			listaNotaDisciplina = new ArrayList<NotaDisciplina>();
		
		NotaDisciplina notaDisciplina = new NotaDisciplina(nota, disciplina, situacao);
		listaNotaDisciplina.add(notaDisciplina);
		
		return notaDisciplina;
	}
	
	public List<NotaDisciplina> listaNotaDisciplina() {
		return listaNotaDisciplina;
	}
	
	protected void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
}
