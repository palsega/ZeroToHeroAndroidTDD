package ru.easycode.zerotoheroandroidtdd.data


interface Repository {

    suspend fun load(): SimpleResponse

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): SimpleResponse {
            return try {
                service.fetch(url)
            } catch (e: Exception) {
                throw Exception(e.message, e)
            }
        }
    }
}
