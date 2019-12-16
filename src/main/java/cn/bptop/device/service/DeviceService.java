package cn.bptop.device.service;

import cn.bptop.device.dao.DeviceMapper;
import cn.bptop.device.dao.UserMapper;
import cn.bptop.device.pojo.Device;
import cn.bptop.device.pojo.User;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.bptop.device.until.Json.getJson;
import static cn.bptop.device.until.Tool.getUrlDate;

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

    public int updateDevice(String opId, String ddeviceId, String ddeviceName, String ddeviceCid, String ddeviceType, String dproductionTime, String dtestTime, String dmanufacturer, String dplant, String dsite, String dspace, String dpic, String dnote)
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
        int res = deviceMapper.updateDevice(ddeviceId, ddeviceCid, ddeviceName, ddeviceType, dmanufacturer, dtestTime, dnote, dpic, user.getUUserId(), dplant, dproductionTime, dsite, dspace, "");
        if (res == 1)
        {
            String str = userMapper.findUser(opId, "").getUUserName() + "修改了设备记录";
            DeviceLog(ddeviceId, str);
        }
        return res;
    }

    public  int deleteDevice(String opId, String ddeviceId ){
        int res = deviceMapper.deleteDevice(ddeviceId);
        if (res == 1)
        {
            String str = userMapper.findUser(opId, "").getUUserName() + "删除了设备记录";
            DeviceLog(ddeviceId, str);
        }
        return res;
    }

    public void DeviceLog(String ddeviceId, String str)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(getUrlDate());
        String log = date + "--" + str + " \\n";
        deviceMapper.updateDeviceLog(ddeviceId, log);
    }

    public int newDevice(String opId, String ddeviceName, String ddeviceCid, String ddeviceType, String dproductionTime, String dtestTime, String dmanufacturer, String dplant, String dsite, String dspace, String dpic, String dnote)
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(getUrlDate());
        String str = userMapper.findUser(opId, "").getUUserName() + "添加了设备";
        String dlog = date + "--" + str + " \\n";
        int res = deviceMapper.newDevice(ddeviceCid, ddeviceName, ddeviceType, dmanufacturer, dtestTime, dnote, dpic, user.getUUserId(), dplant, dproductionTime, dsite, dspace, dlog);
        return res;
    }

    public String exportExcel() throws IOException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HHmmsszz");
//        String file = "C:/file/装调车间重要设备台账" + sdf.format(getUrlDate()) + ".xls";
        String file = "C:/file/装调车间重要设备台账.xls";
        FileOutputStream out = new FileOutputStream(new File(file));
        List<Device> list = findAllDevice();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);//下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setAlignment(HorizontalAlignment.CENTER);
        HSSFSheet sheet0 = wb.createSheet("调试车间重要设备");
        HSSFSheet[] sheet = {sheet0};
        for ( int i = 0; i < 1; i++ )
        {
            CellRangeAddress region = new CellRangeAddress(0, 0, 0, 10);
            sheet[i].addMergedRegion(region);
            HSSFRow titlerRow = sheet[i].createRow(0);
            titlerRow.createCell(0).setCellStyle(style);
            titlerRow.getCell(0).setCellValue("装调车间重要设备管理台账");
            titlerRow = sheet[i].createRow(1);
            titlerRow.createCell(0).setCellValue("序号");
            titlerRow.createCell(1).setCellValue("设备编号");
            titlerRow.createCell(2).setCellValue("设备名称");
            titlerRow.createCell(3).setCellValue("使用时间");
            titlerRow.createCell(4).setCellValue("设备型号、规格");
            titlerRow.createCell(5).setCellValue("制造商");
            titlerRow.createCell(6).setCellValue("调试间");
            titlerRow.createCell(7).setCellValue("占地面积");
            titlerRow.createCell(8).setCellValue("备注");
            titlerRow.createCell(9).setCellValue("负责人");
        }
        //4.遍历数据,创建数据行
        for ( Device alist : list )
        {
            int i = 0;
            //获取最后一行的行号
            int lastRowNum = sheet[i].getLastRowNum();
            HSSFRow dataRow = sheet[i].createRow(lastRowNum + 1);
//            System.out.println(lastRowNum);
            dataRow.createCell(0).setCellValue(lastRowNum);
            dataRow.createCell(1).setCellValue(alist.getDDeviceCid());
            dataRow.createCell(2).setCellValue(alist.getDDeviceName());
            dataRow.createCell(3).setCellValue(alist.getDProductionTime());
            dataRow.createCell(4).setCellValue(alist.getDDeviceType());
            dataRow.createCell(5).setCellValue(alist.getDManufacturer());
            dataRow.createCell(6).setCellValue(alist.getDPlant());
            dataRow.createCell(7).setCellValue(alist.getDSpace());
            dataRow.createCell(8).setCellValue(alist.getDNote());
            dataRow.createCell(9).setCellValue(alist.getDPic());
        }
//
        for ( int i = 0; i < 1; i++ )
        {
            for ( int j = 1; j < sheet[i].getLastRowNum() + 1; j++ )
            {
                sheet[i].getRow(j).setHeight((short) 400);
                for ( int k = 0; k < sheet[i].getRow(1).getLastCellNum(); k++ )
                {
                    sheet[i].autoSizeColumn((short) k);
                    // 解决自动设置列宽中文失效的问题
                    sheet[i].setColumnWidth(k, sheet[i].getColumnWidth(k) * 17 / 10);
                    sheet[i].getRow(j).getCell(k).setCellStyle(style);
                }
            }
        }
//        //7.获取mimeType
//        ServletContext servletContext = ServletActionContext.getServletContext();
//        String mimeType = servletContext.getMimeType(fileName);
//        //8.获取浏览器信息,对文件名进行重新编码
//        HttpServletRequest request = ServletActionContext.getRequest();
//        fileName = FileUtils.filenameEncoding(fileName, request);
//        //9.设置信息头
//        response.setContentType(mimeType);
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        wb.write(out);
        out.close();
        return file;
    }
}
