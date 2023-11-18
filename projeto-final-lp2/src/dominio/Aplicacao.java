package dominio;

import java.util.ArrayList;
import java.util.List;

public class Aplicacao {
	public static enum OpcaoMenu {
		CADASTRA_DISCIPLINA(1),
		CADASTRA_CURSO(2),
		INCLUI_DISCIPLINA_CURSO(3),
		CADASTRA_ALUNO(4),
		EFETUA_MATRICULA_ALUNO_CURSO(5),
		CRIA_REGISTRO_ANO_SEMESTRE(6),
		REGISTRA_HISTORICO(7),
		GERA_HISTORICO(8),
		LISTA_DISCIPLINAS_CURSO(9),
		MOSTRA_DISCIPLINAS_FALTANTES(10),
		SAIR(11);
		
		public final int opcaoMenu;
		
		private OpcaoMenu(int opcaoMenu) {
			this.opcaoMenu = opcaoMenu;
		}
		
		public static OpcaoMenu selecionaOpcao(int input) {
			for(OpcaoMenu opcao : values())
				if(opcao.opcaoMenu == input)
					return opcao;
			
			return null;
		}
	}
	
	public boolean encerraExecucao = false;
	public List<Curso> cursos = new ArrayList<Curso>();
	public List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	public List<Aluno> alunos = new ArrayList<Aluno>();
	public List<Matricula> matriculas = new ArrayList<Matricula>();
	
	public void imprimeMenuPrincipal() {
		Util.imprimeMsg(" 1 -  Cadastrar disciplina", true);
		Util.imprimeMsg(" 2 -  Cadastrar curso", true);
		Util.imprimeMsg(" 3 -  Incluir disciplina em curso", true);
		Util.imprimeMsg(" 4 -  Cadastrar aluno", true);
		Util.imprimeMsg(" 5 -  Efetuar matrícula", true);
		Util.imprimeMsg(" 6 -  Registrar ano/semestre", true);
		Util.imprimeMsg(" 7 -  Registrar histórico", true);
		Util.imprimeMsg(" 8 -  Gerar histórico", true);
		Util.imprimeMsg(" 9 -  Listar disciplinas", true);
		Util.imprimeMsg("10 -  Listar disciplinas restantes", true);
		Util.imprimeMsg("11 -  Sair", true);
	}
	
	public void carregaMenuPrincipal() throws Exception {		
		while(!encerraExecucao) {
			imprimeMenuPrincipal();
			
			int input = Util.digitaInt("Digite uma opção: ");
			OpcaoMenu opcaoMenu = OpcaoMenu.selecionaOpcao(input);
			
			if(opcaoMenu == null)
				continue;
			
			switch (opcaoMenu) {
				case CADASTRA_DISCIPLINA:
					cadastraDisciplina();
					break;
				case CADASTRA_CURSO:
					cadastraCurso();
					break;
				case INCLUI_DISCIPLINA_CURSO:
					incluiDisciplinaCurso();
					break;
				case CADASTRA_ALUNO:
					cadastraAluno();
					break;
				case EFETUA_MATRICULA_ALUNO_CURSO:
					efetuaMatriculaAluno();
					break;
				case CRIA_REGISTRO_ANO_SEMESTRE:
					criaRegistroAnoSemestreAluno();
					break;
				case REGISTRA_HISTORICO:
					registraHistoricoAluno();
					break;
				case GERA_HISTORICO:
					pesquisaHistoricoAluno(true);
					break;
				case LISTA_DISCIPLINAS_CURSO:
					listaDisciplinasCurso();
					break;
				case MOSTRA_DISCIPLINAS_FALTANTES:
					listaDisciplinasFaltantesAluno();
					break;
				case SAIR:
					encerraExecucao = true;
					break;
				default:
					break;
			}
		}
	}
	
	public void cadastraDisciplina() {
		String codigo = Util.digitaString("Digite o código da disciplina: ");
		String nome = Util.digitaString("Digite o nome da disciplina: ");
		int cargaHoraria = Util.digitaInt("Digite carga horária da disciplina: ");
		
		Disciplina disciplina = new Disciplina(codigo, nome, cargaHoraria);
		
		disciplinas.add(disciplina);
	}
	
