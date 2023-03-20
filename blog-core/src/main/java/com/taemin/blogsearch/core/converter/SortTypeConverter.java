package com.taemin.blogsearch.core.converter;

import com.taemin.blogsearch.core.domain.SortType;
import org.springframework.core.convert.converter.Converter;

public class SortTypeConverter implements Converter<String, SortType> {

    @Override
    public SortType convert(String sortType) {
        return SortType.of(sortType);
    }
}
