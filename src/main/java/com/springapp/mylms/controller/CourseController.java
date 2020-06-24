package com.springapp.mylms.controller;

import com.springapp.mylms.repository.CourseRepository;
import com.springapp.mylms.repository.PersonCourseRepository;
import com.springapp.mylms.repository.PersonRepository;
import com.springapp.mylms.entity.CourseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@Scope("session")
@RequestMapping("/")
@Slf4j
public class CourseController {

    private final CourseRepository courseDao;
    private final PersonCourseRepository personCourseDao;
    private final PersonRepository personDao;

    public CourseController(CourseRepository courseDao,
            PersonCourseRepository personCourseDao, PersonRepository personDao) {
        this.courseDao = courseDao;
        this.personCourseDao = personCourseDao;
        this.personDao = personDao;
    }

    @GetMapping("/allcourses")
    public String allCourses(HttpServletRequest request, Model m) {
        m.addAttribute("person", request.getSession().getAttribute("person"));
        m.addAttribute("allcourses", courseDao.findAll());
        m.addAttribute("mylmstitle", "All courses");
        return "allcourses";
    }

    @GetMapping(value = "/viewpersoncourses/{personid}")
    public String viewPersonCourses(@PathVariable("personid") Long personid, Model m, HttpServletRequest request) {
        // Send Strings from .properties maybe?
        m.addAttribute("person", request.getSession().getAttribute("person"));
        m.addAttribute("personcourses", personCourseDao.findAll());
        m.addAttribute("mylmstitle", "My courses");
        return "viewpersoncourses";
    }

    @GetMapping(value = "/view_editcourse/{courseid}/{action}")
    public String viewEditCourse(Model m, HttpServletRequest request,
            @PathVariable("courseid") Long courseid,
            @PathVariable("action") String action) {
        boolean isView = false;
        if (action.equals("view")) {
            isView = true;
        }

        m.addAttribute("view", isView);
        m.addAttribute("person", request.getSession().getAttribute("person"));
        m.addAttribute("course", courseDao.findById(courseid));
        m.addAttribute("mylmstitle", "Edit course");
        return "view_editcourse";
    }

    //NEW view course
    @GetMapping(value = "/course/{courseid}")
    public String viewCourse(Model m, HttpServletRequest request,
            @PathVariable("courseid") Long courseid) {

        CourseEntity course = courseDao.findById(courseid).get();
        m.addAttribute("person", request.getSession().getAttribute("person"));
        m.addAttribute("course", course);
        m.addAttribute("mylmstitle", course.getCoursename());
        return "course";
    }

    @RequestMapping("/editcourse/{courseid}")
    public String editCourse(@PathVariable("courseid") Long courseid,
            @RequestParam("course_image") MultipartFile image,
            Model m, HttpServletRequest request) {

        m.addAttribute("person", request.getSession().getAttribute("person"));
        CourseEntity course = new CourseEntity();

        //Course image
        String orgName;
        boolean imageUpdated = false;
        if (!image.isEmpty()) {
            imageUpdated = true;
            try {
                String uploadsDir = File.separator + "src/main/resources" + File.separator + "uploads";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }

                orgName = image.getOriginalFilename();
                String filePath = realPathtoUploads + File.separator + orgName;
                image.transferTo(new File(filePath));

                log.info("filePath:--------------------- " + filePath);
                course.setCourseimage(orgName); // orgName
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        if (courseid > 0) { //updating
            CourseEntity currentCourse = courseDao.findById(courseid).get();
            course.setId(courseid);
            if (!imageUpdated) {
                course.setCourseimage(currentCourse.getCourseimage()); //For now we are not updating the image.
            }
        }

        course.setCoursename(request.getParameter("course_name"));
        course.setDescription(request.getParameter("course_description"));
        course.setIdnumber(request.getParameter("course_idnumber"));
        course.setCategoryid(Integer.parseInt(request.getParameter("course_category")));

        courseDao.save(course);
        m.addAttribute("mylmstitle", "All courses");
        return "redirect:/allcourses";
    }

    @RequestMapping(value = "enroltocourse/{courseid}/{userid}")
    public String enrolToCourse(@PathVariable("courseid") Long courseid, @PathVariable(value = "userid") String userid,
            Model m, HttpServletRequest request) {
        //If userid is 'enrol', means go to enroltocourse page to choose user.
        if (userid.contains("enrol")) {
            //Send list of users who are enrolled and NOT already enrolled to this course.
            //List<PersonEntity> allUnerolledUsers = personDao.getAllUnerolledUsers(courseid);
            //List<PersonEntity> allEnrolledUsers = personDao.getAllEnrolledUsers(courseid);
            CourseEntity course = courseDao.findById(courseid).get();
            //m.addAttribute("allUnerolledUsers", allUnerolledUsers);
            //m.addAttribute("allEnrolledUsers", allEnrolledUsers);
            m.addAttribute("course", course);
            m.addAttribute("person", request.getSession().getAttribute("person"));
            m.addAttribute("mylmstitle", "Course enrolment");
            return "enroltocourse";
        } else {
            //Enrol user to the course.
            // personCourseDao.enrolUserToCourse(courseid, Integer.parseInt(userid));
            //return "redirect:/view_editcourse/"+courseid+"/view";
            m.addAttribute("mylmstitle", "Course enrolment");
            return "redirect:/enroltocourse/" + courseid + "/enrol";
        }

    }

    @RequestMapping(value = "/delete/course/{courseid}")
    public String removeCourse(@PathVariable("courseid") Long courseid, Model m) {
        courseDao.deleteById(courseid);
        m.addAttribute("mylmstitle", "All courses");
        return "redirect:/allcourses";
    }
}