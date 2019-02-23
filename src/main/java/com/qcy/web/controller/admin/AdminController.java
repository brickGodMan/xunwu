package com.qcy.web.controller.admin;

import com.qcy.base.ApiResponse;
import com.qcy.entity.SupportAddress;
import com.qcy.service.ServiceResult;
import com.qcy.service.house.IAddressService;
import com.qcy.service.house.IHouseService;
import com.qcy.utils.TFSClient;
import com.qcy.web.dto.HouseDTO;
import com.qcy.web.dto.SupportAddressDTO;
import com.qcy.web.form.HouseForm;
import com.taobao.common.tfs.TfsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisCluster;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName AdminController
 * @Description TODO
 * @Author qiancy
 * @Date 2018/11/1 12:44
 * @Version 1.0
 **/
@Controller
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private TfsManager tfsManager;

    @Value("${spring.http.multipart.location}")
    private String fileDir;
    /**
     * 后台管理中心
     * @return
     */
    @GetMapping("/admin/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    /**
     * 管理员登录页
     * @return
     */
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }

    /**
     * 房源列表页
     * @return
     */
    @GetMapping("admin/house/list")
    public String houseListPage() {
        return "admin/house-list";
    }

    /**
     * 新增房源功能页
     * @return
     */
    @GetMapping("admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }

    /**
     * 上传图片接口
     * @param file
     * @return
     */
    @PostMapping(value = "admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        //文件存放到本地
        String fileName = file.getOriginalFilename();
        Map<String,String> resultMap = new HashMap<>(3);
        try {
            String tfsName = TFSClient.uploadFile(file.getInputStream(),fileName.substring(fileName.indexOf(".")),null,tfsManager);
            resultMap.put("key",tfsName);
            // 通过MultipartFile得到InputStream，从而得到BufferedImage
            BufferedImage bufferedImage =ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                // 证明上传的文件不是图片，获取图片流失败，不进行下面的操作
                resultMap.put("width","120");
                resultMap.put("height","100");
            }else{
                // 通过图片流获取图片宽度
                Integer width = bufferedImage.getWidth();
                // 通过图片流获取图片高度
                Integer height = bufferedImage.getHeight();
                resultMap.put("width", String.valueOf(width));
                resultMap.put("height", String.valueOf(height));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
        return ApiResponse.ofSuccess(resultMap);
    }

    /**
     * 新增房源接口
     * @param houseForm
     * @param bindingResult
     * @return
     */
    @PostMapping("admin/add/house")
    @ResponseBody
    public ApiResponse addHouse(@Valid @ModelAttribute("form-house-add") HouseForm houseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }

        if (houseForm.getPhotos() == null || houseForm.getCover() == null) {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "必须上传图片");
        }

        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());
        int size = 2;
        if (addressMap.keySet().size() != size) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }

        ServiceResult<HouseDTO> result = houseService.save(houseForm);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess(result.getResult());
        }

        return ApiResponse.ofSuccess(ApiResponse.Status.NOT_VALID_PARAM);
    }
}
