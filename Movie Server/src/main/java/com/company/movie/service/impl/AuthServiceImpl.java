package com.company.movie.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.company.movie.entity.User;
import com.company.movie.entity.dto.UserDTO;
import com.company.movie.entity.repository.UserRepository;
import com.company.movie.exception.AppError;
import com.company.movie.exception.AppException;
import com.company.movie.service.AuthService;
import com.company.movie.util.EncryptionUtil;
import com.company.movie.util.JWTUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	@Value("${encryption.saltKey}")
	private String saltKey;

	@Value("${token.validity-period-in-minutes}")
	private long expiryDuration;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO login(User userObj, HttpServletResponse response) {

		try {
			if (userObj != null) {
				User user = userRepository.findByUserName(userObj.getUserName());
				String password = EncryptionUtil.decrypt(user.getPassword(), saltKey);
				if (password.equals(userObj.getPassword())) {
					System.out.println("user logged successfuly");
					Map<String, Object> claims = new HashMap<String, Object>(1);
					claims.put("userName", user.getUserName());
					byte[] apiKey = "movie.secure.token".getBytes();
					String token = JWTUtil.generateToken(claims, expiryDuration, apiKey);
					response.setHeader("X-AUTH-TOKEN", token);
					user.setToken(token);
				}
				ModelMapper modelMapper = new ModelMapper();
				return modelMapper.map(user, UserDTO.class);

			} else {
				System.out.println("user logging failed");
				// log.info("user logged failed");
				throw new AppException("");
			}

		} catch (NoSuchElementException e) {
			throw new AppException(AppError.LOGIN_INVALID);
		} catch (Exception e) {
			throw new AppException(AppError.LOGIN_INVALID);
		}
		// return userDTO;
	}

	@Override
	public UserDTO register(User user) {
		String encryptedPassword = EncryptionUtil.encrypt(user.getPassword(), saltKey);
		user.setPassword(encryptedPassword);
		this.userRepository.save(user);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<User> getusers() {
		return userRepository.findAll();
	}

}
