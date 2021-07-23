package co.com.cliente.exception;


import co.com.cliente.util.IMensajes;

/**
 * Captura excepciones de transacciones no exitosas: por ejemplo cuando base de
 * datos lanza una excepcion. Puede usarse cuando una transacción no es exitosa
 * según lo deseado.
 * 
 * Esta excepción la atrapa el controlleradvice de ControllerException para
 * hacer captura genérica .
 */
public class TransactionException extends RuntimeException {

	private static final long serialVersionUID = 5841953166572722091L;

	/**
	 * Mensaje por defecto
	 */
	public TransactionException() {
		super(IMensajes.MENSAJE_TRANSACCION_NO_EXITOSA);
	}

	/**
	 * Mensaje pasado como parámetro
	 * 
	 * @param aMensaje
	 */
	public TransactionException(String aMensaje) {
		super(aMensaje);
	}

	/**
	 * Mensaje pasado como parámetro, con posiblidad de mostrar otro parámetro en
	 * pantalla en true que permita al cliente continuar o enviar otros datos
	 * 
	 * @param aMensaje
	 */
	public TransactionException(String aMensaje, boolean aPosibilidadContinuar) {
		super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
	}

	/**
	 * Mensaje + exception a relanzar
	 * 
	 * @param aMensaje
	 * @param aCausa:       exception que se puede relanzar
	 */
	public TransactionException(String aMensaje, Throwable aCausa) {
		super(aMensaje, aCausa);
	}

	/**
	 * Mensaje + exception
	 * 
	 * @param aMensaje
	 * @param aException:       aException que trae la traza que se quiere
	 */
	public TransactionException(String aMensaje, Exception aException) {
		super(aMensaje, aException);
	}

	/**
	 * Mensaje + Exception con traza completa
	 */
	public TransactionException(String aMensaje, Throwable aCausa, boolean enableSuppression,
			boolean writableStackTrace) {
		super(aMensaje, aCausa, enableSuppression, writableStackTrace);
	}
}
