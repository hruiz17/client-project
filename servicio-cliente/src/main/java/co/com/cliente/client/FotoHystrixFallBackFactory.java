package co.com.cliente.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class FotoHystrixFallBackFactory implements FotoCliente {

  @Override
  public ResponseEntity<String> downloadBase64File(@PathVariable String fileName){
    return new ResponseEntity<>("Servicio-foto caido", HttpStatus.OK);
  }


}
