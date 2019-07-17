package com.cho2.finalproject.bean;

import java.io.Serializable;
import java.util.List;

public class MemberBean implements Serializable {

    public String userEmail; //이메일
    public String ImgIDuri; //사진 주소
    public String Phonenum;//핸드폰 번호
    public String name;//학생 이름
    public boolean isAdmin;//관리자 계정 구분

}
