package com.grupo.alimentos.peru.util;

public class Messages {

    // PRODUCTO (ProductoServiceImpl)
    public static final String PRODUCT_NOT_FOUND = "No existe el producto"; // eliminar producto, actualizar producto

    public static final String PRODUCT_NOT_FOUND_IMPUT = "producto no encontrado"; // registrar entrada 

    public static final String PRODUCT_NOT_FOUND_OUTPUT = "No se encuentra el producto"; // registrar salida 

    public static final String PRODUCT_ALREADY_EXISTS = "Ya existe este producto en esta tienda"; // crear producto 

    public static final String PRODUCT_DUPLICATE_UPDATE = "Ya existe un producto con ese nombre en esta tienda"; // actualizar producto 

    public static final String PRODUCT_STOCK_EXISTS = "Existen %d productos en stock: No se puede eliminar"; // eliminar producto 

    public static final String INVALID_QUANTITY_INPUT = "Cantidad inválida"; // registrar entrada 

    public static final String INVALID_QUANTITY_OUTPUT = "Cantidad no permitida"; // registrar salida 

    public static final String INSUFFICIENT_STOCK = "No hay stock suficiente"; // registrar salida 


    
    // CATEGORIA (CategoriaServiceImpl)
    public static final String CATEGORY_ALREADY_EXISTS = "La categoría ya existe"; // crear categoria 
    
    public static final String CATEGORY_NOT_FOUND_ID = "Categoria no existe"; // obtener categoriaPorID 
    
    public static final String CATEGORY_NOT_FOUND_UPDATE = "No se encontró la categoría"; // actualizar categoria 
    
    public static final String CATEGORY_NOT_FOUND = "Categoría no encontrada"; // eliminar categoria 

  
    // TIENDA (TiendaServiceImpl)
    public static final String STORE_ALREADY_EXISTS = "La tienda ya existe"; // crear tienda 

    public static final String STORE_NOT_FOUND_ID = "Tienda no existe"; // obtener por id
    
    public static final String STORE_NOT_FOUND_UPDATE = "Tienda no existe para actualizar"; // actualizar 
    
    public static final String STORE_NOT_FOUND = "Tienda no encontrada"; // eliminar tienda 

    public static final String STORE_WITH_PRODUCTS = "No puedes eliminar la tienda porque tiene productos asociados"; // eliminar tienda 

   
    // USUARIO (Auth / UserService)
    public static final String USERNAME_ALREADY_EXISTS = "El nombre de usuario ya existe"; // registrar 

    public static final String EMAIL_ALREADY_EXISTS = "El email ya existe"; // registrar 

    public static final String USER_NOT_FOUND = "Usuario no encontrado"; // obtener por id 

    public static final String STORE_REQUIRED = "Debe seleccionar una tienda"; // registrar 

    public static final String DEFAULT_ROLE_NOT_FOUND = "No se encontró el rol por defecto"; // registrar 

    public static final String ROLE_NOT_FOUND = "Rol no encontrado"; // registrar


    
    // VALIDACIONES RequestDTO

    public static final String DESCRIPTION_EMPTY = "Descripción vacía"; // nombreCategoria, tienda, producto 

    public static final String DESCRIPTION_TOO_LONG_250 = "No debe exceder los 250 caracteres"; // descripcionCategoria, tienda, producto

    public static final String DESCRIPTION_TOO_LONG_150 = "No debe exceder los 150 caracteres"; // nombreCategoria, tienda, producto

    public static final String PRICE_GREATER_CERO = "El precio debe ser mayor a cero"; // productoRequesetDTO 


    // GLOBAL EXCEPTION HANDLER
    public static final String ERROR_NOT_FOUND = "Parece que lo que estás buscando no existe"; // handleResourceNotFoundException

    public static final String ERROR_ALREADY_EXISTS = "No se puede crear porque el recurso ya está registrado"; // handleAlreadyExistsException 

    public static final String ERROR_BAD_REQUEST = "Los datos enviados son incorrectos"; // handleBadRequestException

    public static final String ERROR_BUSINESS_RULE = "Parece que estás intentando hacer algo que va contra las reglas de negocio"; // handleBusinessRuleException 

    // ProductoMapper

    public static final String PRODUCT_NOT_CATEGORY_ASSIGNED = "El producto %d no tiene una categoria asignada";
    
    public static final String PRODUCT_NOT_STORE_ASSIGNED = "El producto %d no tiene una tienda asignada";
    
    // AuthController
    
    public static final String INVALID_CREDENTIALS = "Credenciales inválidas";

    // SECURITY
    public static final String UNAUTHORIZED_STORE_ACCESS = "No tiene permiso para operar en esta tienda";
    

}
