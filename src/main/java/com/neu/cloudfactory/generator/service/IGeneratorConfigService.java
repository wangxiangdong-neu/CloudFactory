package com.neu.cloudfactory.generator.service;

import com.neu.cloudfactory.generator.entity.GeneratorConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author wxd
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig> {

    /**
     * 查询
     *
     * @return GeneratorConfig
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 修改
     *
     * @param generatorConfig generatorConfig
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);

}
