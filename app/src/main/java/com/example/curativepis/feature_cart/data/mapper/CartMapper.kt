package com.example.curativepis.feature_cart.data.mapper

import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto
import com.example.curativepis.feature_cart.data.remote.response.dto.CartItemDto
import com.example.curativepis.feature_cart.domian.model.Cart
import com.example.curativepis.feature_cart.domian.model.CartItem

fun CartDto.toCart():Cart=
    Cart(
        createdAt = this.createdAt?:"",
        items = this.items?.map {it.toCartItem()}?: emptyList(),
        _id = this._id?:"",
        purchased = this.purchased?:false,
        subTotal = this.subTotal?:0.0,
        updatedAt = this.updatedAt?:"",
        user_uid = this.user_uid?:""
    )

fun CartItemDto.toCartItem():CartItem=
    CartItem(
        image = this.image?:"",
        drugId = this.drugId?:"",
        price = this.price?:0.0,
        drug_name = this.drug_name?:"",
        quantity = this.quantity?:0,
        total = this.total?:0.0
    )