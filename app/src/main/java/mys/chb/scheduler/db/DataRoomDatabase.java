package mys.chb.scheduler.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mys.chb.scheduler.db.dao.DiaryDao;
import mys.chb.scheduler.db.entity.Diary;

@Database(entities = {Diary.class},version = 1)
public abstract class DataRoomDatabase extends RoomDatabase {

    public abstract DiaryDao diaryDao();

    private static volatile DataRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DataRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataRoomDatabase.class, "diary_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
