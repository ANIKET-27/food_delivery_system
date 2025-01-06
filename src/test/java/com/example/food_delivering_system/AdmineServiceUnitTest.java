package com.example.food_delivering_system;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.DishDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.services.impl.AdminServicesImpl;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import com.example.food_delivering_system.services.impl.security.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdmineServiceUnitTest {

    @Autowired
    private AdminServicesImpl adminServices;

    @Autowired
    private UserDetailServiceImpl userDetailService;



    @Test
    void getAllCustormers(){

        List<UserDTO> customers =  adminServices.getAllCustomers();

        for(UserDTO u : customers) System.out.println(u);

    }

    @Test
    void createDish(){

//        adminServices.createDish(new DishDTO(1L, "Chicken Curry", "Spicy chicken curry with aromatic spices", 250.00, "Main Course", "https://media.istockphoto.com/id/914007980/photo/indian-dish-of-spicy-hot-chicken-curry-in-red.jpg?b=1&s=612x612&w=0&k=20&c=Lml6hwqV3TymW7RCqCXRPr-8xppEyF9Rf23Az5FOslg="));
//        adminServices.createDish(new DishDTO(2L, "Paneer Butter Masala", "Paneer cubes in a rich buttery tomato gravy", 200.00, "Main Course", "https://media.istockphoto.com/id/1355695253/photo/paneer-masala-a-curry-made-with-cottage-cheese-cubes-cooked-in-a-gravy-of-onions-tomatoes-and.jpg?s=612x612&w=0&k=20&c=YnoP7cKjY_vRex4KlA6GPHEfS0A061GA_B9EuEzHD-g="));
//        adminServices.createDish(new DishDTO(3L, "Vegetable Biryani", "Mixed vegetable biryani with basmati rice and spices", 150.00, "Main Course", "https://media.istockphoto.com/id/1039691852/photo/vegetable-fried-rice.jpg?s=612x612&w=0&k=20&c=ylOZDjkctSmrZIWP24DQkMf_XKpg2xFPA4KOolk3B_U="));
//        adminServices.createDish(new DishDTO(4L, "Gulab Jamun", "Sweet milk-solid-based balls soaked in syrup", 80.00, "Dessert", "https://media.istockphoto.com/id/1408284950/photo/gulab-jamun-with-nuts-served-in-a-dish-isolated-on-napkin-side-view-on-dark-background-indian.jpg?s=2048x2048&w=is&k=20&c=_o1tFaSRkBCRPSzh3J4rdfgN2PDFK2jr9bTAALHWt5E="));
//        adminServices.createDish(new DishDTO(5L, "Masala Dosa", "Crispy dosa filled with spicy potato filling", 100.00, "Snack", "https://media.istockphoto.com/id/1413555828/photo/crispy-masala-dosa-is-a-popular-south-indian-food-item-served-with-tomato-chutney-coconut.jpg?s=612x612&w=0&k=20&c=4bc2ZpJ7WrS2eLAjEzkLjNE3LPPqZTQLEvu0mU_BHOA="));
//        adminServices.createDish(new DishDTO(6L, "Chicken Biryani", "Fragrant rice and chicken cooked with spices", 300.00, "Main Course", "https://media.istockphoto.com/id/1254720533/photo/chicken-biryani-with-yogurt-dip-popular-indian-pakistani-non-vegetarian-food.jpg?s=612x612&w=0&k=20&c=mnyhCsS3ysk3W37mpYfOsF2hSHA4a2CV3PDjr8aLdCU="));
//        adminServices.createDish(new DishDTO(7L, "Samosa", "Crispy fried pastry with a savory filling", 20.00, "Snack", "https://media.istockphoto.com/id/980106992/photo/samosa-snack-served-with-tomato-ketchup-and-mint-chutney.jpg?s=612x612&w=0&k=20&c=GEbt-l44Uj-Guvt89HVer3bI5Kd5RSMn6QFkJW6oN7k="));
//        adminServices.createDish(new DishDTO(8L, "Rajma Chawal", "Red kidney beans curry served with rice", 120.00, "Main Course", "https://media.istockphoto.com/id/669635320/photo/kidney-bean-curry-or-rajma-or-rajmah-chawal-and-roti-typical-north-indian-main-course.jpg?s=612x612&w=0&k=20&c=3ir3vZKHa2oPXkpEKHLbWPAPF1yhxRCyr4DIew41pzg="));
//        adminServices.createDish(new DishDTO(9L, "Butter Chicken", "Chicken cooked in a creamy buttery gravy", 350.00, "Main Course", "https://media.istockphoto.com/id/1479262112/photo/butter-chicken.jpg?s=612x612&w=0&k=20&c=m0XtnTX_x5ideVEWl0wi_hj9u4G2MifCahvXs0zi3Wg="));
//        adminServices.createDish(new DishDTO(10L, "Aloo Paratha", "Stuffed flatbread with spiced potatoes", 50.00, "Breakfast", "https://media.istockphoto.com/id/1413566368/photo/indian-food-aloo-paratha-or-indian-potato-stuffed-flatbread-served-with-butter-pickle-and.jpg?s=612x612&w=0&k=20&c=hIqRXgQTFJ4Ta_Zh_JhOc_Okjk_yJUB--uDYIjifVCc="));
//        adminServices.createDish(new DishDTO(11L, "Fish Fry", "Crispy fried fish with spices", 250.00, "Main Course", "https://media.istockphoto.com/id/1363859965/photo/fish-fry.jpg?s=2048x2048&w=is&k=20&c=mIVDQnqYoEfr8DyX0K3XaFFJJq_iBxhiSxUNpXEZxiA="));
//        adminServices.createDish(new DishDTO(12L, "Rasgulla", "Spongy cheese balls soaked in sugar syrup", 90.00, "Dessert", "https://media.istockphoto.com/id/1044079322/photo/indian-rasgulla-or-rosogulla-dessert-sweet-served-in-a-bowl-selective-focus.jpg?s=612x612&w=0&k=20&c=V0aohnAtDl8F6YyBbBr5JzmeTzZRfVXK0dfZVmXLO4o="));
//        adminServices.createDish(new DishDTO(13L, "Tandoori Chicken", "Chicken marinated in spices and cooked in a tandoor", 400.00, "Appetizer", "https://media.istockphoto.com/id/1396604313/photo/roasted-whole-chicken-legs-with-condiment-directly-above-photo.jpg?s=612x612&w=0&k=20&c=JDs72E-fX5SdcBQREta58T82W8zO_rFiKC7d1WwEEUE="));
//        adminServices.createDish(new DishDTO(14L, "Chole Bhature", "Spicy chickpea curry served with fried bread", 150.00, "Main Course", "https://media.istockphoto.com/id/979914742/photo/chole-bhature-or-chick-pea-curry-and-fried-puri-served-in-terracotta-crockery-over-white.jpg?s=612x612&w=0&k=20&c=OLAw-ZleN1UVaa468OlPSAc6dkz2sjehxWevbvZQNew="));
//        adminServices.createDish(new DishDTO(15L, "Pani Puri", "Crispy hollow puris filled with spicy water", 30.00, "Snack", "https://media.istockphoto.com/id/979145974/photo/pani-puri-or-golgappa-is-a-popular-indian-chat-menu-selective-focus.jpg?s=612x612&w=0&k=20&c=hg4yj5ygrFM9enVvZf0rzw0_mqH-80UNt0eLxWSeAYQ="));
//        adminServices.createDish(new DishDTO(16L, "Pav Bhaji", "Spicy mashed vegetable curry served with bread rolls", 100.00, "Snack", "https://media.istockphoto.com/id/1057140522/photo/indian-mumbai-food-pav-bhaji-from-vegetables-with-bread-close-up-in-a-bowl-horizontal-top-view.jpg?s=612x612&w=0&k=20&c=j4jz4CtwnmQ_ZTGgVg75gYPJdcExwbfRE9ZOEQP1tRs="));
        adminServices.createDish(new DishDTO(17L, "Lassi", "Sweet or salty yogurt-based drink", 50.00, "Beverage", "https://images.unsplash.com/photo-1692620609860-be6717812f71?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8bGFzc2l8ZW58MHx8MHx8fDA%3D"));
//        adminServices.createDish(new DishDTO(18L, "Hyderabadi Biryani", "Flavorful rice dish with marinated meat", 400.00, "Main Course", "https://media.istockphoto.com/id/1333127665/photo/chicken-biryani-spicy-indian-malabar-biryani-hyderabadi-biryani-dum-biriyani-pulao-golden.jpg?s=612x612&w=0&k=20&c=63UXYPOISm8nJ8SNK79dDm0w1gY6jXzYQP0heL6fnOg="));
//        adminServices.createDish(new DishDTO(19L, "Mutton Rogan Josh", "Tender mutton pieces in a rich gravy", 450.00, "Main Course", "https://media.istockphoto.com/id/1253934130/photo/mutton-masala-curry-in-plastic-container-for-home-delivery.jpg?s=612x612&w=0&k=20&c=KQTQTS9RK7f4wt1_ZgRuvFsUMvc2MP1GF3yQqgaVShQ="));
//        adminServices.createDish(new DishDTO(20L, "Paneer Tikka", "Marinated paneer cubes grilled to perfection", 180.00, "Appetizer", "https://media.istockphoto.com/id/1085158128/photo/malai-or-achari-paneer-in-a-gravy-made-using-red-gravy-and-green-capsicum-served-in-a-bowl.jpg?s=612x612&w=0&k=20&c=R1baJqIO7z7bMOzONLWO42EudMDOt_c4kxoczMvJJ34="));
//        adminServices.createDish(new DishDTO(21L, "Roti", "Whole wheat flatbread", 10.00, "Side", "https://media.istockphoto.com/id/1126849659/photo/chapati-tava-roti-also-known-as-indian-bread-or-fulka-phulka-main-ingredient-of-lunch-dinner.jpg?s=612x612&w=0&k=20&c=Ils1PF94pnInHpu6sf7g_78AmNmKcwjbv3oeVqVtaW0="));
//        adminServices.createDish(new DishDTO(22L, "Aloo Gobi", "Potato and cauliflower cooked with spices", 120.00, "Main Course", "https://media.istockphoto.com/id/1435479837/photo/italian-crispeddi-calabresi-ca-lici-calabrian-crispeddi-traditional-fried-salted-donuts-with.jpg?s=612x612&w=0&k=20&c=vxZIKR3sAmgWdGr_IG54shbylbtC7vdpZNZKw56lVCo="));
//        adminServices.createDish(new DishDTO(23L, "Dhokla", "Steamed savory cake made from fermented batter", 60.00, "Snack", "https://media.istockphoto.com/id/1257018928/photo/gujarati-khaman-dhokla-or-steamed-gram-flour-puffy-snack-cake.jpg?s=612x612&w=0&k=20&c=GQiWSBv8uPyL2VGjw3qoQUUVGY86jHK7blSW7YJOAFg="));
//        adminServices.createDish(new DishDTO(24L, "Kheer", "Creamy rice pudding flavored with cardamom", 80.00, "Dessert", "https://media.istockphoto.com/id/980089086/photo/rice-pudding-or-kheer-from-india-called-also-called-firnee-served-in-a-bowl-selective-focus.jpg?s=612x612&w=0&k=20&c=k69t8VUFXzoAzQcn0qxdPRdO1BHRqR36gGWpd5PhJ6g="));
//        adminServices.createDish(new DishDTO(25L, "Palak Paneer", "Paneer cubes cooked in a spinach gravy", 200.00, "Main Course", "https://media.istockphoto.com/id/1146291429/photo/palak-paneer-or-spinach-and-cottage-cheese-curry-on-a-dark-background-traditional-indian-food.jpg?s=612x612&w=0&k=20&c=LuY5cNs9p9EonSEqRE8t7xgvCPjLfT2iPYhsg5ZRNkw="));
//        adminServices.createDish(new DishDTO(26L, "Baingan Bharta", "Smoky mashed eggplant cooked with spices", 150.00, "Main Course", "https://media.istockphoto.com/id/1336531760/photo/a-fire-roasted-brinjal-dish-with-onions-tomatoes-and-spices-commonly-known-in-indian-as.jpg?s=612x612&w=0&k=20&c=EB1P_UJM3QIWTgk3ztzitfiMu9PcCOzUXm9zu1CRE_o="));
//        adminServices.createDish(new DishDTO(27L, "Jalebi", "Sweet spirals soaked in sugar syrup", 50.00, "Dessert", "https://media.istockphoto.com/id/1430753492/photo/indian-sweet-jalebi.jpg?s=2048x2048&w=is&k=20&c=46r_vAMFhQ25HG4xZGL5g0WIyOySGYfjLa7VrajE-pw="));
//        adminServices.createDish(new DishDTO(28L, "Poha", "Flat rice mixed with spices", 70.00, "Breakfast", "https://media.istockphoto.com/id/1093261264/photo/aloo-kanda-poha-or-tarri-pohe-with-spicy-chana-masala-curry-selective-focus.jpg?s=612x612&w=0&k=20&c=Oq1UT3a5sDqj2GMLHTUkdjOai3zcfYWxaSx6J4u_LB8="));
//        adminServices.createDish(new DishDTO(29L, "Mysore Pak", "Rich sweet made from ghee, sugar, and gram flour", 90.00, "Dessert", "https://media.istockphoto.com/id/499045932/photo/mysore-pak.jpg?s=612x612&w=0&k=20&c=RGJzhT4DPJLLqX0LVwhDpNlxD7GtjSyZLguKh8uLAiY="));
//        adminServices.createDish(new DishDTO(30L, "Thali", "Assorted Indian dishes served on a platter", 500.00, "Main Course", "https://media.istockphoto.com/id/1158623408/photo/indian-hindu-veg-thali-food-platter-selective-focus.jpg?s=612x612&w=0&k=20&c=MOm3sfIfL22URV6juSCxpA3yfr4O63yJUV5vitufR7Y="));

    }

    @Test
    void createAdmin(){
         userDetailService.signupAdmin(
                CreateUserDTO.builder()
                        .userName("Admin1")
                        .password("password")
                        .email("admin@ex.com")
                        .phoneNo("239439")
                        .latitude(234.4)
                        .longitude(23.5)
                        .accNo(2L)
                        .available(false)
                        .build()
        );
    }

    @Test
    void deleteAllDishes(){
//        for(long i=1; i<31; i++) {
//            try {
//                adminServices.deleteDish(i);
//            }
//            catch (Exception e){
//                continue;
//            }
//        }

        adminServices.deleteDish(46l);

    }


}
