package sp.senai.br.Oficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sp.senai.br.Oficina.model.Peca;

public interface PecaRepository extends JpaRepository<Peca, Integer> {
	
}
