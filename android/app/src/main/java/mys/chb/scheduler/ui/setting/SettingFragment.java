package mys.chb.scheduler.ui.setting;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import mys.chb.scheduler.R;
import mys.chb.scheduler.db.entity.Diary;
import mys.chb.scheduler.db.repository.DiaryRepository;
import mys.chb.scheduler.dto.DiaryDTO;
import mys.chb.scheduler.service.DiaryService;
import mys.chb.scheduler.service.api.DiarySaveTask;

public class SettingFragment extends Fragment {

    private Context context;
    private LinearLayout saveButton,loadButton;
    private DiaryService service;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        service = new DiaryService(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        saveButton = rootView.findViewById(R.id.save);
        loadButton = rootView.findViewById(R.id.load);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.saveDiary();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.loadDiary();
            }
        });

        return rootView;

    }
}