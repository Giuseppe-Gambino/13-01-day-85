package it.epicode.gestione_viaggi.cloudinary;

import it.epicode.gestione_viaggi.dto.RequestCambioStato;
import it.epicode.gestione_viaggi.services.DipendenteSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@RequiredArgsConstructor
public class CloudinaryUpload {

        private final CloudinarySvc cloudinaryService;
        private final DipendenteSvc dipendenteSvc;

        @PostMapping(path="/upload/{id}", consumes = "multipart/form-data")
        public ResponseEntity<Map> uploadFile( @RequestPart("file") MultipartFile file, @PathVariable Long id) {
            String folder = "test";

            Map result = cloudinaryService.uploader(file, folder);
            // se si vuole restituire solo url usare il get della mappa
            // result.get("url");

            String cover = (String) result.get("url");
            dipendenteSvc.editCover(id,cover);

            return ResponseEntity.ok(result);
        }

}
