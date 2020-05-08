package by.bsu.easytutor.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper<E, D> implements Mapper<E, D>{

    @Autowired
    ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, entityClass);
    }

    @Override
    public D toDTO(E entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, dtoClass);
    }

    Converter<E, D> toDtoConverter() {
        return mappingContext -> {
            E source = mappingContext.getSource();
            D destination = mappingContext.getDestination();
            entityToDtoSpecificFields(source, destination);
            return mappingContext.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return mappingContext -> {
            D source = mappingContext.getSource();
            E destination = mappingContext.getDestination();
            dtoToEntitySpecificFields(source, destination);
            return mappingContext.getDestination();
        };
    }

    void entityToDtoSpecificFields(E entitySource, D dtoDestination) {}

    void dtoToEntitySpecificFields(D dtoSource, E entityDestination) {}
}
