import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import model.Prioridade;
import model.StatusTarefa;
import model.Tarefa;
import repository.TarefaRepository;
import service.TarefaService;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		TarefaService service = new TarefaService(new TarefaRepository());

		int opcao;

		do {
			System.out.println("\n--- MENU DEV TASKS ---");
			System.out.println("1. Criar nova tarefa");
			System.out.println("2. Listar tarefas");
			System.out.println("3. Buscar tarefa pelo ID");
			System.out.println("4. Atualizar status de tarefa");
			System.out.println("5. Deletar tarefa");
			System.out.println("6. Relatório por status");
			System.out.println("7. Relatório por prioridade");
			System.out.println("8. Tarefas vencidas");
			System.out.println("0. Sair");
			System.out.println("Escolha um opção");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				System.out.println("Titulo: ");
				String titulo = sc.nextLine();

				System.out.println("Descrição: ");
				String descricao = sc.nextLine();

				System.out.println("Data limite (AAAA-MM-DD): ");
				LocalDate dataLimite = LocalDate.parse(sc.nextLine());

				System.out.println("Prioridade (BAIXA/MEDIA/ALTA): ");
				Prioridade prioridade = Prioridade.valueOf(sc.nextLine().toUpperCase());

				service.criarTarefa(titulo, descricao, dataLimite, prioridade);
				break;

			case 2:
				service.listarTarefas().forEach(System.out::println);
				break;

			case 3:
				System.out.println("ID da tarefa: ");
				int id = sc.nextInt();
				sc.nextLine();

				Tarefa t = service.buscarPorId(id);
				System.out.println(t != null ? t : "Tarefa não encontrada.");
				break;

			case 4:
				System.out.println("ID da tarefa");
				int idStatus = sc.nextInt();
				sc.nextLine();

				System.out.print("Novo status (PENDENTE/CONCLUIDA): ");
				String novo = sc.nextLine().toUpperCase();

				boolean atualizada = service.atualizarStatus(idStatus, novo);
				System.out.println(atualizada ? "Status atualizado." : "Erro ao atualizar: id não existe");

				break;

			case 5:
				System.out.println("ID da tarefa");
				int idDel = sc.nextInt();
				sc.nextLine();

				boolean deletada = service.deletarTarefa(idDel);
				System.out.println(deletada ? "Tarefa deletada." : "Erro ao deletar.");
				break;
				
			case 6:
				Map<StatusTarefa, Long> statusMap = service.relatorioPorStatus();
				System.out.println("Relatório por status: ");
				statusMap.forEach((k, v) -> System.out.println(k + ": " + v));
				break;
				
			case 7:
				Map<Prioridade, Long> prioridadeMap = service.relatorioPorPrioridade();
				System.out.println("Relatório por prioridade: ");
				prioridadeMap.forEach((k, v) -> System.out.println(k + ": " + v));
				break;
				
			case 8:
				System.out.println("Tarefas vencidas:");
				service.listarTarefasVencidas().forEach(System.out::println);

			case 0:
				System.out.println("Encerrando o programa...");
				break;

			default:
				System.out.println("Opção errada");

			}
		} while (opcao != 0);

		sc.close();
	}

}
