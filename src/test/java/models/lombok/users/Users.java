package models.lombok.users;

import lombok.Data;

@Data
public class Users {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
