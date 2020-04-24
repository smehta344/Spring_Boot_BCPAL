package com.altimetrik.bcp.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.stereotype.Component;

import com.altimetrik.bcp.dao.UserAccessRepo;
import com.altimetrik.bcp.entity.UserAccess;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserAccessRepo userAccessRepo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(
				"ldap://blr-pdc-00.altimetrik.com:389/dc=altimetrik,dc=com");
		contextSource.setUserDn("BCPDasboard@altimetrik.com");
		contextSource.setPassword("G9kEa3JY$");
		contextSource.setReferral("follow");
		try {
			contextSource.afterPropertiesSet();
		} catch (Exception e) {
			throw new BadCredentialsException("ldap details not enough");
		}

		LdapUserSearch ldapUserSearch = new FilterBasedLdapUserSearch("", "(sAMAccountName={0})", contextSource);

		BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
		bindAuthenticator.setUserSearch(ldapUserSearch);
		LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(bindAuthenticator);
		ldapAuthenticationProvider.authenticate(authentication);

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserAccess access = userAccessRepo.getUserAccessByUserId(name.toLowerCase());
		System.out.println("----->"+access);
		if (access == null) {
			throw new BadCredentialsException("user not authorized");
		}

		return new UsernamePasswordAuthenticationToken(name, password, Collections.emptyList());
		// return new UsernamePasswordAuthenticationToken(name, password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
