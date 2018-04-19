package movies.flag.pt.moviesapp.items;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rafae_000 on 18/01/2018.
 */

public class AdapterItem implements Parcelable {
    private int row;
    private int value;

    public AdapterItem(int row, int value) {
        this.row = row;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.row);
        dest.writeInt(this.value);
    }

    protected AdapterItem(Parcel in) {
        this.row = in.readInt();
        this.value = in.readInt();
    }

    public static final Parcelable.Creator<AdapterItem> CREATOR = new Parcelable.Creator<AdapterItem>() {
        @Override
        public AdapterItem createFromParcel(Parcel source) {
            return new AdapterItem(source);
        }

        @Override
        public AdapterItem[] newArray(int size) {
            return new AdapterItem[size];
        }
    };
}
