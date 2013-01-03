package ru.lighttms.tms.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lighttms.tms.beans.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */
public interface TestCaseRepository extends PagingAndSortingRepository<TestCase, String> {
}
