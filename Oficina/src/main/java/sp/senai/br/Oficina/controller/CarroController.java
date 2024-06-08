package sp.senai.br.Oficina.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import sp.senai.br.Oficina.model.Carro;
import sp.senai.br.Oficina.model.Peca;
import sp.senai.br.Oficina.repository.CarroRepository;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	CarroRepository repositorio;
	
	@GetMapping
	public List<Carro> listar() {
		return repositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Carro listar(@PathVariable int id) {
		return repositorio.findById(id).orElseThrow();
	}	
	
	@PostMapping
	public Carro gravar(@RequestBody Carro carro) {
 		List<Peca> pecasTmp = carro.getPecas();
		carro.setPecas(null);
		Carro carroNovo = repositorio.save(carro);
		
		List<Peca> tmp = new ArrayList<Peca>();
		for (Peca peca : pecasTmp) {
			peca.setCarro(carroNovo);
			tmp.add(peca);
		}
		carroNovo.setPecas(tmp);
		
		return repositorio.save(carroNovo);
	}

	@PutMapping("/{id}")
	public Carro alterar(@RequestBody Carro carro, @PathVariable int id) {
 		List<Peca> pecasTmp = carro.getPecas();
		carro.setPecas(null);
		Carro carroNovo = repositorio.save(carro);
		
		List<Peca> tmp = new ArrayList<Peca>();
		for (Peca peca : pecasTmp) {
			peca.setCarro(carroNovo);
			tmp.add(peca);
		}
		carroNovo.setPecas(tmp);
		
		return repositorio.save(carroNovo);
	}	
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable int id) {
		Carro tmp = null;
		if (repositorio.existsById(id)) {
			tmp = repositorio.findById(id).get();
			repositorio.delete(tmp);

		}else {
		}
		
		
		
	}

}
