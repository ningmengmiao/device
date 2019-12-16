package cn.bptop.device.controller;

import cn.bptop.device.dao.DeviceMapper;
import cn.bptop.device.pojo.Device;
import cn.bptop.device.service.DeviceService;

import cn.bptop.device.service.EmailServer;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static cn.bptop.device.until.Ding.sendCardMsg;
import static cn.bptop.device.until.FileTool.deleteFile;
import static cn.bptop.device.until.Json.getJson;
import static cn.bptop.device.until.Json.jsonToObject;

@Controller
public class DeviceController
{
    @Autowired
    DeviceService deviceService;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    EmailServer emailServer;

    @ResponseBody
    @RequestMapping("/device/findAllDevice")
    public String findAllDevice()
    {
        return getJson(deviceService.findAllDevice());
    }

    @ResponseBody
    @RequestMapping("/device/searchDevice")
    public String searchDevice(String str)
    {
        return getJson(deviceService.searchDevice(str));
    }

    @ResponseBody
    @RequestMapping("/device/updateDevice")
    public int updateDevice(String opId, String ddeviceId, String ddeviceName, String ddeviceCid, String ddeviceType, String dproductionTime, String dtestTime, String dmanufacturer, String dplant, String dsite, String dspace, String dpic, String dnote)
    {
        return deviceService.updateDevice(opId, ddeviceId, ddeviceName, ddeviceCid, ddeviceType, dproductionTime, dtestTime, dmanufacturer, dplant, dsite, dspace, dpic, dnote);
    }

    @ResponseBody
    @RequestMapping("/device/newDevice")
    public int newDevice(String opId, String ddeviceName, String ddeviceCid, String ddeviceType, String dproductionTime, String dtestTime, String dmanufacturer, String dplant, String dsite, String dspace, String dpic, String dnote)
    {
        return deviceService.newDevice(opId, ddeviceName, ddeviceCid, ddeviceType, dproductionTime, dtestTime, dmanufacturer, dplant, dsite, dspace, dpic, dnote);
    }

    @ResponseBody
    @RequestMapping("/device/deleteDevice")
    public int deleteDevice(String opId, String ddeviceId)
    {
        return deviceService.deleteDevice(opId, ddeviceId);
    }

    @ResponseBody
    @RequestMapping("/device/sendMessage")
    public void sendMessage(String opId, String opName, String adminId, String message, String item) throws ApiException
    {
        Device device = jsonToObject(item, Device.class);
        OapiMessageCorpconversationAsyncsendV2Response response = sendCardMsg(adminId, "车间重要设备异常提报", message, "", "");
    }

    @ResponseBody
    @RequestMapping("/device/filterDevice")
    public String filterDevice(String picId, String deviceName, String plant)
    {
        return getJson(deviceMapper.filterDevice(picId, deviceName, plant));
    }

    @ResponseBody
    @RequestMapping("/device/findPlant")
    public String findPlant()
    {
        return getJson(deviceMapper.findPlant());
    }

    @ResponseBody
    @RequestMapping("/device/findDeviceName")
    public String findDeviceName()
    {
        return getJson(deviceMapper.findDeviceName());
    }

    @ResponseBody
    @RequestMapping("/device/exportExcel")
    public String exportExcel() throws IOException
    {
        return getJson(deviceService.exportExcel());
    }

    @ResponseBody
    @RequestMapping("/device/sendMail")
    public String sendMail(String to)
    {
        String file = "C:/file/装调车间重要设备台账.xls";
        String mp = emailServer.sendEmail(file, to);
        return getJson(mp);
    }
}
