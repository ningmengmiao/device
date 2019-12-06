package cn.bptop.device.dao;

import cn.bptop.device.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.assertj.core.util.VisibleForTesting;
import org.springframework.stereotype.Component;

import java.util.List;

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
                     @Param("dlog") String dlog,
                     @Param("dstatus") String dstatus

    );
    int deleteDevice ( @Param("ddeviceId") String ddeviceId );

}
