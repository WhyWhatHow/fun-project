package com.fun.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.core.domain.R;
import com.fun.system.api.entity.OauthClientDetails;
import com.fun.system.service.OauthClientDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:15
 */
@Tag(name = "OauthClient", description = "认证客户端管理模块")
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("client")
public class OauthClientDetailsController {
    @Resource
    private final OauthClientDetailsService oauthClientDetailsService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R Page(Page page) {
        return R.ok(oauthClientDetailsService.page(page, Wrappers.emptyWrapper()));
    }


    /**
     * 通过id查询oauthClientDetails
     *
     * @param clientId id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{clientId}")
    public R getById(@PathVariable("clientId")@NotBlank String clientId) {
        return R.ok(oauthClientDetailsService.getById(clientId));
    }

    /**
     * 通过id删除oauthClientDetails
     *
     * @param clientId id
     * @return R
     */
    @Operation(summary = "通过id删除oauthClientDetails", description = "通过id删除oauthClientDetails")
    @DeleteMapping("/{clientId}")
    @CacheEvict(value = "clientDetails", key = "#clientId")
    public R removeById(@PathVariable @NotBlank String clientId) {
        return R.ok(oauthClientDetailsService.removeById(clientId));
    }

    /**
     * 新增oauthClientDetails
     *
     * @param oauthClientDetails oauthClientDetails
     * @return R
     */
    @Operation(summary = "新增oauthClientDetails", description = "新增oauthClientDetails")
    @PostMapping
    public R save(@RequestBody @Valid OauthClientDetails oauthClientDetails) {
        return R.ok(oauthClientDetailsService.save(oauthClientDetails));
    }

    /**
     * 修改oauthClientDetails
     *
     * @param oauthClientDetails oauthClientDetails
     * @return R
     */
    @Operation(summary = "修改oauthClientDetails", description = "修改oauthClientDetails")
    @PutMapping
    public R updateById(@RequestBody @Valid OauthClientDetails oauthClientDetails) {
        return R.ok(oauthClientDetailsService.updateById(oauthClientDetails));
    }


}

