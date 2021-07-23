package co.com.cliente.exception;


import co.com.cliente.util.IMensajes;

/**
 * Captura excepciones de tipo validación de negocio que se lanza cuando ha
 * ocurrido un error en la validación de datos en el sistema o validaciones del
 * programador. Por lo general se usa en la capa de servicio.
 * 
 * No es necesario atrapar la excepción (ya que es no checkeada, heredada de
 * RuntimeException), Esta excepción la atrapa el controlleradvice de
 * ControllerException para hacer captura genérica (por ello se dice que no es
 * necesaria atraparla luego pero en caso que se haga, se debe relanzar)
 * 
 */
public class ValidacionException extends RuntimeException {

	private static final long serialVersionUID = -7104201407117843878L;

	/**
	 * Mensaje por defecto
	 */
	public ValidacionException() {
		super(IMensajes.MENSAJE_VALIDACION_NO_EXITOSA);
	}

	/**
	 * Mensaje pasado como parámetro
	 * 
	 * @param aMensaje
	 */
	public ValidacionException(String aMensaje) {
		super(aMensaje);
	}

	/**
	 * Mensaje pasado como parámetro, con posiblidad de mostrar otro parámetro en
	 * pantalla en true que permita al cliente continuar o enviar otros datos
	 * 
	 * @param aMensaje
	 */
	public ValidacionException(String aMensaje, boolean aPosibilidadContinuar) {
		super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
	}

	/**
	 * Mensaje + exception a relanzar
	 * 
	 * @param aMensaje
	 * @param aCausa:       exception que se puede relanzar
	 */
	public ValidacionException(String aMensaje, Throwable aCausa) {
		super(aMensaje, aCausa);
	}

	/**
	 * Mensaje + exception
	 * 
	 * @param aMensaje
	 * @param aException:       aException que trae la traza que se quiere
	 */
	public ValidacionException(String aMensaje, Exception aException) {
		super(aMensaje, aException);
	}

	/**
	 * Mensaje + Exception con traza completa
	 */
	public ValidacionException(String aMensaje, Throwable aCausa, boolean enableSuppression,
			boolean writableStackTrace) {
		super(aMensaje, aCausa, enableSuppression, writableStackTrace);
	}

}
