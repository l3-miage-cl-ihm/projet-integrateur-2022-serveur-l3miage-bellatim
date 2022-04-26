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
    public ArrayList<Chami> allUsers(HttpServletResponse response) throws SQLException{
        try (Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lesChamis");

            ArrayList<Chami> L = new ArrayList<Chami>();
            while(rs.next()){
                Chami c = new Chami();
                String login = rs.getString("login");
                int age = rs.getInt("age");
                c.setLogin(login); 
                c.setAge(age); 

                PreparedStatement prep = connection.prepareStatement("SELECT * FROM lesDefis WHERE auteur= ? ");
                prep.setString(1, login);
                ResultSet result = prep.executeQuery();  

                while(result.next()){

                    String titre = result.getString("titre");
                    String id = result.getString("id");

                    java.sql.Timestamp dateDeCreation = result.getTimestamp("dateDeCreation");
                    String description = result.getString("description");

                    Defi def = new Defi(id, titre, dateDeCreation.toLocalDateTime(), description, null);

                    c.addDefis(def);

                }            
                L.add(c);
            }
            return L;

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
                    Defi def = new Defi(idDefi, titre, dateDeCreation.toLocalDateTime(), description, chami);
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

                if(!rs.next()){
                    PreparedStatement stmtInsert = connection.prepareStatement("INSERT INTO lesChamis (login, age) VALUES (?,?)");
                    stmtInsert.setString(1, chami.getLogin());
                    stmtInsert.setInt(2, chami.getAge());
                    stmtInsert.execute();
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
        //if (id.equals(chami.getLogin())) {

            try(Connection connection = dataSource.getConnection()){
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesChamis WHERE login= ? ");
                stmt.setString(1,id);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){

                    //il faut modifier mettre le nom du créateur à null a cause de la clé primaire
                    /*PreparedStatement statDefis = connection.prepareStatement("select * from lesDefis where auteur = ? ");
                    statDefis.setString(1, id);
                    ResultSet resultDefis = statDefis.executeQuery();
                    while(resultDefis.next()){*/
                        PreparedStatement statModifDefi = connection.prepareStatement("UPDATE lesDefis SET auteur = null where auteur = ? ");
                        statModifDefi.setString(1, id);
                        statModifDefi.execute();
                    //} 

                    //Modifier le login du chamis
                    PreparedStatement stmtPut = connection.prepareStatement("UPDATE lesChamis SET login = ?, age = ? WHERE login = ? ");
                    stmtPut.setString(1, chami.getLogin());
                    stmtPut.setInt(2, chami.getAge());
                    stmtPut.setString(3, id);
                    stmtPut.execute();

                    //Changer le nom du créateur des defis
                    //PreparedStatement statDefisFinal = connection.prepareStatement("select * from lesDefis where auteur = null ");
                    //statDefisFinal.setString(1, id);
                    //ResultSet resultDefisFinal = statDefisFinal.executeQuery();
                    //while(resultDefisFinal.next()){
                    PreparedStatement statModifDefiFinal = connection.prepareStatement("UPDATE lesDefis SET auteur = ? where auteur is NULL");
                    statModifDefiFinal.setString(1, chami.getLogin());
                    statModifDefiFinal.execute();
                    //} 


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
        /*} else {
            response.setStatus(412);
            try {
                response.getOutputStream().print("l'utilisateur est different");
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }
            return null;
        }*/
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(value="userId") String id, HttpServletResponse response){
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM lesChamis WHERE login = ? ");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //supprimer les défis qu'il a créé a cause de la clé étrangère
                PreparedStatement statModifDefi = connection.prepareStatement("DELETE FROM lesDefis where auteur = ?");
                statModifDefi.setString(1, id);
                statModifDefi.execute();

                //supprimer le chamis
                PreparedStatement stmtPut = connection.prepareStatement("DELETE FROM lesChamis WHERE login = ? ");
                stmtPut.setString(1, id);  
                stmtPut.execute();
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
