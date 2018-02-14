package gr.unirico.mcfapp.config;

import gr.unirico.mcfapp.infrastracture.handler.CustomAuthenticationFailureHandler;
import gr.unirico.mcfapp.infrastracture.handler.CustomAuthenticationSuccessHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
				.antMatchers("/", "/login", "/topics/**", "/archives/**").permitAll()
				.antMatchers("/api/topics/**").authenticated()
				.anyRequest().authenticated()
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		// ログイン設定
		http.formLogin()
				.loginPage("/login")
				.usernameParameter("uid").passwordParameter("password")
				.successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler())
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

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
		handler.setRedirectStrategy(new RedirectStrategy() {
			@Override
			public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
				String targetUrl = StringUtils.isNotBlank(request.getParameter("redirectUri")) ? request.getParameter("redirectUri"):"/";
				response.sendRedirect(targetUrl);
			}
		});
		return handler;
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		ExceptionMappingAuthenticationFailureHandler handler = new CustomAuthenticationFailureHandler();

		Map<String, String> exceptions = new HashMap<String, String>();
		exceptions.put("org.springframework.security.authentication.AuthenticationServiceException", "/login?error=failure");

		handler.setDefaultFailureUrl("/login?error=failure");
		handler.setUseForward(false);
		handler.setExceptionMappings(exceptions);

		handler.setRedirectStrategy(new RedirectStrategy() {
			@Override
			public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
				String redirectUrl = request.getContextPath() + url;

				if(StringUtils.isNotBlank(request.getParameter("redirectUri")))
					redirectUrl += "&redirectUri=" + request.getParameter("redirectUri");

				response.sendRedirect(redirectUrl);
			}
		});

		return handler;
	}
}
