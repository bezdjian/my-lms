package com.springapp.mylms.controller;

import com.springapp.mylms.entity.PersonEntity;
import com.springapp.mylms.helpers.CryptoUtils;
import com.springapp.mylms.repository.PersonCourseRepository;
import com.springapp.mylms.repository.PersonProductRepository;
import com.springapp.mylms.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.springapp.mylms.helpers.CryptoUtils.byteArrayToHexString;

@Controller
@Scope("session")
@RequestMapping("/")
@Slf4j
public class PersonController {

    private final PersonProductRepository personProductDao;
    private final PersonCourseRepository personCourseDao;
    private final PersonRepository personDao;

    public PersonController(PersonProductRepository personProductDao,
            PersonCourseRepository personCourseDao, PersonRepository personDao) {
        this.personProductDao = personProductDao;
        this.personCourseDao = personCourseDao;
        this.personDao = personDao;
    }

    @RequestMapping("/profile/{userId}")
    public String userProfile(HttpServletRequest request, Model model, @PathVariable("userId") Long userid) {
        //reload user with userId then send to profile page.
        PersonEntity user = personDao.findById(userid).get();
        model.addAttribute("persontoview", user);
        model.addAttribute("mylmstitle", "Profile | " + user.getUsername());
        return "profile";
    }

    @PostMapping(value = "/editprofile/{userId}/{edit}")
    public String userProfileEdit(HttpServletRequest request, Model model,
            @PathVariable("userId") Long userid, @PathVariable("edit") String edit,
            @RequestParam(value = "profile_image", required = false) MultipartFile image,
            @ModelAttribute("adduser") PersonEntity adduser) {
        //Profile image
        String orgName = "";
        boolean imageUpdated = false;
        if (image != null && !image.isEmpty()) {
            imageUpdated = true;
            try {
                String uploadsDir = File.separator + "src/main/resources" + File.separator + "profile_pictures";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                File profilePicturesFolder = new File(realPathtoUploads);
                if (!profilePicturesFolder.exists()) {
                    profilePicturesFolder.mkdir();
                }

                orgName = image.getOriginalFilename();
                String filePath = realPathtoUploads + File.separator + orgName;
                image.transferTo(new File(filePath));

            } catch (Exception e) {
                log.error(e.getMessage());
                String errormsg = "Could not save profile picture: " + e.getMessage();
                return "redirect:/error/" + errormsg;
            }
        }

        switch (edit) {
        case "doedit":
            //Edit person from form.
            PersonEntity editUser = personDao.findById(userid).get();
            editUser.setUsername(request.getParameter("person_uname"));

            //Hash the user's password before editing.
            String password = request.getParameter("person_password");
            String hash = byteArrayToHexString(CryptoUtils.computeHash(password));
            editUser.setPassword(hash);

            editUser.setFirstName(request.getParameter("person_fname"));
            editUser.setLastName(request.getParameter("person_lname"));
            editUser.setGender(request.getParameter("gender"));
            editUser.setCountry(request.getParameter("country"));
            editUser.setEmail(request.getParameter("person_email"));
            editUser.setCompanyName(request.getParameter("person_company"));
            editUser.setCompanyLocation(request.getParameter("person_clocation"));
            editUser.setCompanyServices(request.getParameter("person_cservices"));
            editUser.setRole(request.getParameter("role"));
            editUser.setAccountType(request.getParameter("accounttype"));

            //We already loaded the userById, update image if the new one is uploaded.
            if (imageUpdated) {
                editUser.setProfileImage(orgName);
            }

            personDao.save(editUser);
            //Return back to viewprofile
            model.addAttribute("mylmstitle", "Profile | " + editUser.getUsername());
            return "redirect:/profile/" + userid;
        case "doadd":
            //Hash user's password before insertion.
            String newPassword = adduser.getPassword();
            String hash1 = byteArrayToHexString(CryptoUtils.computeHash(newPassword));
            adduser.setPassword(hash1);
            adduser.setProfileImage(orgName);

            personDao.save(adduser);
            //Return back to all users
            model.addAttribute("mylmstitle", "All users");
            return "redirect:/allusers";
        case "preedit":
            PersonEntity user = personDao.findById(userid).get();
            model.addAttribute("person", user);
            model.addAttribute("mylmstitle", "Edit profile");
            return "editprofile";
        }

        model.addAttribute("mylmstitle", "Edit profile");
        return "editprofile";
    }


    @GetMapping(value = "/allusers")
    public String allUsers(HttpServletRequest request, Model model) {
        List<PersonEntity> allusers = personDao.findAll();
        model.addAttribute("allusers", allusers);
        model.addAttribute("mylmstitle", "All users");
        return "allusers";
    }

    // TODO: Whats this??
    @RequestMapping(value = "/adduser")
    public String addUser(HttpServletRequest request, Model model) {
        PersonEntity adduser = new PersonEntity();
        model.addAttribute("adduser", adduser);
        model.addAttribute("mylmstitle", "Add new user");

        return "adduser";
    }

    @DeleteMapping(value = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId, RedirectAttributes ra, Model model) {
        personDao.deleteById(userId);
        //When return "redirect:/...", we need RedirectAttributes and addFlashAttribute
        ra.addFlashAttribute("delete_msg", "User with " + userId + " has been deleted.");
        model.addAttribute("mylmstitle", "All users");

        return "redirect:/allusers";
    }

    //Had to put the same function here for the profile page that goes from this controller.
    @ModelAttribute("countryNames")
    public Map<String, String> populateCountryNames() {
        Map<String, String> countryNames = new LinkedHashMap<String, String>();
        countryNames.put("Sweden", "Sweden");
        countryNames.put("Denmark", "Denmark");
        countryNames.put("Norway", "Norway");
        countryNames.put("Finland", "Finland");
        return countryNames;
    }
}