package nlu.edu.vn.forum.services;

import nlu.edu.vn.forum.models.Category;
import nlu.edu.vn.forum.models.Message;
import nlu.edu.vn.forum.models.Topic;
import nlu.edu.vn.forum.models.User;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ForumService {
    private static final ForumService instance = new ForumService();
    private Map<String, User> users;
    private Map<Integer, Topic> topics;

    public ForumService() {
        init();
    }

    private void init() {
        users = new HashMap<String, User>();
        topics = new HashMap<Integer, Topic>();
        Category ca1 = new Category("Âm nhạc");

        users.put("admin", new User("admin", "admin", "admin@gmail.com",
                new GregorianCalendar(2008, Calendar.FEBRUARY, 22).getTime()));
        users.put("kien", new User("kien", "kien", "kien@gmail.com",
                new GregorianCalendar(2008, Calendar.FEBRUARY, 22).getTime()));
        Topic topic1 = new Topic(1, "Âm nhạc",
                "Âm nhạc là một bộ môn nghệ thuật dùng âm thanh để diễn đạt cảm xúc của người hát hoặc người nghe.", users.get("admin"),
                ca1);
        topic1.addMessage(new Message("Âm nhạc",
                "Sự sáng tạo, hiệu quả, ý nghĩa, và thậm chí cả định nghĩa của âm nhạc thay " +
                        "đổi tùy theo bối cảnh văn hóa và xã hội. Âm nhạc thay đổi từ các sáng tác thính phòng được " +
                        "tổ chức chặt chẽ (cả trong sáng tác lẫn trình diễn), đến hình thức âm nhạc ngẫu hứng với các hình thức aleatoric.",
                users.get("kien")));

        Topic topic2 = new Topic(2, "Âm nhạc",
                "Âm nhạc có thể được chia thành các thể loại và thể loại con, mặc dù các phân chia và các mối quan hệ phân chia giữa các thể loại âm nhạc.", users.get("kien"),
                ca1);
        topic2.addMessage(new Message("Re: Âm nhạc",
                "Đối với nhiều người ở nhiều nền văn hóa, âm nhạc là một phần quan trọng trong cách " +
                        "sống của họ. Các nhà triết học Hy Lạp cổ đại và Ấn Độ xác định âm nhạc là giai" +
                        " điệu theo chiều ngang và hòa âm theo chiều dọc. Câu nói phổ biến như \"sự hài" +
                        " hòa của vũ trụ\" và \"đó là âm nhạc rót vào tai tôi\" đều cho thấy rằng âm nhạc " +
                        "thường có tổ chức và dễ nghe.",
                users.get("kien")));


        topics.put(1, topic1);
        topics.put(2, topic2);

    }

    public Collection<Topic> getTopics() {
        return topics.values();
    }
    public Topic addNewTopic(String title, String content, User creator, Category category) {
        if (topics == null) {
            topics = new HashMap<Integer, Topic>();
        }
        Integer id = topics.size() + 1;
        Topic topic = new Topic(id, title, content, creator, category);
        topics.put(id, topic);
        return topic;
    }
    public void addMessage(int id, Message message)  {
        Topic topic = topics.get(id);
        topic.addMessage(message);
    }
    public List<Message> getAllMessagesByTopicId(int topicId) {
        Topic topic = topics.get(topicId);
        if (topic != null) {
            List<Message> messages = topic.getMessages();
            return messages;
        } else {
            return new ArrayList<Message>();
        }
    }

    public User checkUser(String name, String password) {
        User user = users.get(name);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    public Topic findTopic(Integer id) {
        return topics.get(id);
    }

    public static ForumService getInstance() {
        return instance;
    }
}