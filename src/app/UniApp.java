package app;

import java.util.ArrayList;

public class UniApp {
	
	public static void main(String[] args) {
		
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		alunos = Aluno.buscarTodosAlunos();
		
		Aluno a1 = new Aluno("Mário", "20230012", "938.134.983-87", "mario@gmail.com", "(31)92834-2348");
		Aluno.cadastrarAluno(a1);
		
		
		
		//Aluno.deletarAluno(13);
		alunos = Aluno.buscarTodosAlunos();
		for(Aluno a: alunos) {
			System.out.println(a.toString());
		}
		
	}

}
