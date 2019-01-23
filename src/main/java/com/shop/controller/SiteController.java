package com.shop.controller;

import com.shop.model.Customer;
import com.shop.model.CustomerDAO;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import com.shop.model.ManufDAO;
import com.shop.model.Manufacturer;
import com.shop.model.OrderDAO;
import com.shop.model.Orders;
import com.shop.model.ProductDAO;
import com.shop.model.Product;
import com.shop.model.ProductOrderDAO;
import com.shop.model.ProductOrders;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SiteController {

    @Autowired
    ManufDAO manufDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    ProductOrderDAO productOrderDAO;

    private static final Logger logger = LoggerFactory
            .getLogger(SiteController.class);

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String getOrder(@RequestParam("orderId") int id, ModelMap modelMap) {

        Orders order = orderDAO.getByID(id);
        List<ProductOrders> pol = productOrderDAO.getByOrderId(order);

        modelMap.addAttribute("pol", pol);
        modelMap.addAttribute("order", order);

        return "admin/orders";
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String getAdminPage(ModelMap modelMap) {

        List<Orders> orders = orderDAO.getOrders();
        modelMap.addAttribute("orders", orders);

        return "admin/admin";
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public String makeOrder(ModelMap modelMap, HttpSession session,
            @RequestParam("fName") String firstName, @RequestParam("lName") String lastName,
            @RequestParam("gender") String gender, @RequestParam("address") String addr,
            @RequestParam("city") String city, @RequestParam("note") String note,
            @RequestParam("email") String email, @RequestParam("phoneNumb") String pNumb) {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(addr);
        customer.setCity(city);

        if (!gender.equals("0")) {
            if (gender.equals("male")) {
                customer.setGender("MALE");
            } else {
                customer.setGender("FEMALE");
            }
        } else {
            customer.setGender("NOT SELECTED");
        }
        customer.setEmail(email);
        customer.setPhoneNumber(pNumb);
        customer.setOrderNote(note);
        List<Product> sessionProd = getSessionProducts(session);
        Double total = getCartTotal(sessionProd);

        customerDAO.saveCustomer(customer);

        productOrderDAO.saveProductOrder(sessionProd, BigDecimal.valueOf(total), customer);

        List<Manufacturer> manuf = manufDAO.getManuf();
        modelMap.addAttribute("manuf", manuf);

        session.invalidate();

        return "index";
    }

    @RequestMapping(value = "customer", method = RequestMethod.GET)
    public String getCustomerPage(HttpSession session, ModelMap modelMap) {

        getCart(session, modelMap);

        return "customer";
    }

    @RequestMapping(value = "removeFromCart", method = RequestMethod.GET)
    public String removeItem(HttpSession session, ModelMap modelMap, @RequestParam("prodId") int id) {
        if (session.getAttribute("cart") != null) {
            HashMap<Integer, Product> productMovies = (HashMap<Integer, Product>) session.getAttribute("cart");
            if (productMovies.containsKey(id)) {
                productMovies.remove(id);
            }
            if (productMovies.isEmpty()) {
                modelMap.addAttribute("manuf", manufDAO.getManuf());
                return "emptyCart";
            }
            getCart(session, modelMap);
        }
        return "cart";
    }

    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String getCart(HttpSession session, ModelMap modelMap) {

        List<Manufacturer> manuf = manufDAO.getManuf();
        modelMap.addAttribute("manuf", manuf);
        List<Product> products = new ArrayList<Product>();
        if (session.getAttribute("cart") != null) {
            HashMap<Integer, Product> sessionProducts = (HashMap<Integer, Product>) session.getAttribute("cart");
            if (sessionProducts.isEmpty()) {
                modelMap.addAttribute("manuf", manufDAO.getManuf());
                return "emptyCart";
            }
            for (Map.Entry<Integer, Product> m : sessionProducts.entrySet()) {
                products.add(m.getValue());
            }

        } else {
            modelMap.addAttribute("manuf", manufDAO.getManuf());
            return "emptyCart";
        }
        Double total = getCartTotal(products);

        modelMap.addAttribute("cartProducts", products);
        modelMap.addAttribute("total", total);
        System.out.println(products);
        return "cart";
    }

    @RequestMapping(value = "/productpage", method = RequestMethod.POST)
    public String toCart(ModelMap modelMap, HttpSession session,
            @RequestParam("productID") String prID,
            @RequestParam("quant") Integer quantity) {
        List<Manufacturer> manuf = manufDAO.getManuf();
        modelMap.addAttribute("manuf", manuf);

        int id = Integer.parseInt(prID);

        HashMap<Integer, Product> cart;
        //Provaera da li postoji atribut sesije pod nazivom cart
        if (session.getAttribute("cart") == null) {
            //ukoliko ne postoji kreira se 
            session.setAttribute("cart", new HashMap<Integer, Product>());
        }
        cart = (HashMap<Integer, Product>) session.getAttribute("cart");

        //Provera da li film sa odredjenim id-em vec postoji u korpi
        if (!cart.containsKey(id)) {
            //ukoliko ne postoji ubacuje se
            Product product = productDAO.getById(id);
            product.setQuant(quantity);
            cart.put(id, product);
            modelMap.addAttribute("product", product);

        } else {
            //Ukoliko postoji uzima se taj film i povecava mu se kolicina
            Product productFromCart = cart.get(id);
            productFromCart.quant += quantity;
            modelMap.addAttribute("product", productFromCart);

        }

        return "productpage";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String getProductForm(ModelMap modelMap, @RequestParam("prodPict") MultipartFile file,
            @RequestParam("pName") String name,
            @RequestParam("manufact") String manId,
            @RequestParam("price") BigDecimal price,
            @RequestParam("sDiscount") BigDecimal superDisc,
            @RequestParam("desc") String desc
    ) {
        List<Manufacturer> manuf = manufDAO.getManuf();

        modelMap.addAttribute("manuf", manuf);

        String fName = fileUploadHandler(file);

        Manufacturer manufac = manufDAO.getManufById(Integer.parseInt(manId));
        productDAO.saveProduct(name, desc, fName, price, superDisc, manufac);

        return "index";

    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProductPage(ModelMap modelMap) {
        List<Manufacturer> manuf = manufDAO.getManuf();
        modelMap.addAttribute("manuf", manuf);
        return "products";
    }

    @RequestMapping("/{id}")
    public String findByMan(@PathVariable int id, ModelMap modelMap) {
        List<Manufacturer> manuf = manufDAO.getManuf();

        Collection<Product> prodByMan = productDAO.getByManuf(id);
        // int offset = (page - 1) * perPage + 1;
        modelMap.addAttribute("manufId", id);
        modelMap.addAttribute("manuf", manuf);
        modelMap.addAttribute("prodByMan", prodByMan);

        return "manuf";

    }

    @RequestMapping(value = "/begin", method = RequestMethod.GET)
    public String searchProd(@RequestParam("serchQuery") String query, ModelMap modelMap) {
        List<Manufacturer> manuf = manufDAO.getManuf();
        List<Product> products = productDAO.getByName(query);
        modelMap.addAttribute("manuf", manuf);
        modelMap.addAttribute("products", products);
        System.out.println("products \n" + products);
        return "search";
    }

    @RequestMapping(value = {"/", "/homepage", "/loadmore", "/search", "/manuf"}, method = RequestMethod.POST)
    public String productPage(ModelMap modelMap, @RequestParam("productID") String id) {

        List<Manufacturer> manuf = manufDAO.getManuf();
        modelMap.addAttribute("manuf", manuf);
        Product p = productDAO.getById(Integer.parseInt(id));
        modelMap.addAttribute("product", p);
        return "productpage";
    }

    @RequestMapping(value = "loadmore", method = RequestMethod.GET)
    public String loadMoreFresh(ModelMap modelMap, HttpSession session) {
        List<Manufacturer> manuf = manufDAO.getManuf();
        Integer maxResult = (Integer) session.getAttribute("maxres");
        maxResult += 4;
        session.setAttribute("maxres", maxResult);
        List<Product> freshProducts = productDAO.getFresh(maxResult);
        modelMap.addAttribute("fresh", freshProducts);
        modelMap.addAttribute("manuf", manuf);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        List<Manufacturer> manuf = manufDAO.getManuf();
        Integer maxres = null;
        if (session.getAttribute("maxres") == null) {
            maxres = 0;
            session.setAttribute("maxres", maxres);
            List<Product> freshProducts = productDAO.getFresh(maxres);

            modelMap.addAttribute("fresh", freshProducts);
            modelMap.addAttribute("manuf", manuf);

            return "index";
        }
        maxres = (Integer) session.getAttribute("maxres");

    
        
        List<Product> freshProducts = productDAO.getFresh(maxres);
        
        modelMap.addAttribute("fresh", freshProducts);
        modelMap.addAttribute("manuf", manuf);
       
        return "index";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String getHomepage(ModelMap modelMap, HttpSession session) {
        session.setAttribute("maxres", 0);
        index(modelMap, session);

        return "index";
    }

    private List<Product> getSessionProducts(HttpSession session) {
        List<Product> products = new ArrayList<Product>();
        if (session.getAttribute("cart") != null) {
            HashMap<Integer, Product> sessionProducts = (HashMap<Integer, Product>) session.getAttribute("cart");

            for (Map.Entry<Integer, Product> m : sessionProducts.entrySet()) {
                products.add(m.getValue());
            }

        }

        return products;
    }

    private Double getCartTotal(List<Product> products) {
        Double total = new Double(0);
        for (Product p : products) {
            total += p.totalProductPrice().doubleValue();// BigDecimal total.add(p.getProductPrice());

        }
        return total;
    }

    private String fileUploadHandler(MultipartFile file) {

        if (!file.isEmpty()) {
            RandomString random = new RandomString();
            StringBuilder fileName = new StringBuilder();
            fileName.append(random.nextString());
            try {
                fileName.append(file.getOriginalFilename());
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = "C:\\Users\\nrack\\OneDrive\\Documents\\NetBeansProjects\\WebShop\\src\\main\\webapp\\resources\\picts";
                File dir = new File(rootPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName.toString());
                logger.info("file " + serverFile.getPath() + " saved");
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return fileName.toString();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }
}
