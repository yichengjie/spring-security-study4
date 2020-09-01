package com.yicj.security.rbac.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 抽象数据初始化器，所有的数据初始化器应该继承此类
 */
@Slf4j
public abstract class AbstractDataInitializer implements DataInitializer {


	@Override
	@Transactional
	public void init() throws Exception {
		if(isNeedInit()) {
			log.info("使用"+getClass().getSimpleName()+"初始化数据");
			doInit();
			log.info("使用"+getClass().getSimpleName()+"初始化数据完毕");
		}
	}

	/**
	 * 实际的数据初始化逻辑
	 * @throws Exception
	 */
	protected abstract void doInit() throws Exception;
	
	/**
	 * 是否执行数据初始化行为
	 * @return
	 */
	protected abstract boolean isNeedInit();

}