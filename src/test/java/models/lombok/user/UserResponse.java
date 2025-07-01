package models.lombok.user;

import lombok.Data;
import models.lombok.Support;

@Data
public class UserResponse {
    private UserData data;
    private Support support;
}
