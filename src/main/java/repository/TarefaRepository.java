package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Prioridade;
import model.StatusTarefa;
import model.Tarefa;

public class TarefaRepository {

	public void salvar(Tarefa tarefa) {
		String sql = "INSERT INTO tarefa (titulo, descricao, status, data_criacao, data_limite, prioridade) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, tarefa.getTitulo());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setString(3, tarefa.getStatus().name());
			stmt.setObject(4, tarefa.getDataCriacao());
			stmt.setDate(5, java.sql.Date.valueOf(tarefa.getDataLimite()));
			stmt.setString(6, tarefa.getPrioridade().name());

			stmt.executeUpdate();
			System.out.println("Tarefa salva com sucesso!");

		} catch (SQLException e) {
			System.out.println("Erro ao salvar a tarefa: " + e.getMessage());

		}

	}

	public List<Tarefa> listarTodas() {

		List<Tarefa> tarefas = new ArrayList<>();

		String sql = "SELECT * FROM tarefa";

		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet result = stmt.executeQuery()) {

			while (result.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(result.getInt("id"));
				tarefa.setTitulo(result.getString("titulo"));
				tarefa.setDescricao(result.getString("descricao"));
				tarefa.setStatus(StatusTarefa.valueOf(result.getString("status")));
				tarefa.setDataCriacao(result.getTimestamp("data_criacao").toLocalDateTime());
				tarefa.setDataLimite(result.getTimestamp("data_limite").toLocalDateTime().toLocalDate());
				tarefa.setPrioridade(Prioridade.valueOf(result.getString("prioridade")));



				tarefas.add(tarefa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar tarefas: " + e.getMessage());

		}

		return tarefas;
	}

	public Tarefa buscarPorId(int id) {
		String sql = "SELECT * FROM tarefa WHERE id = ?";
		Tarefa tarefa = null;

		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				tarefa = new Tarefa();
				tarefa.setId(rs.getInt("id"));
				tarefa.setTitulo(rs.getString("titulo"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setStatus(StatusTarefa.valueOf(rs.getString("status")));
				tarefa.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
				tarefa.setDataLimite(rs.getDate("data_limite").toLocalDate());
				tarefa.setPrioridade(Prioridade.valueOf(rs.getString("prioridade")));


			}

			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar tarefa por ID: " + e.getMessage());
		}

		return tarefa;
	}

	public boolean atualizarStatus(int id, String novoStatus) {

		String sql = "UPDATE tarefa SET status = ? WHERE id = ?";
		boolean atualizado = false;

		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, novoStatus);
			stmt.setInt(2, id);

			int linhasAfetadas = stmt.executeUpdate();
			atualizado = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar status da tarefa: " + e.getMessage());
		}

		return atualizado;
	}

	public boolean deletar(int id) {
		String sql = "DELETE FROM tarefa WHERE id = ?";
		boolean deletado = false;

		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);

			int linhasAfetadas = stmt.executeUpdate();
			deletado = linhasAfetadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao deletar tarefa: " + e.getMessage());
		}

		return deletado;
	}

}
