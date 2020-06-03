package rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2.valerija_nagl_RN682018.data.models.ClassResponse

interface ClassService {

    @GET("json.php")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<List<ClassResponse>>
}