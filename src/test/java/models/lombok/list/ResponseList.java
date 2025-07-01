package models.lombok.list;


import lombok.Data;
import models.lombok.Support;

import java.util.List;

@Data
public class ResponseList {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<ResponseDataList> data;
    private Support support;

}