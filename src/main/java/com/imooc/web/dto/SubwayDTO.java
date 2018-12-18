package com.imooc.web.dto;

/**
 * @ClassName SubwayDTO
 * @Description 地铁线路
 * @Author qiancy
 * @Date 2018/12/18 16:27
 * @Version 1.0
 **/
public class SubwayDTO {

    private Long id;
    private String name;
    private String cityEnName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }
}
