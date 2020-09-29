package mx.rokegcode.ordermanagement.model.api.service

import mx.rokegcode.ordermanagement.model.api.response.OrderResponse
import mx.rokegcode.ordermanagement.model.data.Order
import retrofit2.http.*

interface OrderService {

    @Headers("Content-Type: application/json")
    @POST
    suspend fun uploadOrders(
        @Url baseURL: String,
        @Query("accion") accion: String,
        @Body orders: List<Order>
    ): OrderResponse
}