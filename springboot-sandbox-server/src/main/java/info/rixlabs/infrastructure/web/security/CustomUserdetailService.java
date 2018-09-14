package info.rixlabs.infrastructure.web.security;

import info.rixlabs.core.domain.Account;
import info.rixlabs.core.domain.AccountRepository;
import info.rixlabs.core.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by riccardo.causo on 30.05.2016.
 */

@Service
public class CustomUserdetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if(account != null) {
            return new CustomUser(
                                    account.getId(),
                                    account.getUsername(),
                                    account.getPassword(),
                                    account.getLastPasswordReset(),
                                    AuthorityUtils.createAuthorityList("USER"));
        } else {
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }
    }
}
