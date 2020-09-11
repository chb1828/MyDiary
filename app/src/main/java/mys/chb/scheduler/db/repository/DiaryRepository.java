package mys.chb.scheduler.db.repository;

import android.content.Context;
import android.os.AsyncTask;


import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

import mys.chb.scheduler.db.DataRoomDatabase;
import mys.chb.scheduler.db.dao.DiaryDao;
import mys.chb.scheduler.db.entity.Diary;

public class DiaryRepository {

    private DiaryDao diaryDao;

    public DiaryRepository(Context context) {
        DataRoomDatabase db = DataRoomDatabase.getDatabase(context);
        this.diaryDao = db.diaryDao();
    }

    public void insert(Diary diary) {
        new InsertAsyncTask(diaryDao).execute(diary);
    }

    public Diary get(String date) throws ExecutionException, InterruptedException {return new GetAsyncTask(diaryDao).execute(date).get();}

    public List<Diary> getAll() throws ExecutionException, InterruptedException {return new GetAllAsyncTask(diaryDao).execute().get();}

    private static class InsertAsyncTask extends AsyncTask<Diary, Void, Void> {
        private DiaryDao mAsyncTaskDao;

        InsertAsyncTask(DiaryDao dao) {
            mAsyncTaskDao=dao;
        }

        @Override
        protected Void doInBackground(final Diary... data) {
            mAsyncTaskDao.insert(data[0]);
            return null;
        }
    }

    private static class GetAsyncTask extends AsyncTask<String, Void, Diary> {
        private DiaryDao mAsyncTaskDao;

        GetAsyncTask(DiaryDao dao) {
            mAsyncTaskDao=dao;
        }

        @Override
        protected Diary doInBackground(final String... date) {
            return mAsyncTaskDao.get(date[0]);
        }
    }

    private static class GetAllAsyncTask extends AsyncTask<Void, Void, List<Diary>> {
        private DiaryDao mAsyncTaskDao;

        GetAllAsyncTask(DiaryDao dao) {
            mAsyncTaskDao=dao;
        }

        @Override
        protected List<Diary> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAll();
        }
    }


}
