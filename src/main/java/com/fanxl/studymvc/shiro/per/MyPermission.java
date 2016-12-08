package com.fanxl.studymvc.shiro.per;

import org.apache.shiro.authz.Permission;

/**
 * Created by fanxl on 2016/12/7 0007.
 */
public class MyPermission implements Permission {

    @Override
    public boolean implies(Permission permission) {
        return false;
    }
}
