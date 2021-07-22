package co.com.cliente.util;


public interface IConstantes {

  // modo de operación
  public static final String MODO_INSERCION = "I";
  public static final String MODO_EDICION = "E";

  // variables para consultas
  public static final Integer MAXIMOS_REGISTROS_CONSULTA = 100;

  // para errores
  public static final String TIPO_WARNING = "warning";
  public static final String TIPO_ERROR = "error";
  public static final String CLIENTE_FIND_ALL = "Retorna el listado de todos los clientes";
  public static final String CLIENTE_FIND_BY_ID = "Retorna el cliente por id";
	public static final String CLIENTE_FIND_BY_DOCUMENTO_AND_ID_TIPO_DOCUMENTO = "Retorna el cliente por id";
  public static final String OK = "La solicitud se realizó correctamente";
  public static final String INTERNAL_SERVER_ERROR = "Error interno del server";
  public static final String UNAUTHORIZED = "La solicitud requiere HTTP autenticación";
  public static final String FORBIDDEN = "Prohibido";
  public static final String NOT_FOUND = "El recurso solicitado no esta disponible";


}
