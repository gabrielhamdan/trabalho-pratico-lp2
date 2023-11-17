package dominio;

import java.util.List;

public class Aplicacao {
	private boolean ehTesteAutom;
	private List<Curso> cursos;
	private List<Disciplina> disciplinas;
	private List<Aluno> alunos;
	private List<Matricula> matriculas;
	
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
			if(disciplina.getCodigo().trim().equals(nomeCodDisc.trim()) || disciplina.getNome().trim().equals(nomeCodDisc.trim())) {
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
	
	public Aluno buscaAluno(String nomeCpf) {
		Aluno alunoBusca = null;
		
		for(Aluno aluno : alunos) {
			if(aluno.getNome().trim().equals(nomeCpf.trim()) || aluno.getCpf().trim().equals(nomeCpf.trim()))
				alunoBusca = aluno;
		}
		
		return alunoBusca;
	}
	
	public void efetuaMatriculaAluno() throws Exception {
		String nomeCpf = Util.digitaString("Digite o nome ou CPF do aluno: ");
		Aluno alunoMatricula = buscaAluno(nomeCpf);;
		
		if(alunoMatricula == null) {
			Util.imprimeMsg(String.format("Aluno %s não encontrado.", nomeCpf));
			efetuaMatriculaAluno();
			return;
		}
				
		String nomeCurso = Util.digitaString("Digite o nome do curso: ");
		Curso cursoMatricula = null;
		
		for(Curso curso : cursos) {
			if(curso.getNome().equals(nomeCurso)) {
				cursoMatricula = curso;
			}
		}
		
		if(cursoMatricula == null) {
			Util.imprimeMsg(String.format("Curso %s não encontrado.", nomeCurso));
			efetuaMatriculaAluno();
			return;
		}
		
		String matricula = Util.digitaString("Digite um código para a matrícula: ");
		int ano = Util.digitaInt("Digite o ano da matrícula: ");
		int semestre = Util.digitaInt("Digite o semestre da matrícula: ");
		Matricula novaMatricula = new Matricula(matricula, ano, semestre, alunoMatricula, cursoMatricula);
		matriculas.add(novaMatricula);
		alunoMatricula.adicionaMatricula(novaMatricula);		
		
		Util.imprimeMsg(String.format("Matrícula realizada com sucesso: %s - %s (%d/%d)", alunoMatricula.getNome(), cursoMatricula.getNome(), ano, semestre));
	}
	
	public void criaRegistroAnoSemestreAluno() throws Exception {
		String nomeCpf = Util.digitaString("Digite o nome ou CPF do aluno: ");
		Aluno aluno = buscaAluno(nomeCpf);
		
		if(aluno == null) {
			Util.imprimeMsg(String.format("Aluno %s não encontrado.", nomeCpf));
			criaRegistroAnoSemestreAluno();
			return;
		}
		
		Matricula matricula = selecionaMatricula(aluno);
		
		if(matricula == null)
			return;
		
		int ano = Util.digitaInt("Digite o ano: ");
		int semestre = Util.digitaInt("Digite o semestre: ");
		matricula.registraAnoSemestre(ano, semestre);
	}
	
	public void registraHistoricoAluno() {
		String nomeCpf = Util.digitaString("Digite o nome ou CPF do aluno: ");
		Aluno aluno = buscaAluno(nomeCpf);
		
		if(aluno == null) {
			Util.imprimeMsg(String.format("Aluno %s não encontrado.", nomeCpf));
			carregaMenuPrincipal();
			return;
		}
		
		// TODO selecionaMatricula
	}
	
	public Matricula selecionaMatricula(Aluno aluno) {
		int i = 1;
		if(aluno.getMatriculas().size() > 0) {
			for(Matricula matricula : aluno.getMatriculas()) {
				Util.imprimeMsg(String.format("%d - %s\n", i, matricula.getMatricula()));
				i++;
			}
		} else {
			Util.imprimeMsg("O aluno não está matriculado em nenhum curso.");
			carregaMenuPrincipal();
			return null;
		}
		
		int indexMatricula = Util.digitaInt("Selecione uma matrícula: ");
		
		if(indexMatricula < 0 || indexMatricula > i) {
			Util.imprimeMsg("Matrícula não encontrada.\n");
			return null;
		}
		
		return aluno.getMatriculas().get(indexMatricula - 1);
	}
	
	public void geraHistoricoAluno() {
		
	}
	
	public void listaDisciplinasCurso() {
		String nomeCurso = Util.digitaString("Digite o nome do curso: ");
		
		/*
		 * Em outro tipo de aplicação seria evidentemente mais apropriado tratar para o usuário 
		 * os casos em que o curso não existe e os cursos sem disciplinas cadastradas (algo nos termos do que já foi
		 * implementado, por exemplo, no método incluiDisciplinaCurso()).
		 * Consciente disso, vou me furtar desse tipo de fluxo alternativo neste caso.
		 * */
		for(Curso curso : cursos) {
			if(curso.getNome().equals(nomeCurso)) {
				int i = 1;
				
				for(Disciplina disciplina : curso.getDisciplinas()) {
					Util.imprimeMsg(String.format("%d - %s\n", i, disciplina.getNome()));
					i++;
				}
				
				break;
			}
		}
		
		carregaMenuPrincipal();
	}
	
	public void listaDisciplinasFaltantesAluno() {
		
	}
}
