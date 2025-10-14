package com.api.blog.module.user.domain.exception.email;

public class EmailInvalidException extends RuntimeException {

  public EmailInvalidException(String message) {
    super(message);
  }

  public EmailInvalidException() {
    super("The format of email is invalid. Please insert a correct");
  }


}
