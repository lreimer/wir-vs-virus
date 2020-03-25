package de.qaware.mercury.rest.login;

import de.qaware.mercury.business.admin.Admin;
import de.qaware.mercury.business.login.AdminLoginService;
import de.qaware.mercury.business.login.AdminToken;
import de.qaware.mercury.business.login.LoginException;
import de.qaware.mercury.rest.login.dto.request.LoginDto;
import de.qaware.mercury.rest.login.dto.response.WhoAmIDto;
import de.qaware.mercury.rest.plumbing.authentication.AuthenticationHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static de.qaware.mercury.rest.plumbing.authentication.AuthenticationHelper.ADMIN_COOKIE_NAME;

@RestController
@RequestMapping(path = "/api/admin/login", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AdminLoginController {
    private final AuthenticationHelper authenticationHelper;
    private final AdminLoginService adminLoginService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void login(@Valid @RequestBody LoginDto request, HttpServletResponse response) throws LoginException {
        AdminToken token = adminLoginService.login(request.getEmail(), request.getPassword());

        Cookie cookie = new Cookie(ADMIN_COOKIE_NAME, token.getToken());
        cookie.setHttpOnly(true);
        // TODO MKA: set secure = true

        response.addCookie(cookie);
    }

    @GetMapping
    public WhoAmIDto whoami(HttpServletRequest request) throws LoginException {
        Admin admin = authenticationHelper.authenticateAdmin(request);
        return new WhoAmIDto(admin.getEmail());
    }
}