	public void cadastraCurso() {
		String nome = Util.digitaString("Digite o nome do curso: ");
		Curso curso = new Curso(nome);
		
		cursos.add(curso);
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
			Util.imprimeMsg(String.format("Disciplina %s não encontrada.\n", nomeCodDisc));
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
			Util.imprimeMsg(String.format("Curso %s não encontrado.\n", nomeCurso));
	}
	
	public void cadastraAluno() {
		String cpf = Util.digitaString("Digite o CPF do aluno: ");
		String nome = Util.digitaString("Digite o nome do aluno: ");
		Aluno aluno = new Aluno(cpf, nome);
		
		alunos.add(aluno);
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
			Util.imprimeMsg(String.format("Aluno %s não encontrado.\n", nomeCpf));
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
			Util.imprimeMsg(String.format("Curso %s não encontrado.\n", nomeCurso));
			return;
		}
		
		String matricula = Util.digitaString("Digite um código para a matrícula: ");
		int ano = Util.digitaInt("Digite o ano da matrícula: ");
		int semestre = Util.digitaInt("Digite o semestre da matrícula: ");
		Matricula novaMatricula = new Matricula(matricula, ano, semestre, alunoMatricula, cursoMatricula);
		matriculas.add(novaMatricula);
		alunoMatricula.adicionaMatricula(novaMatricula);		
		
