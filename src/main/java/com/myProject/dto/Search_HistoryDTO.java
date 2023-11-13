package com.myProject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * Wifi 찾기
 */
public class Search_HistoryDTO {
    private int id;                     //id
    private String lat;                 //lat
    private String lnt;                 //lnt
    private String searchDttm;          //조회일자
}
