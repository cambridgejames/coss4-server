package cn.net.powerinv.coss4.entity;

import java.io.Serializable;
import java.util.Date;

public class Comp implements Serializable {
    private Integer id;

    private String compName;

    private Date startTime;

    private Integer userId;

    private String imageUrl;

    private Boolean endingSign;

    private Boolean enterSign;

    private Integer compState;

    private Date createTime;

    private Date updateTime;

    private String detailedInformation;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEndingSign() {
        return endingSign;
    }

    public void setEndingSign(Boolean endingSign) {
        this.endingSign = endingSign;
    }

    public Boolean getEnterSign() {
        return enterSign;
    }

    public void setEnterSign(Boolean enterSign) {
        this.enterSign = enterSign;
    }

    public Integer getCompState() {
        return compState;
    }

    public void setCompState(Integer compState) {
        this.compState = compState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDetailedInformation() {
        return detailedInformation;
    }

    public void setDetailedInformation(String detailedInformation) {
        this.detailedInformation = detailedInformation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", compName=").append(compName);
        sb.append(", startTime=").append(startTime);
        sb.append(", userId=").append(userId);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", endingSign=").append(endingSign);
        sb.append(", enterSign=").append(enterSign);
        sb.append(", compState=").append(compState);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", detailedInformation=").append(detailedInformation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}