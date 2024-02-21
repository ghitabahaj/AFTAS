package com.youcode.aftas.web.rest;

import com.youcode.aftas.auth.AuthenticationRequest;
import com.youcode.aftas.service.AuthenticationService;
import com.youcode.aftas.auth.AuthenticationResponse;
import com.youcode.aftas.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")

  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

//  @PostMapping("/refresh-token")
//  public void refreshToken(
//      HttpServletRequest request,
//      HttpServletResponse response
//  ) throws IOException {
//    service.refreshToken(request, response);
//  }


}
