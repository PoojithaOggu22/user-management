package com.bilvantis.user.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PageDetailsDTO {
    private Integer pageNo;
    private Integer totalPages;
    private Integer perPage;
    private String sortingOrder;
}
