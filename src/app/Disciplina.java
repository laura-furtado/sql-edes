package app;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;

	public class Disciplina {
		
		private int id;
		private String nome;
		
		public Disciplina() {}

		public Disciplina(String nome) {

			this.nome = nome;
		}

		public int getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public static ArrayList<Disciplina> buscarTodasDisciplinas(){
			String url = "jdbc:sqlite:universidade.db";
			ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
			String sql = "SELECT * FROM Disciplina";
			try {
				Connection conn =  DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					Disciplina disciplina = new Disciplina(nome);
					listaDisciplinas.add(disciplina);
				}
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				System.out.println("Erro ao tentar buscar disciplinas: " + e.getMessage());
			}
			return listaDisciplinas;
			
		}
}
