package gr.unirico.mcfapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 認可設定
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated();

		// ログイン設定
		http.formLogin()
				.loginPage("/login")
				.usernameParameter("uid").passwordParameter("password")
				.defaultSuccessUrl("/")
				.and();

		// ログアウト設定
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("testuser1").password("password").roles("ADMIN")
				.and()
				.withUser("testuser2").password("password").roles("ADMIN")
				.and()
				.withUser("testuser3").password("password").roles("ADMIN");
	}
}
