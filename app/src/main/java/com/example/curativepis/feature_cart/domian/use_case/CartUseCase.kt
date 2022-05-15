package com.example.curativepis.feature_cart.domian.use_case

data class CartUseCase(
    val getCartUseCase: GetCartUseCase,
    val getUserToken: GetUserToken,
    val deleteCartItemUseCase: DeleteCartItemUseCase
)
