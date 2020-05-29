package com.company.movie.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.company.movie.entity.User;
import com.company.movie.entity.dto.UserDTO;

public interface AuthService {

	public UserDTO login(User user, HttpServletResponse response);

	public UserDTO register(User user);

	public List<User> getusers();
}
