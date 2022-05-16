package com.example.curativepis.feature_cart.data.mapper

import com.example.curativepis.feature_cart.data.remote.response.dto.cart.CartDto
import com.example.curativepis.feature_cart.data.remote.response.dto.cart.CartItemDto
import com.example.curativepis.feature_cart.data.remote.response.dto.cart_history.CartHistoryDto
import com.example.curativepis.feature_cart.data.remote.response.dto.cart_history.CartHistoryItemDto
import com.example.curativepis.feature_cart.domian.model.cart.Cart
import com.example.curativepis.feature_cart.domian.model.cart.CartItem
import com.example.curativepis.feature_cart.domian.model.cart_history.CartHistory
import com.example.curativepis.feature_cart.domian.model.cart_history.CartHistoryItem

fun CartDto.toCart(): Cart =
    Cart(
        createdAt = this.createdAt?:"",
        items = this.items?.map {it.toCartItem()}?: emptyList(),
        _id = this._id?:"",
        purchased = this.purchased?:false,
        subTotal = this.subTotal?:0.0,
        updatedAt = this.updatedAt?:"",
        user_uid = this.user_uid?:""
    )

fun CartItemDto.toCartItem(): CartItem =
    CartItem(
        image = this.image?:"",
        drugId = this.drugId?:"",
        price = this.price?:0.0,
        drug_name = this.drug_name?:"",
        quantity = this.quantity?:0,
        total = this.total?:0.0
    )

fun CartHistoryDto.toCartHistory(): CartHistory=
    CartHistory(
        _id = this._id?:"",
        updatedAt = this.updatedAt?:"",
        user_uid = this.user_uid?:"",
        subTotal = this.subTotal?:0.0,
        purchased = this.purchased?:false,
        createdAt = this.createdAt?:"",
        items = this.items?.map { it.toCartHistoryItem() }?: emptyList()
    )

fun CartHistoryItemDto.toCartHistoryItem():CartHistoryItem=
    CartHistoryItem(
        drugId = this.drugId ?: "",
        drug_name = this.drug_name ?: "",
        image = this.image ?: "",
        price = this.price ?: 0.0,
        quantity = this.quantity ?: 0,
        total = this.total ?: 0.0
    )