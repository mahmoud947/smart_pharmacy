package com.example.curativepis.feature_drugs.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.curativepis.feature_drugs.data.remote.CurativePisApi
import com.example.curativepis.feature_drugs.domain.model.Drug
import com.example.curativepis.feature_drugs.data.mapper.toDrug
import retrofit2.HttpException
import java.io.IOException

class DrugsPagingSource (
    private val api: CurativePisApi
) :PagingSource<Int,Drug>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Drug> {
       val currentPage=params.key ?:1
        return try {
            val response=api.getDrugs(offset = 20, limit = 20, page = currentPage)
            val endOfPaginationReached=response.data.isEmpty()
            if (response.data.isNotEmpty()){
                LoadResult.Page(
                    data = response.data.map { it.toDrug() },
                    prevKey = if (currentPage==1) null else currentPage -1,
                    nextKey = if (endOfPaginationReached) null else currentPage +1
                )
            }else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }catch (e:HttpException){
            LoadResult.Error(e)
        }catch (e:IOException){
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Drug>): Int? {
       return  state.anchorPosition
    }
}
