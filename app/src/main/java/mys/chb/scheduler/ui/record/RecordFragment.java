package mys.chb.scheduler.ui.record;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;
import java.util.concurrent.ExecutionException;

import mys.chb.scheduler.R;
import mys.chb.scheduler.db.entity.Diary;
import mys.chb.scheduler.db.repository.DiaryRepository;


public class RecordFragment extends Fragment {

    RecordAdapter recordAdapter;
    RecyclerView recyclerView;
    private DiaryRepository diaryRepository;
    private Context context;

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
        View rootView = inflater.inflate(R.layout.fragment_record, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            List<Diary> diaries = diaryRepository.getAll();
            recordAdapter = new RecordAdapter(diaries);
            recyclerView.setAdapter(recordAdapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rootView;
    }
}