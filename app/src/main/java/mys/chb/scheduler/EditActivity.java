package mys.chb.scheduler;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.richeditor.RichEditor;
import mys.chb.scheduler.db.entity.Diary;
import mys.chb.scheduler.db.repository.DiaryRepository;

public class EditActivity extends AppCompatActivity {

    TextView title;
    RichEditor editor;
    private DiaryRepository diaryRepository;
    private Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        diaryRepository = new DiaryRepository(getApplicationContext());
        editor=findViewById(R.id.editor);
        title=findViewById(R.id.title);

        editor.setEditorHeight(200);
        editor.setEditorFontSize(22);
        editor.setEditorFontColor(Color.RED);
        editor.setEditorBackgroundColor(Color.BLUE);
        editor.setBackgroundColor(Color.BLUE);
        editor.setBackgroundResource(R.drawable.bg);
        editor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        editor.setPadding(10, 10, 10, 10);
        editor.setPlaceholder("오늘의 말을 적어주세요");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Diary newDiary = new Diary();
        newDiary.setContent(editor.getHtml());
        newDiary.setTitle(title.getText().toString());
        newDiary.setCreateDate(currentDate);
        diaryRepository.insert(newDiary);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent !=null) {
            if(intent.hasExtra("clickedDate"))
                currentDate =(Date)intent.getExtras().get("clickedDate");
            else
                currentDate= new Date();
        }
        Diary diary = getContent(currentDate);
        if(diary!=null) {
            editor.setHtml(diary.getContent());
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