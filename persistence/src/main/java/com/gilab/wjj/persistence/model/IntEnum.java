package com.gilab.wjj.persistence.model;

/**
 * Created by yuankui on 10/31/17.
 */
public interface IntEnum<T extends Enum<T>> {
    int getValue();
}
