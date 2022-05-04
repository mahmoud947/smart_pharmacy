package com.example.curativepis.feature_drugs.domian.use_case

import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository

class GetDrugsUseCase(
    private val repository: DrugsRepository,
) {
suspend operator fun invoke(page:Int): Result<List<Drug>> {
    return repository.getDrugs(page = page)
}
}