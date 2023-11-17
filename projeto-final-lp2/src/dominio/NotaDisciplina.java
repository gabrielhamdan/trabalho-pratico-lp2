package dominio;

public class NotaDisciplina {
	private float nota;
	private Disciplina disciplina;
	private Situacao situacao;
	private AnoSemestre anoSemestre;
	
	protected NotaDisciplina(float nota, Disciplina disciplina, Situacao situacao) throws Exception {
		if(disciplina == null || situacao == null)
			throw new Exception("Disciplina e/ou Situação não pode(m) ter valor(es) nulo(s)!");
		
		this.nota = nota;
		this.disciplina = disciplina;
		this.situacao = situacao;
	}

	public float getNota() {
		return nota;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public Situacao getSituacao() {
		return situacao;
	}
	
	public AnoSemestre getAnoSemestre() {
		return anoSemestre;
	}

	protected void setAnoSemestre(AnoSemestre anoSemestre) {
		this.anoSemestre = anoSemestre;
	}
	
	protected void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}
