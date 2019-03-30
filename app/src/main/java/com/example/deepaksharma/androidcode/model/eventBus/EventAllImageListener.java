package com.example.deepaksharma.androidcode.model.eventBus;

import java.io.File;
import java.util.List;

/**
 * Created by Deepak Sharma on 18/1/19.
 */
public class EventAllImageListener {
    private List<File> mFile;

    public EventAllImageListener(List<File> file) {
        this.mFile  = file;
    }

    public List<File> getmFile() {
        return mFile;
    }

    public void setmFile(List<File> mFile) {
        this.mFile = mFile;
    }
}