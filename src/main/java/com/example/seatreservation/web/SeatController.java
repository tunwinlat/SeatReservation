package com.example.seatreservation.web;

import com.example.seatreservation.entities.Seat;
import com.example.seatreservation.respositories.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor




public class SeatController {
    @Autowired
    private SeatRepository seatRepository;

    static int num = 0;

    @GetMapping(path = "/index")
    public String seats(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
//
        List<Seat> seats;
        if (keyword.isEmpty()) {
            seats = seatRepository.findAll();
        } else {
            int key = Integer.parseInt(keyword);
            seats = seatRepository.findSeatById(key);
        }
        model.addAttribute("listSeats", seats);
        return "seats";
    }

    @GetMapping("/delete")
    public String delete(int id) {
        seatRepository.deleteById(id);
        return "redirect:/index";
    }

    @PostMapping(path = "/save")
    public String save(Model model, Seat seat, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "seats";
        } else {
            seatRepository.save(seat);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }

    }

    @GetMapping("/editSeats")
    public String editSeats(Model model, int id, HttpSession session){
        num = 2;
        session.setAttribute("info", 0);
        Seat seat = seatRepository.findById(id).orElse(null);
        if(seat==null) throw new RuntimeException("Patient does not exist");
        model.addAttribute("seat", seat);
        return "editSeats";
    }

    @GetMapping(path = "/")
    public String seats2(Model model, ModelMap mm,
                            @RequestParam(name="keyword",defaultValue = "") String
                                    keyword,HttpSession session){
        List<Seat> seats;
        if (keyword.isEmpty()) {
            seats = seatRepository.findAll();
        } else {
            mm.put("e", 0);
            mm.put("a", 0);
            int key = Integer.parseInt(keyword);
            seats = seatRepository.findSeatById(key);
        }
        model.addAttribute("listSeats", seats);
        return "seats";
    }
}