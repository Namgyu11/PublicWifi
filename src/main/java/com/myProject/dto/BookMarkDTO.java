package com.myProject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * 북마크 리스트
 */
public class BookMarkDTO {
    private int id; // id
    private int groupNo; // 북마크 그룹 번호
    private String mgrNo; // 와이파이 번호
    private String register_dttm; // 등록일자
}
