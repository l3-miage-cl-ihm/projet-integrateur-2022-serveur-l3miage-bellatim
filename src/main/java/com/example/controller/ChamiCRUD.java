package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.model.Chami;
import com.example.model.Defi;

//import com.google.api.services.storage.Storage.BucketAccessControls.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class ChamiCRUD {
    
    @Autowired
    private DataSource dataSource;

    // 404 si pas de slash
    @GetMapping("/")
    public ArrayList<Chami> allUsers(HttpServletResponse response) throws SQLException {

        System.out.println("test");

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lesChamis");

            ArrayList<Chami> lesChamis = new ArrayList<Chami>();

            while (rs.next()) {
                Chami c = new Chami();
                c.setLogin(rs.getString("login")); 
                c.setAge(rs.getInt("age")); 

                PreparedStatement prep = connection.prepareStatement("SELECT * FROM lesDefis WHERE auteur= ? ");
                prep.setString(1,c.getLogin());
                ResultSet result = prep.executeQuery();   
                while(result.next()){
                    String titre = result.getString("titre");
                    String id = result.getString("id");
                    
                    java.sql.Timestamp dateDeCreation = result.getTimestamp("dateDeCreation");
                    String description = result.getString("description");

                    Defi def = new Defi(id, titre, dateDeCreation.toLocalDateTime(), description, c.getLogin());
                    c.addDefis(def);
                }            
                lesChamis.add(c);
            }
            return lesChamis;

        } catch (Exception e) {
            response.setStatus(500);
            try {
                response.getOutputStream().print(e.getMessage());
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }
    }

    @GetMapping("/{userId}")
    public Chami read(@PathVariable(value="userId") String id, HttpServletResponse response) throws SQLException{
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesChamis WHERE login= ? ");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();
            Chami chami = new Chami();

            if(rs.next()){
                chami.setLogin(rs.getString("login")); 
                chami.setAge(rs.getInt("age")); 
                PreparedStatement prep = connection.prepareStatement("SELECT * FROM lesDefis WHERE auteur= ? ");
                prep.setString(1,chami.getLogin());
                ResultSet result = prep.executeQuery();   
                while(result.next()){
                    String titre = result.getString("titre");
                    String idDefi = result.getString("id");
                    java.sql.Timestamp dateDeCreation = result.getTimestamp("dateDeCreation");

                    String description = result.getString("description");
                    Defi def = new Defi(idDefi, titre, dateDeCreation.toLocalDateTime(), description, chami.getLogin());
                    chami.addDefis(def);
                }    
                return chami;

            } else {
                response.setStatus(404);
                try {
                    response.getOutputStream().print("l'utilisateur n'existe pas");
                } catch (Exception e2) {
                    System.err.println(e2.getMessage());
                }
                return null;

            }

        } catch (Exception e) {
            // throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            response.setStatus(405);
            try {
                response.getOutputStream().print(e.getMessage());
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }

    }

    @PostMapping("/{userId}")
    public Chami create(@PathVariable(value="userId")String id, @RequestBody Chami chami,  HttpServletResponse response) throws SQLException{
        if(id.equals(chami.getLogin())){
            
            try(Connection connection = dataSource.getConnection()){
                //on verifie quil nexiste pas d'abord
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesChamis WHERE login= ? ");
                stmt.setString(1,id);
                ResultSet rs = stmt.executeQuery();
                Chami chamiTest = new Chami();
                if(!rs.first()){
                    PreparedStatement stmtInsert = connection.prepareStatement("INSERT INTO lesChamis (login, age) VALUES (?,?)");
                    stmtInsert.setString(1, chami.getLogin());
                    stmtInsert.setInt(2, chami.getAge());
                    int row = stmt.executeUpdate();
                    return chami;
                } else {
                    response.setStatus(403);
                    try {
                        response.getOutputStream().print("l'utilisateur existe déjà");
                    } catch (Exception e2) {
                        System.err.println(e2.getMessage());
                    }
                    return null;
                }
            } catch (Exception e) {
                response.setStatus(405);
                System.err.println(e.getMessage());
                // try{
                // response.getOutputStream().print("l'utilisateur existe déjà");
                // }catch(Exception e2){
                // System.err.println(e2.getMessage());
                // }
                return null;
            }
        } else {
            response.setStatus(412);
            try {
                response.getOutputStream().print("l'id ne correspond pas");
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }
    }

    @PutMapping("/{userId}")
    public Chami update(@PathVariable(value = "userId") String id, @RequestBody Chami chami,
            HttpServletResponse response) {
        if (id.equals(chami.getLogin())) {

            try(Connection connection = dataSource.getConnection()){
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesChamis WHERE login= ? ");
                stmt.setString(1,id);
                ResultSet rs = stmt.executeQuery();
                if(rs.first()){
                    PreparedStatement stmtPut = connection.prepareStatement("UPDATE lesChamis SET login = ?, age = ? WHERE login = ");
                    stmtPut.setString(1, chami.getLogin());
                    stmtPut.setInt(2, chami.getAge());
                    stmtPut.setString(3, id);
                    return chami;
                } else {
                    response.setStatus(404);
                    try {
                        response.getOutputStream().print("l'utilisateur n'existe pas");
                    } catch (Exception e2) {
                        System.err.println(e2.getMessage());
                    }
                    return null;
                }
            } catch (Exception e) {
                response.setStatus(405);
                try {
                    response.getOutputStream().print(e.getMessage());
                } catch (Exception e2) {
                    System.err.println(e2.getMessage());
                }
                return null;
            }
        } else {
            response.setStatus(412);
            try {
                response.getOutputStream().print("l'utilisateur est different");
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(value="userId") String id, HttpServletResponse response){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesChamis WHERE login= ? ");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.first()){
                PreparedStatement stmtPut = connection.prepareStatement("DELETE FROM lesChamis  WHERE login = ");
                stmtPut.setString(1, id);  
            }
            else{
                response.setStatus(404);
                try {
                    response.getOutputStream().print("l'utilisateur n'existe pas");
                } catch (Exception e2) {
                    System.err.println(e2.getMessage());
                }
            }
        } catch (Exception e) {
            response.setStatus(405);
            try {
                response.getOutputStream().print(e.getMessage());
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
        }
    }
}
