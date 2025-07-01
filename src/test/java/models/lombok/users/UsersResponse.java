package models.lombok.users;

import lombok.Data;
import models.lombok.Support;

import java.util.List;

@Data
public class UsersResponse {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<Users> data;
    private Support support;
}
