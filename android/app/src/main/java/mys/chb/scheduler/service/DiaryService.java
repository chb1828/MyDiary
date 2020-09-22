package mys.chb.scheduler.service;


import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mys.chb.scheduler.db.entity.Diary;
import mys.chb.scheduler.db.repository.DiaryRepository;
import mys.chb.scheduler.dto.DiaryDTO;
import mys.chb.scheduler.service.api.DiaryLoadTask;
import mys.chb.scheduler.service.api.DiarySaveTask;

public class DiaryService {

    private DiaryRepository repository;

    public DiaryService(Context context) {
        repository = new DiaryRepository(context);
    }

    public void saveDiary() {
        List<Diary> diaries;
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            diaries  = repository.getAll();

            diaries.stream().map(f->DiaryDTO.builder()
                    .id(f.getId())
                    .content(f.getContent())
                    .createDate(simpleDateFormat.format(f.getCreateDate()))
                    .title(f.getTitle())
                    .build()).forEach(result ->{
                DiarySaveTask task = new DiarySaveTask();
                task.execute(result);
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadDiary() {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        DiaryLoadTask task = new DiaryLoadTask();
        try {
            List<DiaryDTO> diaries = task.execute().get();
            diaries.stream().map(f-> {
                try {
                    return Diary.builder()
                    .content(f.getContent())
                    .createDate(transFormat.parse(f.getCreateDate()))
                    .id(f.getId())
                    .title(f.getTitle())
                    .build();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }).forEach(result->repository.insert(result));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
