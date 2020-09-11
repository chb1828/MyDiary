package mys.chb.scheduler;


import android.content.Intent;
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
    private Date clickedDate;

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
        Diary newDiary = new Diary();
        newDiary.setContent(content.getText().toString());
        newDiary.setTitle(title.getText().toString());
        newDiary.setCreateDate(new Date());
        diaryRepository.insert(newDiary);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Date date = null;
        if(intent !=null) {
            if(intent.hasExtra("clickedDate"))
                date =(Date)intent.getExtras().get("clickedDate");
            else
                date= new Date();
        }
        Diary diary = getContent(date);
        if(diary!=null) {
            content.setText(diary.getContent());
            title.setText(diary.getTitle());
        }
    }

    private Diary getContent(Date cDate) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(cDate);
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