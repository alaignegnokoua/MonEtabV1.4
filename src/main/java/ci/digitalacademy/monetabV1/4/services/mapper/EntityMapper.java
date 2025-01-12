package ci.digitalacademy.MonEtabV14.services.mapper;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);
}
