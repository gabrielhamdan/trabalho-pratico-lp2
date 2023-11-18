package dominio;

public class TesteTelas {

	/*
	 * 6) Solicitar uma matricula (já cadastrada), uma disciplina (já cadastrada), ano, semestre, nota e situação. Registrar que o Aluno fez essa Disciplina no ano/semestre, com nota e situação.
	 * 8) Solicitar um curso. Listar todas as Disciplinas do curso.
	 * */
	public static void main(String[] args) throws Exception {
		Aplicacao aplicacao = new Aplicacao();
		
		Disciplina disciplina = new Disciplina("1", "LP2", 40);
		aplicacao.disciplinas.add(disciplina);
		
		Curso curso = new Curso("SSI");
		curso.incluiDisciplina(disciplina);
		aplicacao.cursos.add(curso);
		
		Aluno aluno = new Aluno("12345678900", "Gabriel Hamdan");
		Matricula matricula = new Matricula("SSI01", 2022, 1, aluno, curso);
		matricula.registraAnoSemestre(2023, 1);
		aluno.adicionaMatricula(matricula);
		aplicacao.alunos.add(aluno);
		aplicacao.matriculas.add(matricula);
		
		AnoSemestre anoSemestre = matricula.listaAnoSemestre().get(0);
		NotaDisciplina notaDisciplina = anoSemestre.registraNotaDisciplina(10, disciplina, new Situacao("aprovado"));;
		notaDisciplina.setAnoSemestre(anoSemestre);
		
		aplicacao.carregaMenuPrincipal();
	}
	
}
