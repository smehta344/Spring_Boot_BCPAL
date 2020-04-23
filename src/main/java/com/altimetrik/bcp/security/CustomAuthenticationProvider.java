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
		users.add("kramakrishnan");
		users.add("sraj4437");
		users.add("agandhiraj");
		
		users.add("abi.sivagnanam");
		users.add("rchandregowda");
		users.add("ramesh.devarajan");
		users.add("sgunasekaran");
		users.add("akumar3715");
		users.add("aallewar");
		users.add("djhala");
		users.add("skothalkar");
		users.add("echristoph");
		users.add("gparimelazhagan");
		users.add("jveluchamy");
		users.add("mkarthik");
		users.add("rnagarajan4931");
		users.add("pnellian");
		users.add("srazik");
		users.add("ksubramanium");
		users.add("mohanraj.k");
		users.add("ashish.singh4605");
		users.add("bvenkatappan");
		users.add("darunachalam");
		users.add("gsubramanian2217");
		users.add("ktheivasigamani");
		users.add("mahesh.rajagopal");
		users.add("msahib");
		users.add("nthankam");
		users.add("pvenkatesh1981");
		users.add("prabhu.ramaswamy");
		users.add("prasanna.venkatesh1224");
		users.add("rkandasamy");
		users.add("ramsankar.marichamy");
		users.add("rudhra.mohandoss");
		users.add("umarudai");
		users.add("vishnu.rajendran");
		users.add("vishnuprem.radhakrishnan");
		users.add("jnair");
		users.add("brajaraman");
		users.add("jhegde");
		users.add("sshivaramakrishna");
		users.add("akhatri");
		users.add("awagrawal");
		users.add("dinesh.choudhary");
		users.add("manishkumar.lathiya");
		users.add("ssule");
		users.add("sujatha.rajesh");
		users.add("rajeshkumar.nagarajan");
		users.add("rvasudevan");
		users.add("svallapil");
		users.add("sdandibhotla");
		users.add("tkhader");
		users.add("kyatham");
		users.add("mbafna");
		users.add("rveerabahu");
		users.add("sujatha.rajesh");
		users.add("chandrasekar.kalyanaraman");
		users.add("dkumarasamy");
		users.add("mrajasekaran");
		users.add("purushothama.raj");
		users.add("rajendra.mishra4603");
		users.add("schandrakumar");
		users.add("sjha");
		users.add("sathyan.ranganathan");
		users.add("svaithiyanathan");
		users.add("schodisetti");
		users.add("achatterjee");
		users.add("arun.bhaskaran");
		users.add("chandra.sarma");
		users.add("debabrata.das");
		users.add("rajashekhar.gunaga");
		users.add("rbantupalli");
		users.add("sreehari.prasad");
		users.add("gnaidu");
		users.add("mpaira");
		users.add("pkathiresan");
		users.add("rgopala");
		users.add("satchyajidt.raj");
		users.add("svallapil");
		users.add("hmohammad");
		users.add("vijaya.marneni");
		users.add("arputha.lazer");
		users.add("arul.ilamuhilan");
		users.add("bharathi.raja4375");
		users.add("deepachandaran.palanisamy");
		users.add("vkathanan");
		users.add("kalagarswamy");
		users.add("mohanraj.pandurangan");
		users.add("mmahalingam");
		users.add("nveerappan");
		users.add("nvijayakumar");
		users.add("rahul.srivastava");
		users.add("smohanram");
		users.add("smurugesan");
		users.add("svenkatasamy");
		users.add("sudhakar.krovi");
		users.add("tamilselvan.jayaraman");
		users.add("vnithianandam");
		users.add("abhandari");
		users.add("bkumar7248");
		users.add("pjothiramalingam");
		users.add("psingh");
		users.add("engineeringassurance-in");
		users.add("ranga.kanapathy");
		users.add("asivagnanam");
		users.add("rdevarajan");
		users.add("mohanrajk");
		users.add("asingh4605");
		users.add("mrajagopal");
		users.add("pramaswamy");
		users.add("pvenkatesh1224");
		users.add("rmarichamy");
		users.add("rmohandoss");
		users.add("vrajendran");
		users.add("vradhakrishnan");
		users.add("dchoudhary");
		users.add("mlathiya");
		users.add("srajesh4741");
		users.add("rnagarajan");
		users.add("ckalyanaraman");
		users.add("praj4617");
		users.add("rmishra4603");
		users.add("sranganathan");
		users.add("ddas");
		users.add("vmarneni");
		users.add("alazer");
		users.add("arilamuhilan");
		users.add("braja4375");
		users.add("dpalanisamy");
		users.add("mpandurangan");
		users.add("rsrivastava");
		users.add("skrovi");
		users.add("rkanapathy");
		users.add("asomani");
		users.add("rajs");
		users.add("dmetho");
		users.add("fvecino");
		users.add("gmakani");
		users.add("jnair");
		users.add("jlorenzini");
		users.add("rnadig");
		users.add("svenkatasamy");
		users.add("tmuthiah");
		users.add("tsrivastava");
		users.add("mfarooqui");
		users.add("mkarunanithi");
		users.add("aallewar");
		users.add("vdosapati");
		users.add("ndabadghav");
		users.add("smohanty");
		users.add("bkannan");
		users.add("gparimelazhagan");
		users.add("kramakrishnan");
		users.add("ksubramanium");
		users.add("mramarpandian");
		users.add("mkarunanithi");
		users.add("rnagarajan4931");
		users.add("pnellian");
		users.add("rvasudevan");
		users.add("rseetharaman");
		users.add("svenkatasamy");
		users.add("srazik");
		users.add("bbaskar");
		users.add("broy");
		users.add("jnair");
		users.add("nnaidu6876");
		users.add("pkathiresan");
		users.add("pjothiramalingam");
		users.add("pp");
		users.add("smariappan2806");
		users.add("sshivaramakrishna");
		users.add("svadaguru");
		users.add("vvalamjee");
		users.add("verapalli");
		users.add("ssinha4202");
		users.add("schandra");
		users.add("arilamuhilan");


		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (!users.contains(name.toLowerCase())) {
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
