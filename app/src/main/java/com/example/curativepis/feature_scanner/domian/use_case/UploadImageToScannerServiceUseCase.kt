package com.example.curativepis.feature_scanner.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_scanner.data.mapper.toScannerResponse
import com.example.curativepis.feature_scanner.domian.model.PostResponse
import com.example.curativepis.feature_scanner.domian.model.ScannerResponse
import com.example.curativepis.feature_scanner.domian.repository.ScannerReposetory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class UploadImageToScannerServiceUseCase(
  private val reposetory: ScannerReposetory
){
     operator fun invoke(imagePath:String): Flow<Resource<List<ScannerResponse>>> = flow{
         try {
             emit(Resource.Loading<List<ScannerResponse>>())
             val imgMultipartBody=prepareImagePart(path = imagePath, partName = "img")
             val response = reposetory.getScannerResponse(image = imgMultipartBody).map { it.toScannerResponse()}
             emit(Resource.Success<List<ScannerResponse>>(data = response))
         } catch (e: HttpException) {
             emit(Resource.Error<List<ScannerResponse>>(e.localizedMessage ?: "An unexpected error occured"))
         } catch (e: IOException) {
             emit(Resource.Error<List<ScannerResponse>>(e.localizedMessage
                 ?: "Couldn't reach server. Check your internet connection."))
         }
     }


    private fun prepareImagePart(path: String, partName: String): MultipartBody.Part {
        val file = File(path)
        val requestBody = file.asRequestBody("image/*".toMediaType())
        return MultipartBody.Part.createFormData(partName, file.name, requestBody)
    }
}
