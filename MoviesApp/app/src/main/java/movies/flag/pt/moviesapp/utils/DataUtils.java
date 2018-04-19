package movies.flag.pt.moviesapp.utils;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rafae_000 on 03/02/2018.
 */

public class DataUtils {

    @NonNull
    public static String getStringFromDate(long dateRefresh) {
        Date currentTime = new Date(dateRefresh);
        String hours = (currentTime.toString());
        String horas = SimpleDateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date(hours));
        String datas = SimpleDateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date(hours));
        return datas + " - " + horas;
    }

}
