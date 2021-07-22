package co.com.cliente.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-foto", path = "/file", fallback = FotoHystrixFallBackFactory.class)
public interface FotoCliente {

	//@GetMapping("/download/{fileName}")
	//public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName);

	@GetMapping("/download/base64/{fileName}")
	public ResponseEntity<String> downloadBase64File(@PathVariable String fileName);

}
