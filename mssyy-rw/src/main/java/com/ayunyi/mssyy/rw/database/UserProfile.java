package com.ayunyi.mssyy.rw.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ft on 2018/9/19.
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {

    @Id(autoincrement = true)
    private long userID = 0;
    private String name = null;
    private String avatar = null;
    private String gender = null;
    private String address = null;
    @Generated(hash = 1783208083)
    public UserProfile(long userID, String name, String avatar, String gender,
            String address) {
        this.userID = userID;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserID() {
        return this.userID;
    }
    public void setUserID(long userID) {
        this.userID = userID;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
