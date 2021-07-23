package co.com.cliente.exception;


import co.com.cliente.util.IMensajes;

/**
 * Excepción que se lanza cuando se intenta acceder a un recurso que no existe.
 * La controla la capa controlleradvice, De manera genérica
 */
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9053298484630521305L;

	/**
	 * Mensaje por defecto
	 */
	public DataNotFoundException() {
		super(IMensajes.NO_SE_ENCUENTRA_EL_RECURSO);
	}

	/**
	 * Mensaje pasado como parámetro
	 *
	 * @param aMensaje
	 */
	public DataNotFoundException(String aMensaje) {
		super(aMensaje);
	}

	/**
	 * Mensaje pasado como parámetro, con posiblidad de mostrar otro parámetro en
	 * pantalla en true que permita al cliente continuar o enviar otros datos
	 *
	 * @param aMensaje
	 */
	public DataNotFoundException(String aMensaje, boolean aPosibilidadContinuar) {
		super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
	}

	/**
	 * Mensaje + exception a relanzar
	 *
	 * @param aMensaje
	 * @param e:       exception que se puede relanzar
	 */
	public DataNotFoundException(String aMensaje, Throwable aCausa) {
		super(aMensaje, aCausa);
	}

	/**
	 * Mensaje + exception
	 * 
	 * @param aMensaje
	 * @param aException: aException que trae la traza que se quiere
	 */
	public DataNotFoundException(String aMensaje, Exception aException) {
		super(aMensaje, aException);
	}

	/**
	 * Mnesaaje + Exception con traza completa
	 */
	public DataNotFoundException(String aMensaje, Throwable aCausa, boolean enableSuppression,
			boolean writableStackTrace) {
		super(aMensaje, aCausa, enableSuppression, writableStackTrace);
	}

}
