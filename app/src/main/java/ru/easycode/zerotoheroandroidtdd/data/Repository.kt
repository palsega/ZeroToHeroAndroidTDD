package ru.easycode.zerotoheroandroidtdd.data

import retrofit2.HttpException
import java.net.UnknownHostException


interface Repository {
    suspend fun load(): LoadResult

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): LoadResult {
            return try {
                val result = service.fetch(url)
                LoadResult.Success(result)
            } catch (e: Exception) {
                LoadResult.Error(e is UnknownHostException)
            }
        }
    }
}