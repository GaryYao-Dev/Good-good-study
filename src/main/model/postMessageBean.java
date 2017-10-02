package main.model;


import java.sql.Timestamp;
import java.util.List;

public class postMessageBean {

    private int p_id;
    private String p_content;
    private String p_image;
    private Timestamp p_time;
    private int p_userid;

    private String username;
    private String userPhoto;


    private String like;


    private List<String> likeUserList;
    private int listUseramount;



    public  postMessageBean(){

    }

    public List<String> getLikeUserList() {
        return likeUserList;
    }

    public void setLikeUserList(List<String> likeUserList) {
        this.likeUserList = likeUserList;
    }

    public int getListUseramount() {
        return listUseramount;
    }

    public void setListUseramount(int listUseramount) {
        this.listUseramount = listUseramount;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getP_id() {
        return this.p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_content() {
        return this.p_content;
    }

    public void setP_content(String p_content) {
        this.p_content = p_content;
    }

    public String getP_image() {
        return this.p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public Timestamp getP_time() {
        return this.p_time;
    }

    public void setP_time(Timestamp p_time) {
        this.p_time = p_time;
    }

    public int getP_userid() {
        return this.p_userid;
    }

    public void setP_userid(int p_userid) {
        this.p_userid = p_userid;
    }






}
