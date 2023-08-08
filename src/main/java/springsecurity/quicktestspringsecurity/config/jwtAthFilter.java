// package springsecurity.quicktestspringsecurity.config;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// /**
//  * jwtAthFilter
//  */
// public class jwtAthFilter extends OncePerRequestFilter {
//   @Autowired
//   UserDetailsService userDetailsService;

//   @Override
//   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//       throws ServletException, IOException {
//     // TODO Auto-generated method stub
//     final String authHeader = request.getHeader("AUTHORIZATION");
//     final String userEmail;
//     final String jwtToken;
//     if (authHeader == null || !authHeader.startsWith("Bearer")) {
//       filterChain.doFilter(request, response);
//       return;
//     }
//     jwtToken = authHeader.substring(7);
//     userEmail = "somthing";
//     if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//       UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//       final boolean isTokenValid;
//       if(isTokenValid){
//         new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
//         authToken.setDatail 
//       }
//     }
//   }

// }