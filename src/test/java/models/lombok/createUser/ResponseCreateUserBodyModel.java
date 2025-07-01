package models.lombok.createUser;

import lombok.Data;

@Data
public class ResponseCreateUserBodyModel {
    String id, name, job, createdAt;
}
