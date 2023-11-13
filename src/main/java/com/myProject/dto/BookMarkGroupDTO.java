package com.myProject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * 북마크 그룹
 */

public class BookMarkGroupDTO {
    private int id;  // id
    private String name; // X 좌표
    private int order; // Y 좌표
    private String register_dttm; // Y 좌표
    private String update_dttm; // 조회일자
}
