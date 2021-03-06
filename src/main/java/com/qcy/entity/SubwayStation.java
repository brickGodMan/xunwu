package com.qcy.entity;

import javax.persistence.*;

/**
 * @ClassName SubwayStation
 * @Description TODO
 * @Author qiancy
 * @Date 2018/12/18 16:33
 * @Version 1.0
 **/
@Entity
@Table(name = "subway_station")
public class SubwayStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subway_id")
    private Long subwayId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(Long subwayId) {
        this.subwayId = subwayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
