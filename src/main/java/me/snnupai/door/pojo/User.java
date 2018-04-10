package me.snnupai.door.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    private String salt;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 个人描述
     */
    private String description;

    /**
     * 专业
     */
    private String major;

    /**
     * email
     */
    private String email;

    /**
     * 是否被封号
     */
    private Integer status;

    /**
     * 默认是女，1表示男
     */
    private Byte sex;

    /**
     * 注册状态
     */
    private Integer registerStatus;

    /**
     * 个人积分
     */
    private Integer accPoints;

    /**
     * 是否是vip会员
     */
    private Integer vip;

    /**
     * 出生年份
     */
    private Integer birthYear;

    /**
     * 头像url
     */
    private String headUrl;

    /**
     * 入学年份
     */
    private Integer entranceYear;

    private Date createdDate;

    private Date updatedDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public Integer getAccPoints() {
        return accPoints;
    }

    public void setAccPoints(Integer accPoints) {
        this.accPoints = accPoints;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getMajor() == null ? other.getMajor() == null : this.getMajor().equals(other.getMajor()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getRegisterStatus() == null ? other.getRegisterStatus() == null : this.getRegisterStatus().equals(other.getRegisterStatus()))
            && (this.getAccPoints() == null ? other.getAccPoints() == null : this.getAccPoints().equals(other.getAccPoints()))
            && (this.getVip() == null ? other.getVip() == null : this.getVip().equals(other.getVip()))
            && (this.getBirthYear() == null ? other.getBirthYear() == null : this.getBirthYear().equals(other.getBirthYear()))
            && (this.getHeadUrl() == null ? other.getHeadUrl() == null : this.getHeadUrl().equals(other.getHeadUrl()))
            && (this.getEntranceYear() == null ? other.getEntranceYear() == null : this.getEntranceYear().equals(other.getEntranceYear()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getUpdatedDate() == null ? other.getUpdatedDate() == null : this.getUpdatedDate().equals(other.getUpdatedDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getMajor() == null) ? 0 : getMajor().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getRegisterStatus() == null) ? 0 : getRegisterStatus().hashCode());
        result = prime * result + ((getAccPoints() == null) ? 0 : getAccPoints().hashCode());
        result = prime * result + ((getVip() == null) ? 0 : getVip().hashCode());
        result = prime * result + ((getBirthYear() == null) ? 0 : getBirthYear().hashCode());
        result = prime * result + ((getHeadUrl() == null) ? 0 : getHeadUrl().hashCode());
        result = prime * result + ((getEntranceYear() == null) ? 0 : getEntranceYear().hashCode());
        result = prime * result + ((getCreatedDate() == null) ? 0 : getCreatedDate().hashCode());
        result = prime * result + ((getUpdatedDate() == null) ? 0 : getUpdatedDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", realName=").append(realName);
        sb.append(", nickName=").append(nickName);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", phone=").append(phone);
        sb.append(", description=").append(description);
        sb.append(", major=").append(major);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append(", sex=").append(sex);
        sb.append(", registerStatus=").append(registerStatus);
        sb.append(", accPoints=").append(accPoints);
        sb.append(", vip=").append(vip);
        sb.append(", birthYear=").append(birthYear);
        sb.append(", headUrl=").append(headUrl);
        sb.append(", entranceYear=").append(entranceYear);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}