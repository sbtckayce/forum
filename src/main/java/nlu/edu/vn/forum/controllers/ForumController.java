package nlu.edu.vn.forum.controllers;

import nlu.edu.vn.forum.models.Message;
import nlu.edu.vn.forum.models.Topic;
import nlu.edu.vn.forum.models.User;
import nlu.edu.vn.forum.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Collection;
import java.util.Map;

@Controller
public class ForumController {
    @Autowired
    private ForumService forumService;
    @GetMapping("/forum")
    public String index(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Collection<Topic> topics = forumService.getTopics();
        request.setAttribute("topics", topics);
        request.setAttribute("user", user);
        return "forum";
    }
    @GetMapping("/reply")
    public String reply(@RequestParam("id") Integer id, Model model){
        Topic topic = forumService.findTopic(id);
        model.addAttribute("topic", topic);
        return "reply";
    }
    @PostMapping("/topics/{id}/messages")
    public ResponseEntity<String> postMessage(@PathVariable int id, @RequestBody Map<String, String> requestMap, HttpServletRequest request) {
        String title = requestMap.get("title");
        String content = requestMap.get("content");
        HttpSession session = request.getSession();
        User creator = (User) session.getAttribute("user");
        Topic topic = forumService.findTopic(id);
        if (topic == null) {
            return ResponseEntity.notFound().build();
        }
        topic.addMessage(new Message(title,content,creator));
        return ResponseEntity.ok("Message posted successfully");
    }

}
