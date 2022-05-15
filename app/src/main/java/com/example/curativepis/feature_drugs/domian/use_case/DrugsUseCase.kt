package com.example.curativepis.feature_drugs.domian.use_case

data class DrugsUseCase(
    val getDrugsUseCase: GetDrugsUseCase,
    val getDrugsByNameUseCase: GetDrugsByNameUseCase,
    val getDrugByIdUseCase: GetDrugByIdUseCase,
    val drugsGetUserTokenUseCase: DrugsGetUserTokenUseCase,
    val addItemToCartUseCase: AddItemToCartUseCase
)
