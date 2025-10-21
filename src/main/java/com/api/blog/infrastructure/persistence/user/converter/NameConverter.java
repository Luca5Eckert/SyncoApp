package com.api.blog.infrastructure.persistence.user.converter;

import com.api.blog.module.user.domain.vo.Name;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) 
public class NameConverter implements AttributeConverter<Name, String> {

    @Override
    public String convertToDatabaseColumn(Name name) {
        return name != null ? name.value() : null;
    }
    @Override
    public Name convertToEntityAttribute(String dbData) {
        return dbData != null ? new Name(dbData) : null;
    }

}