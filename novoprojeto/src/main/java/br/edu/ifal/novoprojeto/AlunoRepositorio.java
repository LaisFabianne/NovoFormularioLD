package br.edu.ifal.novoprojeto;

import org.springframework.data.repository.CrudRepository;

public interface AlunoRepositorio extends CrudRepository <Aluno,Long>{

    public Iterable<Aluno> findByNomeContaining(String q);

}