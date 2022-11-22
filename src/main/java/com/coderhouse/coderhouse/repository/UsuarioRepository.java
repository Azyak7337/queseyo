package com.coderhouse.coderhouse.repository;

import com.coderhouse.coderhouse.document.UsuarioDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioDocument, String> {

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public UsuarioDocument findByUsername(String username);

    public UsuarioDocument findByEmail(String email);
}
