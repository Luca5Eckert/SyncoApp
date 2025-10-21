package com.api.blog.infrastructure.persistence.user.converter;

import com.api.blog.module.user.domain.vo.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email email) {
        return email != null ? email.address() : null;
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return dbData != null ? new Email(dbData) : null;
    }

}