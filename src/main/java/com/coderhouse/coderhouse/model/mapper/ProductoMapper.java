package com.coderhouse.coderhouse.model.mapper;

import com.coderhouse.coderhouse.document.ProductoDocument;
import com.coderhouse.coderhouse.model.response.ProductoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoResponse toFullModel(ProductoDocument document);
}
