package cn.net.powerinv.coss4.entity;

import java.io.Serializable;
import java.util.Date;

public class Comps implements Serializable {
    private Integer id;

    private String compName;

    private Date startTime;

    private Boolean endingSign;

    private Boolean enterSign;

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
        sb.append(", endingSign=").append(endingSign);
        sb.append(", enterSign=").append(enterSign);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", detailedInformation=").append(detailedInformation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}