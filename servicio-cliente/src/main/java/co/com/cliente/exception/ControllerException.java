package co.com.cliente.exception;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;

import co.com.cliente.dto.ErrorDTO;
import co.com.cliente.util.IConstantes;
import co.com.cliente.util.IMensajes;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import lombok.extern.slf4j.Slf4j;

/**
 * Esta clase permite radicar en un solo lado los tipos de excepciones que
 * pueden ser atrapadas y lanzardas al cliente con un estado y mensaje
 * predeterminado. No es necesario hacerle un try-catch por heredar de
 * RuntimeException. Es una clase especial ya que la capa controladora no debe
 * preocuparse por este tipo de excepciones mencionadas aquí, ya que esta lanza
 * al cliente la mismo
 */
@Slf4j
@RestControllerAdvice
public class ControllerException extends Exception {


	private static final long serialVersionUID = 4581791676057366467L;

	// errores autodetectados por contenedor
	/**
	 * Se lanza cuando los parámetros de entrada al contralador son inválidos, es
	 * decir cuando se comparan con las anotaciones del Mapeo y resulta inválido al
	 * ingresar como RequestBody a la capa
	 * 
	 * @param request:  petición del contexto para atrapar si va hacia alguna página
	 * @param exception que viene
	 * @return ResponseEntity<>
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorDTO> invalidoObjetoDeEntrada(HttpServletRequest request,
			MethodArgumentNotValidException exception) {

		final HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;

		// obtiene errores spring
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		// convierte los mensajes a una cadena legible
		StringBuilder errorMessage = new StringBuilder();
		fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() + " "));

		return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), errorMessage.toString(), IConstantes.TIPO_WARNING,
				request.getRequestURI(), false), codigoHttp);
	}

	/**
	 * Atrapa excepciones de conversión de datos
	 * 
	 * @param request
	 * 
	 * 
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<ErrorDTO> invalidoConversionTipoDato(HttpServletRequest request,
																														 MethodArgumentTypeMismatchException e, HandlerMethod handlerMethod) {

		final HttpStatus codigoHttp = HttpStatus.METHOD_NOT_ALLOWED;

		saveExceptionLog(e, handlerMethod, true);

		return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), IMensajes.TIPO_DATO_ASIGNADO_INCORRECTAMENTE,
				IConstantes.TIPO_ERROR, request.getRequestURI(), false), codigoHttp);
	}

	/**
	 * Atrapa excepciones de tamaño en adjuntos
	 * 
	 * @param request
	 * 
	 */
	@ExceptionHandler({ MaxUploadSizeExceededException.class, SizeLimitExceededException.class, MultipartException.class })
	public ResponseEntity<ErrorDTO> maxSizeException(HttpServletRequest request, Exception e,
			HandlerMethod handlerMethod) {

		final HttpStatus codigoHttp = HttpStatus.EXPECTATION_FAILED;

		saveExceptionLog(e, handlerMethod, true);  

		return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), IMensajes.TAMANO_UPLOAD_FILE_SUPERADO,
				IConstantes.TIPO_ERROR, request.getRequestURI(), false), codigoHttp);
	}

	/**
	 * Transacción no exitosa cuando incumple anotaciones o validaciones de mapeo
	 * eje @NotNull o @Length
	 * 
	 */
	@ExceptionHandler({ javax.validation.ConstraintViolationException.class,
			org.springframework.transaction.TransactionSystemException.class,
			org.springframework.http.converter.HttpMessageNotReadableException.class,
			com.fasterxml.jackson.databind.exc.InvalidFormatException.class,
			org.springframework.web.HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<ErrorDTO> transaccionNoExitosaIncumpleValidacionesMapeo(HttpServletRequest request,
			Exception e) {

		final HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;

		return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), IMensajes.VALORES_INVALIDOS_ANTES_BD,
				IConstantes.TIPO_ERROR, request.getRequestURI(), false), codigoHttp);
	}

	/**
	 * Transacción no exitosa detectada por el contenedor (hibernate revisando bd),
	 * cuando no colocamos las anotaciones de una condición de mapeo, para que el
	 * motor o hibernate la detecte
	 * 
	 * 
	 */
	@ExceptionHandler({ ConstraintViolationException.class, TransientPropertyValueException.class,
			InvalidDataAccessApiUsageException.class, GenericJDBCException.class })
	public ResponseEntity<ErrorDTO> transaccionNoExitosaHibernateYBaseDatos(HttpServletRequest request, Exception e,
			HandlerMethod handlerMethod) {
		final HttpStatus codigoHttp = HttpStatus.CONFLICT;

		saveExceptionLog(e, handlerMethod, true);

		return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), IMensajes.VALORES_INVALIDOS_BD,
				IConstantes.TIPO_ERROR, request.getRequestURI(), false), codigoHttp);
	}

	/**
	 * Atrapa valores nulos en capa de servicio
	 * 
	 * 
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDTO> nullPointerException(HttpServletRequest request, NullPointerException e,
			HandlerMethod handlerMethod) {

		saveExceptionLog(e, handlerMethod, true);
		return new ResponseEntity<>(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), IMensajes.VALORES_NULOS,
				IConstantes.TIPO_ERROR, request.getRequestURI(), false), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// errores que pueden ser lanzados por programador

	/**
	 * Esta excepción se relanza al cliente cuando hay una validación de negocio que
	 * se incumple
	 */
	@ExceptionHandler(ValidacionException.class)
	public ResponseEntity<ErrorDTO> incumpleValidacionNegocio(HttpServletRequest request, ValidacionException validationException) {
		final HttpStatus codigoHttp = HttpStatus.CONFLICT;
		if (validationException.getCause() != null && validationException.getCause().getMessage().equals("true")) {
			return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), validationException.getMessage(), IConstantes.TIPO_WARNING,
					request.getRequestURI(), true), codigoHttp);
		} else {
			return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), validationException.getMessage(), IConstantes.TIPO_WARNING,
					request.getRequestURI(), false), codigoHttp);
		}
	}

	/**
	 * Esta esxepción se relanza al cliente cuando hay una transacción no exitosa
	 * que alguien aquiera relanzar o un error técnico
	 */
	@ExceptionHandler({ TransactionException.class, TechnicalException.class, IOException.class })
	public ResponseEntity<ErrorDTO> transaccioNoExitosaOErrorTecnicoAtrapado(Exception e, HandlerMethod handlerMethod) {
		final HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;

		saveExceptionLog(e, handlerMethod, false);

		return new ResponseEntity<>(
				new ErrorDTO(codigoHttp.value(), e.getMessage(), IConstantes.TIPO_ERROR, null, false), codigoHttp);
	}

	/**
	 * Esta excepción se relanza al cliente cuando hay un dato que no se encuentra
	 */
	@ExceptionHandler({ DataNotFoundException.class })
	public ResponseEntity<ErrorDTO> dataNotFound(DataNotFoundException e, HandlerMethod handlerMethod) {
		final HttpStatus codigoHttp = HttpStatus.NOT_FOUND;

		saveExceptionLog(e, handlerMethod, false);

		return new ResponseEntity<>(
				new ErrorDTO(codigoHttp.value(), e.getMessage(), IConstantes.TIPO_ERROR, null, false), codigoHttp);
	}

	/**
	 * Guarda log de la excepción, tanto en archivo como en base de datos
	 *
	 * @param e:                   exception y su información de clase de servcio,
	 * @param handlerMethod:       información que viene desde la capa controladora
	 * @param aExceptionContainer: si viene del contenedor o no controlada es true y
	 *                             si manual del usuario es false
	 */
	private void saveExceptionLog(Exception e, HandlerMethod handlerMethod, boolean aExceptionContainer) {
		String clase = null;
		String claseConPaquete = null;
		String metodo = null;
		String linea = null;
		String error = null;

		// capa de servicio o clase que lo lanza
		if (e != null) {
			claseConPaquete = e.getStackTrace()[0].getClassName();
			metodo = e.getStackTrace()[0].getMethodName();
			linea = "" + e.getStackTrace()[0].getLineNumber();
			error = aExceptionContainer ? e.toString() : e.getMessage();
			clase = e.getStackTrace()[0].getClassName()
					.split("\\.")[e.getStackTrace()[0].getClassName().split("\\.").length - 1];
		}

		// desde el origen padre (capac controladora)
		if (handlerMethod != null) {
			claseConPaquete = handlerMethod.getMethod().getDeclaringClass().getName();
			metodo = handlerMethod.getMethod().getName();
			linea = null;
			error = null;
			clase = handlerMethod.getMethod().getDeclaringClass().getName()
					.split(handlerMethod.getMethod().getDeclaringClass().getPackageName() + ".")[1];
		}
	}

}
