package es.carlosnh.grovestreet.dto.jwt;

import es.carlosnh.grovestreet.entidades.Usuario;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface JwtMapper {
    JwtSignupResponse toDto(Usuario entity);
    Usuario toEntity(JwtSignupRequest dto);

}
