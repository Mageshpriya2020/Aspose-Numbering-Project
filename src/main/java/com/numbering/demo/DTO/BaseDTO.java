package com.numbering.demo.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Transient;
import java.util.List;

public class BaseDTO {
    @JsonIgnore
    @Transient
    protected String reasonComment;
    @JsonIgnore
    @Transient
    protected String changedData;
    @JsonIgnore
    @Transient
    protected String  refEntityName;
    @JsonIgnore
    @Transient
    protected Long refEntityId;
    @JsonIgnore
    @Transient
    protected String  parentEntityName;
    @JsonIgnore
    @Transient
    protected Long parentEntityId;
    @JsonIgnore
    @Transient
    protected String action;
    @JsonIgnore
    @Transient
    protected String user;

    @JsonIgnore
    @Transient
    protected List diffrenceList;

    @JsonIgnore
    @Transient
    protected String createRef;


    public String getChangedData() {
        return changedData;
    }

    public void setChangedData(String changedData) {
        this.changedData = changedData;
    }

    public String getReasonComment() {
        return reasonComment;
    }

    public void setReasonComment(String reasonComment) {
        this.reasonComment = reasonComment;
    }

    public String getRefEntityName() {
        return refEntityName;
    }

    public void setRefEntityName(String refEntityName) {
        this.refEntityName = refEntityName;
    }

    public Long getRefEntityId() {
        return refEntityId;
    }

    public void setRefEntityId(Long refEntityId) {
        this.refEntityId = refEntityId;
    }

    public String getParentEntityName() {
        return parentEntityName;
    }

    public void setParentEntityName(String parentEntityName) {
        this.parentEntityName = parentEntityName;
    }

    public Long getParentEntityId() {
        return parentEntityId;
    }

    public void setParentEntityId(Long parentEntityId) {
        this.parentEntityId = parentEntityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List getDiffrenceList() {
        return diffrenceList;
    }

    public void setDiffrenceList(List diffrenceList) {
        this.diffrenceList = diffrenceList;
    }
    @JsonIgnore
    public String getAuditEntryValue() {
        return "";
    }
    @JsonIgnore
    public String getAuditEntryLable() {
        return "";
    }

    public String getCreateRef() {
        return createRef;
    }

    public void setCreateRef(String createRef) {
        this.createRef = createRef;
    }
}

