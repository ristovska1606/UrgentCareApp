package mk.ukim.finki.dashw.controller;

import mk.ukim.finki.dashw.model.FavoriteLocation;
import mk.ukim.finki.dashw.model.User;
import mk.ukim.finki.dashw.model.exceptions.FavoriteLocationAlreadyExistsException;
import mk.ukim.finki.dashw.service.FavoriteLocationService;
import mk.ukim.finki.dashw.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FavoriteLocationController {

    private final FavoriteLocationService favoriteLocationService;
    private final UserService userService;

    public FavoriteLocationController(FavoriteLocationService favoriteLocationService, UserService userService) {
        this.favoriteLocationService = favoriteLocationService;
        this.userService = userService;
    }

    @GetMapping("/favoriteLocation")
    public String favoriteLocation(HttpServletRequest req, Model model, String error){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        User user = userService.findByUserName(username);
        List<FavoriteLocation> locations = favoriteLocationService.findAllByUser(user);
        model.addAttribute("locations", locations);
        return "myLocations";
    }

    @PostMapping("/favoriteLocation")
    public String favoriteLocation(HttpServletRequest req){
        try{
            String destination = req.getSession().getAttribute("destination").toString();
            String username = req.getRemoteUser();
            User user = userService.findByUserName(username);
            this.favoriteLocationService.create(user, destination);
            return "redirect:/favoriteLocation";
        }catch (FavoriteLocationAlreadyExistsException e){
            return "redirect:/favoriteLocation?error="+e.getMessage();
        }

    }

}
