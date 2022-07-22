package com.kimmingyu.aws.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {

    private String id;
    private Long idx;
    private String member_id;
    private String member_name;
    private String title;
    private String content;
    private String category;
    private int viewCnt;
    private String regDate;
    private Boolean enabled;
    private Boolean isSecret;

    public BoardDTO() { }

    public BoardDTO(String id, Long idx, String member_id, String member_name, String title, String content, int viewCnt,
                    String category, String regDate, Boolean enabled, Boolean isSecret) {
        this.id = id;
        this.idx = idx;
        this.member_id = member_id;
        this.member_name = member_name;
        this.title = title;
        this.content = content;
        this.category = category;
        this.viewCnt = viewCnt;
        this.regDate = regDate;
        this.enabled = enabled;
        this.isSecret = isSecret;
    }

}
