package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Prioridade;
import model.StatusTarefa;
import model.Tarefa;
import repository.TarefaRepository;

public class TarefaService {
	private final TarefaRepository repo;
	
	
	public TarefaService(TarefaRepository repo) {
        this.repo = repo;
    }
	
	
	public void criarTarefa(String titulo, String descricao, LocalDate dataLimite, Prioridade prioridade) {
		Tarefa nova = new Tarefa();
		nova.setTitulo(titulo);
		nova.setDescricao(descricao);
		nova.setStatus(StatusTarefa.PENDENTE);
		nova.setDataCriacao(LocalDateTime.now());
		nova.setDataLimite(dataLimite);
		nova.setPrioridade(prioridade);
		
		repo.salvar(nova);
	}
	
	public List<Tarefa> listarTarefas(){
		return repo.listarTodas();
	}
	
	public List<Tarefa> listarOrdenadasPorDataLimite(){
		return repo.listarTodas().stream()
				.sorted(Comparator.comparing(Tarefa::getDataLimite))
				.toList();
	}
	
	public List<Tarefa> listarOrdenadasPorPrioridade() {
	    return repo.listarTodas().stream()
	            .sorted(Comparator.comparing(Tarefa::getPrioridade))
	            .toList();
	}
	
	public Tarefa buscarPorId(int id) {
		return repo.buscarPorId(id);
	}
	
	public boolean atualizarStatus(int id, String novoStatusStr) {
		try {
			StatusTarefa novoStatus = StatusTarefa.valueOf(novoStatusStr.toUpperCase());
			return repo.atualizarStatus(id, novoStatus.name());
			
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public boolean deletarTarefa(int id) {
		return repo.deletar(id);
	}
	
	public Map<StatusTarefa, Long> relatorioPorStatus(){
		return repo.listarTodas().stream()
				.collect(Collectors.groupingBy(Tarefa::getStatus, Collectors.counting()));
		
	}
	
	
	
	public Map<Prioridade, Long> relatorioPorPrioridade() {
	    return repo.listarTodas().stream()
	        .collect(Collectors.groupingBy(Tarefa::getPrioridade, Collectors.counting()));
	}
	
	public List<Tarefa> listarTarefasVencidas(){
		return repo.listarTodas().stream()
				.filter(t -> t.getDataLimite() != null)
				.filter(t -> t.getDataLimite().isBefore(LocalDate.now()))
				.filter(t -> t.getStatus() != StatusTarefa.CONCLUIDA)
				.toList();
	}


}
