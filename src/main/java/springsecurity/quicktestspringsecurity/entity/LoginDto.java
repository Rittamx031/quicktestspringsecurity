package springsecurity.quicktestspringsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  
public class LoginDto {
  private String email;
  private String pass;
}