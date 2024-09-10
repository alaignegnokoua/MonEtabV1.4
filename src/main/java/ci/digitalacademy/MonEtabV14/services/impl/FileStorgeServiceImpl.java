package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.services.FileStorageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileStorgeServiceImpl implements FileStorageService {

    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file) throws IOException {
        Map params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,
                "overwrite", true
        );
        Map upload = cloudinary.uploader().upload(file.getBytes(), params1);
        System.out.println(upload);
        return upload.get("url").toString();
    }



}
