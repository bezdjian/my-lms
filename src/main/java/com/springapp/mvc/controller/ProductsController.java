package com.springapp.mvc.controller;

import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.dao.ProductDao;
import com.springapp.mvc.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
		m.addAttribute("mylmstitle", "Book Library");
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
		ProductEntity product = productDao.getProductById(productid);
		m.addAttribute("view", isView);
		m.addAttribute("person", request.getSession().getAttribute("person"));
		m.addAttribute("product", product);
		m.addAttribute("mylmstitle", product.getName());
		return "view_editproduct";
	}

	@RequestMapping(value = "/editproduct/{productid}")
	public String editProduct(@PathVariable("productid") int productid,
							  @RequestParam("product_image") MultipartFile image,
							  Model m, HttpServletRequest request){

		m.addAttribute("person", request.getSession().getAttribute("person"));
		ProductEntity product = new ProductEntity();

		//Product image
		String orgName;
		boolean imageUpdated = false;
		if (!image.isEmpty()) {
			imageUpdated = true;
			try {
				String uploadsDir = File.separator + "resources" + File.separator + "uploads";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				orgName = image.getOriginalFilename();
				String filePath = realPathtoUploads + File.separator + orgName;
				image.transferTo(new File(filePath));

				System.out.println("filePath:--------------------- " + filePath);
				product.setImage(orgName); // orgName
			}catch (Exception e){
				e.printStackTrace();
			}
		}


        if(productid > 0){ //updating
            ProductEntity currentProduct = productDao.getProductById(productid);
            product.setId(productid);
            if(!imageUpdated){
				product.setImage(currentProduct.getImage()); //For now we are not updating the image.
			}
        }

        product.setName(request.getParameter("product_name"));
        product.setProduct_description(request.getParameter("product_description"));
        product.setCreateDate(request.getParameter("product_date"));
        product.setPrice(Double.parseDouble(request.getParameter("product_price")));
        product.setCurrency(request.getParameter("product_price_currency"));

        productDao.insertProduct(product);
		m.addAttribute("mylmstitle", "Book library");

		return "redirect:/allproducts";
	}


	@RequestMapping(value = "/viewpersonproducts/{personid}", method = RequestMethod.GET)
	public String viewPersonCourses(@PathVariable("personid") int personid, Model m, HttpServletRequest request){
		// Send Strings from .properties maybe?
		// m.addAttribute("courses", "Courses--");
		m.addAttribute("person", request.getSession().getAttribute("person"));
		m.addAttribute("personproducts", personProductDao.getAllPersonProducts(personid));
		m.addAttribute("mylmstitle", "My books");
		return "viewpersonproducts";
	}
}