package com.example.finalprojectgg.data.source.remote

import android.util.Log
import com.example.finalprojectgg.data.source.remote.network.ApiResponse
import com.example.finalprojectgg.data.source.remote.network.PetaBencanaApiService
import com.example.finalprojectgg.data.source.remote.response.GeometriesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: PetaBencanaApiService) {
    suspend fun getReports(): Flow<ApiResponse<List<GeometriesItem?>>> {
        return flow {
            try {
                val response = apiService.getReports(timePeriod = 604800)
                val dataArray = response.result?.objects?.output?.geometries

                dataArray?.let {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result.objects.output.geometries))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", e.toString())
            } catch (e: IOException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", "Check Connection")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getReportsArchive(
        start: String,
        end: String
    ): Flow<ApiResponse<List<GeometriesItem?>>> {
        return flow {
            try {
                val response = apiService.getReportsArchive(start = "2023-01-04T00:00:00+0700",end = "2023-02-10T00:00:00+0700")
                val dataArray = response.result?.objects?.output?.geometries

                dataArray?.let {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result.objects.output.geometries))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", e.toString())
            } catch (e: IOException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", "Check Connection")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFloodGauges(
        admin: String,
        format: String,
    ): Flow<ApiResponse<List<GeometriesItem?>>> {
        return flow {
            try {
                val response = apiService.getFloodgauges(admin = "", format = "", geoFormat = "")
                val dataArray = response.result?.objects?.output?.geometries

                dataArray?.let {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result.objects.output.geometries))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", e.toString())
            } catch (e: IOException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", "Check Connection")
            }
        }.flowOn(Dispatchers.IO)
    }


}
