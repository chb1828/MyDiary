package mys.chb.scheduler.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import mys.chb.scheduler.db.entity.audit.DateConverter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "diary", indices = {@Index(value = {"createDate"},unique = true)})
public class Diary {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @NonNull
    @ColumnInfo(name = "createDate")
    @TypeConverters(DateConverter.class)
    private Date createDate;


    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "createDate=" + createDate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
