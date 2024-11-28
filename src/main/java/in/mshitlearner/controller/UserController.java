package in.mshitlearner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mshitlearner.service.UserService;
import in.mshitlearner.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "Operations related to users")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/add")
	@Operation(summary = "Saving the Users", description = "Returns a msg as Saved Successfully for the user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully/Unsuccessfully saved")
            /*,
            @ApiResponse(responseCode = "400", description = "Invalid user ID supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
            */
    })
	 public String addUser(@RequestBody User user) {
		String msg = userService.insertOrUpdateUser(user);
		return msg;
	}
	
	@GetMapping(value="/getUsers")
	@Operation(summary = "Fetching the list of Users", description = "Returns a List of users saved from the DB", security = @SecurityRequirement(name = "bearerAuth"))
	public List<User> getAllUsers(){
		List<User> lstUsers = userService.getAllUsers();
		return lstUsers;
	}
	
	@DeleteMapping(value = "/deleteUser/{userId}")
	@Operation(summary = "Deleting the User", description = "Returns a msg after deleting the specified user", security = @SecurityRequirement(name = "bearerAuth"))
	public String deleteUser(@Parameter(description = "ID of the User to Delete") @PathVariable Integer userId) {
		String msg = userService.deleteUser(userId);
		return msg;
	}
}
