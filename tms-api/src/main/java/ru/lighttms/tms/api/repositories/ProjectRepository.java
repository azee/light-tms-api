package ru.lighttms.tms.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lighttms.tms.beans.Project;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project, String> {
}
