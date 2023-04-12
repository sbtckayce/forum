package nlu.edu.vn.forum.controllers;

import nlu.edu.vn.forum.models.Category;
import nlu.edu.vn.forum.models.Topic;
import nlu.edu.vn.forum.models.User;
import nlu.edu.vn.forum.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AddNewTopicController {
    @Autowired
    private ForumService forumService;
    @GetMapping("/add-topic")
    public String index(){
        return "newTopic";
    }

    @PostMapping("/topics")
    public String postNewTopic(HttpServletRequest request, @RequestBody Map<String, String> requestMap) {
        String title = requestMap.get("title");
        String content = requestMap.get("content");
        Category cat1 = new Category("Học hành");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }

        forumService.addNewTopic(title, content, user, cat1);

        return "redirect:/";
    }
}
