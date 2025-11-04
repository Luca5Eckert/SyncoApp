package com.api.synco.module.course.domain.filter;

import com.api.synco.module.user.domain.enumerator.RoleUser;
import com.api.synco.module.user.domain.filter.UserFilter;

import java.time.Instant;

public record CourseFilter(String name, String acronym) {

    public static Builder builder() {
        return new CourseFilter.Builder();
    }

    public static class Builder {

        private String nameContains;
        private String acronymContains;

        private Builder() {
        }

        public Builder setNameContains(String nameContains) {
            this.nameContains = nameContains;
            return this;
        }

        public Builder setAcronymContains(String emailContains) {
            this.acronymContains = acronymContains;
            return this;
        }

        public CourseFilter build() {
            return new CourseFilter(
                    this.nameContains,
                    this.acronymContains
            );
        }

    }

}
