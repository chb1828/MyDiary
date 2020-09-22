package mys.chb.scheduler.service.api;

import android.content.res.Resources;
import android.os.AsyncTask;

import java.io.IOException;

import mys.chb.scheduler.R;
import mys.chb.scheduler.dto.DiaryDTO;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiarySaveTask extends AsyncTask<DiaryDTO,Void, Void> {

    @Override
    protected Void doInBackground(DiaryDTO... diary) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Resources.getSystem().getString(R.string.address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        try {
            service.saveDiaryList(diary[0]).execute();
            System.out.println( service.saveDiaryList(diary[0]).execute().toString()+"튜스트링");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
