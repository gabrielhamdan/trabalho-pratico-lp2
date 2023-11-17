package dominio;

import java.util.List;

public class Aplicacao {
	private boolean ehTesteAutom;
	private List<Curso> cursos;
	private List<Disciplina> disciplinas;
	private List<Aluno> alunos;
	
	Aplicacao(boolean ehTesteAutom) {
		this.ehTesteAutom = ehTesteAutom;
	}
	
	public void carregaMenuPrincipal() {
		if(ehTesteAutom)
			return;
	}
	
	public void cadastraDisciplina() {
		String codigo = Util.digitaString("Digite o código da disciplina: ");
		String nome = Util.digitaString("Digite o nome da disciplina: ");
		int cargaHoraria = Util.digitaInt("Digite carga horária da disciplina: ");
		
		Disciplina disciplina = new Disciplina(codigo, nome, cargaHoraria);
		
		disciplinas.add(disciplina);
		
		carregaMenuPrincipal();
	}
	
	public void cadastraCurso() {
		String nome = Util.digitaString();
		Curso curso = new Curso(nome);
		
		cursos.add(curso);
		
		carregaMenuPrincipal();
	}
	
	public void incluiDisciplinaCurso() {
		String nomeCodDisc = Util.digitaString("Digite o nome ou código da disciplina: ");
		Disciplina novaDisciplina = null;
		
		for(Disciplina disciplina : disciplinas) {
			if(disciplina.getCodigo().equals(nomeCodDisc.trim()) || disciplina.getNome().equals(nomeCodDisc.trim())) {
				novaDisciplina = disciplina;
				break;
			}
		}
		
		if(novaDisciplina == null) {
			Util.imprimeMsg(String.format("Disciplina %s não encontrada.", nomeCodDisc));
			incluiDisciplinaCurso();
			return;
		}
		
		String nomeCurso = Util.digitaString("Digite o nome do curso: ");
		boolean disciplinaIncluida = false;
		
		for(Curso curso : cursos) {
			if(curso.getNome().equals(nomeCurso)) {
				curso.incluiDisciplina(novaDisciplina);
				disciplinaIncluida = true;
			}
		}
		
		if(!disciplinaIncluida)
			Util.imprimeMsg(String.format("Curso %s não encontrado.", nomeCurso));
		
		carregaMenuPrincipal();
	}
	
	public void cadastraAluno() {
		String cpf = Util.digitaString("Digite o CPF do aluno: ");
		String nome = Util.digitaString("Digite o nome do aluno: ");
		Aluno aluno = new Aluno(cpf, nome);
		
		alunos.add(aluno);
		
		carregaMenuPrincipal();
	}
	
	public void efetuaMatriculaAluno() {
		// TODO: 
	}
	
	public void criaRegistroAnoSemestreAluno() {
		
	}
	
	public void registraHistoricoAluno() {
		
	}
	
	public void geraHistoricoAluno() {
		
	}
	
	public void listaDisciplinasCurso() {
		int i = 1;
		for(Disciplina disciplina : disciplinas) {
			Util.imprimeMsg(String.format("%d %s", i, disciplina.getNome()));
			i++;
		}
	}
	
	public void listaDisciplinasFaltantesAluno() {
		
	}
}
