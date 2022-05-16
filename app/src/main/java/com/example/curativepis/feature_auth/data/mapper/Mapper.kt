package com.example.curativepis.feature_auth.data.mapper

import com.example.curativepis.feature_auth.data.remote.response.dto.CurrentUserResponseDto
import com.example.curativepis.feature_auth.domian.model.CurrentUserResponse

fun CurrentUserResponseDto.toCurrentUserResponse():CurrentUserResponse=
    CurrentUserResponse(
        _id = this._id?:"",
        createdAt = this.createdAt?:"",
        updatedAt = this.updatedAt?:"",
        email = this.email?:"",
        username = this.username?:"",
        isMale = this.isMale?:true,
        uid = this.uid?:"",
        deviceToken = this.deviceToken?:"",
        dob = this.dob?:"",
        phoneNumber = this.phoneNumber?:"",
        role = this.role?:""
    )