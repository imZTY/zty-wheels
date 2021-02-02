package com.zty.web.controller;

import com.zty.bo.service.FileService;
import com.zty.common.DO.FileInfoDO;
import com.zty.framework.annotation.CheckToken;
import com.zty.framework.dto.ResultDTO;
import com.zty.framework.util.FileUtil;
import com.zty.common.config.PathConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author tianyi
 * @date 2020-04-19 17:36
 */
@RestController
@RequestMapping("/file")
public class FileController {

    Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private PathConfig mapPathConfig;

    /**
     * @apiDefine File 文件
     */

    /**
     *  @apiDefine ResultDTO
     *  @apiSuccess {Integer} resultCode 响应结果
     *  @apiSuccess {String} resultMsg 结果描述
     *  @apiSuccess {Object} data 数据主体
     *  @apiSuccess {Integer} count 总数据量
     */

    /**
     * @api {post} /file/upload 上传文件
     * @apiGroup File
     * @apiParam {File} uploadFile 文件【必传】
     * @apiSuccessExample Success-Request:
     * {
     *     uploadFile: 二维码.jpg
     * }
     * @apiUse ResultDTO
     * @apiSuccessExample Success-Response:
     * {
     *     "resultCode": 200,
     *     "resultMsg": "成功",
     *     "data": {
     *         "id": 5,
     *         "name": "wxQRC.jpg",
     *         "fileKind": null,
     *         "publicUrl": "/zhihao/map/download?filename=1590148804080.jpg",
     *         "createBy": 2,
     *         "createTime": "2020-05-22T12:00:04.000+0000"
     *     },
     *     "count": 0
     * }
     */
    @CheckToken
    @PostMapping("/upload")
    public ResultDTO upload(FileInfoDO fileInfoDO, @RequestParam("uploadFile") MultipartFile uploadFile){
        if (uploadFile.isEmpty()) {
            // 判断文件是否为空
            log.error("上传的文件为空");
            return ResultDTO.error(403, "上传的文件为空");
        }else{
            // 参数校验
            if (fileInfoDO.getCurrentUID() == 0){
                return ResultDTO.error(403, "用户未登陆");
            }
            try {
                // 获取文件内容字节数组
                InputStream input = uploadFile.getInputStream();
                byte[] byt = new byte[input.available()];
                input.read(byt);
                // 关闭用完的流
                input.close();

                // 获取原始文件名
                String originalFilename = uploadFile.getOriginalFilename();
                String filename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
                // 存储路径
                String privateUrl = mapPathConfig.getStaticDir() + "/" + filename;
                // 外部访问路径
                String publicUrl = mapPathConfig.getPublicUrl() + filename;
                // 存储文件
                FileUtil.saveFile(privateUrl, byt);

                // 存入数据库
                fileInfoDO.setName(originalFilename);
                fileInfoDO.setPrivateUrl(privateUrl);
                fileInfoDO.setPublicUrl(publicUrl);
                fileInfoDO.setCreateTime(new Date());
                fileInfoDO.setCreateBy(fileInfoDO.getCurrentUID());

                FileInfoDO record = fileService.createAndReturnRecord(fileInfoDO);
                if (record != null){
                    return ResultDTO.success(record.parseFileInfoDTO());
                }else{
                    return ResultDTO.error(500, "存入数据库时失败，原因未知");
                }
            } catch (FileNotFoundException e) {
                log.error("系统错误，文件夹不存在, FileNotFoundException, ",e);
                return ResultDTO.error(500, "系统错误，文件夹不存在");
            } catch (IOException e) {
                log.error("系统错误，字节传输时出现异常, IOException, ",e);
                return ResultDTO.error(500, "系统错误，字节传输时出现异常");
            } catch (IllegalAccessException e) {
                log.error("反射脱敏时，字段访问非法", e);
                return ResultDTO.error(500, "反射脱敏时，字段访问非法");
            } catch (InstantiationException e) {
                log.error("反射脱敏时，实例化失败", e);
                return ResultDTO.error(500, "反射脱敏时，实例化失败");
            }
        }
    }

    @GetMapping("/download")
    public ResultDTO download(HttpServletResponse response, @RequestParam("filename")String filename){
        if (StringUtils.isBlank(filename)){
            return ResultDTO.error(403, "参数错误，未传入filename");
        }
        // 存储路径
        String filePath = mapPathConfig.getStaticDir() + "/" + filename;
        FileInputStream fileInputStream = null;
        byte[] byt = null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            byt = new byte[fileInputStream.available()];
            fileInputStream.read(byt);
        } catch (FileNotFoundException e) {
            log.error("系统错误，文件夹不存在, FileNotFoundException, ",e);
            return ResultDTO.error(500, "系统错误，文件夹不存在");
        } catch (IOException e) {
            log.error("系统错误，读取文件字节时出现异常, IOException, ",e);
            return ResultDTO.error(500, "系统错误，读取文件字节时出现异常");
        }
        if (byt != null){
            response.setContentType("image/*");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                out.write(byt);
                out.close();
            } catch (IOException e) {
                log.error("系统错误，返回文件字节时出现异常, IOException, ",e);
                return ResultDTO.error(500, "系统错误，返回文件字节时出现异常");
            }
        }else{
            return ResultDTO.error(444, "文件内容为空或不可读");
        }
        return ResultDTO.success();
    }

}
