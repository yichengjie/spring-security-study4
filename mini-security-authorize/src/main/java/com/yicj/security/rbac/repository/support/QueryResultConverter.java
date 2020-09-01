package com.yicj.security.rbac.repository.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QueryResultConverter {
	

	public static <T, I> Page<I> convert(Page<T> pageData, Class<I> clazz, Pageable pageable) {
		List<T> contents = pageData.getContent();
		List<I> infos = convert(contents, clazz);
		return new PageImpl<I>(infos, pageable, pageData.getTotalElements());
	}

	public static <I, T> List<I> convert(List<T> contents, Class<I> clazz) {
		List<I> infos = new ArrayList<I>();
		for (T domain : contents) {
			I info = null;
			try {
				info = clazz.newInstance();
				BeanUtils.copyProperties(info, domain);
			} catch (Exception e) {
				log.info("转换数据失败", e);
				throw new RuntimeException("转换数据失败");
			} 
			if(info != null) {
				infos.add(info);
			}
			
		}
		return infos;
	}
	

	public static <T, I> Page<I> convert(Page<T> pageData, Pageable pageable, Domain2InfoConverter<T, I> converter) {
		List<T> contents = pageData.getContent();
		List<I> infos = convert(contents, converter);
		return new PageImpl<I>(infos, pageable, pageData.getTotalElements());
	}

	public static <I, T> List<I> convert(List<T> contents, Domain2InfoConverter<T, I> converter) {
		List<I> infos = new ArrayList<I>();
		for (T domain : contents) {
			infos.add(converter.convert(domain));
		}
		return infos;
	}
	
	

}