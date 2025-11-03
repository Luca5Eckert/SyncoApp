package com.api.synco.module.user.domain.vo;

import com.api.synco.module.user.domain.exception.email.EmailBlankDomainException;
import com.api.synco.module.user.domain.exception.email.EmailInvalidDomainException;
import com.api.synco.module.user.domain.exception.email.EmailLengthDomainException;
import org.apache.commons.validator.routines.EmailValidator;

public record Email(String address) {


    public Email{
        if(address.isBlank()) throw new EmailBlankDomainException();

        if(address.length() > 150) throw new EmailLengthDomainException(150);

        if(!isValid(address)) throw new EmailInvalidDomainException();

    }

    private boolean isValid(String address) {
        return EmailValidator.getInstance().isValid(address);
    }


}
