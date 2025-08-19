package cn.smxy.forum.controller;

import cn.smxy.forum.utils.AjaxResult;
import cn.smxy.forum.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("")
public class FileController {

    // 根目录
    private static final String ROOT_DIR = "F:/Java/YearDesign/upload/";
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/uploadfile")
    public R uploadFile(MultipartFile file) throws Exception {
        // 1. 计算子目录：2024/08/17
        String datePath = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        // 2. 完整目录
        File folder = new File(ROOT_DIR + datePath);
        if (!folder.exists()) {
            folder.mkdirs();   // 递归创建
        }

        // 3. 新文件名
        String original = file.getOriginalFilename();
        String suffix   = original.substring(original.lastIndexOf('.'));
        String newName  = UUID.randomUUID().toString() + suffix;

        // 4. 保存到磁盘
        File dest = new File(folder, newName);
        file.transferTo(dest);

        // 5. 对外访问 URL
        String url = "http://localhost:12050/upload/" + datePath + "/" + newName;
        return R.ok(url, "文件上传成功");
    }

    @PostMapping("/richText/uploadFile")
    public AjaxResult uploadRichText(MultipartFile file, String type) throws Exception {
        String fileName = file.getOriginalFilename();
        AjaxResult ajaxResult=AjaxResult.success("操作成功");

        String newFileName = UUID.randomUUID().toString()+fileName;
        String filePath="F:/Java/YearDesign/upload/"+newFileName;

        File f=new File(filePath);
        file.transferTo(f);

        String imagePath="http://localhost:8089/upload/"+newFileName;

        if("img".equals(type)){
            Map<String,Object> m=new HashMap<>();
            m.put("url",imagePath);
            List<Map<String,Object>> list=new ArrayList<>();
            list.add(m);

            ajaxResult.put("errno",0);
            ajaxResult.put("data",list);
            return ajaxResult;
        }else{
            Map<String,Object> m=new HashMap<>();
            m.put("url",imagePath);

            ajaxResult.put("errno",0);
            ajaxResult.put("data",m);
            return ajaxResult;
        }
    }
}
