package in.mshitlearner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.mshitlearner.book.model.Book;
import in.mshitlearner.user.model.User;
import in.mshitlearner.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public String insertOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		User userResult = userRepository.save(user);
		if (userResult.getUserId() > 0)
			return "Save Successfully";
		else
			return "UnSaved Successfully";
	}
	
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> lstUser = userRepository.findAll();
		return lstUser;
	}

	public String deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.delete(user.get());
			return "Deleted Successfully";
		}
		return "Deleted unsuccessfully";
	}
}
