package com.springapp.mylms.controller;

import com.springapp.mylms.entity.ProductEntity;
import com.springapp.mylms.repository.PersonProductRepository;
import com.springapp.mylms.repository.ProductRepository;
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
public class ProductsController {

    private final ProductRepository productRepository;
    private PersonProductRepository personProductRepository;

    public ProductsController(
            ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/allproducts")
    public String viewProducts(Model m, HttpServletRequest request) {
        // Send Strings from .properties maybe?
        // m.addAttribute("courses", "Courses--");
        m.addAttribute("person", request.getSession().getAttribute("person"));
        m.addAttribute("products", productRepository.findAll());
        m.addAttribute("mylmstitle", "Book Library");
        return "allproducts";
    }

    @GetMapping(value = "/view_editproduct/{productId}/{action}")
    public String viewEditProduct(Model m, HttpServletRequest request, @PathVariable("productId") Long productid,
            @PathVariable("action") String action) {
        // Send Strings from .properties maybe?
        // m.addAttribute("courses", "Courses--");
        boolean isView = false;
        if (action.equals("view")) {
            isView = true;
        }
        ProductEntity product = productRepository.findById(productid).get();
        m.addAttribute("view", isView);
        m.addAttribute("person", request.getSession().getAttribute("person"));
        m.addAttribute("product", product);
        m.addAttribute("mylmstitle", product.getName());
        return "view_editproduct";
    }

    @RequestMapping(value = "/editproduct/{productId}")
    public String editProduct(@PathVariable("productId") Long productId,
            @RequestParam("product_image") MultipartFile image,
            Model m, HttpServletRequest request) {

        m.addAttribute("person", request.getSession().getAttribute("person"));
        ProductEntity product = new ProductEntity();

        //Product image
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
                product.setImage(orgName); // orgName
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        if (productId > 0) { //updating
            ProductEntity currentProduct = productRepository.findById(productId).get();
            product.setId(productId);
            if (!imageUpdated) {
                product.setImage(currentProduct.getImage()); //For now we are not updating the image.
            }
        }

        product.setName(request.getParameter("product_name"));
        product.setProductDescription(request.getParameter("product_description"));
        product.setCreateDate(request.getParameter("product_date"));
        product.setPrice(Double.parseDouble(request.getParameter("product_price")));
        product.setCurrency(request.getParameter("product_price_currency"));

        productRepository.save(product);
        m.addAttribute("mylmstitle", "Book library");

        return "redirect:/allproducts";
    }


    @GetMapping(value = "/viewpersonproducts/{personId}")
    public String viewPersonCourses(@PathVariable("personId") Long personId, Model m, HttpServletRequest request) {
        // Send Strings from .properties maybe?
        // m.addAttribute("courses", "Courses--");
        m.addAttribute("person", request.getSession().getAttribute("person"));
        m.addAttribute("personproducts", personProductRepository.findAllByPersonId(personId));
        m.addAttribute("mylmstitle", "My books");
        return "viewpersonproducts";
    }
}