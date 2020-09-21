package mys.chb.scheduler.service.api;

import android.os.AsyncTask;

import java.io.IOException;

import mys.chb.scheduler.dto.DiaryDTO;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiarySaveTask extends AsyncTask<DiaryDTO,Void, Void> {

    @Override
    protected Void doInBackground(DiaryDTO... diary) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.34.174.159:8882")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        try {
            service.saveDiaryList(diary[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
