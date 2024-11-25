package fr.cyu.jee.dto;

import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;

import java.util.Optional;
import java.util.function.Predicate;

public class SearchDTO {

    private String emailSearch;

    private UserType userType;

    public Optional<String> getEmailSearch() {
        return Optional.ofNullable(emailSearch);
    }

    public void setEmailSearch(String emailSearch) {
        this.emailSearch = emailSearch;
    }

    public Optional<UserType> getUserType() {
        return Optional.ofNullable(userType);
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean check(User user) {
        return getEmailSearch().map(email -> user.getEmail().startsWith(email)).orElse(true)
                && getUserType().map(type -> user.getUserType() == type).orElse(true);
    }
}
