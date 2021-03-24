package com.hower.hotel.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hower.hotel.framework.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentInfoDto extends BaseModel {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 父级部门
     */
    @JsonIgnore
    private Integer pId;
    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门状态
     */
    private Integer status;

    /**
     * 子部门
     */
    private List<DepartmentInfoDto> children;


}
