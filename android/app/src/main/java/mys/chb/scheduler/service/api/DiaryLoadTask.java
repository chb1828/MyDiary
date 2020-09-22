package mys.chb.scheduler.service.api;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import mys.chb.scheduler.dto.DiaryDTO;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiaryLoadTask extends AsyncTask<Void,Void, List<DiaryDTO>> {
    @Override
    protected List<DiaryDTO> doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.34.174.159:8882")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        List<DiaryDTO> diaries = null;
        try {
            diaries = service.getAllDiary().execute().body().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diaries;
    }
}
