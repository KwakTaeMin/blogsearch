package com.taemin.blogsearch.core.converter;

import com.taemin.blogsearch.core.domain.SortType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SortTypeConverter 테스트")
public class SortTypeConverterTest {

    @Test
    public void SortType_convert_테스트() {
        SortTypeConverter sortTypeConverter = new SortTypeConverter();
        SortType accuracySortType = sortTypeConverter.convert("accuracy");
        SortType recencySortType = sortTypeConverter.convert("recency");
        assertThat(accuracySortType).isEqualTo(SortType.ACCURACY);
        assertThat(recencySortType).isEqualTo(SortType.RECENCY);
    }
}
