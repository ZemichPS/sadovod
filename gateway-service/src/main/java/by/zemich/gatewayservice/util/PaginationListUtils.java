package by.zemich.gatewayservice.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class PaginationListUtils {
    public static <E> Page<E> returnPagedList(Pageable pageable, List<E> data) {
        List<E> result;

        int pageSize = pageable.getPageSize();
        int page = pageable.getPageNumber();
        int startIndex = page * pageSize;

        // Проверяем на null и что индекс не выходит за рамки data.size()
        if (CollectionUtils.isEmpty(data) || data.size() <= startIndex) {
            result = Collections.emptyList();
        } else {
            // Вычисляем список элементов которые соответствуют запросу
            result = data.subList(startIndex, Math.min(startIndex + pageSize, data.size()));
        }

        return new PageImpl<>(result, pageable, data.size());
    }
}
