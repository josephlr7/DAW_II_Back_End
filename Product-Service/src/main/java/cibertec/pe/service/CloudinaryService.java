package cibertec.pe.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;
    public String subirImagen(MultipartFile archivo) throws IOException {
        // Sube los bytes del archivo a Cloudinary en una carpeta llamada 'productos_cibertec'
        Map<?, ?> resultado = cloudinary.uploader().upload(
                archivo.getBytes(),
                ObjectUtils.asMap(
                        "folder", "productos_cibertec",
                        "resource_type", "image"
                )
        );
        // Devuelve la URL HTTPS oficial generada por Cloudinary
        return resultado.get("secure_url").toString();
    }
}
