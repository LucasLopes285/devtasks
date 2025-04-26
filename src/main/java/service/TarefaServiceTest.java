package service;

import repository.TarefaRepository;

public class TarefaServiceTest {
	
	private TarefaService service;
	
	
	public void setup() {
        service = new TarefaService(new TarefaRepository());
    }
	
}
