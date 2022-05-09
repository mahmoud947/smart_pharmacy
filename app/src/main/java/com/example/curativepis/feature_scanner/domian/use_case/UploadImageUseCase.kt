package com.example.curativepis.feature_scanner.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_scanner.domian.model.PostResponse
import com.example.curativepis.feature_scanner.domian.repository.ScannerReposetory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class UploadImageUseCase(
  private val reposetory: ScannerReposetory
){
     operator fun invoke(imagePath:String): Flow<Resource<PostResponse>> = flow{
         try {
             emit(Resource.Loading<PostResponse>())
             val imgMultipartBody=prepareImagePart(path = imagePath, partName = "productImage")
             val request = reposetory.sendScannerResult(image = imgMultipartBody )
             emit(Resource.Success<PostResponse>(data = request))
         } catch (e: HttpException) {
             emit(Resource.Error<PostResponse>(e.localizedMessage ?: "An unexpected error occured"))
         } catch (e: IOException) {
             emit(Resource.Error<PostResponse>(e.localizedMessage
                 ?: "Couldn't reach server. Check your internet connection."))
         }
     }


    private fun prepareImagePart(path: String, partName: String): MultipartBody.Part {
        val file = File(path)
        val requestBody = file.asRequestBody("image/*".toMediaType())
        return MultipartBody.Part.createFormData(partName, file.name, requestBody)
    }
}
