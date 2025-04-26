package devtasks;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Prioridade;
import model.Tarefa;
import repository.TarefaRepository;
import service.TarefaService;

public class TarefaServiceTest {
	
	private TarefaService service;
	
	@BeforeEach
	public void setup() {
		service = new TarefaService(new TarefaRepository());
	}
	
	@Test
	public void deveCriarTarefaComSucesso() {
		
		String titulo =  "Estudar Java";
		String descricao = "Estudar sobre POO";
		LocalDate dataLimite = LocalDate.parse("2025-07-04");
		Prioridade prioridade = Prioridade.valueOf("MEDIA");
		
		service.criarTarefa(titulo, descricao, dataLimite, prioridade);
		
	}
	
	@Test
	public void deveListarTarefas() {
		List<Tarefa> tarefas = service.listarTarefas();
		Assertions.assertNull(tarefas);
	}
	

}
