package com.fun.system.api.vo;

import com.sun.istack.internal.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-04-14 16:38
 **/
@Data
@Schema(description = "前端menu, role 传输对象")
@AllArgsConstructor
public class RoleVo {

    @NotNull
    Long roleId;
    /**
     * 以逗号分隔 menuId
     */
    String menuIds;

    public List<Long> split() {
        List<Long> collect = Arrays.stream(menuIds.split(",")).map(val -> {
            return Long.valueOf(val);
        }).collect(Collectors.toList());
        return collect;
    }
}