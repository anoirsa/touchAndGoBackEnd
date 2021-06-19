package com.example.springtouchgo.model.appuser;

public enum Permissions {
    VIEW_ALL_USER_SCORE("user:read_all_scores"),
    EDIT_ALL_SCORE("user:edit_own_score"),
    VIEW_ALL_DETAILS("user_view_all_details"),
    MODIFY_USER_DETAILS("user_modify_details"),
    ADD_OWN_NEW_USER("user:add");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
