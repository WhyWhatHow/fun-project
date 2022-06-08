package com.fun.common.core.constants;

/**
 * @program: fun-project
 * @description: 权限常量
 * @author: WhyWhatHow
 * @create: 2022-06-08 14:27
 **/
public interface PermissionConstant {

    //     用户权限
    String USER_ADD = "sys_user_add";
    String USER_EDIT = "sys_user_edit";
    String USER_DEL = "sys_user_del";
    String USER_IMPORT_EXPORT = "sys_user_import_export";
    //  menu 权限
    String MENU_ADD = "sys_menu_add";
    String MENU_EDIT = "sys_menu_edit";
    String MENU_DEL = "sys_menu_del";
    //    role 权限
    String ROLE_ADD = "sys_role_add";
    String ROLE_EDIT = "sys_role_edit";
    String ROLE_DEL = "sys_role_del";
    String ROLE_PERM = "sys_role_perm";


    default void say() {
    }

}
