package semestre;

import java.util.ArrayList;
import java.util.List;

import aluno.Matricula;
import disciplina.Disciplina;
import disciplina.NotaDisciplina;
import disciplina.Situacao;

public class AnoSemestre {
	private int ano;
	private int semestre;
	private Matricula matricula;
	
	public AnoSemestre(int ano, int semestre) { // protected no diagrama, mas não faz nenhum sentido
		this.ano = ano;
		this.semestre = semestre;
	}
	
	public NotaDisciplina registrarNotaDisciplina(float nota, Disciplina disciplina, Situacao situacao) {
		return new NotaDisciplina(nota, disciplina, situacao);
	}
	
	public List<NotaDisciplina> listaNotaDisciplina() {
		return new ArrayList<NotaDisciplina>();
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
}
