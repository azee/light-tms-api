package ru.lighttms.tms.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.lighttms.tms.api.repositories.custom.UserRepositoryCustom;
import ru.lighttms.tms.beans.User;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */
public interface UserRepository extends PagingAndSortingRepository<User, String>, UserRepositoryCustom{
}
