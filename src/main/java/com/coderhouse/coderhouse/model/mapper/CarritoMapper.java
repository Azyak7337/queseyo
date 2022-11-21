package com.coderhouse.coderhouse.model.mapper;

import com.coderhouse.coderhouse.document.CarritoDocument;
import com.coderhouse.coderhouse.model.response.CarritoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarritoMapper {

    CarritoResponse toFullModel(CarritoDocument document);
}
