package com.jsjlzj.wayne.enums;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumPrepareMaterial {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            WAITING, DOING_PREPARE_MATERIAL, LACK_MATERIAL, COMPLETE
    })
    public @interface Status {
    }

    public static final int WAITING = 0;
    public static final int DOING_PREPARE_MATERIAL = 1;
    public static final int LACK_MATERIAL = 2;
    public static final int COMPLETE = 3;

}
