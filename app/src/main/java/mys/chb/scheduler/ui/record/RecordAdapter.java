package mys.chb.scheduler.ui.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import mys.chb.scheduler.R;
import mys.chb.scheduler.db.entity.Diary;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<Diary> diaries;

    public RecordAdapter(List<Diary> diaries) {
        this.diaries = diaries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.record_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Diary diary= diaries.get(position);
        if(diary !=null) {
            holder.count.setText(String.valueOf(diaries.size()-position));
            holder.title.setText(diary.getTitle());
            holder.date.setText(dateToTime(diary.getCreateDate()));
        }
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    public String dateToTime(Date value) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        {
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        if (value != null) {
            return df.format(value);
        } else {
            return null;
        }
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView count,title,date;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            count = itemView.findViewById(R.id.record_count) ;
            title = itemView.findViewById(R.id.record_title) ;
            date = itemView.findViewById(R.id.record_date) ;
        }
    }
}
