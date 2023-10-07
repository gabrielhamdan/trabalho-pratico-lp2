package disciplina;

import semestre.AnoSemestre;

public class NotaDisciplina {
	private float nota;
	private Disciplina disciplina;
	private Situacao situacao;
	private AnoSemestre anoSemestre;
	
	public NotaDisciplina(float nota, Disciplina disciplina, Situacao situacao) { // diagrama pede protected, mas isso não faz sentido
		this.nota = nota;
		this.disciplina = disciplina;
		this.situacao = situacao;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	protected void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	protected void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
	}
}
