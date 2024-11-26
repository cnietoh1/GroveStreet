package es.carlosnh.grovestreet.dto.propiedad;

import es.carlosnh.grovestreet.entidades.Propiedad;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface PropiedadMapper {

    PropiedadDto toDto(Propiedad propiedad);

    Propiedad toEntity(PropiedadDto dto);
}
