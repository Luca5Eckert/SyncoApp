package com.api.blog.module.user.domain.filter;

public class PagenableUserProvider {

    public static PageUser toInstance(int pageNumber, int pageSize) {
        return new PageUser(pageNumber, pageSize);
    }
}
