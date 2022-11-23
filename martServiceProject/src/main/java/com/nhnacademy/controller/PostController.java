package com.nhnacademy.controller;


import com.nhnacademy.domain.Answer;
import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.FileNotSupportedException;
import com.nhnacademy.exception.PermissionDeniedException;
import com.nhnacademy.exception.PostNotExistException;
import com.nhnacademy.exception.UserNotFoundException;
import com.nhnacademy.repository.AnswerRepository;
import com.nhnacademy.repository.PostRepository;
import com.nhnacademy.repository.UserRepository;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cs")
public class PostController {

    private final PostRepository postRepository;
    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, AnswerRepository answerRepository,
            UserRepository userRepository) {
        this.postRepository = postRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String postViewList(Model model, HttpServletRequest request) {

        User user = getUser(request);

        List<Post> postList = postRepository.getList().stream()
                .filter(p -> p.getUserName().equals(user.getId()))
                .collect(Collectors.toList());

        Collections.reverse(postList);

        model.addAttribute("postList", postList);
        return "postListView";
    }

    @GetMapping("/inquiry")
    public String postRegisterForm() {
        return "postRegisterForm";
    }

    @PostMapping("/inquiry")
    public String postRegister(@Valid @ModelAttribute Post post,
            @RequestParam("file") MultipartFile[] file, HttpServletRequest request)
            throws IOException {

        String uploadDir = "/Users/seohyeon/Downloads/";
        List<String> fileNames = new ArrayList<>();

        checkFileType(file);

        for (MultipartFile multipartFile : file) {
            String fileName = saveFileToDir(uploadDir, multipartFile);
            fileNames.add(fileName);
        }

        User user = getUser(request);
        if (user == null) {
            throw new UserNotFoundException("Not found user!");
        }
        postRepository.register(user.getId(), post.getTitle(), post.getType(),
                post.getContent(), fileNames);

        return "redirect:/cs/";
    }

    @GetMapping("/inquiry/{id}")
    public String postViewDetail(@PathVariable long id, Model model, HttpServletRequest request) {
        Post post = postRepository.getPost(id);
        if (post == null) {
            throw new PostNotExistException("No exist Post!");
        }

        if (getUser(request) == null) {
            throw new PermissionDeniedException("Permission denied!");
        }

        model.addAttribute("post", post);
        model.addAttribute("answer", answerRepository.getAnswer(id));

        return "postDetail";
    }


    @GetMapping("/admin")
    public String managerPostViewList(Model model, @RequestParam(required = false) String type) {
        List<Post> noRepliedList = postRepository.getList().stream()
                .filter(check -> !check.isReplyCheck())
                .collect(Collectors.toList());
        Collections.reverse(noRepliedList);

        if (type == null) {
            model.addAttribute("postList", noRepliedList);
        } else {
            List<Post> filterList = noRepliedList.stream().filter(t -> t.getType().equals(type))
                    .collect(Collectors.toList());
            model.addAttribute("postList", filterList);
        }

        return "managerPostListView";
    }

    @GetMapping("/admin/answer/{id}")
    public String commentForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepository.getPost(id));
        return "managerAnswerForm";

    }

    @PostMapping("/admin/answer/{id}")
    public String answer(@PathVariable long id, @Valid @ModelAttribute Answer answer) {
        answerRepository.registerAnswer(answer);
        postRepository.getPost(id).setReplyCheck(true);

        return "redirect:/cs/admin";

    }

    private String saveFileToDir(String uploadDir, MultipartFile multipartFile)
            throws IOException {
        UUID one = UUID.randomUUID();
        String fileName = one + multipartFile.getOriginalFilename();
        multipartFile.transferTo(Paths.get(uploadDir + fileName));
        return fileName;
    }

    private User getUser(HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute("id");
        return userRepository.getUser(id);
    }

    private void checkFileType(MultipartFile[] file) {
        List<String> allowedFileExtension = List.of("image/gif", "image/jpg", "image/jpeg",
                "image/png");
        for (MultipartFile multipartFile : file) {
            if (!allowedFileExtension.contains(multipartFile.getContentType())) {
                throw new FileNotSupportedException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }
        }
    }

}
