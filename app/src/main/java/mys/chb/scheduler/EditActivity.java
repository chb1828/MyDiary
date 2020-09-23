package mys.chb.scheduler;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button closeButton,saveButton;
    private boolean closeBtnCheck=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        diaryRepository = new DiaryRepository(getApplicationContext());
        editor = findViewById(R.id.editor);
        title = findViewById(R.id.title);

        editor.setEditorHeight(200);
        editor.setEditorFontSize(16);
        editor.setPadding(10, 10, 10, 10);
        editor.setPlaceholder("오늘의 말을 적어주세요");

        clickEditorEventInit();

        closeButton = findViewById(R.id.close_edit);
        saveButton = findViewById(R.id.save_edit);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeBtnCheck=true;
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!closeBtnCheck) {
            Diary newDiary = new Diary();
            newDiary.setContent(editor.getHtml());
            newDiary.setTitle(title.getText().toString());
            newDiary.setCreateDate(currentDate);
            diaryRepository.insert(newDiary);
            closeBtnCheck = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("clickedDate"))
                currentDate = (Date) intent.getExtras().get("clickedDate");
            else
                currentDate = new Date();
        }
        Diary diary = getContent(currentDate);
        if (diary != null) {
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


    public void clickEditorEventInit() {
        findViewById(R.id.action_undo).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.undo();
                        return true;
                }
                return false;
            }
        });


        findViewById(R.id.action_redo).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.redo();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_bold).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setBold();
                        return true;
                }
                return false;
            }

        });

        findViewById(R.id.action_italic).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setItalic();
                        return true;
                }
                return false;
            }

        });

        findViewById(R.id.action_subscript).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setSubscript();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_superscript).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setSuperscript();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_strikethrough).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setStrikeThrough();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_underline).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setUnderline();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_heading1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setHeading(1);
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_heading2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setHeading(2);
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_heading3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setHeading(3);
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_heading4).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setHeading(4);
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_heading5).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setHeading(5);
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_heading6).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setHeading(6);
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_txt_color).setOnTouchListener(new View.OnTouchListener() {
            private boolean isChanged;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                        isChanged = !isChanged;
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_bg_color).setOnTouchListener(new View.OnTouchListener() {
            private boolean isChanged;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                        isChanged = !isChanged;
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_indent).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setIndent();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_outdent).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setOutdent();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_align_left).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setAlignLeft();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_align_center).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setAlignCenter();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_align_right).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setAlignRight();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_blockquote).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setBlockquote();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_insert_bullets).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setBullets();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_insert_numbers).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.setNumbers();
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_insert_image).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        /*editor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                                "dachshund");*/
                        editor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                                "미구현...");
                        return true;
                }
                return false;
            }
        });

        findViewById(R.id.action_insert_link).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        //editor.insertLink("https://github.com/wasabeef", "wasabeef");
                        editor.insertLink("https://github.com/wasabeef", "미구현..");
                        return true;
                }
                return false;
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        editor.insertTodo();
                        return true;
                }
                return false;
            }
        });
    }

}