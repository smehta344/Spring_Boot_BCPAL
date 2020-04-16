package com.altimetrik.bcp.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

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

		/** **/
		List<String> users = new ArrayList<String>();
		users.add("lanandh");
		users.add("ksankar");
		users.add("snagappan");
		users.add("smehta");
		users.add("csarma");
		users.add("sraj4437");
		
		
		users.add("Abi.Sivagnanam");
		users.add("kramakrishnan");
		users.add("rchandregowda");
		users.add("ramesh.devarajan");
		users.add("sgunasekaran");
		users.add("akumar3715");
		users.add("aallewar");
		users.add("djhala");
		users.add("skothalkar");
		users.add("agandhiraj");
		users.add("echristoph");
		users.add("gparimelazhagan");
		users.add("jveluchamy");
		users.add("mkarthik");
		users.add("rnagarajan4931");
		users.add("pnellian");
		users.add("srazik");
		users.add("ksubramanium");
		users.add("Mohanraj.K");
		users.add("Ashish.Singh4605");
		users.add("bvenkatappan");
		users.add("DArunachalam");
		users.add("gsubramanian2217");
		users.add("ktheivasigamani");
		users.add("Mahesh.Rajagopal");
		users.add("msahib");
		users.add("nthankam");
		users.add("pvenkatesh1981");
		users.add("Prabhu.Ramaswamy");
		users.add("Prasanna.Venkatesh1224");
		users.add("rkandasamy");
		users.add("Ramsankar.Marichamy");
		users.add("Rudhra.Mohandoss");
		users.add("umarudai");
		users.add("Vishnu.Rajendran");
		users.add("Vishnuprem.Radhakrishnan");
		users.add("jnair");
		users.add("brajaraman");
		users.add("Jhegde");
		users.add("sshivaramakrishna");
		users.add("akhatri");
		users.add("awagrawal");
		users.add("Dinesh.Choudhary");
		users.add("Manishkumar.Lathiya");
		users.add("ssule");
		users.add("Sujatha.rajesh");
		users.add("Rajeshkumar.Nagarajan");
		users.add("rvasudevan");
		users.add("svallapil");
		users.add("sdandibhotla");
		users.add("tkhader");
		users.add("kyatham");
		users.add("mbafna");
		users.add("rveerabahu");
		users.add("Sujatha.rajesh");
		users.add("Chandrasekar.Kalyanaraman");
		users.add("dkumarasamy");
		users.add("mrajasekaran");
		users.add("Purushothama.Raj");
		users.add("Rajendra.Mishra4603");
		users.add("schandrakumar");
		users.add("sjha");
		users.add("Sathyan.Ranganathan");
		users.add("SVaithiyanathan");
		users.add("schodisetti");
		users.add("achatterjee");
		users.add("Arun.Bhaskaran");
		users.add("Chandra.Sarma");
		users.add("Debabrata.Das");
		users.add("Rajashekhar.Gunaga");
		users.add("rbantupalli");
		users.add("Sreehari.Prasad");
		users.add("gnaidu");
		users.add("MPaira");
		users.add("PKathiresan");
		users.add("rgopala");
		users.add("satchyajidt.raj");
		users.add("svallapil");
		users.add("hmohammad");
		users.add("vijaya.marneni");
		users.add("Arputha.Lazer");
		users.add("Arul.Ilamuhilan");
		users.add("Bharathi.Raja4375");
		users.add("Deepachandaran.Palanisamy");
		users.add("vkathanan");
		users.add("kalagarswamy");
		users.add("Mohanraj.Pandurangan");
		users.add("mmahalingam");
		users.add("nveerappan");
		users.add("nvijayakumar");
		users.add("Rahul.Srivastava");
		users.add("smohanram");
		users.add("Smurugesan");
		users.add("svenkatasamy");
		users.add("Sudhakar.Krovi");
		users.add("Tamilselvan.Jayaraman");
		users.add("vnithianandam");
		users.add("abhandari");
		users.add("bkumar7248");
		users.add("pjothiramalingam");
		users.add("psingh");
		users.add("EngineeringAssurance-IN");
		users.add("Ranga.kanapathy");


		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (!users.contains(name)) {
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
