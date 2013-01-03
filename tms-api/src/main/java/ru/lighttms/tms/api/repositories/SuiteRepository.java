package ru.lighttms.tms.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lighttms.tms.beans.Suite;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */
public interface SuiteRepository extends PagingAndSortingRepository<Suite, String> {
}
