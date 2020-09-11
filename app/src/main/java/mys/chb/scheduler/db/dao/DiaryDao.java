package mys.chb.scheduler.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import mys.chb.scheduler.db.entity.Diary;

@Dao
public interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Diary diary);

/*    @Query("DELETE FROM diary WHERE id=:id")
    void delete(Long id);*/

    @Query("SELECT * FROM diary ORDER BY createDate desc")
    LiveData<List<Diary>> getAll();

    @Query("SELECT * FROM diary WHERE createDate=:date")
    Diary get(String date);
}
