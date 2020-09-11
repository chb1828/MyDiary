package mys.chb.scheduler;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import mys.chb.scheduler.db.entity.Diary;
import mys.chb.scheduler.db.repository.DiaryRepository;

public class EditActivity extends AppCompatActivity {

    TextView content,title;
    private DiaryRepository diaryRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        diaryRepository = new DiaryRepository(getApplicationContext());
        content=findViewById(R.id.content);
        title=findViewById(R.id.title);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(content.getText().toString().length()!=0 || title.getText().toString().length()!=0) {
            Diary newDiary = new Diary();
            newDiary.setContent(content.getText().toString());
            newDiary.setTitle(title.getText().toString());
            newDiary.setCreateDate(new Date());
            diaryRepository.insert(newDiary);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Diary diary = getContent();
        if(diary!=null) {
            content.setText(diary.getContent());
            title.setText(diary.getTitle());
        }
    }

    private Diary getContent() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Diary diary = null;
        try {
            diary = diaryRepository.get(date);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return diary;
    }
}