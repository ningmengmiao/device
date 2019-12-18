package cn.bptop.device.dao;

import cn.bptop.device.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface DeviceMapper
{
    List<Device> findDevice(@Param("dDeviceId") String dDeviceId,
                            @Param("dDeviceName") String dDeviceName,
                            @Param("dPlant") String dPlant,
                            @Param("dStatus") String dStatus,
                            @Param("dPic") String dPic);

    int updateDevice(@Param("ddeviceId") String ddeviceId,
                     @Param("ddeviceCid") String ddeviceCid,
                     @Param("ddeviceName") String ddeviceName,
                     @Param("ddeviceType") String ddeviceType,
                     @Param("dmanufacturer") String dmanufacturer,
                     @Param("dtestTime") String dtestTime,
                     @Param("dnote") String dnote,
                     @Param("dpic") String dpic,
                     @Param("dpicId") String dpicId,
                     @Param("dplant") String dplant,
                     @Param("dproductionTime") String dproductionTime,
                     @Param("dsite") String dsite,
                     @Param("dspace") String dspace,

                     @Param("dstatus") String dstatus
    );

    int newDevice(
            @Param("ddeviceCid") String ddeviceCid,
            @Param("ddeviceName") String ddeviceName,
            @Param("ddeviceType") String ddeviceType,
            @Param("dmanufacturer") String dmanufacturer,
            @Param("dtestTime") String dtestTime,
            @Param("dnote") String dnote,
            @Param("dpic") String dpic,
            @Param("dpicId") String dpicId,
            @Param("dplant") String dplant,
            @Param("dproductionTime") String dproductionTime,
            @Param("dsite") String dsite,
            @Param("dspace") String dspace,
            @Param("dlog") String dlog
    );
    int deleteDevice ( @Param("ddeviceId") String ddeviceId );

    int updateDeviceLog(@Param("ddeviceId") String ddeviceId, @Param("log") String log);

    List<Device> filterDevice(@Param("picId") String picId, @Param("devcieName") String deviceName, @Param("plant") String plant);

    List<Map> findPlant();

    List<Map> findDeviceName();
}
