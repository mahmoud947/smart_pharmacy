package com.example.curativepis.feature_drugs.domian.repository

import com.example.curativepis.feature_drugs.data.remote.dto.DrugDto
import com.example.curativepis.feature_drugs.domian.model.Drug


interface DrugsRepository {
    suspend fun getDrugs(page:Int): Result<List<Drug>>
    suspend fun getDrugsByName(drugName:String):List<DrugDto>
    suspend fun getDrugsById(drugId:String):DrugDto

}
