package com.example.fc_api.domains.users.input;

import com.example.fc_api.controller.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
