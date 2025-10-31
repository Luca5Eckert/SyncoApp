package com.api.synco.module.user.domain.vo;

import com.api.synco.module.user.domain.exception.email.EmailBlankException;
import com.api.synco.module.user.domain.exception.email.EmailInvalidException;
import com.api.synco.module.user.domain.exception.email.EmailLengthException;
import org.apache.commons.validator.routines.EmailValidator;

public record Email(String address) {


    public Email{
        if(address.isBlank()) throw new EmailBlankException();

        if(address.length() > 150) throw new EmailLengthException(150);

        if(!isValid(address)) throw new EmailInvalidException();

    }

    private boolean isValid(String address) {
        return EmailValidator.getInstance().isValid(address);
    }


}
