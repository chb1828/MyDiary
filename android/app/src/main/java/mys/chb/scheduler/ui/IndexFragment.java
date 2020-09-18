package mys.chb.scheduler.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import mys.chb.scheduler.EditActivity;
import mys.chb.scheduler.MainActivity;
import mys.chb.scheduler.R;
import mys.chb.scheduler.db.entity.Diary;
import mys.chb.scheduler.db.repository.DiaryRepository;


public class IndexFragment extends Fragment {

    private Context context;
    private TextView dayOfTheWeek,day;
    private TextView writeText;
    private DiaryRepository diaryRepository;
    private Button button;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diaryRepository = new DiaryRepository(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_index, container, false);
        dayOfTheWeek = rootView.findViewById(R.id.day_of_the_week);
        day = rootView.findViewById(R.id.day);
        writeText = rootView.findViewById(R.id.diary_write);

        //날짜 설정
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd",Locale.getDefault());
        String cWeekDay = weekdayFormat.format(currentTime);
        String cDay = dayFormat.format(currentTime);
        dayOfTheWeek.setText(cWeekDay);
        day.setText(cDay);



        button = rootView.findViewById(R.id.changeActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //글씨 설정
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
        if(diary!=null) {
            button.setText("수정");
        }
    }
}