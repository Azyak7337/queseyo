package com.coderhouse.coderhouse.repository;

import com.coderhouse.coderhouse.document.ProductoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<ProductoDocument, String> {
}
