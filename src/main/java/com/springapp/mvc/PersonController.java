package com.springapp.mvc;

import com.springapp.mvc.dao.PersonCourseDao;
import com.springapp.mvc.dao.PersonDao;
import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.helpers.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.springapp.mvc.helpers.CryptoUtils.byteArrayToHexString;

@Controller
@Scope("session")
@RequestMapping("/")
public class PersonController {

	@Autowired
	private PersonDao personDao;

	@Autowired
	PersonProductDao personProductDao;

	@Autowired
	PersonCourseDao personCourseDao;

	@RequestMapping("/profile/{userid}")
	public String userProfile(HttpServletRequest request, Model model, @PathVariable("userid") int userid){
		//reload user with userid then send to profile page.
		PersonEntity user = personDao.getUserById(userid);
		model.addAttribute("person", user);
		model.addAttribute("mylmstitle", "Profile | "+ user.getUsername());
		return "profile";
	}

	@RequestMapping(value = "/editprofile/{userid}/{edit}",  method = RequestMethod.POST)
	public String userProfileEdit(HttpServletRequest request, Model model,
							  @PathVariable("userid") int userid, @PathVariable("edit") String edit,
								@RequestParam(value = "profile_image", required = false) MultipartFile image,
								@ModelAttribute("adduser") PersonEntity adduser){

		//Profile image
		String orgName = "";
		boolean imageUpdated = false;
		if (image != null && !image.isEmpty()) {
			imageUpdated = true;
			try{
				String uploadsDir = File.separator + "resources" + File.separator + "profile_pictures";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				orgName = image.getOriginalFilename();
				String filePath = realPathtoUploads + File.separator + orgName;
				image.transferTo(new File(filePath));

			}catch (Exception e){
				e.printStackTrace();
			}
		}

		switch (edit){
			case "doedit":
				//Edit person from form.
				PersonEntity editUser = personDao.getUserById(userid);
				editUser.setUsername(request.getParameter("person_uname"));

				//Hash the user's password before editing.
				String password = request.getParameter("person_password");
				String hash = byteArrayToHexString(CryptoUtils.computeHash(password));
				editUser.setPassword(hash);

				editUser.setFirstname(request.getParameter("person_fname"));
				editUser.setLastname(request.getParameter("person_lname"));
				editUser.setGender(request.getParameter("gender"));
				editUser.setCountry(request.getParameter("country"));
				editUser.setEmail(request.getParameter("person_email"));
				editUser.setCompanyname(request.getParameter("person_company"));
				editUser.setCompanylocation(request.getParameter("person_clocation"));
				editUser.setCompanyservices(request.getParameter("person_cservices"));
				editUser.setRole(request.getParameter("role"));
				editUser.setAccounttype(request.getParameter("accounttype"));

				//We already loaded the userById, update image if the new one is uploaded.
				if(imageUpdated){
					editUser.setProfileImage(orgName);
				}

				personDao.insertPerson(editUser);
				//Return back to viewprofile
				model.addAttribute("mylmstitle", "Profile | " + editUser.getUsername());
				return "redirect:/profile/"+userid;
			case "doadd":
				//Hash user's password before insertion.
				String newPassword = adduser.getPassword();
				String hash1 = byteArrayToHexString(CryptoUtils.computeHash(newPassword));
				adduser.setPassword(hash1);
				adduser.setProfileImage(orgName);

				personDao.insertPerson(adduser);
				//Return back to all users
				model.addAttribute("mylmstitle", "All users");
				return "redirect:/allusers";
			case "preedit":
				PersonEntity user = personDao.getUserById(userid);
				model.addAttribute("person", user);
				model.addAttribute("mylmstitle", "Edit profile");
				return "editprofile";
		}

		model.addAttribute("mylmstitle", "Edit profile");
		return "editprofile";
	}


	@RequestMapping(value = "/allusers")
	public String allUsers(HttpServletRequest request, Model model){
		List<PersonEntity> allusers = personDao.getAllUsers();
		model.addAttribute("allusers", allusers);
		model.addAttribute("mylmstitle", "All users");
		return "allusers";
	}

	@RequestMapping(value = "/adduser")
	public String addUser(HttpServletRequest request, Model model){
		PersonEntity adduser = new PersonEntity();
		model.addAttribute("adduser", adduser);
		model.addAttribute("mylmstitle", "Add new user");

		return "adduser";
	}

	@RequestMapping(value = "/delete/{userid}")
	public String deleteUser(@PathVariable("userid") int userid, RedirectAttributes ra, Model model){
		personDao.removeUser(userid);
		//When return "redirect:/...", we need RedirectAttributes and addFlashAttribute
		ra.addFlashAttribute("delete_msg", "User with " + userid + " has been deleted.");
		model.addAttribute("mylmstitle", "All users");

		return "redirect:/allusers";
	}


	//Had to put the same function here for the profile page that goes from this controller.
	@ModelAttribute("countryNames")
	public Map<String,String> populateCountryNames() {
		Map<String,String> countryNames = new LinkedHashMap<String,String>();
		countryNames.put("Sweden", "Sweden");
		countryNames.put("Denmark", "Denmark");
		countryNames.put("Norway", "Norway");
		countryNames.put("Finland", "Finland");
		return countryNames;
	}
}