package com.imooc.service.house;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.entity.Subway;
import com.imooc.entity.SubwayStation;
import com.imooc.entity.SupportAddress;
import com.imooc.repository.SubwayRepository;
import com.imooc.repository.SubwayStationRepository;
import com.imooc.repository.SupportAddressRepository;
import com.imooc.service.ServiceMultiResult;
import com.imooc.service.ServiceResult;
import com.imooc.web.dto.SubwayDTO;
import com.imooc.web.dto.SubwayStationDTO;
import com.imooc.web.dto.SupportAddressDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AddressServiceImpl
 * @Description TODO
 * @Author qiancy
 * @Date 2018/12/18 15:30
 * @Version 1.0
 **/
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private SubwayRepository subwayRepository;

    @Autowired
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());
        List<SupportAddressDTO> addressDTOS = new ArrayList<>();
        for (SupportAddress supportAddress : addresses) {
            SupportAddressDTO target = modelMapper.map(supportAddress, SupportAddressDTO.class);
            addressDTOS.add(target);
        }

        return new ServiceMultiResult<>(addressDTOS.size(), addressDTOS);
    }

    @Override
    public Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName) {
        Map<SupportAddress.Level, SupportAddressDTO> result = new HashMap<>();

        SupportAddress city = supportAddressRepository.findByEnNameAndLevel(cityEnName, SupportAddress.Level.CITY
                .getValue());
        SupportAddress region = supportAddressRepository.findByEnNameAndBelongTo(regionEnName, city.getEnName());

        result.put(SupportAddress.Level.CITY, modelMapper.map(city, SupportAddressDTO.class));
        result.put(SupportAddress.Level.REGION, modelMapper.map(region, SupportAddressDTO.class));
        return result;
    }

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityName) {
        if (cityName == null) {
            return new ServiceMultiResult<>(0, null);
        }

        List<SupportAddressDTO> result = new ArrayList<>();

        List<SupportAddress> regions = supportAddressRepository.findAllByLevelAndBelongTo(SupportAddress.Level.REGION
                .getValue(), cityName);
        for (SupportAddress region : regions) {
            result.add(modelMapper.map(region, SupportAddressDTO.class));
        }
        return new ServiceMultiResult<>(regions.size(), result);
    }

    @Override
    public List<SubwayDTO> findAllSubwayByCity(String cityEnName) {
        List<SubwayDTO> result = new ArrayList<>();
        List<Subway> subways = subwayRepository.findAllByCityEnName(cityEnName);
        if (subways.isEmpty()) {
            return result;
        }

        subways.forEach(subway -> result.add(modelMapper.map(subway, SubwayDTO.class)));
        return result;
    }

    @Override
    public List<SubwayStationDTO> findAllStationBySubway(Long subwayId) {
        List<SubwayStationDTO> result = new ArrayList<>();
        List<SubwayStation> stations = subwayStationRepository.findAllBySubwayId(subwayId);
        if (stations.isEmpty()) {
            return result;
        }

        stations.forEach(station -> result.add(modelMapper.map(station, SubwayStationDTO.class)));
        return result;
    }

    @Override
    public ServiceResult<SubwayDTO> findSubway(Long subwayId) {
        if (subwayId == null) {
            return ServiceResult.notFound();
        }
        Subway subway = subwayRepository.findOne(subwayId);
        if (subway == null) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(modelMapper.map(subway, SubwayDTO.class));
    }

    @Override
    public ServiceResult<SubwayStationDTO> findSubwayStation(Long stationId) {
        if (stationId == null) {
            return ServiceResult.notFound();
        }
        SubwayStation station = subwayStationRepository.findOne(stationId);
        if (station == null) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(modelMapper.map(station, SubwayStationDTO.class));
    }

    @Override
    public ServiceResult<SupportAddressDTO> findCity(String cityEnName) {
        if (cityEnName == null) {
            return ServiceResult.notFound();
        }

        SupportAddress supportAddress = supportAddressRepository.findByEnNameAndLevel(cityEnName, SupportAddress.Level.CITY.getValue());
        if (supportAddress == null) {
            return ServiceResult.notFound();
        }

        SupportAddressDTO addressDTO = modelMapper.map(supportAddress, SupportAddressDTO.class);
        return ServiceResult.of(addressDTO);
    }




}
