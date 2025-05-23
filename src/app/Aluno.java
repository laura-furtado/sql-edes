package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Aluno {
	
	private int id;
	private String nome;
	private String registro;
	private String cpf;
	private String email;
	private String telefone;
	
	public Aluno() {}
	
	public Aluno(String nome, String registro, String cpf, String email, String telefone) {

		this.nome = nome;
		this.registro = registro;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	
	public Aluno(int id, String nome, String registro, String cpf, String email, String telefone) {

		this.id = id;
		this.nome = nome;
		this.registro = registro;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
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

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	public static ArrayList<Aluno> buscarTodosAlunos(){
		String url = "jdbc:sqlite:universidade.db";
		ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
		String sql = "SELECT * FROM Aluno";
		try {
			Connection conn =  DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String registro = rs.getString("numero_registro");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				Aluno aluno = new Aluno(id, nome, registro, cpf, email, telefone);
				listaAlunos.add(aluno);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println("Erro ao tentar buscar alunos: " + e.getMessage());
		}
		return listaAlunos;
		
	}
	
	public static Aluno buscarAluno(int id_aluno) {
		
		String url = "jdbc:sqlite:universidade.db";
		Aluno aluno = null;
		String sql = "SELECT * FROM Aluno WHERE id = ?";
		
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id_aluno);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String registro = rs.getString("numero_registro");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				aluno = new Aluno(id, nome, registro, cpf, email, telefone);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return aluno;
	}
	
	
public static void cadastrarAluno(Aluno aluno) {
		
		String url = "jdbc:sqlite:universidade.db";
		String sql = "INSERT INTO Aluno (nome, numero_registro, cpf, email, telefone) VALUES (?,?,?,?,?)";
		
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aluno.getNome());
			pstmt.setString(2, aluno.getRegistro());
			pstmt.setString(3, aluno.getCpf());
			pstmt.setString(4, aluno.getEmail());
			pstmt.setString(5, aluno.getTelefone());
			
			int rows = pstmt.executeUpdate();
					
					
			if(rows > 0 ) {
				System.out.println("Sucesso!");
			}
			
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void atualizarAluno(Aluno aluno) {
		String url = "jdbc:sqlite:universidade.db";
		String sql = "UPDATE Aluno SET nome = ?, numero_registro=?, cpf=?, email=?, telefone=? WHERE id=?";
		
		try {
			
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aluno.getNome());
			pstmt.setString(2, aluno.getRegistro());
			pstmt.setString(3, aluno.getCpf());
			pstmt.setString(4, aluno.getEmail());
			pstmt.setString(5, aluno.getTelefone());
			pstmt.setInt(6, aluno.getId());
			
			int rows = pstmt.executeUpdate();
			
			if(rows > 0) {
				System.out.println("Aluno atualizado com sucesso!");
			}
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void deletarAluno(int id_aluno) {
		String url = "jdbc:sqlite:universidade.db";
		String sql = "DELETE FROM Aluno WHERE id = ?";
		
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id_aluno);
			
			int rows = pstmt.executeUpdate();
			
			if(rows > 0) {
				System.out.println("Aluno removido com sucesso!");
			}
			
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", registro=" + registro + ", cpf=" + cpf + ", email=" + email
				+ ", telefone=" + telefone + "]";
	}
	
	
	
	
	
	

}
