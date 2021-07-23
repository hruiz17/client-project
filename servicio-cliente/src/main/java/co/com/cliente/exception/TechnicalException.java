package co.com.cliente.exception;


import co.com.cliente.util.IMensajes;

/**
 * Este tipo de excepción se debe lanzar cuando se captura algún error técnico.
 * Un error técnico se define como un error que el usuario no puede manejar y
 * debe contactar con un administrador o personal técnico. Ej: Error de
 * configuración grave, error de programación, etc
 * 
 * Esta excepción la atrapa el controlleradvice de ControllerException para
 * hacer captura genérica (por ello se dice que no es necesaria atraparla luego
 * pero en caso que se haga, se debe relanzar)
 *
 */
public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = -164380584136127902L;

	/**
	 * Mensaje por defecto
	 */
	public TechnicalException() {
		super(IMensajes.MENSAJE_PROBLEMAS_TECNICOS);
	}

	/**
	 * Mensaje pasado como parámetro
	 * 
	 * @param aMensaje
	 */
	public TechnicalException(String aMensaje) {
		super(aMensaje);
	}

	/**
	 * Mensaje pasado como parámetro, con posiblidad de mostrar otro parámetro en
	 * pantalla en true que permita al cliente continuar o enviar otros datos
	 * 
	 * @param aMensaje
	 */
	public TechnicalException(String aMensaje, boolean aPosibilidadContinuar) {
		super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
	}

	/**
	 * Mensaje + exception a relanzar
	 * 
	 * @param aMensaje
	 * @param aCausa: exception que se puede relanzar
	 */
	public TechnicalException(String aMensaje, Throwable aCausa) {
		super(aMensaje, aCausa);
	}

	/**
	 * Mensaje + exception
	 * 
	 * @param aMensaje
	 * @param aException:       aException que trae la traza que se quiere
	 */
	public TechnicalException(String aMensaje, Exception aException) {
		super(aMensaje, aException);
	}

	/**
	 * Mnesaaje + Exception con traza completa
	 */
	public TechnicalException(String aMensaje, Throwable aCausa, boolean enableSuppression,
			boolean writableStackTrace) {
		super(aMensaje, aCausa, enableSuppression, writableStackTrace);
	}

}
