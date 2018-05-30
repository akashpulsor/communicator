package com.communicate.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.communicate.model.Role;
import com.communicate.model.User;
import com.communicate.service.RegistrationForm;
import com.communicate.service.SecurityServiceImplementation;
import com.communicate.service.UserManagerImplementation;

@Controller
@RequestMapping(value = "/web")
public class MainController {
	private static final Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	UserManagerImplementation userManager;

	@Autowired
	SecurityServiceImplementation securityService;

	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public String showForm(ModelMap map) {
		map.addAttribute("registrationForm", new RegistrationForm());
		return "/home";
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm regform, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) throws Exception {

		logger.info("Regitration for data" + regform.toString());

		if (result.hasErrors()) {
			logger.error("not able to create user " + result.getAllErrors());
			return "/error";
		}

		regform.setRoles(Role.ROLE_USER);
		User user = userManager.createUser(regform);
		securityService.autologin(user.getEmail(), user.getPassword());
		redirectAttributes.addFlashAttribute("user", user);

		return "redirect:dashboard.html";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login() throws Exception {
		return "login";
	}

	@RequestMapping(value = "/dashboard.html", method = RequestMethod.GET)
	public String dashboard(@AuthenticationPrincipal User user, ModelMap map) {
		UserDetails userDetails = userManager.loadUserByUsername(user.getEmail());
		map.addAttribute("user", (User) userDetails);
		return "/dashboard";
	}

	@RequestMapping(value = "/uploadimg.html", params = { "userid", "image_type" }, method = RequestMethod.POST)
	public String imageUpload(@RequestParam("file") MultipartFile image, @RequestParam("userid") String userId,
			@RequestParam("image_type") String profile_pic, RedirectAttributes redirectAttributes) {

		boolean profilePic = false;
		if (profile_pic != null) {
			profilePic = true;
		}
		userManager.storeImage(userId, image, profilePic);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + image.getOriginalFilename() + "!");
		return "redirect:dashboard.html";
	}

	@RequestMapping(value = "/image/{userid}/{albumid}/{imageid:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> getImage(@PathVariable("albumid") String albumId,
			@PathVariable("imageid") String imageId, @PathVariable("userid") String userId) {

		Resource file = userManager.getFile(userId, albumId, imageId);
		if (file != null) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.CONTENT_DISPOSITION, "Error").body(null);
	}

}
