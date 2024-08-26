package com.bookShare.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/image")
public class ImageController {

    private String saveTempImageLocally(MultipartFile image) throws IOException {
        String tempImagePath = System.getProperty("java.io.tmpdir") + "/temp_" + image.getOriginalFilename();
        File file = new File(tempImagePath);
        file.getParentFile().mkdirs(); // Asegúrate de que los directorios existen
        image.transferTo(file);

        return tempImagePath;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("No image file provided");
        }

        // TODO: Implementar el almacenamiento de la imagen en el servidor y retornar la ruta pública para acceder a ella

        // Set your Cloudinary credentials

        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
        System.out.println(cloudinary.config.cloudName);

        // Upload the image
        Map params1 = ObjectUtils.asMap(
            "use_filename", false,
            "unique_filename", true,
            "overwrite", false
        );

        String imageUrl = saveTempImageLocally(image);

        Map result = cloudinary.uploader().upload(imageUrl, params1);

        System.out.println(result);

        return result.getOrDefault("url", "").toString();



        // Generar un nombre único para la imagen
        // String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

        // // Ruta en el servidor
        // String currentDir = System.getProperty("user.dir");
        // // Ruta relativa en el servidor del frontend
        // String relativePath = Paths.get(currentDir, "..", "bookshare", "public", "user_images", imageName).toString();

        // File file = new File(relativePath);
        // file.getParentFile().mkdirs(); // Asegúrate de que los directorios existen
        // image.transferTo(file);

        // // Retorna la ruta pública para acceder a la imagen
        // return "public/user_images/" + imageName;
    }
}
