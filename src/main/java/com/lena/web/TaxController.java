package com.lena.web;


import com.lena.entity.Tax;
import com.lena.service.ITaxService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lena
 * @since 2019-03-07
 */
@Controller
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private ITaxService iTaxService;

    @RequestMapping("/taxdata")
    public String getsalarybasedatapage() {
        return "salarybasedata";
    }

    @RequestMapping("/emport")
    public String importAndEmport() {
        return "export";
    }

    @RequestMapping("/exportall")
    public String exportall() {
        System.out.println("下载成功");
        return "sussecc";
    }

    @RequestMapping("/importall")
    public String importall(MultipartFile filename) throws Exception {
        // 獲取流對象
        // MultipartFile 的filename必須和input的name屬性名稱一致
        InputStream is = filename.getInputStream();
        // 導入流對象
        HSSFWorkbook wb = new HSSFWorkbook(is);
        // 獲取sheet表的名稱

        HSSFSheet sheet = wb.getSheet("正常工资薪金收入");
        // 獲取有數據的最後一行
        for (Row row : sheet) {
            int index = 0;
            for (Cell cell : row) {
                // 读取数据前设置单元格类型
                cell.setCellType(CellType.STRING);
            }
        }
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(lastRowNum);
        // 除掉標題行,進行遍歷

        for (int i = 2; i < lastRowNum; i++) {
            for (int j = 0; j < 22; j++) {
                if(null==sheet.getRow(i).getCell(0)){
                    break;
                }
                Cell cell = sheet.getRow(i).getCell(j);

                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                }
                    // 獲取pojo對象
                    Tax tax =new Tax();
                    //数据id	id自增
                    //姓名	xingming
                   tax.setXingming(sheet.getRow(i).getCell(1).getStringCellValue());
                    //*证照类型	zhengzhaoleixing
                    tax.setZhengzhaoleixing(sheet.getRow(i).getCell(2).getStringCellValue());
                    //*证照号码	zhengzhaohaoma
                    tax.setZhengzhaohaoma(sheet.getRow(i).getCell(3).getStringCellValue());
                    //*本期收入	benqishouru
                    tax.setBenqishouru(sheet.getRow(i).getCell(4).getStringCellValue());
                    //本期免税收入	benqimianshuishouru
                    tax.setBenqimianshuishouru(sheet.getRow(i).getCell(5).getStringCellValue());
                    //基本养老保险费	jibenyanglaobaoxian
                    tax.setJibenyanglaobaoxian(sheet.getRow(i).getCell(6).getStringCellValue());
                    //基本医疗保险费	jibenyiliaobaoxianfei
                    tax.setJibenyiliaobaoxianfei(sheet.getRow(i).getCell(7).getStringCellValue());
                    //失业保险费	shiyebaoxianfei
                    tax.setShiyebaoxianfei(sheet.getRow(i).getCell(8).getStringCellValue());
                    //住房公积金	zhufanggongjijin
                    tax.setZhufanggongjijin(sheet.getRow(i).getCell(9).getStringCellValue());
                    //累计子女教育	leijizinvjiaoyu
                    tax.setLeijijixujiaoyu(sheet.getRow(i).getCell(10).getStringCellValue());
                    //累计继续教育	leijijixujiaoyu
                    tax.setLeijijixujiaoyu(sheet.getRow(i).getCell(11).getStringCellValue());
                    //累计住房贷款利息	leijizhufangdaikuanlixi
                    tax.setLeijizhufangdaikuanlixi(sheet.getRow(i).getCell(12).getStringCellValue());
                    //累计住房租金	leijizhufangzujin
                    tax.setLeijizhufangzujin(sheet.getRow(i).getCell(13).getStringCellValue());
                    //累计赡养老人	leijishanyanglaoren
                    tax.setLeijishanyanglaoren(sheet.getRow(i).getCell(14).getStringCellValue());
                    //企业(职业)年金	qiyenianjin
                    tax.setQiyenianjin(sheet.getRow(i).getCell(15).getStringCellValue());
                    //商业健康保险	shangyejiankangbaoxian
                    tax.setShangyejiankangbaoxian(sheet.getRow(i).getCell(16).getStringCellValue());
                    //税延养老保险	shuiyanyanglaobaoxian
                    tax.setShuiyanyanglaobaoxian(sheet.getRow(i).getCell(17).getStringCellValue());
                    //其他	qita
                    tax.setQita(sheet.getRow(i).getCell(8).getStringCellValue());
                    //准予扣除的捐赠额	zhunyukouchudejuanzenge
                    tax.setZhunyukouchudejuanzenge(sheet.getRow(i).getCell(19).getStringCellValue());
                    //减免税额	jianmianshuie
                    tax.setJianmianshuie(sheet.getRow(i).getCell(20).getStringCellValue());
                    //累计应补(退)税额	leijiyingbutuishuie
                    tax.setLeijiyingbutuishuie(sheet.getRow(i).getCell(21).getStringCellValue());
                    //部门	dept
                    tax.setDept(filename.getOriginalFilename());
                    System.out.println(filename.getOriginalFilename());
                    //创建时间	creatdate
                    tax.setCreatdate(new Date());



                    /*
                     * if (sheet.getRow(i).getCell(5).getStringCellValue() !=
                     * null) { // 出生年月 salarybasedata.setChushengnianyue( new
                     * SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).
                     * getCell(5).getStringCellValue())); } if
                     * (sheet.getRow(i).getCell(6).getStringCellValue() != null)
                     * { // 参加工作时间 salarybasedata.setCanjiagongzuoshijian( new
                     * SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).
                     * getCell(6).getStringCellValue())); } if
                     * (sheet.getRow(i).getCell(21).getStringCellValue() !=
                     * null) { // 最近一次岗位调整时间
                     * salarybasedata.setZuijingangweitiaozhengshijian( new
                     * SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).
                     * getCell(21).getStringCellValue())); }
                     */
                    // sheet.getRow(i).getCell(15).setCellType(CellType.STRING);
                    //  salarybasedata.setYuangdangci(Integer.parseInt(sheet.getRow(i).getCell(15).getStringCellValue()));
                    // 避免number數據轉string類型出錯，先設置cell內容類型
                    // sheet.getRow(i).getCell(2).setCellType(CellType.STRING);
                    // 字符串轉int類型
                    // salarybasedata.setAge(Integer.parseInt(sheet.getRow(i).getCell(2).getStringCellValue()));
                    // 調用addUser方法將對象數據傳入數據庫
                    //this.salaryBaseDataService.addSalarybasedata(salarybasedata);
                    //this.iTaxService.insert(tax);
                    System.out.println(tax);
                }
            }

        // 返回成功頁面
        return "sussecc";
    }

    /*
     * @RequestMapping("/exportall")
     *
     * @ResponseBody public void exportAll(HttpServletRequest request,
     * HttpServletResponse response, Model model) {
     *
     * // 加載數據庫數據 List<salarybasedata> salarybasedatas =
     * this.salaryBaseDataService.findAll(); // 建立工作簿 HSSFWorkbook wb = new
     * HSSFWorkbook(); // 建立sheet表格 HSSFSheet sheet = wb.createSheet("user"); //
     * 文件名稱 String fileName = "user2.xls"; // 表格行標題 HSSFRow row =
     * sheet.createRow(0); String[] header = { "Id", "姓名", "年龄" }; // 創建表格
     * HSSFCell cell = null; // 遍歷行標題 for (int i = 0; i < header.length; i++) {
     * cell = row.createCell(i); cell.setCellValue(header[i]); } // 設置行數據的起始位置
     * int rowCount = 1; // 遍歷list存取的對象 for (Salarybasedata salarybasedata :
     * salarybasedatas) { row = sheet.createRow(rowCount);
     * row.createCell(0).setCellValue(salarybasedata.getId());
     * row.createCell(1).setCellValue(salarybasedata.getName());
     * row.createCell(2).setCellValue(salarybasedata.getAge()); rowCount++; }
     * try { // 相應頭的設置response.setContentType(MIME)的作用是使客户端浏览器，区分不同种类的数据， //
     * 并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。octet-stream流的形式下载文件，这样可以实现任意格式的文件下载。
     *
     * response.reset(); response.setContentType("application/octet-stream"); //
     * Content-Disposition属性有两种类型：inline 和 attachment // inline ：将文件内容直接显示在页面 //
     * attachment弹出保存框代码 response.setHeader("Content-disposition",
     * "attachment;filename=" + fileName); // 刷新緩存
     *
     * :response有个buffer，flushBuffer()会强行把Buffer的 内容写到客户端浏览器。 （Forces any
     * content in the buffer to be written to the client. ），
     * 如果你不flushBuffer的话，当程序运行到最后的右大括号的时候， Tomcat也会把Response的Buffer的东西，一次性发给客户
     * 端。
     *
     * response.flushBuffer(); wb.write(response.getOutputStream());
     *
     * // wb.write(new File("d:\\userTwo.xls")); } catch (Exception e) {
     *
     * e.printStackTrace(); } finally { try { // 關閉流
     *
     * ServletOutputStream os = response.getOutputStream();
     *
     * os.close();
     *
     * wb.close();
     *
     * } catch (IOException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } } model.addAttribute("msg", "下載成功。。。。。。。");
     *
     * }
     */
}


