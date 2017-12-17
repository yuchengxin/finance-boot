package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.IntEnumTypeHandler;
import com.gilab.wjj.persistence.model.Permission;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class PermissionHandler extends IntEnumTypeHandler<Permission> {
    public PermissionHandler() {
        super(Permission.class);
    }
}
