package com.example.springtouchgo.controller.appuser;

import com.example.springtouchgo.model.appuser.ScoreEntry;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController

// @PreAuthorize("")
@RequestMapping("/management/api/v1/coners")

public class SpecialController {




    @GetMapping
    @CrossOrigin(origins = "*" , allowedHeaders = "*")
   // @PreAuthorize()
    public String isLoggedIn(HttpServletRequest request , HttpServletResponse response) {

        //System.out.println("is authentication real " + );
       response.setStatus(300);
        return "log";
        //return arrayList;
    }
}
