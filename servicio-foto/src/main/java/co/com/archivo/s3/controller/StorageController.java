package co.com.archivo.s3.controller;

import co.com.archivo.s3.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;


@RestController
@RequestMapping("/file")
public class StorageController {

  @Autowired
  private StorageService service;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
    return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
    byte[] data = service.downloadFile(fileName);
    ByteArrayResource resource = new ByteArrayResource(data);
    return ResponseEntity
            .ok()
            .contentLength(data.length)
            .header("Content-type", "application/octet-stream")
            .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
            .body(resource);
  }

  @GetMapping("/download/base64/{fileName}")
  public ResponseEntity<String> downloadBase64File(@PathVariable String fileName) {
    byte[] data = service.downloadFile(fileName);
    // byte[] to base64 encoded string
    String base64 = Base64.getEncoder().encodeToString(data);
    return new ResponseEntity<>(base64, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{fileName}")
  public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
    return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
  }

  // base64 encoded string to byte[]
  //  byte[] decode = Base64.getDecoder().decode(s);

}
