package com.springapp.mvc;

import com.springapp.mvc.ajaxClasses.UploadItem;
import com.springapp.mvc.dao.OrderDetailsDao;
import com.springapp.mvc.dao.PersonCourseDao;
import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.dao.ProductDao;
import com.springapp.mvc.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;

@Controller
@Scope("session")
@RequestMapping("/")
public class ProductsController {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private PersonProductDao personProductDao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/allproducts", method = RequestMethod.GET)
	public String viewProducts(Model m, HttpServletRequest request){
		// Send Strings from .properties maybe?
		// m.addAttribute("courses", "Courses--");
		m.addAttribute("person", request.getSession().getAttribute("person"));
		m.addAttribute("products", productDao.getAllProducts());
		return "allproducts";
	}


	@RequestMapping(value = "/view_editproduct/{productid}/{action}", method = RequestMethod.GET)
	public String viewEditProduct(Model m, HttpServletRequest request, @PathVariable("productid") int productid, @PathVariable("action") String action){
		// Send Strings from .properties maybe?
		// m.addAttribute("courses", "Courses--");
		boolean isView = false;
		if(action.equals("view")){
			isView = true;
		}
		m.addAttribute("view", isView);
		m.addAttribute("person", request.getSession().getAttribute("person"));
		m.addAttribute("product", productDao.getProductById(productid));
		return "view_editproduct";
	}

	@RequestMapping("/editproduct/{productid}")
	public String editProduct(@RequestParam("product_name") String product_name, @RequestParam("product_description") String product_description,
							  @RequestParam("product_date") String product_date, @RequestParam("product_price") double product_price,
							  @PathVariable("productid") int productid, @RequestParam("product_price_currency") String product_price_currency,
                              @RequestParam("product_image") String image, Model m, HttpServletRequest request){

		//Better to use insertProduct function which has saveOrUpdate in it.
		m.addAttribute("person", request.getSession().getAttribute("person"));
        ProductEntity product = new ProductEntity();
        if(productid > 0){ //updating
            ProductEntity currentProduct = productDao.getProductById(productid);
            product.setId(productid);
            product.setImage(currentProduct.getImage()); //For now we are not updating the image.
        }else{ // adding
            product.setImage(image);
        }

        product.setName(product_name);
        product.setProduct_description(product_description);
        product.setCreateDate(product_date);
        product.setPrice(product_price);
        product.setCurrency(product_price_currency);

        productDao.insertProduct(product);
		//m.addAttribute("product", product);
		return "redirect:/allproducts";
	}


	@RequestMapping(value = "/viewpersonproducts/{personid}", method = RequestMethod.GET)
	public String viewPersonCourses(@PathVariable("personid") int personid, Model m, HttpServletRequest request){
		// Send Strings from .properties maybe?
		// m.addAttribute("courses", "Courses--");
		m.addAttribute("person", request.getSession().getAttribute("person"));
		m.addAttribute("personproducts", personProductDao.getAllPersonProducts(personid));
		return "viewpersonproducts";
	}
}