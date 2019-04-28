package com.yidusoft.project.system.domain;

import javax.persistence.*;

@Table(name = "sec_menu_org")
public class SecMenuOrg {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 菜单ID
     */
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 获取主键ID
     *
     * @return ID_ - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return ORG_ID - 用户ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置用户ID
     *
     * @param orgId 用户ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取菜单ID
     *
     * @return MENU_ID - 菜单ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单ID
     *
     * @param menuId 菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}