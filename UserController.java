package com.example.demouser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")

public class UserController {
	
	 @Autowired
	  private UserRepository UserRepository;
	 
	  @Autowired
	    private emailService emailService;
	 
	  @GetMapping("/users")
	  public List<User> getAllUsers() {
	    return UserRepository.findAll();
	  }
	  
	  @GetMapping("/users/{id}")
	  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Integer userId)
	      throws ResourceNotFoundException {
	    User user =
	        UserRepository
	            .findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
	    return ResponseEntity.ok().body(user);
	  }
	  
	  @PostMapping("/saveuser")
	  public User createUser(@RequestBody User user) {
	    return UserRepository.save(user);
	  }
	  
	  @PutMapping("/users/{id}")
	  public ResponseEntity<User> updateUser(
	      @PathVariable(value = "id") int userId, @RequestBody User userDetails)
	      throws ResourceNotFoundException {

	    User user =
	        UserRepository
	            .findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

	    user.setEmail(userDetails.getEmail());
	    user.setName(userDetails.getName());
	    final User updatedUser = UserRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	  }
	  @DeleteMapping("/user/{id}")
	  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws Exception {
	    User user =
	        UserRepository
	            .findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

	    UserRepository.delete(user);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }
	  
	  @GetMapping("/users/{id}/{operation}/{param1}/{param2}")
	  public String getUserDetailsById(@PathVariable(value = "id") Integer userId,@PathVariable String operation,@PathVariable int param1,@PathVariable int param2)
	      throws ResourceNotFoundException {
		  int result = 0;
	    User user =
	        UserRepository
	            .findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
	    String op = operation;
	    System.out.println(ResponseEntity.ok().body(user).getBody().getEmail());
	    System.out.println(operation.getClass().getTypeName());
	    
	    String email = ResponseEntity.ok().body(user).getBody().getEmail();
	    if (operation.equals("sum")) {
	    	result = param1+param2;
		    System.out.println(result);

		    }
	    else if (operation.equals("diff")) {
	    	result = param1-param2;
		    System.out.println(result);

		    }
	    else if (operation.equals("mul")) {
	    	result = param1*param2;
		    System.out.println(result);

		    }
	    else if (operation.equals("div")) {
	    	result = param1/param2;
		    System.out.println(result);

		    }
	    else {
		    System.out.println("enter correct operation");

	    }
	    String res = String.valueOf(result);
	    
	    emailService.sendMail(email, res);
	    return ResponseEntity.ok().body(user).getBody().getEmail() +" "+result;

	  }

}
	  
	  

	
	  
	  
	 
	  


