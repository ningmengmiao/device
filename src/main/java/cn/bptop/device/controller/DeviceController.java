package cn.bptop.device.controller;

import cn.bptop.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.bptop.device.until.Json.getJson;

@Controller
public class DeviceController
{
    @Autowired
    DeviceService deviceService;

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
    public int updateDevice(String opId, String ddeviceId, String ddeviceName, String ddeviceCid, String ddeviceType, String dproductionTime, String dmanufacturer, String dplant, String dsite, String dspace, String dpic, String dnote)
    {
        return deviceService.updateDevice(opId, ddeviceId, ddeviceName, ddeviceCid, ddeviceType, dproductionTime, dmanufacturer, dplant, dsite, dspace, dpic, dnote);
    }

    @ResponseBody
    @RequestMapping("/device/deleteDevice")
    public int deleteDevice(String opId, String ddeviceId)
    {
        return deviceService.deleteDevice(opId, ddeviceId);
    }
}
