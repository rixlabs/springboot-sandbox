package info.rixlabs.core.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by riccardo.causo on 04.05.2016.
 */

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);

}
