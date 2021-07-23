package co.com.cliente.util;


public interface IMensajes {

  // excepciones
  public static final String VALORES_NULOS = "Problemas internos del aplicativo (Valores nulos). Intente luego.";
  public static final String VALORES_INVALIDOS_BD = "Los valores ingresados o seleccionados no cumplen las condiciones de base de datos.";
  public static final String VALORES_INVALIDOS_ANTES_BD = "Los valores ingresados o seleccionados no cumplen las validaciones para realizar la transacción.";
  public static final String MENSAJE_TRANSACCION_NO_EXITOSA = "Transacción no exitosa";
  public static final String MENSAJE_PROBLEMAS_TECNICOS = "Problemas técnicos. contacte administrador";
  public static final String NO_SE_ENCUENTRA_EL_RECURSO = "Objeto o recurso no encontrado";
  public static final String MENSAJE_VALIDACION_NO_EXITOSA = "No se ha superado las validaciones. Revise el formulario e intente de nuevo";
  public static final String FECHA_PERSONAL_INVALIDA = "inválida; no se encuentra dentro del rango permitido";
  public static final String TIPO_DATO_ASIGNADO_INCORRECTAMENTE = "Problemas al convertir los tipos de datos";
  public static final String TAMANO_UPLOAD_FILE_SUPERADO = "Archivo adjunto supera el máximo tamaño permitido de 10MB";


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
