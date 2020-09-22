package mys.chb.scheduler.service.api;



import mys.chb.scheduler.dto.ApiDTO;
import mys.chb.scheduler.dto.DiaryDTO;
        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitService {

    @GET("/diary")
    Call<ApiDTO> getAllDiary();

    @PUT("/diary")
    Call<DiaryDTO> saveDiaryList(@Body DiaryDTO DiaryDTO);
}
