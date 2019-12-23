package com.jsjlzj.wayne.entity.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 团队角色表
 */
@Entity
public class MdlRole {
    public static final int ADMIN_ROLE_ID = 1;
    public static final int PMC_ROLE_ID = 2;
    public static final int SENIOR_ROLE_ID = 3;

    @Id(autoincrement = true)
    public long id;
    public String roleName;
    public long createtime;
    @Generated(hash = 1825427075)
    public MdlRole(long id, String roleName, long createtime) {
        this.id = id;
        this.roleName = roleName;
        this.createtime = createtime;
    }
    @Generated(hash = 1472709378)
    public MdlRole() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getRoleName() {
        return this.roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public long getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public MdlRole(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MdlRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdlRole role = (MdlRole) o;

        return id == role.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
