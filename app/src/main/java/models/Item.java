package models;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

}
