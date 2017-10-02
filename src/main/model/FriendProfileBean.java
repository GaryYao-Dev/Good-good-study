package main.model;

public class FriendProfileBean {

    private int userid;
    private String userName;
    private String photo;
    private String email;
    private String gender;
    private String u_day;
    private String u_year;
    private String u_month;
    private Boolean confirm;

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getU_day() {
        return u_day;
    }

    public void setU_day(String u_day) {
        this.u_day = u_day;
    }

    public String getU_year() {
        return u_year;
    }

    public void setU_year(String u_year) {
        this.u_year = u_year;
    }

    public String getU_month() {
        return u_month;
    }

    public void setU_month(String u_month) {
        this.u_month = u_month;
    }
}