		Util.imprimeMsg(String.format("Matrícula realizada com sucesso: %s - %s (%d/%d)\n", alunoMatricula.getNome(), cursoMatricula.getNome(), ano, semestre));
	}
	
	public void criaRegistroAnoSemestreAluno() throws Exception {
		String nomeCpf = Util.digitaString("Digite o nome ou CPF do aluno: ");
		Aluno aluno = buscaAluno(nomeCpf);
		
		if(aluno == null) {
			Util.imprimeMsg(String.format("Aluno %s não encontrado.\n", nomeCpf));
			return;
		}
		
		Matricula matricula = selecionaMatricula(aluno);
		
		if(matricula == null)
			return;
		
		int ano = Util.digitaInt("Digite o ano: ");
		int semestre = Util.digitaInt("Digite o semestre: ");
		matricula.registraAnoSemestre(ano, semestre);
	}
	
	public void registraHistoricoAluno() throws Exception {
		String nomeCpf = Util.digitaString("Digite o nome ou CPF do aluno: ");
		Aluno aluno = buscaAluno(nomeCpf);
		
		if(aluno == null) {
			Util.imprimeMsg(String.format("Aluno %s não encontrado.\n", nomeCpf));
			return;
		}
		
		Matricula matricula = selecionaMatricula(aluno);
		
		if(matricula == null)
			return;
		
		int ano = Util.digitaInt("Digite o ano: ");
		int semestre = Util.digitaInt("Digite o semestre: ");
		
		for(AnoSemestre anoSemestre : matricula.listaAnoSemestre())
			if(anoSemestre.getAno() == ano && anoSemestre.getSemestre() == semestre) {
				Disciplina disciplina = selecionaDisciplina(matricula.getCurso());
				
				if(disciplina == null) {
					Util.imprimeMsg(String.format("Disciplina não encontrada no curso %s\n", matricula.getCurso().getNome()));
					return;
				}
				
				float nota = Util.digitaFloat(String.format("Digite a nota de %s para a disciplina %s (%d/%d): ", aluno.getNome(), disciplina.getNome(), ano, semestre));
				
				String descricaoSituacao = Util.digitaString("Digite a situação (Aprovado/Reprovado/etc.): "); // Seria, a meu ver, mais adequado um enum para regisrar o tipo de situação.
				Situacao situacao = new Situacao(descricaoSituacao);
				
				NotaDisciplina notaDisciplina = anoSemestre.registraNotaDisciplina(nota, disciplina, situacao);
				notaDisciplina.setAnoSemestre(anoSemestre);
				
				break;
			}
	}
	
	public Disciplina selecionaDisciplina(Curso curso) {
		String nomeCod = Util.digitaString("Digite o nome ou o código da discplina: ");
		
		for(Disciplina disciplina : curso.getDisciplinas())
			if(disciplina.getCodigo().trim().equals(nomeCod.trim()) || disciplina.getNome().trim().equals(nomeCod.trim()))
				return disciplina;
		
		return null;
	}
	
	public Matricula selecionaMatricula(Aluno aluno) {
		int i = 1;
		if(aluno.getMatriculas().size() > 0) {
			for(Matricula matricula : aluno.getMatriculas()) {
				Util.imprimeMsg(String.format("%d - %s\n", i, matricula.getMatricula()));
				i++;
			}
		} else {
			Util.imprimeMsg("O aluno não está matriculado em nenhum curso.\n");
			return null;
		}
		
		int indexMatricula = Util.digitaInt("Selecione uma matrícula: ");
		
		if(indexMatricula < 0 || indexMatricula > i) {
			Util.imprimeMsg("Matrícula não encontrada.\n");
			return null;
		}
		
		return aluno.getMatriculas().get(indexMatricula - 1);
	}
	
	public Matricula pesquisaHistoricoAluno(boolean imprimeHistorico) {
		String nomeCpf = Util.digitaString("Digite o nome ou CPF do aluno: ");
		Aluno aluno = buscaAluno(nomeCpf);
		
		if(aluno == null) {
			Util.imprimeMsg("Aluno não encontrado.\n");
			return null;
		}
		
		Matricula matricula = selecionaMatricula(aluno);
		
		if(matricula == null)
			return null;
		
		geraHistorico(matricula, imprimeHistorico);
		
		return matricula;
	}
	
	public List<Disciplina> geraHistorico(Matricula matricula, boolean imprimeHistorico) {
		List<Disciplina> disciplinasAprovado = new ArrayList<Disciplina>();
		
		if(imprimeHistorico) {			
			Util.imprimeMsg("HISTÓRICO ESCOLAR:\n");
			Util.imprimeMsg(String.format("%s - %s\n", matricula.getAluno().getNome(), matricula.getMatricula()));
		}
		
		for(AnoSemestre anoSemestre : matricula.listaAnoSemestre()) {
			for(NotaDisciplina notaDisciplina : anoSemestre.listaNotaDisciplina()) {
				if(imprimeHistorico)
					Util.imprimeMsg(
						String.format("%s | %d/%d | %f | %s\n", 
						notaDisciplina.getDisciplina().getCodigo(), 
						notaDisciplina.getAnoSemestre().getAno(), 
						notaDisciplina.getAnoSemestre().getSemestre(), 
						notaDisciplina.getNota(), 
						notaDisciplina.getSituacao().getSituacao())
					);
				
				if(notaDisciplina.getSituacao().getSituacao().trim().toUpperCase().equals("APROVADO")) // Aqui, por exemplo, fica evidente por que um enum para status de Situação seria o mais apropriado.
					disciplinasAprovado.add(notaDisciplina.getDisciplina());
			}
		}
		
		return disciplinasAprovado;
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
	}
	
	/*
	 * A regra para este método não é clara, i. e., as disciplinas que faltam cursar são aquelas que o aluno 
	 * jamais cursou ou, inclusive, as que cursou sem aprovação? Optou-se por considerar que faltam cursar todas as 
	 * disciplinas reprovadas e/ou não cursadas.
	 * */
	public void listaDisciplinasFaltantesAluno() {
		Matricula matricula = pesquisaHistoricoAluno(false);
		List<Disciplina> disciplinasAprovado = geraHistorico(matricula, false);
		Curso curso = matricula.getCurso();
		List<Disciplina> disciplinasCurso = curso.getDisciplinas();
		List<String> disciplinasNaoCursadas = new ArrayList<String>();
		
		for(Disciplina disciplinaCurso : disciplinasCurso) {
			boolean cursouDisciplina = false;
			String disciplinaCod = disciplinaCurso.getCodigo();
			
			for(Disciplina disciplinaAprovado : disciplinasAprovado)
				if(disciplinaAprovado.getCodigo().equals(disciplinaCod))
					cursouDisciplina = true;
			
			if(!cursouDisciplina)
				disciplinasNaoCursadas.add(disciplinaCurso.getNome());
		}
		
		if(disciplinasNaoCursadas.size() > 0) {
			Util.imprimeMsg("DISCIPLINAS NÃO CURSADAS:\n");
			
			for(String disciplina : disciplinasNaoCursadas)
				Util.imprimeMsg(disciplina + "\n");
		} else
			Util.imprimeMsg("Não restam disciplinas a serem cursadas.\n");
	}
}
