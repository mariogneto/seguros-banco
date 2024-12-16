package br.com.mgn.segurosbanco.repository;

import br.com.mgn.segurosbanco.domain.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, UUID> {
}
