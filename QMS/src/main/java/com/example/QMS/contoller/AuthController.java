package com.example.QMS.contoller;
//
//import com.example.QMS.dto.LoginRequestDTO;
//import com.example.QMS.dto.LoginResponseDTO;
//import com.example.QMS.model.User;
//import com.example.QMS.repository.UserRepository;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@RestController
//@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:3000")
//public class AuthController {
//
//    private final UserRepository userRepo;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
//        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostMapping("/login")
//    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
//        User user = userRepo.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        return new LoginResponseDTO(user.getUsername(), user.getRole());
//    }
//
//    // Optional: Admin can register new users
//    @PostMapping("/register")
//    public User register(@RequestBody LoginRequestDTO request) {
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole("CUSTOMER"); // Default role
//        return userRepo.save(user);
//    }
//}




import com.example.QMS.dto.LoginRequestDTO;
import com.example.QMS.dto.LoginResponseDTO;
import com.example.QMS.model.User;
import com.example.QMS.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/loginOrRegister")
    public ResponseEntity<LoginResponseDTO> loginOrRegister(@RequestBody LoginRequestDTO request) {

        // Try to find user
        User user = userRepo.findByUsername(request.getUsername()).orElse(null);

        if (user != null) {
            // User exists → check password
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body(
                        new LoginResponseDTO("Invalid username or password", "NONE")
                );
            }
        } else {
            // User doesn't exist → register new user
            user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole("CUSTOMER"); // default role
            user = userRepo.save(user);
        }

        // Return login response
        LoginResponseDTO response = new LoginResponseDTO(user.getUsername(), user.getRole());
        return ResponseEntity.ok(response);
    }
}

