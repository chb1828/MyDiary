package mys.chb.scheduler.db.entity.audit;

import android.util.Log;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConverter {
    // Set timezone value as GMT 존맛탱... to make time as reasonable
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    static {
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @TypeConverter
    public static Date timeToDate(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                Log.e("데이트 에러", e.getMessage());
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String dateToTime(Date value) {
        if (value != null) {
            return df.format(value);
        } else {
            return null;
        }
    }
}