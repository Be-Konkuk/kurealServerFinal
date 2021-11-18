package com.konkuk.kureal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Postings {
    private int pk;
    private String date;
    private String nickname;
    private String article;
    private String photo;
    private String tag;
    private double latitude;
    private double longitude;
    private int building;
}
