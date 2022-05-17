package com.example.curativepis.feature_scanner.data.mapper

import com.example.curativepis.feature_scanner.data.remote.dto.ScannerResponseDto
import com.example.curativepis.feature_scanner.data.remote.dto.ScannerResponseFormDto
import com.example.curativepis.feature_scanner.domian.model.ScannerResponse
import com.example.curativepis.feature_scanner.domian.model.ScannerResponseForm

fun ScannerResponseDto.toScannerResponse():ScannerResponse{
    return ScannerResponse(
        _id = this._id?:"",
        price = this.price?:0.0,
        drug_name = this.drug_name?:"",
        active_ingredients = this.active_ingredients?: emptyList(),
        strength =this.strength ?:"",
        status = this.status?:"",
        __v = this.__v?:0,
        forms = this.forms?.map { it.toScannerResponseForm() }?: emptyList()
    )
}

fun ScannerResponseFormDto.toScannerResponseForm(): ScannerResponseForm {
    return ScannerResponseForm(
        image = this.image?:"",
        form = this.form?:""
    )
}

