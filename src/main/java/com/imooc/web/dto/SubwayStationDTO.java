package com.imooc.web.dto;

/**
 * @ClassName SubwayStationDTO
 * @Description 地铁站点
 * @Author qiancy
 * @Date 2018/12/18 16:28
 * @Version 1.0
 **/
public class SubwayStationDTO {
    private Long id;
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
