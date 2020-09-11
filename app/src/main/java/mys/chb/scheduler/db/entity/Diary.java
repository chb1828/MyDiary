package mys.chb.scheduler.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import mys.chb.scheduler.db.entity.audit.DateConverter;

@Entity(tableName = "diary")
public class Diary {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "createDate")
    @TypeConverters(DateConverter.class)
    private Date createDate;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;


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
