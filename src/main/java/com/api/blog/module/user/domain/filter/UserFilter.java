package com.api.blog.module.user.domain.filter;

public class UserFilter {

    private String name;
    private String email;
    private String role;

    public UserFilter(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.role = builder.role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public static class Builder {

        private String name;
        private String email;
        private String role;

        public Builder(){
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setRole(String role){
            this.role = role;
            return this;
        }

        public UserFilter build(){
            return new UserFilter(this);
        }


    }


}
