package cn.bptop.device.service;

import cn.bptop.device.dao.DeviceMapper;
import cn.bptop.device.dao.UserMapper;
import cn.bptop.device.pojo.Device;
import cn.bptop.device.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.bptop.device.until.Json.getJson;

@Service
public class DeviceService
{
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    UserMapper userMapper;

    public List<Device> findAllDevice()
    {
        return deviceMapper.findDevice("", "", "", "", "");
    }

    public List<Device> searchDevice(String str)
    {
        List<Device> list = deviceMapper.findDevice("", "", "", "", "");
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
        for ( Object alist : list )
        {
            Matcher matcher = pattern.matcher(getJson(alist));
            if (matcher.find())
            {
                results.add(alist);
            }
        }
        return results;
    }

    public int updateDevice(String opId, String ddeviceId, String ddeviceName, String ddeviceCid, String ddeviceType, String dproductionTime, String dmanufacturer, String dplant, String dsite, String dspace, String dpic, String dnote)
    {
        User user;
        try
        {
            user = userMapper.findUser("", dpic);
            user.getUId();
        }
        catch (Exception e)
        {
            return 4;
        }
        return deviceMapper.updateDevice(ddeviceId, ddeviceCid, ddeviceName, ddeviceType, dmanufacturer, "", dnote, dpic, user.getUUserId(), dplant, dproductionTime, dsite, dspace, "", "");
    }


    public  int deleteDevice(String opId, String ddeviceId ){
        int res = deviceMapper.deleteDevice(ddeviceId);
        return res;
    }
}
