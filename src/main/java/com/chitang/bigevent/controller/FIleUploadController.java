package com.chitang.bigevent.controller;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.credentials.StaticCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.chitang.bigevent.pojo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FIleUploadController {

    @Value("${aliyun.oss.region}")
    private String region;

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //保证文件名唯一，防止覆盖
        String fileName = UUID.randomUUID().toString()
                + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传到OSS
        try (OSSClient client = OSSClient.newBuilder()
                .region(region)
                .credentialsProvider(new StaticCredentialsProvider(accessKeyId, accessKeySecret))
                .build()) {

            client.putObject(PutObjectRequest.newBuilder()
                    .bucket(bucket)
                    .key(fileName)
                    .body(BinaryData.fromStream(file.getInputStream()))
                    .build());
        }

        //拼接公网访问地址
        String url = "https://" + bucket + ".oss-" + region + ".aliyuncs.com/" + fileName;
        return Result.success(url);
    }
}
