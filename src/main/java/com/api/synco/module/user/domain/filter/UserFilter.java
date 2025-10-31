package com.api.synco.module.user.domain.filter;

import com.api.synco.module.user.domain.enumerator.RoleUser;
import java.time.Instant;

public record UserFilter(String nameContains,
                         String emailContains,
                         RoleUser role,
                         Instant createAt,
                         Instant createTo
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String nameContains;
        private String emailContains;
        private RoleUser role;
        private Instant createAt;
        private Instant createTo;

        private Builder() {
        }

        public Builder setNameContains(String nameContains) {
            this.nameContains = nameContains;
            return this;
        }

        public Builder setEmailContains(String emailContains) {
            this.emailContains = emailContains;
            return this;
        }

        public Builder setRole(RoleUser role) {
            this.role = role;
            return this;
        }

        public Builder setCreatedFrom(Instant createAt) {
            this.createAt = createAt;
            return this;
        }

        public Builder setCreatedTo(Instant createTo) {
            this.createTo = createTo;
            return this;
        }

        public UserFilter build() {
            return new UserFilter(
                    this.nameContains,
                    this.emailContains,
                    this.role,
                    this.createAt,
                    this.createTo
            );
        }
    }
}