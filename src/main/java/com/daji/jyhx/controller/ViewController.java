package com.daji.jyhx.controller;

import com.daji.jyhx.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 大稽
 * @date2018/8/919:18
 */
@Controller()
public class ViewController {

    @Autowired
    private PaperService paperService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/studentList")
    public String menberlist() {
        return "student-list";
    }

    @GetMapping("/examList")
    public String getExamList() {
        return "exam-list";
    }

    @GetMapping("/examAdd")
    public String getExamAdd() {
        return "exam-add";
    }

    @GetMapping("/paperList")
    public String getOrderView(String examId) {
        return "paper-list";
    }

    //    @GetMapping("/paperEdit")
//    public String toPaperEdit(){
//        return "paper-edit";
//    }
    @PostMapping("/paperEdit")
    public String getPaperEdit(String title, String paperId, String subject, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("subject", subject);
        model.addAttribute("paperId", paperId);
        List questions = paperService.getQuestionsByPaperId(paperId);
        if (questions.size()>0) {
            model.addAttribute("questions", questions);
        }else{
            model.addAttribute("questions", new ArrayList<>());
        }
        return "paper-edit";
    }

    @PostMapping("/paperCorrect")
    public String getPaperCorrect(String title, String paperId, String subject, Model model) {
        model.addAttribute("title", title+"-批改");
        model.addAttribute("subject", subject);
        model.addAttribute("paperId", paperId);
        return "paper-correct";
    }
    @PostMapping("/paperEntry")
    public String getPaperEntry(String title, String paperId, String subject, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("subject", subject);
        model.addAttribute("paperId", paperId);
        return "paper-entry";
    }


    @GetMapping("/subjectiveAdd")
    public String getSubjectiveAdd() {
        return "subjective-add";
    }

    @GetMapping("/paperAdd")
    public String paperAdd(String examId, Model model) {
        model.addAttribute("examId", examId);
        return "paper-add";
    }

}
