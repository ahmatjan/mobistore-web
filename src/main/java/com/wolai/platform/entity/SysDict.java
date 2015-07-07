package com.wolai.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * 
 * 数据字典实体
 * @author xuxiang
 * @version $Id$
 * @since
 * @see
 */
@Entity
@Table(name = "sys_dict")
public class SysDict extends idEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 3244534542389193186L;

    /**
     * 字典名称
     */
    private String name;
    
    /**
     * 字典编号
     */
    private String code;
    
    /**
     * 字典值
     */
    private String value;
    
    /**
     * 父字典
     */
    @Column(name = "parent_id")
    private Long parentId;
    
    /**
     * 父字典
     */
    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private SysDict parent;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public SysDict getParent() {
        return parent;
    }

    public void setParent(SysDict parent) {
        this.parent = parent;
    }
}