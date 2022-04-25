package com.example.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.springframework.web.server.ResponseStatusException; 

import org.springframework.http.HttpStatus;

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

        } catch (Exception e){
            response.setStatus(500);
            try{
                response.getOutputStream().print(e.getMessage());
            } catch(Exception e2){
                System.err.println(e2.getMessage());
            }
            return null;
            }
    }

    @GetMapping("/{userId}")
    public Chami read(@PathVariable(value="userId") String id, HttpServletResponse response) throws SQLException{
        try(Connection connection = dataSource.getConnection()){
            // Statement stmt = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Chamis WHERE login= ? ");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();
            Chami chami = new Chami();

            while(rs.next()){
                chami.setLogin(rs.getString("login")); 
                chami.setAge(rs.getInt("age")); 
            }

            return chami;

        } catch(Exception e){
            // throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            response.setStatus(404);
            try{
                response.getOutputStream().print(e.getMessage());
            } catch(Exception e2){
                System.err.println(e2.getMessage());
            }
            return null;
            }
        
    }

}
