package com.example.curativepis.feature_drugs.data.mapper

import com.example.curativepis.feature_drugs.data.remote.response.dto.DrugDto
import com.example.curativepis.feature_drugs.data.remote.response.dto.DrugFormDto
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.feature_drugs.domian.model.DrugForm


fun DrugFormDto.toDrugFrom(): DrugForm =
    DrugForm(
        form = this.form,
        image = this.image
    )

fun DrugDto.toDrug(): Drug =
    Drug(
        _id = this._id ?: "",
        drug_name = this.drug_name ?: "",
        price = this.price ?: 0.0,
        strength = this.strength ?: "",
        image = this.drugFormsDto?.get(0)?.image ?: "",
        drugForm = this.drugFormsDto?.map { it.toDrugFrom() } ?: emptyList(),
        active_ingredients = this.active_ingredients?: emptyList()
    )
