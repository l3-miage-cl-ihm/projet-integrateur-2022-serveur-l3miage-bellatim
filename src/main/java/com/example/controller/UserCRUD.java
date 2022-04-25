package com.example.controller;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList; 
import javax.servlet.http.HttpServletResponse; 
import javax.sql.DataSource;

import com.example.User;
import com.example.model.Chami;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserCRUD {
    
    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public ArrayList<Chami> allUsers(HttpServletResponse response) throws SQLException{
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Chamis");

            ArrayList<Chami> L = new ArrayList<Chami>();
            while(rs.next()){
                Chami c = new Chami();
                c.setLogin(rs.getString("login")); 
                c.setAge(rs.getInt("age")); 
                L.add(c);
            }
            return L;
        }
    }


}
