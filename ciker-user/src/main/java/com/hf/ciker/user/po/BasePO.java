package com.hf.ciker.user.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 12:37
 */
@Data
public class BasePO implements Serializable {
    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 更新者
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedDate;

    /**
     * 软删除（Y-删除，N-未删除）
     */
    private String isDelete;
}
