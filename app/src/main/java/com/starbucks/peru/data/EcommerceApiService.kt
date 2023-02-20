package com.starbucks.peru.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EcommerceApiService {
    companion object {
        //const val ENDPOINT = "http://172.32.16.51:43/ApiGateway/"
        const val ENDPOINT = "http://172.10.1.12:89/ApiGateway/"
        //const val ENDPOINT = "http://172.32.16.81:123/ApiGateway/"
        //const val ENDPOINTDELOSI = "https://apidev.franquiciasperu.com/"
    }

    /*@Headers(
          "Token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IkNvK0FXREhscDRCd2FLYmNSVitQWkVObTJ2UVlwc0JURkNRdzF0MTFnY2txQUNlYXIrTGxqQ0pnNkVpdnk5K3V1TGRSdGxGUjJSS2h1UDcwRDBhMWUvdmhxOVBENC9GVkRrQ01ZNUFLQXluRUdvQWhjdmliRUVpZmkway9CTy9yZ1dnMGxVYzBmYWhQbXlZckd5WWhySjBzRUxXeklDWVNVTmRXTzlqZ3l2cz0iLCJuYmYiOjE2MDI1NDczMDIsImV4cCI6MTkyMDc1ODQwMCwiaWF0IjoxNjAyNTQ3MzAyLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0Ojg5L0FwaVNlZ3VyaWRhZCIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODkvQXBpU2VndXJpZGFkIn0.moxG7rYzyHN99gi6jz3eXkWqtMKziOlVkvA0EelhLQA",
          "IdEjecucion: 12345678",
          "IdMarca: 41"
      )*/
  /*  @POST("api/marca/autenticar")
    fun tokenRefreshv2(@Body request: RequestToken): Call<TokenResponse>

    @POST("api/marca/autenticar")
    suspend fun tokenRefresh(@Body request: RequestToken): Response<TokenResponse>

    @GET("api/producto/buscarproductos?solicitud.Filtros.IdTienda=485&solicitud.Filtros.NombreProducto=fra&solicitud.paginacion.pagina=1&solicitud.paginacion.tamanio=10&solicitud.paginacion.orden=NombreProducto&solicitud.paginacion.ordenAscendente=true")
    suspend fun getPopularMovies(*//*@Query("api_key") apiKey: String?,*//* @Query("page") page: Int? = null): Response<DataResponse>

    @GET("api/home/cargaincialsinautenticar?home.idTienda=485&home.idPedido=0&home.tipoBanner=110001&home.idUsuario=0")
    suspend fun getInitialCharge(): Response<DataInitialChargeResponse>

    @GET("api/producto/buscarproductos?solicitud.Filtros.IdTienda=485&solicitud.Filtros.NombreProducto=fra&solicitud.paginacion.pagina=1&solicitud.paginacion.tamanio=10&solicitud.paginacion.orden=NombreProducto&solicitud.paginacion.ordenAscendente=true")
    suspend fun getProducts(): Response<DataProductsResponse>


    @GET("api/producto/listardetalle?idSeccion=93&idSubSeccion=98&idTienda=633&idGrupo=717&idUsuario=0")
    suspend fun getProductsSizes(): Response<DataSizeCoffeResponse>


    @GET("api/producto/cargaunicapersonalizacion?solicitud.producto.idTienda=485&solicitud.Producto.IdProducto=9626&solicitud.Producto.IdSubSeccion=101&solicitud.Producto.ListarHijos=false&solicitud.producto.idUsuario=0")
    suspend fun getProductCargaPersonalizada(): Response<DataCargaPersonalizadaProductoResponse>


    @GET("api/home/cargaincialsinautenticar?home.idTienda=485&home.idPedido=0&home.tipoBanner=110001&home.idUsuario=0")
    suspend fun getCartShoppingResponse(idUsuario: String): Response<CartShoppingResponse>


    @GET("api/banner/listar?tipoBanner=110001")
    suspend fun getBannerHomeResponse(): Response<DataBannersResponse>

    @GET("api/menuunico/cargamenuunicosinautenticar?solicitud.usuario.usuario=admin_sb&solicitud.usuario.clave=delosi@2021&solicitud.menuUnico.idTienda=485&solicitud.menuUnico.idUsuario=0")
    suspend fun getCargaMenuProductos(
        @Query("solicitud.menuUnico.slugSeccion") slugSeccion: String,
        @Query("solicitud.menuUnico.slugSubSeccion") slugSubSeccion: String
    ): Response<MenuProductosDataResponse>

    @GET("api/producto/listarproductofavoritoporusuario")
    suspend fun getFavorito(
        @Query(
            encoded = true,
            value = "producto.idUsuario"
        ) idUsuario: String
    ): Response<FavoritoResponse>

    @POST("api/producto/crearproductofavoritoporusuario")
    suspend fun setFavorito(@Body request: RequestFavorito): Response<DataFavoritoResponse>*/


    /*@GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String?, @Query("page") page: Int? = null): Response<MoviesResponse>*/


}