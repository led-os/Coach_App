package com.jsjlzj.wayne.entity.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class MdlAddressItem {

    @Id(autoincrement = true)
    public long id;

    public int gradeid;
    public String name;
    public long parentid;
    @Generated(hash = 975916001)
    public MdlAddressItem(long id, int gradeid, String name, long parentid) {
        this.id = id;
        this.gradeid = gradeid;
        this.name = name;
        this.parentid = parentid;
    }
    @Generated(hash = 1540070083)
    public MdlAddressItem() {
    }

    public MdlAddressItem(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getGradeid() {
        return this.gradeid;
    }
    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getParentid() {
        return this.parentid;
    }
    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdlAddressItem that = (MdlAddressItem) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "MdlAddressItem{" +
                "id=" + id +
                ", gradeid=" + gradeid +
                ", name='" + name + '\'' +
                ", parentid=" + parentid +
                '}';
    }
}
