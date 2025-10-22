package com.api.blog.module.user.domain.filter;

public record PageUser(int pageNumber,
                       int pageSize) {

    public final static int MIN_PAGE_NUMBER = 0;
    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int MAX_PAGE_SIZE = 50;

    public PageUser {
        if (pageNumber < MIN_PAGE_NUMBER) {
            pageNumber = MIN_PAGE_NUMBER;
        }

        if (pageSize <= 0 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

    }

}