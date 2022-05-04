package com.example.curativepis.feature_drugs.data.mapper

import com.example.curativepis.feature_drugs.data.remote.dto.DrugDto
import com.example.curativepis.feature_drugs.domian.model.Drug

fun DrugDto.toDrug(): Drug =
    Drug(
        _id = this._id ?: "",
        drug_name = this.drug_name ?: "",
        price = this.price ?: 0.0,
        strength = this.strength ?: "",
        image = this.forms?.get(0)?.image ?: ""
    )